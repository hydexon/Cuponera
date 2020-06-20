package sv.edu.udb.cuponera.pojo;

import java.sql.Date;

public class Promocion {
	private int id;
	private double precioRegular;
	private double precioOferta;
	private Date   fechaInicio;
	private Date   fechaFinal;
	private Date   fechaLimite;
	private int    cantidad;
	private String descripcion;
	private Empresa empresa;
	private int    status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getPrecioRegular() {
		return precioRegular;
	}
	public void setPrecioRegular(double precioRegular) {
		this.precioRegular = precioRegular;
	}
	public double getPrecioOferta() {
		return precioOferta;
	}
	public void setPrecioOferta(double precioOferta) {
		this.precioOferta = precioOferta;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFinal() {
		return fechaFinal;
	}
	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	public Date getFechaLimite() {
		return fechaLimite;
	}
	public void setFechaLimite(Date fechaLimite) {
		this.fechaLimite = fechaLimite;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	

}
