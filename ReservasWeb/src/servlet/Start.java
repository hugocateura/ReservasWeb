package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logica.ControladorDePersona;
import entidades.Persona;

/**
 * Servlet implementation class Start
 */
@WebServlet({ "/Start", "/start" })
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Start() {

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("WEB-INF/Principal.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String user=request.getParameter("user"); //recupero user
			String pass=request.getParameter("pass");	//recupero pass (todos los parametros vienen como string)
			
			Persona per=new Persona();
			per.setUsuario(user);
			per.setContrasena(pass);
			
			ControladorDePersona ctrlPersona= new ControladorDePersona();
			Persona pers=ctrlPersona.buscarPersonaPorUsuyClave(per);
			
			if(pers.isHabilitado()){
				request.getSession().setAttribute("user", pers); //crea o recupera una sesion si ya esta creada			
				request.getRequestDispatcher("WEB-INF/Principal.jsp").forward(request, response);							
			}
			else {
				if(pers.getUsuario().equals("null")){
					request.getSession().setAttribute("mensaje", "Error, Usuario o contraseña incorrectos");
					request.getRequestDispatcher("WEB-INF/LoginFail.jsp").forward(request, response);
				}else{
					request.getSession().setAttribute("mensaje", "El usuario ingresado se encuentra deshabilitado, por favor póngase en contacto con un administrador");
					request.getRequestDispatcher("WEB-INF/LoginFail.jsp").forward(request, response);
				}
			}
						
		} catch (Exception e) {
			
			request.getSession().setAttribute("mensaje", "Usuario o contraseña incorrectos, intente nuevamente");
			request.getRequestDispatcher("WEB-INF/LoginFail.jsp").forward(request, response);
		}
		return;
	}

}
