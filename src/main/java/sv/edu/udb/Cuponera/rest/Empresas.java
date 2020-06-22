package sv.edu.udb.Cuponera.rest;

import java.util.List;

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

import sv.edu.udb.Cuponera.dao.EmpresasDAO;
import sv.edu.udb.Cuponera.dao.RubrosDAO;
import sv.edu.udb.Cuponera.pojo.Empresa;

@Path("empresas")
public class Empresas {
	private EmpresasDAO empresas = new EmpresasDAO();
	private RubrosDAO   rubros   = new RubrosDAO();
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() throws SQLException {
		List<Empresa> empresaList = empresas.findAll();
		return Response.status(200).entity(empresaList).build();		
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("id") String id) throws SQLException {
		Empresa emp = empresas.findById(id);
		if(emp == null) {
			return Response.status(400)
					 .entity("Empresa no corresponde a ninguna existencia")
					 .header("Access-Control-Allow-Origin", "*")
					 .build();
		}
		
		return Response.status(200).entity(emp).build();
	}
	
	@POST
	@Path("add")
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertEmpresa(@FormParam("id") String id, @FormParam("nombre") String nombre,
			@FormParam("direccion") String direccion, @FormParam("telefono") String telefono,
			@FormParam("correo") String correo, @FormParam("rubro") int rubro, @FormParam("comision") double comision) throws SQLException {
		
		//Tenemos que verificar que existe
		Empresa emp = empresas.findById(id);
		if(emp == null) {
			return Response.status(400)
					 .entity("La nueva empresa no puede ser agregada con el codigo:"+ id +", porque otro registro ya ocupa dicho codigo")
					 .header("Access-Control-Allow-Origin", "*")
					 .build();
		}
		
		emp = new Empresa();
		emp.setId(id);
		emp.setNombre(nombre);
		emp.setDireccion(direccion);
		emp.setTelefono(telefono);
		emp.setCorreo(correo);
		emp.setRubro(rubros.findById(rubro));
		emp.setComision(comision);
		empresas.insert(emp);
		return Response.status(200).entity(emp).build();
	}
	
	@PUT
	@Path("update/{id}")
	@Produces(MediaType.APPLICATION_JSON) 
	public Response updateEmpresa(@PathParam("id") String id, @FormParam("nombre") String nombre,
			@FormParam("direccion") String direccion, @FormParam("telefono") String telefono,
			@FormParam("correo") String correo, @FormParam("rubro") int rubro, @FormParam("comision") double comision) throws SQLException {
		
		Empresa emp = empresas.findById(id);
		if(emp == null) {
			return Response.status(400)
					 .entity("Empresa no corresponde a ninguna existencia")
					 .header("Access-Control-Allow-Origin", "*")
					 .build();
		}
		
		emp.setNombre(nombre);
		emp.setDireccion(direccion);
		emp.setTelefono(telefono);
		emp.setCorreo(correo);
		emp.setRubro(rubros.findById(rubro));
		emp.setComision(comision);
		
		empresas.update(emp);
		return Response.status(200).entity(emp).build();
	}

	@DELETE
	@Path("delete/{id}")
	public Response deleteEmpresa(@PathParam("id") String id) throws SQLException {
		Empresa emp = empresas.findById(id);
		if(emp == null) {
			return Response.status(400)
					 .entity("Empresa no corresponde a ninguna existencia")
					 .header("Access-Control-Allow-Origin", "*")
					 .build();
		}
		empresas.delete(id);
		return Response.status(200).build();
	}

	
}
