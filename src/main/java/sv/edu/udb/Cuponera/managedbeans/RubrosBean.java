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

import sv.edu.udb.Cuponera.dao.RubrosDAO;
import sv.edu.udb.Cuponera.pojo.Rubro;

@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped
public class RubrosBean {
	private RubrosDAO rubrosDao = new RubrosDAO();
	private Rubro rubro = new Rubro();
	
	public Rubro getRubro() {
		return rubro;
	}
	
	public void clean() {
		this.rubro = new Rubro();
	}
	
	public void addRubro() throws SQLException {
		rubrosDao.insert(getRubro());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Nuevo rubro agregado!"));
		this.rubro = new Rubro();
	}
	
	public void getRubroId() throws SQLException {
		Rubro tmp = rubrosDao.findById(this.rubro.getId());
		//this.empresa = tmp;
		if(tmp == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Emnpresa NO encontrado"));
			this.rubro = new Rubro();
		}
	}
	
	public String deleteRubro(int id) throws SQLException {
		Rubro obtRubro = rubrosDao.findById(id);
		if(obtRubro != null) {
			rubrosDao.delete(id);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Rubro con ID " + obtRubro.getId() + " eliminado"));
		}
		else FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Rubro con ID" + id + " NO encontrado"));
		return "Rubro";
	}
	
	public List<Rubro> getRubros() {
		List<Rubro> rubros = null;
		try {
			rubros = rubrosDao.findAll();
		}
		catch(SQLException e) { e.printStackTrace(); }
		return rubros;
	}
	
	public String updateRubro() {
		if(this.rubro.getId() == 0) return "Rubro";
		
		try {
			Rubro obtEmpresa = rubrosDao.findById(this.rubro.getId());
			if(obtEmpresa != null) {
				rubrosDao.update(this.rubro);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Rubro con ID " + this.rubro.getId() + " actualizado"));
				this.rubro = new Rubro();
			}
			else FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Rubro con ID" + this.rubro.getId() + " NO encontrado"));
			
		} catch(SQLException e) { e.printStackTrace(); }
		
		return "Rubro";
	}

	public void peekRubro(int id) {
		Rubro r = null;
    	try {
    		r = rubrosDao.findById(id);
    	} catch(SQLException e) { e.printStackTrace(); }
    	
    	if(r != null)
    		this.rubro = r;
	}
	
    public void saveRubro(int id) throws SQLException {
    	if(id == 0) //0 no deberia ser valido en SQL.
    		addRubro();
    	else
    		updateRubro();
    }

	
	
}
