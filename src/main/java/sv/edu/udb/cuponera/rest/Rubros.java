package sv.edu.udb.cuponera.rest;

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

import sv.edu.udb.cuponera.dao.RubrosDAO;
import sv.edu.udb.cuponera.pojo.Rubro;

@Path("rubros")
public class Rubros {
	private RubrosDAO rubros = new RubrosDAO();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() throws SQLException {
		List<Rubro> rubrosList = rubros.findAll();
		return Response.status(200).entity(rubrosList).build();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("id") int id ) throws SQLException {
		Rubro rub = rubros.findById(id);
		if(rub == null) {
			return Response.status(400)
					 .entity("Rubro no corresponde a ninguna existencia")
					 .header("Access-Control-Allow-Origin", "*")
					 .build();
		}
		
		return Response.status(200).entity(rub).build();
	}
	
	@POST
	@Path("add")
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertRubro(@FormParam("rubro") String nombre) throws SQLException {
		Rubro rubro = new Rubro();
		rubro.setRubro(nombre);
		rubros.insert(rubro);
		
		return Response.status(200).entity(rubro).build();
	}
	
	@PUT
	@Path("update/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateRubro(@PathParam("id") int id, @FormParam("rubro") String nombre) throws SQLException {
		Rubro rub = rubros.findById(id);
		if(rub == null) {
			return Response.status(400)
					 .entity("Rubro no corresponde a ninguna existencia")
					 .header("Access-Control-Allow-Origin", "*")
					 .build();
		}
		rub.setRubro(nombre);
		rubros.insert(rub);
		return Response.status(200).entity(rub).build();
	}
	
	@DELETE
	@Path("delete/{id}")
	public Response deleteRubro(@PathParam("id") int id) throws SQLException {
		Rubro rub = rubros.findById(id);
		if(rub == null) {
			return Response.status(400)
					 .entity("Rubro no corresponde a ninguna existencia")
					 .header("Access-Control-Allow-Origin", "*")
					 .build();
		}
		rubros.delete(id);
		return Response.status(200).build();
	}
	
	
	
	
}
