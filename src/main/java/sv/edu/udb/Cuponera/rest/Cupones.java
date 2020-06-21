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

import sv.edu.udb.Cuponera.dao.*;
import sv.edu.udb.Cuponera.pojo.*;

@Path("cupones")
public class Cupones {
	private CuponesDAO cupones = new CuponesDAO();
	private PromocionesDAO promos = new PromocionesDAO();
	private ClientesDAO clientes = new ClientesDAO();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() throws SQLException {
		List<Cupon> cupList = cupones.findAll();
		return Response.status(200).entity(cupList).build();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("id") int id) throws SQLException {
		Cupon cup = cupones.findById(id);
		if(cup == null) {
			return Response.status(400)
					 .entity("[]")
					 .header("Access-Control-Allow-Origin", "*")
					 .build();
		}
		return Response.status(200).entity(cup).build();
	}
	
	@GET
	@Path("estado/{status}")
	public Response getByStatus(@PathParam("status") int status) throws SQLException {
		List<Cupon> cupList = cupones.findByStatus(status);
		return Response.status(200).entity(cupList).build();
	}
	
	@POST
	@Path("buy")
	public Response comprarCupon(@FormParam("promo") int promoId, @FormParam("cliente") int clienteId, @FormParam("cantidad") int cantidad) throws SQLException {
		Promocion promo = promos.findById(promoId);
		Cliente   cliente = clientes.findById(clienteId);
		
		if(promo == null && cliente == null) {
			return Response.status(400)
					 .entity("Cupon o Cliente no corresponde a ninguna existencia")
					 .header("Access-Control-Allow-Origin", "*")
					 .build();
		}
		
		int cant = promo.getCantidad();
		if(cant != -1 && cantidad > cant) {
			return Response.status(400)
					 .entity("No puede exceder el limite de compra de cupones!")
					 .header("Access-Control-Allow-Origin", "*")
					 .build();

		}
		
		for(int i = 0; i < cantidad; i++) {
			Cupon cup = new Cupon();
			cup.setPromo(promo);
			cup.setCliente(cliente);
			cup.setEstado(0); // 0 = Sin Reclamar.
			cupones.insert(cup);
		}
		
		return Response.status(200).build();
	}
	
	@GET
	@Path("redeem/{id}")
	public Response reclamarCupon(@PathParam("id") int id) throws SQLException {
		Cupon cup = cupones.findById(id);
		if(cup == null) {
			return Response.status(400)
					 .entity("Cupon o Cliente no corresponde a ninguna existencia")
					 .header("Access-Control-Allow-Origin", "*")
					 .build();
		}
		cup.setEstado(1); // 1 = Reclamado.
		cupones.update(cup);
		
		return Response.status(200).entity(cup).build();
	}


}
