package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logica.ControladorDePersona;
import logica.ControladorTipoDeElemento;
import entidades.Persona;
import entidades.TipoElemento;

/**
 * Servlet implementation class Start
 */
@WebServlet({ "/ReservarElemento", "/reservarElemento", "/reservarelemento", "/Reservarelemento" })
public class ReservarElemento extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ReservarElemento() {
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

		ControladorTipoDeElemento ctrlTipoDeElemento = new ControladorTipoDeElemento();
		Persona pers = (Persona)request.getSession().getAttribute("user");
      	ArrayList<TipoElemento> tiposHabilitados = new ArrayList<TipoElemento>();
      	      	      	
      	if (pers.getCategoria().equals("Online")){     						//Si es online se guarda el user actual
      		      		
      		request.getSession().setAttribute("usuarioReserva", pers);
      	}
      	else {
      		String idPersona = request.getParameter("inputId");				//si no es online se guarda el user seleccionado
      		
      		pers.setId(Integer.parseInt(idPersona));
      		
      		ControladorDePersona ctrlPersona = new ControladorDePersona();
      		
      		try {
				pers = ctrlPersona.getPersona(pers);
			} catch (Exception e) {
				e.printStackTrace();
			}
      		request.getSession().setAttribute("usuarioReserva", pers);
      		      		
      	}
      	
		try {
							
			request.setAttribute("listaTipos", ctrlTipoDeElemento.consultarTodo(pers));
			
			request.getRequestDispatcher("WEB-INF/ReservarElementoPaso1.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

}
