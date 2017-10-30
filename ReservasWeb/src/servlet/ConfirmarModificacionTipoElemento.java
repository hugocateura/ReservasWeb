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
import utilidades.ExcepcionEspecial;

/**
 * Servlet implementation class ConfirmarModificacionTipoElemento
 */
@WebServlet({ "/ConfirmarModificacionTipoElemento", "/confirmarmodificaciontipoelemento" })
public class ConfirmarModificacionTipoElemento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmarModificacionTipoElemento() {
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
		
		TipoElemento tipoEle = (TipoElemento)request.getSession().getAttribute("tipoEleaModificar");
		
		String nombre = request.getParameter("tipoElementoNombre");
		String cantMaxRes = request.getParameter("tipoElementoCantMaxRes");
		String duracion = request.getParameter("tipoElementoCantMaxHs");
		String anticipacion	= request.getParameter("anticipacionMaxima");
		String reservaEncargado	= request.getParameter("reservaEncargado");
				
		tipoEle.setNombre(nombre);
		tipoEle.setCant_max_reservas(Integer.parseInt(cantMaxRes));
		tipoEle.setLimiteMaxHorasReserva(Integer.parseInt(duracion));
		tipoEle.setCantMaxDiasAnticipacion(Integer.parseInt(anticipacion));
		if (reservaEncargado != null){
			if (reservaEncargado.equals("1")){
				tipoEle.setReservaEncargado(true);
			};
			}
		else{
			tipoEle.setReservaEncargado(false);	
		}			

		ControladorTipoDeElemento ctrlTipo = new ControladorTipoDeElemento();
		try {
			ctrlTipo.modificarTipoElemento(tipoEle);
		} 
		catch (ExcepcionEspecial ex) {
			request.getSession().setAttribute("mensaje", ex.getMessage());
			request.getSession().setAttribute("error", ex.getClass().toString());
			request.getRequestDispatcher("WEB-INF/Error.jsp").forward(request, response);
		} 
		catch (Exception e) {
			request.getSession().setAttribute("mensaje", "Error Genérico");
			request.getSession().setAttribute("error", e.getClass().toString());
			request.getRequestDispatcher("WEB-INF/Error.jsp").forward(request, response);
		}
		try {
			request.setAttribute("listadoTipoElementos", ctrlTipo.consultarTodos());
			request.getRequestDispatcher("WEB-INF/ListadoTipoElemento.jsp").forward(request, response);
			
		} 
		catch (Exception e) {
			request.getSession().setAttribute("mensaje", "Error Genérico");
			request.getSession().setAttribute("error", e.getClass().toString());
			request.getRequestDispatcher("WEB-INF/Error.jsp").forward(request, response);
		}
		return;
	}
}
