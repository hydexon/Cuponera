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
import sv.edu.udb.Cuponera.dao.PromocionesDAO;
import sv.edu.udb.Cuponera.pojo.Promocion;


@Path("promociones")
public class Promociones {
	private PromocionesDAO promociones = new PromocionesDAO();
	private EmpresasDAO    empresas    = new EmpresasDAO();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() throws SQLException {
		List<Promocion> promoList = promociones.findAll();
		return Response.status(200).entity(promoList).build();		
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("id") int id) throws SQLException {
		Promocion promo = promociones.findById(id);
		if(promo == null) {
			return Response.status(400)
					 .entity("Promocion no corresponde a ninguna existencia")
					 .header("Access-Control-Allow-Origin", "*")
					 .build();
		}
		
		return Response.status(200).entity(promo).build();
	}

	@POST
	@Path("add")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPromo(@FormParam("precioRegular") double precioRegular, @FormParam("precioOferta") double precioOferta,
			@FormParam("fechaInicio") String fechaInicio, @FormParam("fechaFinal") String fechaFinal, 
			@FormParam("fechaLimite") String fechaLimite, @FormParam("cantidad") int cantidad,
			@FormParam("descripcion") String descripcion, @FormParam("empresa") String empresa, @FormParam("status") int status) throws SQLException {
		Promocion promo = new Promocion();
		promo.setPrecioRegular(precioRegular);
		promo.setPrecioOferta(precioOferta);
		promo.setFechaInicio(java.sql.Date.valueOf(fechaInicio));
		promo.setFechaFinal(java.sql.Date.valueOf(fechaFinal));
		promo.setFechaLimite(java.sql.Date.valueOf(fechaLimite));
		promo.setCantidad(cantidad);
		promo.setDescripcion(descripcion);
		promo.setEmpresa(empresas.findById(empresa));
		promociones.insert(promo);
		return Response.status(200).entity(promo).build();
	}
	
	@PUT
	@Path("update/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePromo(@PathParam("id") int id, @FormParam("precioRegular") double precioRegular, @FormParam("precioOferta") double precioOferta,
			@FormParam("fechaInicio") String fechaInicio, @FormParam("fechaFinal") String fechaFinal, 
			@FormParam("fechaLimite") String fechaLimite, @FormParam("cantidad") int cantidad,
			@FormParam("descripcion") String descripcion, @FormParam("empresa") String empresa, @FormParam("status") int status) throws SQLException {
		Promocion promo = promociones.findById(id);
		if(promo == null) {
			return Response.status(400)
					 .entity("Promocion no corresponde a ninguna existencia")
					 .header("Access-Control-Allow-Origin", "*")
					 .build();
		}
		promo.setPrecioRegular(precioRegular);
		promo.setPrecioOferta(precioOferta);
		promo.setFechaInicio(java.sql.Date.valueOf(fechaInicio));
		promo.setFechaFinal(java.sql.Date.valueOf(fechaFinal));
		promo.setFechaLimite(java.sql.Date.valueOf(fechaLimite));
		promo.setCantidad(cantidad);
		promo.setDescripcion(descripcion);
		promo.setEmpresa(empresas.findById(empresa));
		promociones.update(promo);
		return Response.status(200).entity(promo).build();
	}
	
	@DELETE
	@Path("delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletePromo(@PathParam("id") int id) throws SQLException{
		Promocion promo = promociones.findById(id);
		if(promo == null) {
			return Response.status(400)
					 .entity("Promocion no corresponde a ninguna existencia")
					 .header("Access-Control-Allow-Origin", "*")
					 .build();
		}
		
		promociones.delete(id);
		return Response.status(200).build();
	}

	

}
