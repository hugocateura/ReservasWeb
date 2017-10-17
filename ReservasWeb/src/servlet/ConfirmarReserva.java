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
			
			System.out.println(idElemento);
			System.out.println(fechaHoraDesde);
			System.out.println(fechaHoraHasta);
			System.out.println(observacion);
			
			ControladorDeElemento ctrlElemento = new ControladorDeElemento(); 				//Instancio controladorElemento
			Elemento elemento = new Elemento();												//Instancio un nuevo Objeto Elemento
			elemento.setId(Integer.parseInt(idElemento));									//Seteo el Id obtenido
			Elemento elementoElegido = ctrlElemento.buscarElemento(elemento);				//Busco el objeto Elemento existente
			
			Reserva reservaActual = new Reserva();											//Creo la nueva reserva
			reservaActual.setElemento(elementoElegido);
			reservaActual.setFechaHoraDesde(fechaHoraDesde);
			reservaActual.setFechaHoraHasta(fechaHoraHasta);
			reservaActual.setObservacion(observacion);
			reservaActual.setTipo(elementoElegido.getTipo());
			reservaActual.setEstado("Activa");
			reservaActual.setPersona((Persona)request.getSession().getAttribute("user"));		
			
			ControladorDeReserva ctrlReserva = new ControladorDeReserva();
			ctrlReserva.crearReserva(reservaActual);
			
			request.getSession().setAttribute("reserva", reservaActual);
			
			//String fechaHoraDesde = request.getParameter("diaDesdeReserva")+" "+request.getParameter("horaDesdeReserva");  //Obtengo y concateno fecha y hora desde
			//String fechaHoraHasta = request.getParameter("diaHastaReserva")+" "+request.getParameter("horaHastaReserva");  //Obtengo y concateno fecha y hora hasta	
			
			//request.getSession().setAttribute("tipoElemento", tipoElegido); 				//Envia el objeto TipoElemento
			//request.getSession().setAttribute("fechaHoraDesde", fechaHoraDesde); 				//Envia el objeto TipoElemento
			//request.getSession().setAttribute("fechaHoraHasta", fechaHoraHasta); 				//Envia el objeto TipoElemento
			
			request.getRequestDispatcher("WEB-INF/ConfirmacionReserva.jsp").forward(request, response);
			//response.getWriter().append(user).append(" ").append(pass);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//doGet(request, response);
	}

}
