package logica;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import org.apache.logging.log4j.Level;

import datos.DatosElemento;
import datos.DatosReserva;
import datos.DatosTipoElemento;
import entidades.Elemento;
import entidades.Persona;
import entidades.Reserva;
import entidades.TipoElemento;
import utilidades.Emailer;
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
		Date fechaActual=null;
		Date horaBD=null;
		DatosReserva baseReserva = new DatosReserva();
		String strHoraBD = baseReserva.getFechaHoraActual();				//OBTENGO LA HORA DE LA BD
		
<<<<<<< HEAD
		long duracion=0;
=======
		int contador=0;
		double duracion=0;
>>>>>>> branch 'master' of https://github.com/hugocateura/ReservasWeb.git
		long anticipacion=0;
		try 
			{
				fechaDesde = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(strFechaDesde);		//PARSEO LAS HORAS
				fechaHasta = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(strFechaHasta);
				horaBD = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(strHoraBD);					
				duracion = ((double)(fechaHasta.getTime() - fechaDesde.getTime()))/(1000*60*60);			//CALCULO LA DURACION DE LA RESERVA
				anticipacion = (fechaDesde.getTime() - horaBD.getTime())/(1000*60*60*24);		//CALCULO LA ANTICIPACION CON RESPECTO A LA HORA ACTUAL DE LA BD
			}
		catch (ParseException ex) 
			{
			throw ex;
			}
		
		fechaActual = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(baseReserva.getFechaHoraActual());
		
		if (fechaActual.before(fechaDesde) && fechaActual.before(fechaHasta)) {	//VALIDO QUE LA RESERVA SEA POSTERIOR A AHORA 
			
			if (fechaHasta.after(fechaDesde)){ 	//VALIDO QUE EL FIN SEA POSTERIOR AL INICIO
				
				if (res.getTipo().getCant_max_reservas()>baseReserva.getActivasTipo(res.getTipo(),res.getPersona())){  //VALIDA QUE NO SUPERE LA CANTIDAD DE RESERVAS ACTIVAS DEL TIPO	
				
					if (duracion <= res.getTipo().getLimiteMaxHorasReserva()){			//VALIDO QUE LA DURACION NO EXCEDA EL MAXIMO
						
						if (anticipacion<res.getTipo().getCantMaxDiasAnticipacion()){		//VALIDO QUE LA ANTICIPACION NO EXCEDA EL MAXIMO
							
							try {
								baseReserva.agregarReserva(res);
							} catch (Exception e) {
								throw new ExcepcionEspecial("Error al agregar la reserva en la BD", Level.ERROR);
							}
							
							try {
								String contenidoMail = ("Reserva exitosa de:\nNombre: "+res.getPersona().getNombre()+"\nApellido: "+res.getPersona().getApellido()+"\nUsuario: "+res.getPersona().getUsuario()+"\nElemento: "+res.getElemento().getNombre()+"\nFecha Desde: "+res.getFechaHoraDesde()+"\nFecha Hasta: "+res.getFechaHoraHasta());
								Emailer.getInstance().send("tpfinaljava2017@gmail.com","Reserva de Elemento",contenidoMail);
							} catch (Exception e) {
								throw new ExcepcionEspecial("No es posible enviar el correo.", Level.ERROR);
							}
						}
						else{
							String mensaje = ("La anticipación máxima permitida es "+res.getTipo().getCantMaxDiasAnticipacion()+"días y ud ingreso "+anticipacion+" días.");
							throw new ExcepcionEspecial(mensaje, Level.ERROR);
							}
					}
					else{
						String mensaje = ("La duración máxima es "+res.getTipo().getLimiteMaxHorasReserva()+" hs y ud. intentó reservar por "+duracion+" hs.");
						throw new ExcepcionEspecial(mensaje, Level.ERROR);
					}
				}
				else {
					String mensaje = ("Se excedió la cantidad máxima de reservas del Tipo de Elemento, no se puede reservar");
					throw new ExcepcionEspecial(mensaje, Level.ERROR);
					}
			}
			else{
				String mensaje = ("La fecha de inicio no puede ser igual o anterior a la de fin");
				throw new ExcepcionEspecial(mensaje, Level.ERROR);
			}
		}
		else{
			String mensaje = ("Las fechas seleccionadas no pueden ser anteriores a la fecha actual");
			throw new ExcepcionEspecial(mensaje, Level.ERROR);
			};
	};	
	
	
	public void borrarReserva(Reserva res) throws Exception{
		baseReserva.eliminarReserva(res);
		try {
			String contenidoMail = ("Reserva:\nNombre: "+res.getPersona().getNombre()+"\nApellido: "+res.getPersona().getApellido()+"\nUsuario: "+res.getPersona().getUsuario()+"\nElemento: "+res.getElemento().getNombre()+"\nFecha Desde: "+res.getFechaHoraDesde()+"\nFecha Hasta: "+res.getFechaHoraHasta());
			Emailer.getInstance().send("tpfinaljava2017@gmail.com","Cancelacion de Reserva de Elemento",contenidoMail);
		} catch (Exception e) {
			throw new ExcepcionEspecial("No es posible enviar el correo.", Level.ERROR);
		}
		
	};
	
	public void modificarReserva(Reserva res) throws Exception{
		baseReserva.modificarReserva(res);
	};
	
	public ArrayList<Reserva> consultarTodo() throws Exception{
		
		ControladorTipoDeElemento ctrlTipo = new ControladorTipoDeElemento();
		ControladorDeElemento ctrlElemento = new ControladorDeElemento();
		ControladorDePersona ctrlPersona = new ControladorDePersona();
		
		ArrayList<Reserva> listado = baseReserva.buscarTodo();
		for (Reserva res: listado){
			res.setTipo(ctrlTipo.buscarTipoElemento(res.getTipo()));
			res.setElemento(ctrlElemento.buscarElemento(res.getElemento()));
			res.setPersona(ctrlPersona.getPersona(res.getPersona()));
		}
		
		return listado;
		
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
		try {
			String contenidoMail = ("Reserva:\nNombre: "+res.getPersona().getNombre()+"\nApellido: "+res.getPersona().getApellido()+"\nUsuario: "+res.getPersona().getUsuario()+"\nElemento: "+res.getElemento().getNombre()+"\nFecha Desde: "+res.getFechaHoraDesde()+"\nFecha Hasta: "+res.getFechaHoraHasta());
			Emailer.getInstance().send("tpfinaljava2017@gmail.com","Anulación de Reserva de Elemento",contenidoMail);
		} catch (Exception e) {
			throw new ExcepcionEspecial("No es posible enviar el correo.", Level.ERROR);
		}
		
	}


	public ArrayList<Reserva> reservasPendientesPersona(Persona pers) throws Exception{
		
		ControladorTipoDeElemento ctrlTipo = new ControladorTipoDeElemento();
		ControladorDeElemento ctrlElemento = new ControladorDeElemento();
		ControladorDePersona ctrlPersona = new ControladorDePersona();
		
		ArrayList<Reserva> listado = baseReserva.reservasPendientesPersona(pers);
		for (Reserva res: listado){
			res.setTipo(ctrlTipo.buscarTipoElemento(res.getTipo()));
			res.setElemento(ctrlElemento.buscarElemento(res.getElemento()));
			res.setPersona(ctrlPersona.getPersona(res.getPersona()));
		}
		
		return listado;
	}
	
	public Reserva getReserva(Reserva res) throws Exception{
		
		res = baseReserva.getReserva(res);
		Persona per = new Persona();
		ControladorDePersona ctrlPers = new ControladorDePersona();
		per.setId(res.getPersona().getId());
		res.setPersona(ctrlPers.getPersona(per));
		
		Elemento ele = new Elemento();
		ControladorDeElemento ctrlEle = new ControladorDeElemento();
		ele.setId(res.getElemento().getId());
		res.setElemento(ctrlEle.buscarElemento(ele));
		
		res.setTipo(ele.getTipo());		
		
		return res;
		
	}
}
