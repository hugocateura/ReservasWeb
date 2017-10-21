package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Persona;
import logica.ControladorDePersona;

/**
 * Servlet implementation class ConfirmarModificacionPersona
 */
@WebServlet({ "/ConfirmarModificacionPersona", "/Confirmarmodificacionpersona", "/confirmarmodificacionpersona" })
public class ConfirmarModificacionPersona extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmarModificacionPersona() {
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
		
		Persona persModificar = (Persona)request.getSession().getAttribute("personaModificar");
		
		String dni = request.getParameter("personaDni");
		String nombre = request.getParameter("personaNombre");
		String apellido = request.getParameter("personaApellido");
		String usuario = request.getParameter("personaUsuario");
		String pass = request.getParameter("personaPass");
		String categoria = request.getParameter("personaCategoria");
		String habilitado = request.getParameter("personaHabilitado");	
		
		persModificar.setDni(dni);
		persModificar.setNombre(nombre);
		persModificar.setApellido(apellido);
		persModificar.setUsuario(usuario);
		persModificar.setContrasena(pass);
		persModificar.setCategoria(categoria);
		if (habilitado != null){
			if (habilitado.equals("1")){
				persModificar.setHabilitado(true);
			};
			}
		else{
			persModificar.setHabilitado(false);	
		}
										
		
		ControladorDePersona ctrlPersona = new ControladorDePersona();
		try {
			ctrlPersona.modificarPersona(persModificar);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ArrayList<Persona> listadoPersonas = new ArrayList<Persona>();
		
		try {
			listadoPersonas = ctrlPersona.consultarTodo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("listadoPersonas", listadoPersonas);
		
		request.getRequestDispatcher("WEB-INF/ListadoPersona.jsp").forward(request, response);
		return;
	}

}
