package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Persona;
import logica.ControladorDePersona;
import utilidades.ExcepcionEspecial;

/**
 * Servlet implementation class EliminarPersona
 */
@WebServlet({ "/EliminarPersona", "/eliminarpersona", "/Eliminarpersona", "/eliminarPersona" })
public class EliminarPersona extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarPersona() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String inputId = request.getParameter("inputId");
		ControladorDePersona ctrlPersona = new ControladorDePersona();
		ArrayList<Persona> listadoPersonas = new ArrayList<Persona>();
		
		if (inputId!="0"){
			Persona pers = new Persona();
			pers.setId(Integer.parseInt(inputId));
				
			try {
				ctrlPersona.borrarPersona(pers);
				listadoPersonas = ctrlPersona.consultarTodo();
		
				request.setAttribute("listadoPersonas", listadoPersonas);
				request.getRequestDispatcher("WEB-INF/ListadoPersona.jsp").forward(request, response);
				
			} catch (ExcepcionEspecial ex) {	
				request.getSession().setAttribute("mensaje", ex.getMessage());
				request.getSession().setAttribute("error", ex.getClass().toString());
				request.getRequestDispatcher("WEB-INF/Error.jsp").forward(request, response);
			}	
			catch (IllegalStateException e) {
				request.getSession().setAttribute("mensaje", "Error Genérico");
				request.getSession().setAttribute("error", e.getClass().toString());
				request.getRequestDispatcher("WEB-INF/Error.jsp").forward(request, response);
			}
			catch (Exception e) {
				request.getSession().setAttribute("mensaje", "Error Genérico");
				request.getSession().setAttribute("error", e.getClass().toString());
				request.getRequestDispatcher("WEB-INF/Error.jsp").forward(request, response);
			}		
		}	
		return;
	}
}
