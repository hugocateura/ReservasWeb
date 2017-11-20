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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import logica.ControladorDeElemento;
import logica.ControladorDePersona;
import logica.ControladorTipoDeElemento;
import utilidades.ExcepcionEspecial;

/**
 * Servlet implementation class ConfirmarModificacionElemento
 */
@WebServlet({ "/ConfirmarModificacionElemento", "/confirmarmodificacionelemento", "/confirmarModificacionElemento" })
public class ConfirmarModificacionElemento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmarModificacionElemento() {
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
		
		Elemento elementoaModificar = (Elemento)request.getSession().getAttribute("elementoaModificar");
		
		String nombre = request.getParameter("elementoNombre");
		String idTipo = request.getParameter("itemTipo");
				
		ControladorTipoDeElemento ctrlTipoElemento = new ControladorTipoDeElemento();
		TipoElemento tipoEle = new TipoElemento();
		tipoEle.setId(Integer.parseInt(idTipo));
		try {
			tipoEle = ctrlTipoElemento.buscarTipoElemento(tipoEle);
		} catch (Exception e) {
			request.getSession().setAttribute("mensaje", "Error Genérico");
			request.getSession().setAttribute("error", e.getClass().getSimpleName());
			request.getRequestDispatcher("WEB-INF/Error.jsp").forward(request, response);
		}
				
		elementoaModificar.setNombre(nombre);									//SETEO EL NOMBRE
		elementoaModificar.setTipo(tipoEle);										//SETEO EL TIPO DE LA CLASE TIPOELEMENTO
		
		
		ControladorDeElemento ctrlElemento = new ControladorDeElemento();
		
		try {
			ctrlElemento.modificarElemento(elementoaModificar);
			request.setAttribute("listadoElementos", ctrlElemento.consultarTodo());
		} catch (ExcepcionEspecial ex) {
			request.getSession().setAttribute("mensaje", ex.getMessage());
			request.getSession().setAttribute("error", ex.getClass().getSimpleName());
			request.getRequestDispatcher("WEB-INF/Error.jsp").forward(request, response);
		} catch (Exception e) {
			request.getSession().setAttribute("mensaje", "Error Genérico");
			request.getSession().setAttribute("error", e.getClass().getSimpleName());
			request.getRequestDispatcher("WEB-INF/Error.jsp").forward(request, response);
		};
			
		request.getRequestDispatcher("WEB-INF/ListadoElemento.jsp").forward(request, response);

		return;	
	}

}
