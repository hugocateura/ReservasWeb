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
 * Servlet implementation class SeleccionarUsuarioReserva
 */
@WebServlet("/SeleccionarUsuarioReserva")
public class SeleccionarUsuarioReserva extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SeleccionarUsuarioReserva() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
try {
			
			ControladorDePersona ctrlPersona = new ControladorDePersona();
			ArrayList<Persona> listadoPersonas = ctrlPersona.consultarUsuariosExternos();
			request.setAttribute("listadoPersonas", listadoPersonas);

			request.getRequestDispatcher("WEB-INF/SeleccionarUsuarioReserva.jsp").forward(request, response);
			//response.getWriter().append(user).append(" ").append(pass);
			
			
		} catch (Exception e) {
			request.getSession().setAttribute("mensaje", "Error Genérico");
			request.getSession().setAttribute("error", e.getClass().toString());
			request.getRequestDispatcher("WEB-INF/Error.jsp").forward(request, response);
		}
		return;

	}

}
