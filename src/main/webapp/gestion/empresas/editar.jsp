<%@ page language="java" import="java.util.List,sv.edu.udb.cuponera.dao.*, sv.edu.udb.cuponera.pojo.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/layout/header.jsp" %>
<%
	EmpresasDAO dao = new EmpresasDAO();
	Empresa emp = null;
	String cod = "null";
	if(request.getParameterMap().containsKey("id")) 
		emp = dao.findById(request.getParameter("id"));
	
	RubrosDAO r_dao = new RubrosDAO(); 
	List<Rubro> rubroList = r_dao.findAll();
%>
<h1><%=(request.getParameterMap().containsKey("id")) ? "Editando" : "Nueva" %> Empresa</h1>
<hr/>
<form method="POST" action="EmpresasServlet">
   <input type="hidden" name="id" value=""/>
  <div class="form-group row">
    <label for="id" class="col-4 col-form-label">Código Empresa:</label> 
    <div class="col-8">
      <input id="id" name="id" placeholder="EMP0000" type="text" required="required" class="form-control" value="<%=(emp!=null) ? emp.getId() : ""%>">
    </div>
  </div>
  <div class="form-group row">
    <label for="nombre" class="col-4 col-form-label">Nombre Empresa:</label> 
    <div class="col-8">
      <input id="nombre" name="nombre" type="text" class="form-control" required="required" value="<%=(emp!=null) ? emp.getNombre() : ""%>">
    </div>
  </div>
  <div class="form-group row">
    <label for="direccion" class="col-4 col-form-label">Dirección</label> 
    <div class="col-8">
      <input id="direccion" name="direccion" type="text" class="form-control" required="required" value="<%=(emp!=null) ? emp.getDireccion() : ""%>">
    </div>
  </div>
  <div class="form-group row">
    <label for="telefono" class="col-4 col-form-label">Telefono</label> 
    <div class="col-8">
      <input id="telefono" name="telefono" type="text" required="required" class="form-control" value="<%=(emp!=null) ? emp.getTelefono() : ""%>" >
    </div>
  </div>
  <div class="form-group row">
    <label for="correo" class="col-4 col-form-label">Correo</label> 
    <div class="col-8">
      <input id="correo" name="correo" type="text" class="form-control" required="required"  value="<%=(emp!=null) ? emp.getCorreo() : ""%>">
    </div>
  </div>
<div class="form-group row">
    <label for="rubro" class="col-4 col-form-label">Rubro</label> 
    <div class="col-8">
      <select id="rubro" name="rubro" class="custom-select" required="required">
      <% for(Rubro r : rubroList) {%>
      	<option value="<%=r.getId()%>"><%=r.getRubro()%></option>
      <%}%>
      </select>
    </div>
  </div>
  <div class="form-group row">
    <label for="comision" class="col-4 col-form-label">Comisión (%)</label> 
    <div class="col-8">
      <input id="comision" name="comision" type="text" class="form-control" required="required"  value="<%=(emp!=null) ? emp.getComision() : ""%>">
    </div>
  </div> 
  <div class="form-group row">
    <div class="offset-4 col-8">
      <button name="submit" type="submit" class="btn btn-primary">Guardar Cambios</button>
    </div>
  </div>
</form>
<%@ include file="/layout/footer.jsp" %>
