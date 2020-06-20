<%@ page language="java" import="java.util.List,sv.edu.udb.cuponera.dao.EmpresasDAO, sv.edu.udb.cuponera.pojo.Empresa"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	EmpresasDAO empDAO = new EmpresasDAO();
	List<Empresa> emprList = empDAO.findAll();
	
%>
<%@ include file="/layout/header.jsp" %>
<h1 class="mt-4 text-right">Gestión de Empresas</h1>
<hr/>
<div class="btn-group" role="group" aria-label="Basic example">
  <a type="button" class="btn btn-secondary" href="${pageContext.request.contextPath}/gestion/empresas/editar.jsp" >Nueva Empresa</a>
</div>

<table class="table table-stripped">
	<thead class="thead-dark">
		<tr>
			<td>Código</td>
			<td>Nombre</td>
			<td>Direccion</td>
			<td>Telefono</td>
			<td>Correo</td>
			<td>Rubro</td>
			<td>Comision (%)</td>
			<td>Acciones</td>
		</tr>
	</thead>
	<tbody>
		<% for(Empresa emp : emprList) { %>
			<tr>
				<td><%=emp.getId()%></td>
				<td><%=emp.getNombre()%></td>
				<td><%=emp.getDireccion()%></td>
				<td><%=emp.getTelefono()%></td>
				<td><%=emp.getCorreo()%></td>
				<td><%=emp.getRubro()%></td>
				<td><%=emp.getComision()%></td>
				<td><a class="btn btn-warning" href="${pageContext.request.contextPath}/gestion/empresas/editar.jsp?id=<%=emp.getId()%>">Editar</a></td>
				<td><a class="btn btn-warning">Eliminar</a></td>
			</tr>
		<%} %>
	</tbody>
</table>
 
<%@ include file="/layout/footer.jsp" %>
 