package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logica.ControladorDePersona;
import logica.ControladorDeTipoElemento;
import entidades.Persona;
import entidades.TipoElemento;

/**
 * Servlet implementation class Start
 */
@WebServlet({ "/AgregarTipoElemento", "/agregarTipoElemento" })
public class AgregarTipoElemento extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AgregarTipoElemento() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("WEB-INF/AgregarTipoElemento.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String nombre = request.getParameter("tipoElementoNombre");
			String cantMaxRes = request.getParameter("tipoElementoCantMaxRes");
			TipoElemento tipoEle = new TipoElemento();
			tipoEle.setNombre(nombre);
			tipoEle.setCant_max_reservas(Integer.parseInt(cantMaxRes));
			ControladorDeTipoElemento ctrlTipo = new ControladorDeTipoElemento();
			ctrlTipo.crearTipoElemento(tipoEle);
			request.getRequestDispatcher("WEB-INF/AgregarTipoElemento.jsp").forward(request, response);
			//response.getWriter().append(user).append(" ").append(pass);

			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//doGet(request, response);
	}

}
