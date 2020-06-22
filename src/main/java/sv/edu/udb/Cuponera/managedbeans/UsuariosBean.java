package sv.edu.udb.Cuponera.managedbeans;

import java.sql.SQLException;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import sv.edu.udb.Cuponera.dao.RolesDAO;
import sv.edu.udb.Cuponera.dao.UsuariosDAO;
import sv.edu.udb.Cuponera.pojo.Rol;
import sv.edu.udb.Cuponera.pojo.Usuario;

@ManagedBean
@SessionScoped
public class UsuariosBean {
	private UsuariosDAO usuarios = new UsuariosDAO();
	private Usuario user = new Usuario();
	private RolesDAO roles = new RolesDAO();
	
	public Usuario getUser() {
		return user;
	}
	
	public void clean() {
		this.user = new Usuario();
	}
	
	public void addUsuario() throws SQLException {
		usuarios.insert(getUser());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Nuevo usuario agregado!"));
		this.user = new Usuario();
	}
	
	public String deleteUsuario(int id) throws SQLException {
		Usuario obtUser = usuarios.findById(id);
		if(obtUser != null) {
			usuarios.delete(id);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario con id" + id + "eliminado!"));
		} else FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario con id: " + id + " no encontrado"));;
		
		return "Usuario";
	}
	
	public List<Usuario> getUsuarios() throws SQLException {
		List<Usuario> usuarioList = null;
		usuarioList = usuarios.findAll();
		return usuarioList;
	}
	
	public List<Rol> getRoles() throws SQLException {
		List<Rol> rolesList = roles.findAll();
		return rolesList;
	}
	
	public String updateUsuario() throws SQLException {
		if(this.user.getId() == 0) return "Usuario";
		Usuario obtUser = usuarios.findById(this.user.getId());
		if(obtUser != null) {
			usuarios.update(this.user);
		}
		else FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario con ID" + this.user.getId() + " NO encontrado"));

		return "Usuario";
	}
	
	public void peekUser(int id) {
		Usuario u = null;
		try {
			u = usuarios.findById(id);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		if(u != null)
			this.user = u;
	}
	
	public void saveUsuario(int id) throws SQLException {
		if(id == 0)
			addUsuario();
		else
			updateUsuario();
	}
	
}