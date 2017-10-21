package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Persona;
import entidades.TipoElemento;
import logica.ControladorDePersona;
import logica.ControladorTipoDeElemento;

/**
 * Servlet implementation class ModificarTipoElemento
 */
@WebServlet({ "/ModificarTipoElemento", "/modificartipoelemento", "/modificarTipoElemento" })
public class ModificarTipoElemento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarTipoElemento() {
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
		
		String inputId = request.getParameter("inputId");
		
		TipoElemento tipoEleaModificar = new TipoElemento();
		if (inputId!="0"){
			TipoElemento tipoEle = new TipoElemento();
			tipoEle.setId(Integer.parseInt(inputId));
						
			ControladorTipoDeElemento ctrlTipoElemento = new ControladorTipoDeElemento();
			try {
				tipoEleaModificar = ctrlTipoElemento.buscarTipoElemento(tipoEle);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.getSession().setAttribute("tipoEleaModificar", tipoEleaModificar);
		
		
		request.getRequestDispatcher("WEB-INF/ModificarTipoElemento.jsp").forward(request, response);
		//doGet(request, response);
		return;
		}
	}
}


