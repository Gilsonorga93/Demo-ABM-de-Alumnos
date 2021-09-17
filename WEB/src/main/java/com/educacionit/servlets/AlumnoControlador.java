package com.educacionit.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.educacionit.entidades.Alumno;	
import com.educacionit.entidades.Documento;
import com.educacionit.enumerados.Mensajes;
import com.educacionit.implementaciones.mariadb.AlumnoImplementacion;
import com.educacionit.interfaces.Fechas;

/**
 * Servlet implementation class AlumnoControlador
 */
public class AlumnoControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	AlumnoImplementacion alumnoImplementacion = new AlumnoImplementacion();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlumnoControlador() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String tipoDocumento = request.getParameter("tipoDocumento");
		String numeroDocumento = request.getParameter("numeroDocumento");
		
		Alumno alumno = new Alumno();
		alumno.setDocumento(new Documento(tipoDocumento, numeroDocumento));
		
		
		request.setAttribute("mensaje", alumnoImplementacion.eliminar(alumno)? Mensajes.REGISTRO_ELIMINADO:Mensajes.ERROR);
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String tipoDocumento = request.getParameter("tipoDocumento");
			String numeroDocumento = request.getParameter("numeroDocumento");
			String descripcion = request.getParameter("descripcion");
			Date fechaNacimiento = Fechas.getStringAFechaSQL(request.getParameter("fechaNacimiento"));
			boolean activo = Boolean.valueOf(request.getParameter("activo"));
			
			Alumno alumno = new Alumno();
			
			alumno.setDocumento(new Documento(tipoDocumento, numeroDocumento));
			alumno.setDescripcion(descripcion);
			alumno.setFechaNacimiento(fechaNacimiento);
			alumno.setActivo(activo);
			
			request.setAttribute("mensaje", 
					alumnoImplementacion.guardar(alumno)?Mensajes.REGISTRO_AGREGADO:Mensajes.ERROR);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
			requestDispatcher.forward(request, response);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
