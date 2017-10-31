package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.ControladorDePersona;
import logica.ControladorTipoDeElemento;
import entidades.Persona;
import entidades.TipoElemento;

/**
 * Servlet implementation class ReservarElemento
 */
@WebServlet({ "/ReservarElemento1", "/reservarElemento1", "/reservarelemento1", "/Reservarelemento1" })
public class ReservarElemento1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ReservarElemento1() {
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
			String idTipoElemento=request.getParameter("itemTipo"); 						//recupero idTipoElemento
			ControladorTipoDeElemento ctrlTipoElemento = new ControladorTipoDeElemento(); 	//Instancio controladorTipoElemento
			TipoElemento tipoEle = new TipoElemento();										//Instancio un nuevo Objeto TipoElemento
			tipoEle.setId(Integer.parseInt(idTipoElemento));								//Seteo el Id obtenido
			TipoElemento tipoElegido = ctrlTipoElemento.buscarTipoElemento(tipoEle);		//Busco el objeto TipoElemento existente
			
			String fechaHoraDesde = request.getParameter("diaDesdeReserva")+" "+request.getParameter("horaDesdeReserva");  //Obtengo y concateno fecha y hora desde
			String fechaHoraHasta = request.getParameter("diaHastaReserva")+" "+request.getParameter("horaHastaReserva");  //Obtengo y concateno fecha y hora hasta	
			
			request.getSession().setAttribute("tipoElemento", tipoElegido); 				//Envia el objeto TipoElemento
			request.getSession().setAttribute("fechaHoraDesde", fechaHoraDesde); 			//Envia el objeto TipoElemento
			request.getSession().setAttribute("fechaHoraHasta", fechaHoraHasta); 			//Envia el objeto TipoElemento
			
			request.getRequestDispatcher("WEB-INF/ReservarElementoPaso2.jsp").forward(request, response);
			//response.getWriter().append(user).append(" ").append(pass);
			
			
		} catch (Exception e) {
			request.getSession().setAttribute("mensaje", "Error Genérico");
			request.getSession().setAttribute("error", e.getClass().getSimpleName());
			request.getRequestDispatcher("WEB-INF/Error.jsp").forward(request, response);
		}
		return;
	}

}
