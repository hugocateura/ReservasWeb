package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.TipoElemento;
import logica.ControladorTipoDeElemento;
import utilidades.ExcepcionEspecial;

/**
 * Servlet implementation class ConfirmarAltaTipoElemento
 */
@WebServlet({ "/ConfirmarAltaTipoElemento", "/confirmaraltatipoelemento" })
public class ConfirmarAltaTipoElemento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmarAltaTipoElemento() {
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
		String nombre = request.getParameter("tipoElementoNombre");
		String cantMaxRes = request.getParameter("tipoElementoCantMaxRes");
		String duracion = request.getParameter("tipoElementoCantMaxHs");
		String anticipacion	= request.getParameter("anticipacionMaxima");
		String reservaEncargado	= request.getParameter("reservaEncargado");
		
		TipoElemento tipoEle = new TipoElemento();
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
			ctrlTipo.crearTipoElemento(tipoEle);
		} catch (ExcepcionEspecial e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getSession().setAttribute("nuevoTipo", tipoEle);
		
		request.getRequestDispatcher("WEB-INF/ConfirmacionAltaTipoElemento.jsp").forward(request, response);
		
		
		//response.getWriter().append(user).append(" ").append(pass);

		
		
		
		
		//doGet(request, response);
	}

}
