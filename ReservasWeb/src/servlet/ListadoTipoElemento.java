package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logica.ControladorDePersona;
import logica.ControladorTipoDeElemento;
import entidades.Persona;

/**
 * Servlet implementation class Start
 */
@WebServlet({ "/ListadoTipoElemento", "/listadotipoelemento", "/listadoTipoElemento" })
public class ListadoTipoElemento extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Persona persona = new Persona();
    
	public ListadoTipoElemento() {
    	
      
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
			
			ControladorTipoDeElemento ctrlTipoElemento = new ControladorTipoDeElemento();
			
			persona = (Persona)request.getSession().getAttribute("user");
			
			request.setAttribute("listadoTipoElementos", ctrlTipoElemento.consultarTodos());

			request.getRequestDispatcher("WEB-INF/ListadoTipoElemento.jsp").forward(request, response);
			//response.getWriter().append(user).append(" ").append(pass);
			
			
		} catch (Exception e) {
			request.getSession().setAttribute("mensaje", "Error Genérico");
			request.getSession().setAttribute("error", e.getClass().getSimpleName());
			request.getRequestDispatcher("WEB-INF/Error.jsp").forward(request, response);
		}
		return;
	}

}
