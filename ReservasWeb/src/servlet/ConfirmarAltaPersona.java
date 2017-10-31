package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;

import entidades.Persona;
import logica.ControladorDePersona;
import utilidades.ExcepcionEspecial;

/**
 * Servlet implementation class ConfirmarAlta
 */
@WebServlet({ "/ConfirmarAltaPersona", "/confirmaraltapersona", "/Confirmaraltapersona", "/confirmarAltaPersona" })
public class ConfirmarAltaPersona extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmarAltaPersona() {
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
		
		String dni = request.getParameter("personaDni");
		String nombre = request.getParameter("personaNombre");
		String apellido = request.getParameter("personaApellido");
		String usuario = request.getParameter("personaUsuario");
		String pass = request.getParameter("personaPass");
		String categoria = request.getParameter("personaCategoria");
		String habilitado = request.getParameter("personaHabilitado");
		
		Persona nuevaPersona = new Persona();							//CREO UNA NUEVA PERSONA
		nuevaPersona.setDni(dni);
		nuevaPersona.setNombre(nombre);
		nuevaPersona.setApellido(apellido);
		nuevaPersona.setUsuario(usuario);
		nuevaPersona.setContrasena(pass);
		nuevaPersona.setCategoria(categoria);
		if (habilitado != null){
			if (habilitado.equals("1")){
				nuevaPersona.setHabilitado(true);
			};
			}
		else{
			nuevaPersona.setHabilitado(false);	
		}															//SETEO LOS DATOS DE LA PERSONA
				
		ControladorDePersona ctrlPersona = new ControladorDePersona();
		try {
			ctrlPersona.crearPersona(nuevaPersona);
			Logger logger = LogManager.getLogger(getClass());							//Agrego la transaccion al log de TRACE
			logger.log(Level.INFO,"Alta Exitosa. User: "+nuevaPersona.getUsuario()+" Nombre: "+nuevaPersona.getNombre()+" Apellido: "+nuevaPersona.getApellido());
		} catch (ExcepcionEspecial e) {
		
			request.getSession().setAttribute("mensaje", e.getMessage());
			request.getSession().setAttribute("error", e.getClass().getSimpleName());
			request.getRequestDispatcher("WEB-INF/Error.jsp").forward(request, response);
		} catch (Exception e) {
			request.getSession().setAttribute("mensaje", "Error General");
			request.getSession().setAttribute("error", e.getClass().getSimpleName());
			request.getRequestDispatcher("WEB-INF/Error.jsp").forward(request, response);
		}
		
		request.getSession().setAttribute("nuevoUsuario", nuevaPersona);
		
		request.getRequestDispatcher("WEB-INF/ConfirmacionAltaPersona.jsp").forward(request, response);
		
		return;
		
	}

}
