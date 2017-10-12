package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logica.ControladorDePersona;
import logica.ControladorDeTipoElemento;
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
			
			ControladorDeTipoElemento ctrlTipoElemento = new ControladorDeTipoElemento();
			
			persona = (Persona)request.getSession().getAttribute("user");
			
			request.setAttribute("listadoTipoElementos", ctrlTipoElemento.consultarTodo(persona));

			request.getRequestDispatcher("WEB-INF/ListadoTipoElemento.jsp").forward(request, response);
			//response.getWriter().append(user).append(" ").append(pass);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//doGet(request, response);
	}

}
