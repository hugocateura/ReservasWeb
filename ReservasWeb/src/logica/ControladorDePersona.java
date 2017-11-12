package logica;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.logging.log4j.Level;

import datos.DatosPersona;
import entidades.Persona;
import utilidades.ExcepcionEspecial;


public class ControladorDePersona implements Serializable{
	private DatosPersona basePersona = new DatosPersona();
	
	public void crearPersona(Persona pers) throws Exception, ExcepcionEspecial{
		
		if (pers.getDni().length() != 0){
			if (pers.getNombre().length() != 0){
				if (pers.getApellido().length() != 0){
					basePersona.agregarPersona(pers);
				}
				else{
					throw new ExcepcionEspecial("apellido", Level.WARN);
				}
			}
			else{
				throw new ExcepcionEspecial("nombre", Level.WARN);
			}
		}
		else{
			throw new ExcepcionEspecial("DNI", Level.WARN);
		}	
		};
	
	public void borrarPersona (Persona pers) throws Exception, ExcepcionEspecial{
		
		try {
			basePersona.eliminarPersona(pers);
		} catch (ExcepcionEspecial e) {
			throw e;
		}
		catch (Exception ex) {
			throw ex;
		}
				
	};
	
	public void modificarPersona(Persona pers) throws Exception{
		if (pers.getDni().length() != 0){
			if (pers.getNombre().length() != 0){
				if (pers.getApellido().length() != 0){
					basePersona.modificarPersona(pers);
				}
				else{
					throw new ExcepcionEspecial("apellido", Level.WARN);
				}
			}
			else{
				throw new ExcepcionEspecial("nombre", Level.WARN);
			}
		}
		else{
			throw new ExcepcionEspecial("DNI", Level.WARN);
		}	
		
	};
	

	public ArrayList<Persona> consultarTodo() throws Exception{
		return basePersona.buscarTodo();
	}
		
	public Persona getPersona(Persona pers) throws Exception
	{
		Persona persona = new Persona();
		try {
			persona = basePersona.getPersona(pers);
			
		} catch (Exception e) {
			throw e;
		}
		return persona;
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
