package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Elemento;
import entidades.Persona;
import logica.ControladorDeElemento;

/**
 * Servlet implementation class ModificarElemento
 */
@WebServlet({ "/ModificarElemento", "/Modificarelemento", "/modificarElemento", "/modificarelemento" })
public class ModificarElemento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarElemento() {
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
		ControladorDeElemento ctrlElemento = new ControladorDeElemento();		
		Elemento elementoaModificar = new Elemento();
		
		if (!(inputId.equals("-1"))){
			
			elementoaModificar.setId(Integer.parseInt(inputId));	
			
			try {
				elementoaModificar = ctrlElemento.buscarElemento(elementoaModificar);
			} catch (Exception e) {
				request.getSession().setAttribute("mensaje", "Error Genérico");
				request.getSession().setAttribute("error", e.getClass().toString());
				request.getRequestDispatcher("WEB-INF/Error.jsp").forward(request, response);
			}
			request.getSession().setAttribute("elementoaModificar", elementoaModificar);
			request.getRequestDispatcher("WEB-INF/ModificarElemento.jsp").forward(request, response);
		}
		else{
			
			ArrayList<Elemento> listadoElementos;
			try {
				listadoElementos = ctrlElemento.consultarTodo();
				request.setAttribute("listadoElementos", listadoElementos);
			} catch (Exception e) {
				request.getSession().setAttribute("mensaje", "Error Genérico");
				request.getSession().setAttribute("error", e.getClass().toString());
				request.getRequestDispatcher("WEB-INF/Error.jsp").forward(request, response);
			}
			
			request.getRequestDispatcher("WEB-INF/ListadoElemento.jsp").forward(request, response);
		}
		return;
	}

	

}
