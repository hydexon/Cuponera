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

import sv.edu.udb.Cuponera.dao.EmpresasDAO;
import sv.edu.udb.Cuponera.dao.RubrosDAO;
import sv.edu.udb.Cuponera.pojo.Empresa;
import sv.edu.udb.Cuponera.pojo.Rubro;

@ManagedBean
@SessionScoped
public class EmpresasBean {
	private EmpresasDAO empresasDao = new EmpresasDAO();
	private RubrosDAO rubrosDao = new RubrosDAO();
	
	private Empresa empresa = new Empresa();
	
	public Empresa getEmpresa() {
		return empresa;
	}
	
	public void clean() {
		this.empresa = new Empresa();
	}
	public void addEmpresa() {
		try {
			empresasDao.insert(getEmpresa());
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Nueva Empresa agregado!"));
		this.empresa = new Empresa();
	}
	
	public void getEmpresaId() {
		try {
			Empresa tmp = empresasDao.findById(this.empresa.getId());
			//this.empresa = tmp;
			if(tmp == null) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Emnpresa NO encontrado"));
				this.empresa = new Empresa();
			}
		} catch(SQLException e ) { e.printStackTrace(); }
	}
	
	public String deleteEmpresa(String id) {
		try {
			Empresa obtEmpresa = empresasDao.findById(id);
			if(obtEmpresa != null) {
				empresasDao.delete(obtEmpresa.getId());
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente con ID " + obtEmpresa.getId() + " eliminado"));
			}
			else
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente con ID" + id + " NO encontrado"));
			
			this.empresa = new Empresa();
			return "Empresa";

		} catch(SQLException e) { e.printStackTrace(); }
		return "Empresa";
	}
	
	public String updateEmpresa() {
		if(this.empresa.getId() == null) return "Empresa";
		
		try {
			Empresa obtEmpresa = empresasDao.findById(this.empresa.getId());
			if(obtEmpresa != null) {
				empresasDao.update(this.empresa);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Empresa con ID " + this.empresa.getId() + " actualizado"));
				this.empresa = new Empresa();
			}
			else FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Empresa con ID" + this.empresa.getId() + " NO encontrado"));
			
		} catch(SQLException e) { e.printStackTrace(); }
		
		return "Empresa";
	}
	
	public List<Empresa> getEmpresas() {
		List<Empresa> empresas = null;
		try {
			empresas = empresasDao.findAll();
		}
		catch(SQLException e) { e.printStackTrace(); }
		return empresas;
	}
	
	public List<Rubro> getRubros() {
		List<Rubro> rubros = null;
		try {
			rubros = rubrosDao.findAll();
		}
		catch(SQLException e) { e.printStackTrace(); }
		return rubros;
	}
	
    public void refresh() {
        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        ViewHandler viewHandler = application.getViewHandler();
        UIViewRoot viewRoot = viewHandler.createView(context, context
                .getViewRoot().getViewId());
        context.setViewRoot(viewRoot);
        context.renderResponse();
    }
    
    public void peekEmpresa(String id) {
    	Empresa emp = null;
    	try {
    		emp = empresasDao.findById(id);
    	} catch(SQLException e) { e.printStackTrace(); }
    	
    	if(emp != null)
    		this.empresa = emp;
    }
    
    public void saveEmpresa(String id) {
    	if(id == null || id.trim().equals(""))
    		addEmpresa();
    	else
    		updateEmpresa();
    }
}
