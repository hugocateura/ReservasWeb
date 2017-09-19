package logica;

import java.util.ArrayList;
import java.util.Date;

import datos.DatosElemento;
import datos.DatosReserva;
import datos.DatosTipoElemento;
import entidades.Elemento;
import entidades.Persona;
import entidades.Reserva;
import entidades.TipoElemento;

public class ControladorDeReserva {
private DatosReserva baseReserva = new DatosReserva();
private DatosTipoElemento baseTipoElemento = new DatosTipoElemento();
private DatosElemento baseElemento = new DatosElemento();
	
	public void crearReserva(Reserva res) throws Exception{
		baseReserva.agregarReserva(res);
				
	};
	
	public void borrarReserva(Reserva res) throws Exception{
		baseReserva.eliminarReserva(res);
	};
	
	public void modificarReserva(Reserva res) throws Exception{
		baseReserva.modificarReserva(res);
	};
	
	public ArrayList<Reserva> consultarTodo() throws Exception{
		return baseReserva.buscarTodo();
	}
	
	public ArrayList<TipoElemento> getTipoElemento(Persona pers) throws Exception
	{
		return baseTipoElemento.buscarTodo(pers);
	}
	
	public ArrayList<Elemento> getElemento() throws Exception
	{
		return baseElemento.buscarTodo();
	}

	public ArrayList<Elemento> obtenerElemento(int tipo, String fechaHoraDesde, String fechaHoraHasta) throws Exception {
		ArrayList<Elemento> listadoElementos = new ArrayList<Elemento>();
		
		try {
			listadoElementos = baseReserva.obtenerElementos(tipo,fechaHoraDesde,fechaHoraHasta);
		} catch (Exception e) {
			throw e;
		}
		
		return listadoElementos;
	}
	
	public void cancelarReserva(Reserva res) throws Exception{
		baseReserva.cancelarReserva(res);
	}


	public ArrayList<Reserva> reservasPendientesPersona(Persona pers) throws Exception{
		return baseReserva.reservasPendientesPersona(pers);
	}

}
