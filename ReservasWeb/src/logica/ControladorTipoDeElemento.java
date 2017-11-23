package logica;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.logging.log4j.Level;

import datos.DatosTipoElemento;
import entidades.Persona;
import entidades.TipoElemento;
import utilidades.Emailer;
import utilidades.ExcepcionEspecial;

public class ControladorTipoDeElemento implements Serializable{
private DatosTipoElemento baseTipoElemento = new DatosTipoElemento();
	
	public void crearTipoElemento(TipoElemento tipoele) throws Exception, ExcepcionEspecial{
		if (tipoele.getNombre().length() != 0){
			if (tipoele.getCant_max_reservas() >= 0){			
				baseTipoElemento.agregarTipoElemento(tipoele);
				try {
					String contenidoMail = ("Alta exitosa de:\nNombre: "+tipoele.getNombre()+"\nCant. Max. Reservas: "+tipoele.getCant_max_reservas()+"\nMax. Anticipacion: "+tipoele.getCantMaxDiasAnticipacion()+"\nMax. Duracion: "+tipoele.getLimiteMaxHorasReserva()+"\nReserva Encargado: "+tipoele.getReservaEncargado());
					Emailer.getInstance().send("tpfinaljava2017@gmail.com","Alta de nuevo Tipo de Elemento",contenidoMail);
				} catch (Exception e) {
					throw new ExcepcionEspecial("No es posible enviar el correo.", Level.ERROR);
				}
				
				}
			else{
				throw new ExcepcionEspecial("cantidad máxima de reservas", Level.WARN);
				}
			}
		else{
			throw new ExcepcionEspecial("nombre", Level.WARN);
			}		
	};
	
	public void borrarTipoElemento (TipoElemento tipoele) throws Exception, ExcepcionEspecial{
		try {
			baseTipoElemento.eliminarTipoElemento(tipoele);
		} catch (ExcepcionEspecial e) {
			throw e;
		}
		catch (Exception ex) {
			throw ex;
		}
	};
	
	public void modificarTipoElemento(TipoElemento tipoele) throws Exception, ExcepcionEspecial{
		if (tipoele.getNombre().length() != 0){
			if (tipoele.getCant_max_reservas() >= 0){			
				baseTipoElemento.modificarTipoElemento(tipoele);
				}
			else{
				throw new ExcepcionEspecial("cantidad máxima de reservas", Level.WARN);
				}
			}
		else{
			throw new ExcepcionEspecial("nombre", Level.WARN);
			}		
	};
	
	public ArrayList<TipoElemento> consultarTodo(Persona pers) throws Exception{
		
		DatosTipoElemento datosTipoEle = new DatosTipoElemento();  				
		ArrayList<TipoElemento> listadoTipoElemento = datosTipoEle.devolverTodoTipoElemento();   //OBTIENE TODOS LOS ELEMENTOS
		ArrayList<TipoElemento> listadoADevolver = new  ArrayList<TipoElemento>();     	//LOS QUE SE VAN A DEVOLVER
		
		if (pers.getCategoria().equals("Online") || pers.getCategoria().equals("Administrador"))
		{
			  
			for(TipoElemento te : listadoTipoElemento)
				{
				if (!(te.getReservaEncargado()))
					{
					listadoADevolver.add(te);
					}
				}
		}
		else 
			{
			listadoADevolver = listadoTipoElemento;
			};
		return listadoADevolver;
	};
	
	public ArrayList<TipoElemento> consultarTodos() throws Exception{
		
		DatosTipoElemento datosTipoEle = new DatosTipoElemento();  				
		ArrayList<TipoElemento> listadoTipoElemento = datosTipoEle.devolverTodoTipoElemento();   //OBTIENE TODOS LOS ELEMENTOS
		
		return listadoTipoElemento;
	};
	
	public TipoElemento buscarTipoElemento(TipoElemento tipoEle) throws Exception{
		DatosTipoElemento datosTipoEle = new DatosTipoElemento();
		return datosTipoEle.buscarTipoElemento(tipoEle);
	}
}
