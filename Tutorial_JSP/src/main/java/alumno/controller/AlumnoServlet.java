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
import alumno.model.Alumno;

/**
 * Servlet implementation class AlumnoServlet
 */
@WebServlet("/Registro")
public class AlumnoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private AlumnoDao alumnoDao = new AlumnoDao();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlumnoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher  = request.getRequestDispatcher("/Registro.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		
		Alumno alumno = new Alumno(nombre, apellido);
		try {
			alumnoDao.postAlumno(alumno);
			

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/Exito.jsp");
			dispatcher.forward(request, response);
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setContentType("text/html");
		    PrintWriter out = response.getWriter();
		    out.println("<script>alert('Error al crear Alumno!');</script>");
		    out.close();
		}
		
		
	}

}
