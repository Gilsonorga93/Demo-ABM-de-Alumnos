package com.educacionit.interfaces;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public interface Fechas {
	String FECHA_SQL = "yyyy-MM-dd";
	String FECHA_USUARIO = "dd/MM/yyyy";

	/**
	 * Metodo retorna un String con formato dd/MM/yyyy de un objeto Date
	 * 
	 * @param fecha
	 * @return
	 */
	static String getFechaAString(Date fecha) {
		SimpleDateFormat formato = new SimpleDateFormat(FECHA_USUARIO);

		return formato.format(fecha);
	}

	/**
	 * Metodo retorna un objeto Date de un String dado con formato dd/MM/yyyy
	 * 
	 * @param fecha
	 * @return
	 * @throws ParseException
	 */
	static Date getStringAFecha(String fecha) throws ParseException {
		SimpleDateFormat formato = new SimpleDateFormat(FECHA_USUARIO);

		return formato.parse(fecha);
	}

	/**
	 * Metodo retorna un String con formato yyyy-MM-dd de un objeto Date
	 * 
	 * @param fecha
	 * @return
	 */
	static String getFechaAStringSQL(Date fecha) {
		return new SimpleDateFormat(FECHA_SQL).format(fecha);
	}

	/**
	 * Metodo retorna un objeto Date de un String dado con formato yyyy-MM-dd
	 * 
	 * @param fecha
	 * @return
	 * @throws ParseException
	 */
	static Date getStringAFechaSQL(String fecha) throws ParseException {
		return new SimpleDateFormat(FECHA_SQL).parse(fecha);
	}
	

	
}