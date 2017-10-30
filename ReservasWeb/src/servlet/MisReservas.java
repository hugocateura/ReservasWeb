package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logica.ControladorDePersona;
import logica.ControladorDeReserva;
import entidades.Persona;
import entidades.Reserva;

/**
 * Servlet implementation class Start
 */
@WebServlet({ "/MisReservas", "/misreservas", "/misReservas"  })
public class MisReservas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Persona pers = new Persona();

    /**
     * Default constructor. 
     */
    public MisReservas() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			Persona pers = (Persona)request.getSession().getAttribute("user");

			ControladorDeReserva ctrlReserva = new ControladorDeReserva();
			
			ArrayList<Reserva> listado = new ArrayList<Reserva>(); 
			
			try {
				listado = ctrlReserva.reservasPendientesPersona(pers);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.setAttribute("listadoMisReservas", listado);
			
			request.getRequestDispatcher("WEB-INF/MisReservas.jsp").forward(request, response);
						
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

}
