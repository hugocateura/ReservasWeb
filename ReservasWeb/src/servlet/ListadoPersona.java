package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logica.ControladorDePersona;
import entidades.Persona;

/**
 * Servlet implementation class Start
 */
@WebServlet({ "/ListadoPersona", "/listadoPersona" })
public class ListadoPersona extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ListadoPersona() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			ControladorDePersona ctrlPersona = new ControladorDePersona();
			ArrayList<Persona> listadoPersonas = ctrlPersona.consultarTodo();
			request.setAttribute("listadoPersonas", listadoPersonas);

			request.getRequestDispatcher("WEB-INF/ListadoPersona.jsp").forward(request, response);
			//response.getWriter().append(user).append(" ").append(pass);
			
			
		} catch (Exception e) {
			request.getSession().setAttribute("mensaje", "Error Gen�rico");
			request.getSession().setAttribute("error", e.getClass().getSimpleName());
			request.getRequestDispatcher("WEB-INF/Error.jsp").forward(request, response);
		}
		return;
		
	}

}
