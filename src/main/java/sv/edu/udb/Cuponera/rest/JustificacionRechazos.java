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

import sv.edu.udb.Cuponera.dao.JustificacionesDAO;
import sv.edu.udb.Cuponera.dao.PromocionesDAO;
import sv.edu.udb.Cuponera.pojo.JustificacionRechazo;

@Path("justificaciones")
public class JustificacionRechazos {
	private JustificacionesDAO justificaciones = new JustificacionesDAO();
	private PromocionesDAO promociones = new PromocionesDAO();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() throws SQLException {
		List<JustificacionRechazo> jrList = justificaciones.findAll();
		return Response.status(200).entity(jrList).build();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("id") int id) throws SQLException {
		JustificacionRechazo jr = justificaciones.findById(id);
		if(jr == null) {
			return Response.status(400)
					 .entity("Promocion no corresponde a ninguna existencia")
					 .header("Access-Control-Allow-Origin", "*")
					 .build();
		}
		
		return Response.status(200).entity(jr).build();
	}
	
	@POST
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addJustificacion(@FormParam("promocion") int promo, 
			@FormParam("descripcion") String descripcion) throws SQLException {
		JustificacionRechazo jr = new JustificacionRechazo();
		jr.setPromo(promociones.findById(promo));
		jr.setDescripcion(descripcion);
		justificaciones.insert(jr);
		return Response.status(200).entity(jr).build();
	}
	
	@PUT
	@Path("update/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateJustificacion(@PathParam("id") int id, @FormParam("promocion") int promo, 
			@FormParam("descripcion") String descripcion) throws SQLException {
		JustificacionRechazo jr = justificaciones.findById(id);
		if(jr == null) {
			return Response.status(400)
					 .entity("Promocion no corresponde a ninguna existencia")
					 .header("Access-Control-Allow-Origin", "*")
					 .build();
		}
		jr.setPromo(promociones.findById(promo));
		jr.setDescripcion(descripcion);
		justificaciones.update(jr);
		return Response.status(200).entity(jr).build();
	}
	
	@DELETE
	@Path("delete/{id}")
	public Response deleteJustificacion(@PathParam("id") int id) throws SQLException {
		JustificacionRechazo jr = justificaciones.findById(id);
		if(jr == null) {
			return Response.status(400)
					 .entity("Promocion no corresponde a ninguna existencia")
					 .header("Access-Control-Allow-Origin", "*")
					 .build();
		}
		justificaciones.delete(id);
		return Response.status(200).build();
	}

}
