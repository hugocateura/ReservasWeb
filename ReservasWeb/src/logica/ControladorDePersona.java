package logica;

import java.util.ArrayList;

import datos.DatosPersona;
import entidades.Persona;
import utilidades.ExcepcionEspecial;


public class ControladorDePersona {
	private DatosPersona basePersona = new DatosPersona();
	
	public void crearPersona(Persona pers) throws Exception, ExcepcionEspecial{
		
		if (pers.getDni().length() != 0){
			if (pers.getNombre().length() != 0){
				if (pers.getApellido().length() != 0){
					basePersona.agregarPersona(pers);
				}
				else{
					throw new ExcepcionEspecial("apellido");
				}
			}
			else{
				throw new ExcepcionEspecial("nombre");
			}
		}
		else{
			throw new ExcepcionEspecial("DNI");
		}	
		};
	
	public void borrarPersona (Persona pers) throws Exception{
		basePersona.eliminarPersona(pers);
	};
	
	public void modificarPersona(Persona pers) throws Exception{
		if (pers.getDni().length() != 0){
			if (pers.getNombre().length() != 0){
				if (pers.getApellido().length() != 0){
					basePersona.modificarPersona(pers);
				}
				else{
					throw new ExcepcionEspecial("apellido");
				}
			}
			else{
				throw new ExcepcionEspecial("nombre");
			}
		}
		else{
			throw new ExcepcionEspecial("DNI");
		}	
		
	};
	

	public ArrayList<Persona> consultarTodo() throws Exception{
		return basePersona.buscarTodo();
	}

	public Persona buscarPersonaPorUsuyClave(Persona pers) throws Exception
	{
		Persona persona = new Persona();
		try {
			persona = basePersona.buscarPersonaPorUsuyClave(pers);
			
		} catch (Exception e) {
			throw e;
		}
		return persona;
	}

	public ArrayList<Persona> consultarUsuariosExternos() throws Exception {
				
		return basePersona.buscarUsuariosExternos();
	}

}
