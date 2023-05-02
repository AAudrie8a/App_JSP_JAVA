package alumno.controller;

import alumno.dao.AlumnoDao;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.If;
import org.eclipse.jdt.internal.compiler.ast.IfStatement;

import alumno.model.Alumno;

/**
 * Servlet implementation class AlumnoServlet
 */
@WebServlet(urlPatterns = { "/Registro", "/Login" , "/Modificar", "/Companeros"})
public class AlumnoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AlumnoDao alumnoDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AlumnoServlet() {
		super();
		// TODO Auto-generated constructor stub
		alumnoDao = new AlumnoDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String urlPattern = request.getServletPath();

		switch (urlPattern) {
		case "/Registro":
			forwardToPage("/Registro.jsp", request, response);
			break;
		case "/Login":
			forwardToPage("/Inicio.jsp", request, response);
			break;
		case "/Modificar":
			forwardToPage("/Modificar.jsp", request, response);
			break;
		case "/Companeros":
			request.setAttribute("users", alumnoDao.getAllUsers());
            RequestDispatcher view = request.getRequestDispatcher("/Companeros.jsp");
            view.forward(request, response);
			break;
			
		default:
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			break;
		}

	}

	private void forwardToPage(String page, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String urlPattern = request.getServletPath();

			switch (urlPattern) {
			case "/Registro": {
				String nombre = request.getParameter("nombre");
				String apellido = request.getParameter("apellido");
				registrarAlumno(nombre, apellido, request, response);
			}
			case "/Login": {
				String carnet = request.getParameter("carnet");
				String nombre = request.getParameter("nombre");
				Integer Carnet = Integer.parseInt(carnet);
				iniciarSesion(Carnet, nombre, request, response);
			}
			case "/Modificar":{
				String carnet = request.getParameter("id");
				String nombre = request.getParameter("nombre");
				String apellido = request.getParameter("apellido");
				System.out.println(carnet+"--"+nombre+"--"+"apellido");
				modificarAlumno(carnet,nombre, apellido, request, response);
			}
			case "/Companeros":{
	            request.setAttribute("users", alumnoDao.getAllUsers());
	            RequestDispatcher view = request.getRequestDispatcher("/Companeros.jsp");
	            view.forward(request, response);
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + urlPattern);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('Error al Ingresar Dato!');</script>");

			out.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Inicio.jsp");
			dispatcher.forward(request, response);

		}

	}

	private void registrarAlumno(String nombre, String apellido, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			Alumno alumno = new Alumno(nombre, apellido);
			alumnoDao.postAlumno(alumno);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/Exito.jsp");
			dispatcher.forward(request, response);
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('Error al crear Alumno!');</script>");
			out.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Registro.jsp");
			dispatcher.forward(request, response);
		}

	}

	private void modificarAlumno(String carnet, String nombre, String apellido, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			Alumno alumno = new Alumno(Integer.parseInt(carnet), nombre, apellido );
			alumnoDao.updateAlumno(alumno);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/Exito.jsp");
			dispatcher.forward(request, response);
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('Error al crear Alumno!');</script>");
			out.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Inicio.jsp");
			dispatcher.forward(request, response);
		}

	}
	private void iniciarSesion(Integer Carnet, String Nombre, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			Alumno alumno = new Alumno(Carnet, Nombre);
			int result = alumnoDao.postLogin(alumno);
			if (result == 0) {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('Usuario y contaseña incorrectos!');</script>");
				out.close();
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Inicio.jsp");
				dispatcher.forward(request, response);
			} else {
				//RequestDispatcher dispatcher = request.getRequestDispatcher("/Exito.jsp");
				//dispatcher.forward(request, response);
				System.out.println("Carnet: "+Carnet);
				request.setAttribute("parametro1", Integer.toString(Carnet));
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Modificar.jsp");
				dispatcher.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('Error al iniciar Sesión!');</script>");
			out.close();
		}

	}

}
