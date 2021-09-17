package com.educacionit.enumerados;

public enum Mensajes {

	USUARIO_INACTIVO("Usuario inactivo, comuniquese con el administrador", "alertDanger"), 
	CREDENCIALES_INCORRECTAS("Credenciales Incorrectas", "alertWarning"), 
	REGISTRO_ELIMINADO("Se ha eliminado correctamente el registro", "alertWarning"),
	REGISTRO_AGREGADO("Se ha agragado correctamente el registro", "alertSuccess"),
	CERRAR_SESION("Ha cerrado sesion correctamente", "alertWarning"),
	ERROR("Ha ocurrido un error, comuniquese con el administrador", "alertsDanger");

	private String mensaje;
	private String claseCSS;
	
	private Mensajes(String mensaje, String claseCSS) {
		this.mensaje = mensaje;
		this.claseCSS = claseCSS;
	}

	public String getMensaje() {
		return mensaje;
	}
	
	public String getClaseCSS() {
		return claseCSS;
	}
	
	
}
