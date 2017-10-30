package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Persona;
import entidades.Reserva;
import logica.ControladorDeReserva;

/**
 * Servlet implementation class ConfirmarAnulacionReserva
 */
@WebServlet({ "/ConfirmarAnulacionReserva", "/confirmarAnulacionReserva", "/confirmaranulacionreserva" })
public class ConfirmarAnulacionReserva extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmarAnulacionReserva() {
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
			String idReserva=request.getParameter("inputId"); 							//recupero idReserva
			
			Reserva reservaAnular = new Reserva();
			reservaAnular.setId(Integer.parseInt(idReserva));
			
			ControladorDeReserva ctrlReserva = new ControladorDeReserva(); 					//Instancio controladorReserva
			ctrlReserva.cancelarReserva(reservaAnular);
					
			ArrayList<Reserva> listado = new ArrayList<Reserva>(); 
			
			try {
				listado = ctrlReserva.consultarTodo(); 		//Actualizo el listado de las reservas
			} catch (Exception e) {
				request.getSession().setAttribute("mensaje", "Error Genérico");
				request.getSession().setAttribute("error", e.getClass().toString());
				request.getRequestDispatcher("WEB-INF/Error.jsp").forward(request, response);
			}
			
			request.setAttribute("listadoReserva", listado);
			
			request.getRequestDispatcher("WEB-INF/AnularReserva.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

}


