package com.educacionit.principal;

import java.text.ParseException;

import com.educacionit.entidades.Alumno;
import com.educacionit.entidades.Documento;
import com.educacionit.entidades.Usuario;
import com.educacionit.implementaciones.mariadb.AlumnoImplementacion;
import com.educacionit.implementaciones.mariadb.ExcepcionPatrones;
import com.educacionit.implementaciones.mariadb.UsuarioImplementacion;
import com.educacionit.interfaces.Fechas;

public class Principal {
	public static void main(String[] args) throws ParseException {
		
//		AlumnoImplementacion impl = new AlumnoImplementacion();
//		
//		for (Alumno alumno : impl.listar()) {
//			System.out.println(alumno);
//		}
//		
//		Alumno alumno1 = new Alumno();
//		
//		alumno1.setDocumento(new Documento("DNI", "88"));
//		alumno1.setDescripcion("Octavio Roblet");
//		alumno1.setFechaNacimiento(Fechas.getStringAFecha("01/02/2000"));
//		alumno1.setActivo(false);
//		
//		impl.guardar(alumno1);
		
		try {
			Usuario usuario1 = new Usuario("user1@gmail.c", "User1abc", true);
			
			UsuarioImplementacion impl = new UsuarioImplementacion();
			
			impl.eliminar(usuario1);
			
			for (Usuario usuario : impl.listar()) {
				System.out.println(usuario);
			}
			
			System.out.println(impl.buscar("user1@gmail.com"));
			
		} catch (ExcepcionPatrones e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
