package logica;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import datos.DatosElemento;
import datos.DatosReserva;
import datos.DatosTipoElemento;
import entidades.Elemento;
import entidades.Persona;
import entidades.Reserva;
import entidades.TipoElemento;
import utilidades.ExcepcionEspecial;

public class ControladorDeReserva implements Serializable{
private DatosReserva baseReserva = new DatosReserva();
private DatosTipoElemento baseTipoElemento = new DatosTipoElemento();
private DatosElemento baseElemento = new DatosElemento();
	
	public void crearReserva(Reserva res) throws Exception, ExcepcionEspecial{	
		
		String strFechaDesde = res.getFechaHoraDesde();
		String strFechaHasta = res.getFechaHoraHasta();
		
		Date fechaDesde=null;
		Date fechaHasta=null;
		Date horaBD=null;
		DatosReserva baseReserva = new DatosReserva();
		String strHoraBD = baseReserva.getHoraActual();				//OBTENGO LA HORA DE LA BD
		
		long duracion=0;
		long anticipacion=0;
		try 
			{
				fechaDesde = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(strFechaDesde);		//PARSEO LAS HORAS
				fechaHasta = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(strFechaHasta);
				horaBD = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(strHoraBD);					
				duracion = (fechaHasta.getTime() - fechaDesde.getTime())/(1000*60*60);			//CALCULO LA DURACION DE LA RESERVA
				anticipacion = (fechaDesde.getTime() - horaBD.getTime())/(1000*60*60*24);		//CALCULO LA ANTICIPACION CON RESPECTO A LA HORA ACTUAL DE LA BD
				System.out.println("mi anticipacion es de : "+anticipacion);
				System.out.println("pero solo puedo anticiparme : "+res.getTipo().getCantMaxDiasAnticipacion());
				System.out.println("la duracion es de : "+duracion);
				System.out.println("pero solo puede se de : "+res.getTipo().getLimiteMaxHorasReserva());
			}
		catch (ParseException ex) 
			{
			ex.printStackTrace();
			}
		
		if (duracion <= res.getTipo().getLimiteMaxHorasReserva()){			//VALIDO QUE LA DURACION NO EXCEDA EL MAXIMO
			if (anticipacion<res.getTipo().getCantMaxDiasAnticipacion()){
				
				try {
					baseReserva.agregarReserva(res);
				} catch (Exception e) {
					throw new ExcepcionEspecial("Error al agregar la reserva en la BD");
				}
			}
			else{
				String mensaje = ("La anticipación máxima permitida es "+res.getTipo().getCantMaxDiasAnticipacion()+"días y ud ingreso "+anticipacion+" días.");
				throw new ExcepcionEspecial(mensaje);
				}
		}
		else{
			String mensaje = ("La duración máxima es "+res.getTipo().getLimiteMaxHorasReserva()+" hs y ud. intentó reservar por "+duracion+" hs.");
			throw new ExcepcionEspecial(mensaje);
		}
		
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
