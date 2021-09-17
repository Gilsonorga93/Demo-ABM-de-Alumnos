package com.educacionit.interfaces;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Patrones {
	/*
	correo: "([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]+))"
	clave: "(^(?=\\w*\\d) (?=\\w*[A-Z]) (?=\\w*[a-z])\\S{8,16}$"
	*/
	
	static boolean esCorreo(String correo) {
		Pattern patron = Pattern.compile("([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]+))");
		Matcher comparacion = patron.matcher(correo);
		
		return comparacion.find();
	}
	
	static boolean esClave(String clave) {
		return Pattern.compile("^(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{8,16}$").matcher(clave).find();
	}
	
}
