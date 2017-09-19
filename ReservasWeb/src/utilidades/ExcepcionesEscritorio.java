package utilidades;

import java.lang.Exception;
import java.lang.Throwable;

public class ExcepcionesEscritorio extends Exception {
	private Throwable originalExepcion;
	private String mensaje;
	
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public ExcepcionesEscritorio(Throwable e, String mensaje){
		this.originalExepcion = e;
		this.setMensaje(mensaje);
	}
	
	
}
