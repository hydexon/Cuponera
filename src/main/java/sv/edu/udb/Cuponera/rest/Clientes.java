package sv.edu.udb.Cuponera.rest;

import java.util.List;
import java.util.UUID;

import java.sql.SQLException;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import sv.edu.udb.Cuponera.dao.ClientesDAO;
import sv.edu.udb.Cuponera.dao.ClienteConfirmTokenDAO;
import sv.edu.udb.Cuponera.pojo.Cliente;
import sv.edu.udb.Cuponera.pojo.ClienteConfirmacionToken;

@Path("clientes")
public class Clientes {
	private ClientesDAO clientes = new ClientesDAO();
	private ClienteConfirmTokenDAO cctDAO = new ClienteConfirmTokenDAO();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() throws SQLException {
		List<Cliente> clienteList = clientes.findAll();
		return Response.status(200).entity(clienteList).build();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("id") int id) throws SQLException{
		Cliente c = clientes.findById(id);
		if(c == null) {
			return Response.status(400)
					 .entity("Cliente no corresponde a ninguna existencia")
					 .header("Access-Control-Allow-Origin", "*")
					 .build();
		}
		
		return Response.status(200).entity(c).build();
	}
	
	@POST
	@Path("add")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCliente(@FormParam("username") String username, @FormParam("nombres") String nombres, 
			@FormParam("apellidos") String apellidos, @FormParam("dui") String dui,
			@FormParam("telefono") String telefono, @FormParam("correo") String correo,
			@FormParam("contrasena") String contrasena) throws SQLException {
		Cliente c = new Cliente();
		c.setNombres(nombres);
		c.setApellidos(apellidos);
		c.setDui(dui);
		c.setTelefono(telefono);
		c.setCorreo(correo);
		c.setContrasena(contrasena);
		c.setConfirmado(0); // No esta verificado todavía.
		clientes.insert(c);
		
		//debemos generar el token
		String tokenActivacion = UUID.randomUUID().toString();
		ClienteConfirmacionToken cct = new ClienteConfirmacionToken();
		cct.setToken(tokenActivacion);
		cct.setCliente(c);
		cctDAO.insert(cct);
		
		return Response.status(200).entity(c).build();
	}
	
	@PUT
	@Path("update/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCliente(@PathParam("id") int id, @FormParam("username") String username, @FormParam("nombres") String nombres, 
			@FormParam("apellidos") String apellidos, @FormParam("dui") String dui,
			@FormParam("telefono") String telefono, @FormParam("correo") String correo,
			@FormParam("contrasena") String contrasena) throws SQLException {
		Cliente c = clientes.findById(id);
		if(c == null) {
			return Response.status(400)
					 .entity("Cliente no corresponde a ninguna existencia")
					 .header("Access-Control-Allow-Origin", "*")
					 .build();
		}
		c.setNombres(nombres);
		c.setApellidos(apellidos);
		c.setDui(dui);
		c.setTelefono(telefono);
		c.setCorreo(correo);
		c.setContrasena(contrasena);
		clientes.update(c);
		return Response.status(200).entity(c).build();
	}
	
	@DELETE
	@Path("delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCliente(@PathParam("id") int id) throws SQLException {
		Cliente c = clientes.findById(id);
		if(c == null) {
			return Response.status(400)
					 .entity("Cliente no corresponde a ninguna existencia")
					 .header("Access-Control-Allow-Origin", "*")
					 .build();
		}
		
		clientes.delete(id);
		return Response.status(200).build();
	}
	
	
	@GET
	@Path("activacion/{uuid}")
	public Response activarCliente(@PathParam("uuid") String uuid) throws SQLException {
		ClienteConfirmacionToken ctt = cctDAO.findByToken(uuid);
		if(ctt == null) {
			return Response.status(400)
					 .entity("Cliente no corresponde a ninguna existencia")
					 .header("Access-Control-Allow-Origin", "*")
					 .build();
		}
		Cliente c = ctt.getCliente();
		c.setConfirmado(1);
		clientes.update(c);
		// Ya esta confirmado lo borramos.
		cctDAO.delete(ctt.getId());		
		return Response.status(200).build();
	}
	

	

}
