package sv.edu.udb.Cuponera.hibernate;
// Generated Jun 19, 2020 11:51:27 PM by Hibernate Tools 5.4.14.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Roles generated by hbm2java
 */
public class Roles implements java.io.Serializable {

	private Integer rolId;
	private String rol;
	private int rolVal;
	private Set usuarioses = new HashSet(0);

	public Roles() {
	}

	public Roles(String rol, int rolVal) {
		this.rol = rol;
		this.rolVal = rolVal;
	}

	public Roles(String rol, int rolVal, Set usuarioses) {
		this.rol = rol;
		this.rolVal = rolVal;
		this.usuarioses = usuarioses;
	}

	public Integer getRolId() {
		return this.rolId;
	}

	public void setRolId(Integer rolId) {
		this.rolId = rolId;
	}

	public String getRol() {
		return this.rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public int getRolVal() {
		return this.rolVal;
	}

	public void setRolVal(int rolVal) {
		this.rolVal = rolVal;
	}

	public Set getUsuarioses() {
		return this.usuarioses;
	}

	public void setUsuarioses(Set usuarioses) {
		this.usuarioses = usuarioses;
	}

}