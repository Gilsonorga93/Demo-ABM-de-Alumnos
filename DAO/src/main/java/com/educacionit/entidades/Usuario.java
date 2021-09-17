package com.educacionit.entidades;

import com.educacionit.implementaciones.mariadb.ExcepcionPatrones;
import com.educacionit.interfaces.Patrones;

public class Usuario {
	private String correo;
	private String clave;
	private boolean activo;

	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public Usuario(String correo, String clave, boolean activo) throws ExcepcionPatrones {
		super();
		setCorreo(correo);
		setClave(clave);
		this.activo = activo;
	}

	@Override
	public String toString() {
		return "Usuario [" + correo + ", " + clave + ", " + activo + "]";
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) throws ExcepcionPatrones {
		if (!Patrones.esCorreo(correo)) {
			throw new ExcepcionPatrones(1);
		}
		this.correo = correo;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) throws ExcepcionPatrones {
		if (!Patrones.esClave(clave)) {
			throw new ExcepcionPatrones(2);
		}
		this.clave = clave;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

}
