package com.educacionit.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.educacionit.entidades.Usuario;
import com.educacionit.enumerados.Mensajes;
import com.educacionit.implementaciones.mariadb.UsuarioImplementacion;

/**
 * Servlet implementation class ValidarLogin
 */

@WebServlet("/loginUsuario")
public class ValidarLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UsuarioImplementacion usuarioImplementacion;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ValidarLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() throws ServletException {
		super.init();
		usuarioImplementacion = new UsuarioImplementacion();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	//Cerramos sesion
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Boolean cerrarSesion = Boolean.valueOf(request.getParameter("cerrarSesion"));
		
		if (cerrarSesion && null != request.getSession().getId()) {
			
			request.getSession().invalidate();
			request.setAttribute("mensaje", Mensajes.CERRAR_SESION);
			
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
		}
		

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String correo = request.getParameter("correo");
		String clave = request.getParameter("clave");

		Usuario usuario = usuarioImplementacion.buscar(correo);

		String pagina = "login.jsp";
		
		HttpSession sesion = null;
		
		if (null != usuario && usuario.getClave().equals(clave)) {
			// clave sea la correcta, si la persona esta activa

			if (usuario.isActivo()) {
				// Creamos sesion
				
				sesion  = request.getSession();
				sesion.setAttribute("usuario", usuario);
				pagina = "index.jsp";
				
			} else {

				request.setAttribute("mensaje", Mensajes.USUARIO_INACTIVO);

			}

		} else {
			// credenciales incorrectas

			request.setAttribute("mensaje", Mensajes.CREDENCIALES_INCORRECTAS);

		}

		RequestDispatcher requestDispatcher = request.getRequestDispatcher(pagina);
		requestDispatcher.forward(request, response);
	}

}
