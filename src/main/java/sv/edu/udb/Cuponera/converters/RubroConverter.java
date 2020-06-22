package sv.edu.udb.Cuponera.converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import sv.edu.udb.Cuponera.dao.RubrosDAO;
import sv.edu.udb.Cuponera.pojo.Rubro;

@FacesConverter(forClass = sv.edu.udb.Cuponera.pojo.Rubro.class, value = "rubros")
public class RubroConverter implements Converter<Rubro> {
	RubrosDAO rubrosDao = new RubrosDAO();
	
	@Override
    public Rubro getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
        if (submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                int number = Integer.parseInt(submittedValue);
                Rubro r = rubrosDao.findById(number);
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
    public String getAsString(FacesContext facesContext, UIComponent component, Rubro value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((Rubro) value).getId());
        }
    }

}
