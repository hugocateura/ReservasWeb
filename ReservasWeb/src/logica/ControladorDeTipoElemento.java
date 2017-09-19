package logica;

import java.util.ArrayList;

import datos.DatosTipoElemento;
import entidades.Persona;
import entidades.TipoElemento;
import utilidades.ExcepcionEspecial;

public class ControladorDeTipoElemento {
private DatosTipoElemento baseTipoElemento = new DatosTipoElemento();
	
	public void crearTipoElemento(TipoElemento tipoele) throws Exception, ExcepcionEspecial{
		if (tipoele.getNombre().length() != 0){
			if (tipoele.getCant_max_reservas() >= 0){			
				baseTipoElemento.agregarTipoElemento(tipoele);
				}
			else{
				throw new ExcepcionEspecial("cantidad máxima de reservas");
				}
			}
		else{
			throw new ExcepcionEspecial("nombre");
			}		
	};
	
	public void borrarTipoElemento (TipoElemento tipoele) throws Exception{
		baseTipoElemento.eliminarTipoElemento(tipoele);
	};
	
	public void modificarTipoElemento(TipoElemento tipoele) throws Exception, ExcepcionEspecial{
		if (tipoele.getNombre().length() != 0){
			if (tipoele.getCant_max_reservas() >= 0){			
				baseTipoElemento.modificarTipoElemento(tipoele);
				}
			else{
				throw new ExcepcionEspecial("cantidad máxima de reservas");
				}
			}
		else{
			throw new ExcepcionEspecial("nombre");
			}		
	};
	
	public ArrayList<TipoElemento> consultarTodo(Persona pers) throws Exception{
		return baseTipoElemento.buscarTodo(pers);
	}
}
