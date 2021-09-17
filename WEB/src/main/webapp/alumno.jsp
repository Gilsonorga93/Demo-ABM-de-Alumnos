<%@page import="com.educacionit.interfaces.Fechas"%>
<%@page import="com.educacionit.entidades.Documento"%>
<%@page
	import="com.educacionit.implementaciones.mariadb.AlumnoImplementacion"%>
<%@page import="com.educacionit.entidades.Alumno"%>
<%@page import="com.educacionit.entidades.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/button.css">
<link rel="stylesheet" href="css/form.css">

<title>ABM Alumnos</title>
</head>
<%
Usuario usuario = (Usuario) session.getAttribute("usuario");

if (null == usuario) {
	response.sendRedirect("login.jsp");
}

String tipoDocumento = request.getParameter("tipoDocumento");
String numeroDocumento = request.getParameter("numeroDocumento");
Boolean editar = Boolean.valueOf(request.getParameter("editar"));

Alumno alumno = null;

if (null != tipoDocumento && null != numeroDocumento && null != editar && editar) {
	AlumnoImplementacion alumnoImplementacion = new AlumnoImplementacion();
	alumno = alumnoImplementacion.buscar(new Documento(tipoDocumento, numeroDocumento));
}
%>

<body>

	<header>
	<h1>Digitalers</h1>
	<img alt="Educacion IT" src="images/logo-it.svg" width="200" height="50">
	</header>

	<nav>
		<a href="index.jsp">Lista de Alumnos</a> 
		<a href="loginUsuario?cerrarSesion=true">Cerrar Sesion</a>
	</nav>

	<section>
		<form action="AlumnoControlador" method="post">

			<label for="tipoDocumento">Tipo Documento</label>
			<%
			if (!editar) {
			%>
			<select name="tipoDocumento" id="tipoDocumento" required>
				<option value="">Seleccione un Documento</option>
				<option value="DNI">DNI</option>
				<option value="PAS">PAS</option>
				<option value="CI">CI</option>
				<option value="LE">LE</option>
			</select>
			<%
			} else {
			%>
			<input type="text" name="tipoDocumento" id="tipoDocumento" readonly
				value="<%=editar ? alumno.getDocumento().getTipo() : ""%>">
			<%
			}
			%>

			<label for="numeroDocumento">Numero Documento</label> 
			<input type="text" name="numeroDocumento" id="numeroDocumento"
				<%=editar ? "readonly" : ""%>
				value="<%=editar ? alumno.getDocumento().getNumero() : ""%>"
				required> 
			
			<label for="descripcion">Descripcion</label> 
			<input type="text" name="descripcion" id="descripcion"
				value="<%=editar ? alumno.getDescripcion() : ""%>" required> 
				
			<label for="fechaNacimiento">Fecha de Nacimiento</label> 
			<input type="date"
				name="fechaNacimiento" id="fechaNacimiento"
				value="<%=editar ? Fechas.getFechaAStringSQL(alumno.getFechaNacimiento()) : ""%>"
				required> 
				
			<label for="activo">Activo</label> 
			
			<select name="activo" required>
				<option value="">Seleccione una Opcion</option>
				<option <%=editar && alumno.isActivo() ? "selected" : ""%> value="true">Activo</option>
				<option <%=editar && !alumno.isActivo() ? "selected" : ""%> value="false">Inactivo</option>
			</select>
			
			<button class="success" type="submit">Enviar</button>
			
			<% if(editar){ %>
			<a class="warning" href="index.jsp" style="text-decoration: none">Cancelar</a>
<!-- 			<button class="warning" onclik="window.location.href='index.jsp'">Cancelar</button> -->
			<% } else {%>
			<button class="warning" type="reset">Limpiar</button>
			<% } %>
			
		</form>
	</section>
	
	<footer>
	<br>
	Creado por <b>Gilson Ortiz Garcia</b> en el curso de FullStack Java de Digitalers - Educacion IT
	</footer>
	
</body>
</html>