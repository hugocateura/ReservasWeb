package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Elemento;
import entidades.TipoElemento;
import logica.ControladorDeElemento;
import logica.ControladorTipoDeElemento;
import utilidades.ExcepcionEspecial;

/**
 * Servlet implementation class EliminarElemento
 */
@WebServlet({ "/EliminarElemento", "/eliminarelemento", "/eliminarElemento", "/Eliminarelemento" })
public class EliminarElemento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarElemento() {
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
		ArrayList<Elemento> listadoElementos = new ArrayList<Elemento>();
		
		if (!(inputId.equals("-1"))){
			Elemento ele = new Elemento();
			ele.setId(Integer.parseInt(inputId));
			
			try {
				ctrlElemento.borrarElemento(ele);
				listadoElementos = ctrlElemento.consultarTodo();
				
				request.setAttribute("listadoElementos", listadoElementos);
				request.getRequestDispatcher("WEB-INF/ListadoElemento.jsp").forward(request, response);
			}
			catch (ExcepcionEspecial ex) {	
				request.getSession().setAttribute("mensaje", ex.getMessage());
				request.getRequestDispatcher("WEB-INF/Error.jsp").forward(request, response);
			}	
			catch (IllegalStateException e) {
				request.getSession().setAttribute("mensaje", "Error General");
				request.getRequestDispatcher("WEB-INF/Error.jsp").forward(request, response);
			}
			catch (Exception e) {
				request.getSession().setAttribute("mensaje", "Error Gen�rico");
				request.getRequestDispatcher("WEB-INF/Error.jsp").forward(request, response);
			}	
		}
		
		return;
		}
}

