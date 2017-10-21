package servlet;

import java.io.IOException;
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
				ctrlTipoElemento.borrarTipoElemento(tipoEle);;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		ArrayList<TipoElemento> listadoTipoElementos = new ArrayList<TipoElemento>();
		try {
			listadoTipoElementos = ctrlTipoElemento.consultarTodos();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("listadoTipoElementos", listadoTipoElementos);
		
		request.getRequestDispatcher("WEB-INF/ListadoTipoElemento.jsp").forward(request, response);
		
		//doGet(request, response);
		//doGet(request, response);
	}

}
