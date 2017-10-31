package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Elemento;
import entidades.Persona;
import entidades.Reserva;
import entidades.TipoElemento;
import logica.ControladorDeElemento;
import logica.ControladorDeReserva;
import logica.ControladorTipoDeElemento;
import utilidades.ExcepcionEspecial;

/**
 * Servlet implementation class ConfirmarReserva
 */
@WebServlet({ "/ConfirmarReserva", "/conifrmarreserva", "/confirmarReserva", "/Confirmarreserva" })
public class ConfirmarReserva extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmarReserva() {
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
		try {
			String idElemento=request.getParameter("itemElemento"); 						//recupero idElemento
			String fechaHoraDesde= (String) request.getSession().getAttribute("fechaHoraDesde");					//recupero fechaHoraDesde
			String fechaHoraHasta= (String) request.getSession().getAttribute("fechaHoraHasta");			//recupero fechaHoraHasta
			String observacion=request.getParameter("observacion");							//recupero observaciones
			
			ControladorDeElemento ctrlElemento = new ControladorDeElemento(); 				//Instancio controladorElemento
			Elemento elemento = new Elemento();												//Instancio un nuevo Objeto Elemento
			elemento.setId(Integer.parseInt(idElemento));									//Seteo el Id obtenido
			Elemento elementoElegido = ctrlElemento.buscarElemento(elemento);				//Busco el objeto Elemento existente
			
			Reserva reservaActual = new Reserva();											//Creo la nueva reserva
			reservaActual.setElemento(elementoElegido);
			reservaActual.setFechaHoraDesde(fechaHoraDesde);
			reservaActual.setFechaHoraHasta(fechaHoraHasta);
			reservaActual.setObservacion(observacion);
			reservaActual.setTipo((TipoElemento) request.getSession().getAttribute("tipoElemento"));
			reservaActual.setEstado("Activa");
			reservaActual.setPersona((Persona)request.getSession().getAttribute("usuarioReserva"));	//CARGO TODOS LOS DATOS DE LA RESERVA
			
			ControladorDeReserva ctrlReserva = new ControladorDeReserva();					//PERSISTO LA RESERVA
			try {
				
				ctrlReserva.crearReserva(reservaActual);
				request.getSession().setAttribute("reserva", reservaActual);
				request.getRequestDispatcher("WEB-INF/ConfirmacionReserva.jsp").forward(request, response);
				
			} catch (ExcepcionEspecial ex) {	
				
				request.getSession().setAttribute("mensaje", ex.getMessage());
				request.getSession().setAttribute("error", ex.getClass().getSimpleName());
				request.getRequestDispatcher("WEB-INF/Error.jsp").forward(request, response);
			}
			
		} catch (Exception e) {
			request.getSession().setAttribute("mensaje", "Error Genérico");
			request.getSession().setAttribute("error", e.getClass().getSimpleName());
			request.getRequestDispatcher("WEB-INF/Error.jsp").forward(request, response);
		}
		return;
	}

}
