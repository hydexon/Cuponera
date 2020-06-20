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

import sv.edu.udb.Cuponera.dao.RolesDAO;
import sv.edu.udb.Cuponera.dao.UsuariosDAO;
import sv.edu.udb.Cuponera.pojo.Usuario;


@Path("usuarios")
public class Usuarios {
	private UsuariosDAO users = new UsuariosDAO();
	private RolesDAO roles = new RolesDAO();
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll()  throws SQLException {
		List<Usuario> userList = users.findAll();
		return Response.status(200).entity(userList).build();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("id") int id) throws SQLException {
		Usuario usr = users.findById(id);
		
		if(usr == null) {
			return Response.status(400).
					entity("usuario no corresponde a ninguna existencia").
					header("Access-Control-Allow-Origin", "*").build();
		}
		return Response.status(200).entity(usr).build();
	}
	
	@POST
	@Path("add")
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertUser(@FormParam("username") String username,
			@FormParam("nombreCompleto") String nombreCompleto, @FormParam("correo") String correo,
			@FormParam("contrasena") String contrasena, @FormParam("rol") int rol) throws SQLException {
		Usuario usr = new Usuario();
		usr.setUsername(username);
		usr.setNombreCompleto(nombreCompleto);
		usr.setCorreo(correo);
		usr.setContrasena(contrasena);
		usr.setRol(roles.findById(rol));
		
		users.insert(usr);
		return Response.status(200).entity(usr).build();
	}
	
	@PUT
	@Path("update/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUser(@FormParam("id") int id, @FormParam("username") String username,
			@FormParam("nombreCompleto") String nombreCompleto, @FormParam("correo") String correo,
			@FormParam("contrasena") String contrasena, @FormParam("rol") int rol) throws SQLException {
		Usuario user = users.findById(id);
		if(user == null) {
			return Response.status(400).
					entity("usuario no corresponde a ninguna existencia").
					header("Access-Control-Allow-Origin", "*").build();
		}
		user.setUsername(username);
		user.setNombreCompleto(nombreCompleto);
		user.setCorreo(correo);
		user.setContrasena(contrasena);
		user.setRol(roles.findById(rol));
		users.update(user);
		return Response.status(200).entity(user).build();
	}
	
	@DELETE
	@Path("delete/{id}")
	public Response deleteUser(@FormParam("id") int id) throws SQLException{
		Usuario user = users.findById(id);
		if(user == null) {
			return Response.status(400)
					 .entity("Usuario no corresponde a ninguna existencia")
					 .header("Access-Control-Allow-Origin", "*")
					 .build();
		}
		users.delete(id);
		return Response.status(200).build();
	}
	

	

}
