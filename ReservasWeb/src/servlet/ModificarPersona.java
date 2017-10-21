package servlet;

import java.io.IOException;
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
		
		Persona personaModificar = new Persona();
		if (inputId!="0"){
			Persona pers = new Persona();
			pers.setId(Integer.parseInt(inputId));
						
			ControladorDePersona ctrlPersona = new ControladorDePersona();
			try {
				personaModificar = ctrlPersona.getPersona(pers);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.getSession().setAttribute("personaModificar", personaModificar);
			
			request.getRequestDispatcher("WEB-INF/ModificarPersona.jsp").forward(request, response);
		}
		//doGet(request, response);
		return;
	}

}
