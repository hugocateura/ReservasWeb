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

/**
 * Servlet implementation class ModificarPersona
 */
@WebServlet({ "/ModificarPersona", "/modificarpersona", "/Modificarpersona", "/modificarPersona" })
public class ModificarPersona extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarPersona() {
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
		Persona personaModificar = new Persona();
		
		if (!(inputId.equals("-1"))){
			Persona pers = new Persona();
			pers.setId(Integer.parseInt(inputId));
						
			
			try {
				personaModificar = ctrlPersona.getPersona(pers);
			} catch (Exception e) {
				request.getSession().setAttribute("mensaje", "Error Genérico");
				request.getSession().setAttribute("error", e.getClass().getSimpleName());
				request.getRequestDispatcher("WEB-INF/Error.jsp").forward(request, response);
			}
			request.getSession().setAttribute("personaModificar", personaModificar);
			request.getRequestDispatcher("WEB-INF/ModificarPersona.jsp").forward(request, response);
		}
		else{
			
			ArrayList<Persona> listadoPersonas;
			try {
				listadoPersonas = ctrlPersona.consultarTodo();
				request.setAttribute("listadoPersonas", listadoPersonas);
			} catch (Exception e) {
				request.getSession().setAttribute("mensaje", "Error Genérico");
				request.getSession().setAttribute("error", e.getClass().getSimpleName());
				request.getRequestDispatcher("WEB-INF/Error.jsp").forward(request, response);
			}
			
			request.getRequestDispatcher("WEB-INF/ListadoPersona.jsp").forward(request, response);
		}
		return;
	}

}
