package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Reserva;
import entidades.TipoElemento;
import logica.ControladorDeReserva;
import logica.ControladorTipoDeElemento;

/**
 * Servlet implementation class CancelarReserva
 */
@WebServlet({ "/CancelarReserva", "/cancelarReserva", "/cancelarreserva", "/Cancelarreserva" })
public class CancelarReserva extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelarReserva() {
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
			String idReserva=request.getParameter("itemReserva"); 							//recupero idReserva
			Reserva reservaAEliminar = new Reserva();
			reservaAEliminar.setId(Integer.parseInt(idReserva));
			
			ControladorDeReserva ctrlReserva = new ControladorDeReserva(); 					//Instancio controladorReserva
			ctrlReserva.borrarReserva(reservaAEliminar);
			
			request.getRequestDispatcher("WEB-INF/MisReservas.jsp").forward(request, response);
			//response.getWriter().append(user).append(" ").append(pass);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//doGet(request, response);
	}

}
