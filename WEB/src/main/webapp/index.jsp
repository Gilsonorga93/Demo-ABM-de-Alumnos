<%@page import="com.educacionit.enumerados.Mensajes"%>
<%@page import="com.educacionit.interfaces.Fechas"%>
<%@page import="com.educacionit.entidades.Alumno"%>
<%@page import="com.educacionit.implementaciones.mariadb.AlumnoImplementacion"%>
<%@page import="com.educacionit.entidades.Usuario"%>
<%@ page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/button.css">
<link rel="stylesheet" href="css/table.css">
<link rel="stylesheet" href="css/alert.css">


<title>Inicio</title>
</head>
<%
Usuario usuario = (Usuario) session.getAttribute("usuario");

if(null == usuario){
	response.sendRedirect("login.jsp");
}

Mensajes mensaje = (Mensajes)request.getAttribute("mensaje");
%>
<body>
	
	<%
	if(null!=mensaje){
	%>
	<div id="alert">
		<a class="alert <%=mensaje.getClaseCSS() %>" href="#alert"><%=mensaje.getMensaje() %></a>
	</div>
	<% } %>

	<header>
	<h1>Digitalers</h1>
	<img alt="Educacion IT" src="images/logo-it.svg" width="200" height="50">
	</header>

	<nav>
		<a href="alumno.jsp">Agregar Alumnos</a> 
		<a href="loginUsuario?cerrarSesion=true">Cerrar Sesion</a>
	</nav>

	<section>
	<h1>Lista de Alumnos</h1>
	<table>
	<tr>
		<th>Documento</th>
		<th>Descripcion</th>
		<th>Fecha de Nacimiento</th>
		<th>Activo</th>
		<th>Accion</th>
	</tr>
	
	<%
	AlumnoImplementacion alumnoImplementacion = new AlumnoImplementacion();
	
	for(Alumno alumno : alumnoImplementacion.listar()){
	%>
	
	<tr>
		<td><%=alumno.getDocumento().getTipo().concat(" - ").concat(alumno.getDocumento().getNumero()) %></td>
		<td><%=alumno.getDescripcion() %></td>
		<td><%=Fechas.getFechaAString(alumno.getFechaNacimiento()) %></td>
		<td><%=alumno.isActivo()?"Si":"No" %></td>
		<td>
			<button class="warning" onclick="window.location.href='alumno.jsp?editar=true&tipoDocumento=<%=alumno.getDocumento().getTipo() %>&numeroDocumento=<%=alumno.getDocumento().getNumero() %>'">Modificar</button>
			<button class="danger" onclick="confirmar('Desea eliminar el registro <%=alumno.getDocumento() %>?')?window.location.href='AlumnoControlador?tipoDocumento=<%=alumno.getDocumento().getTipo() %>&numeroDocumento=<%=alumno.getDocumento().getNumero() %>':window.location.href='#'">Eliminar</button>
			
<%-- 			window.location.href='AlumnoControlador?tipoDocumento=<%=alumno.getDocumento().getTipo() %>&numeroDocumento=<%=alumno.getDocumento().getNumero() %>' --%>
			
		</td>
	<%
	} 
	%>
		
	</tr>
	</table>
	</section>
	
	<footer>
	<br>
	Creado por <b>Gilson Ortiz Garcia</b> en el curso de FullStack Java de Digitalers - Educacion IT
	</footer>
	
	<script src="scripts/confirm.js"></script>
	
</body>
</html>