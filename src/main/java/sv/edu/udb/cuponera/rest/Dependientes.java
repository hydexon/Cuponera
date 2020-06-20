package sv.edu.udb.cuponera.rest;

import java.util.List;
import java.util.Random;
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

import sv.edu.udb.cuponera.dao.DependientesDAO;
import sv.edu.udb.cuponera.misc.EnviarCorreo;
import sv.edu.udb.cuponera.pojo.Dependiente;

@Path("dependientes")
public class Dependientes {
	private DependientesDAO dependientes = new DependientesDAO();
	
	private String generarContrasena() {
        int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) 
			(random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		String generatedString = buffer.toString();
		return generatedString;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() throws SQLException {
		List<Dependiente> depList = dependientes.findAll();
		return Response.status(200).entity(depList).build();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("id") int id) throws SQLException {
		Dependiente dep = dependientes.findById(id);
		if(dep == null) {
			return Response.status(400)
					 .entity("Cliente no corresponde a ninguna existencia")
					 .header("Access-Control-Allow-Origin", "*")
					 .build();
		}
		
		return Response.status(200).entity(dep).build();
	}
	
	@POST
	@Path("add")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addDependiente(@FormParam("nombre") String nombre, @FormParam("apellidos") String apellidos, 
			@FormParam("correo") String correo, @FormParam("username") String username ) throws SQLException {
		Dependiente dep = new Dependiente();
		dep.setNombre(nombre);
		dep.setApellidos(apellidos);
		dep.setCorreo(correo);
		dep.setContrasena(generarContrasena());		
		dep.setUsername(username);

		dependientes.insert(dep);
		//Enviamos el correo
		String contenido = "Bienvenido " + nombre + " este será vuestra usuario:" + dep.getUsername() + " contraseña:" + dep.getContrasena();
		EnviarCorreo.enviarCorreoGmail(correo, "Credenciales para Dependiente", contenido);
		
		return Response.status(200).entity(dep).build();
	}
	
	@PUT
	@Path("update/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateDependiente(@PathParam("id") int id, @FormParam("nombre") String nombre, @FormParam("apellidos") String apellidos, 
			@FormParam("correo") String correo, @FormParam("contrasena") String contrasena, @FormParam("username") String username) throws SQLException {
		Dependiente dep = dependientes.findById(id);
		if(dep == null) {
			return Response.status(400)
					 .entity("Cliente no corresponde a ninguna existencia")
					 .header("Access-Control-Allow-Origin", "*")
					 .build();
		}
		dep.setNombre(nombre);
		dep.setApellidos(apellidos);
		dep.setCorreo(correo);
		dep.setContrasena(contrasena);	
		dep.setUsername(username);
		dependientes.update(dep);
		return Response.status(200).entity(dep).build();
	}
	
	@DELETE
	@Path("delete/{id}")
	public Response deleteDependiente(@PathParam("id") int id) throws SQLException {
		Dependiente dep = dependientes.findById(id);
		if(dep == null) {
			return Response.status(400)
					 .entity("Cliente no corresponde a ninguna existencia")
					 .header("Access-Control-Allow-Origin", "*")
					 .build();
		}
		
		dependientes.delete(id);
		return Response.status(200).build();

	}
	
	


	
	
	
	
}
