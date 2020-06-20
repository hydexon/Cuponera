package sv.edu.udb.Cuponera.pojo;

public class Cupon {
	private String id;
	private Promocion promo;
	private Cliente cliente;
	private int estado;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Promocion getPromo() {
		return promo;
	}
	public void setPromo(Promocion promo) {
		this.promo = promo;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	

}
