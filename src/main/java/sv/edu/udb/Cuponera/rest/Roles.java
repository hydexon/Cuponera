package sv.edu.udb.Cuponera.rest;

import java.util.List;

import java.sql.SQLException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import sv.edu.udb.Cuponera.dao.RolesDAO;
import sv.edu.udb.Cuponera.pojo.Rol;

@Path("roles")
public class Roles {
	private RolesDAO rolesDAO = new RolesDAO();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRoles() throws SQLException {
		List<Rol> rolesLista = rolesDAO.findAll();
		return Response.status(200).entity(rolesLista).build();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRoleById(@PathParam("id") int id) throws SQLException {
		Rol rol = rolesDAO.findById(id);
		return Response.status(200).entity(rol).build();
	}

}
