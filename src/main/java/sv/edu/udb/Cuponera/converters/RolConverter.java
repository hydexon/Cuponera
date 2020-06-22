package sv.edu.udb.Cuponera.converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import sv.edu.udb.Cuponera.dao.RolesDAO;
import sv.edu.udb.Cuponera.pojo.Rol;

@FacesConverter(forClass = sv.edu.udb.Cuponera.pojo.Rol.class, value = "roles")
public class RolConverter implements Converter<Rol> {
	private RolesDAO roles = new RolesDAO();
	
	@Override
    public Rol getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
        if (submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                int number = Integer.parseInt(submittedValue);
                Rol r = roles.findById(number);
                if(r != null) {
                	return r;
                }
                
            } catch(Exception exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "No es valido"));
            }
        }

        return null;
    }
	
	@Override
    public String getAsString(FacesContext facesContext, UIComponent component, Rol value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((Rol) value).getId());
        }
    }

}
