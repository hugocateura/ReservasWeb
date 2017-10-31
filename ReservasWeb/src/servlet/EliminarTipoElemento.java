package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Persona;
import entidades.TipoElemento;
import logica.ControladorDePersona;
import logica.ControladorTipoDeElemento;
import utilidades.ExcepcionEspecial;

/**
 * Servlet implementation class EliminarTipoElemento
 */
@WebServlet({ "/EliminarTipoElemento", "/eliminartipoelemento", "/Eliminartipoelemento", "/eliminarTipoElemento" })
public class EliminarTipoElemento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarTipoElemento() {
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
		ControladorTipoDeElemento ctrlTipoElemento = new ControladorTipoDeElemento();
		if (inputId!="0"){
			TipoElemento tipoEle = new TipoElemento();
			tipoEle.setId(Integer.parseInt(inputId));
			
			try {
				ctrlTipoElemento.borrarTipoElemento(tipoEle);
				
				ArrayList<TipoElemento> listadoTipoElementos = new ArrayList<TipoElemento>();
				
	
				listadoTipoElementos = ctrlTipoElemento.consultarTodos();
				request.setAttribute("listadoTipoElementos", listadoTipoElementos);
				request.getRequestDispatcher("WEB-INF/ListadoTipoElemento.jsp").forward(request, response);
		
				} 
			catch (ExcepcionEspecial ex) {	
				request.getSession().setAttribute("mensaje", ex.getMessage());
				request.getSession().setAttribute("error", ex.getClass().getSimpleName());
				request.getRequestDispatcher("WEB-INF/Error.jsp").forward(request, response);
			}	
			catch (IllegalStateException e) {
				request.getSession().setAttribute("mensaje", "Error General");
				request.getSession().setAttribute("error", e.getClass().getSimpleName());
				request.getRequestDispatcher("WEB-INF/Error.jsp").forward(request, response);
			}
			catch (Exception e) {
				request.getSession().setAttribute("mensaje", "Error Genérico");
				request.getSession().setAttribute("error", e.getClass().getSimpleName());
				request.getRequestDispatcher("WEB-INF/Error.jsp").forward(request, response);
			}	
		}
		return;
	}

}
