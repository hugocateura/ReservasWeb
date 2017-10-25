package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Elemento;
import entidades.Persona;
import entidades.TipoElemento;
import logica.ControladorDeElemento;
import logica.ControladorTipoDeElemento;
import utilidades.ExcepcionEspecial;

/**
 * Servlet implementation class ConfirmarAltaElemento
 */
@WebServlet({ "/ConfirmarAltaElemento", "/Confirmaraltaelemento", "/confirmaraltaelemento" })
public class ConfirmarAltaElemento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmarAltaElemento() {
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
		
		
		String nombre = request.getParameter("elementoNombre");
		String idTipo = request.getParameter("itemTipo");
		
		ControladorTipoDeElemento ctrlTipoElemento = new ControladorTipoDeElemento();
		TipoElemento tipoEle = new TipoElemento();
		tipoEle.setId(Integer.parseInt(idTipo));
		try {
			tipoEle = ctrlTipoElemento.buscarTipoElemento(tipoEle);
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		Elemento nuevoElemento = new Elemento();							//CREO UN NUEVO ELEMENTO
		nuevoElemento.setNombre(nombre);									//SETEO EL NOMBRE
		nuevoElemento.setTipo(tipoEle);										//SETEO EL TIPO DE LA CLASE TIPOELEMENTO
		
		
		ControladorDeElemento ctrlElemento = new ControladorDeElemento();
		
		try {
			ctrlElemento.crearElemento(nuevoElemento);
		} catch (ExcepcionEspecial e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		request.getSession().setAttribute("nuevoElemento", nuevoElemento);
		request.getRequestDispatcher("WEB-INF/ConfirmacionAltaElemento.jsp").forward(request, response);

		return;	
	}
}


