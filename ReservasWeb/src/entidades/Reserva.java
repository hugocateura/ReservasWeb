package entidades;

import java.util.Date;


import javax.print.attribute.standard.DateTimeAtCompleted;

public class Reserva{
	private int id;
	private TipoElemento tipo;
	private String fechaHoraDesde;
	private String fechaHoraHasta;
	private Elemento elemento;
	private String observacion;
	private Persona persona;
	private String estado;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public TipoElemento getTipo() {
		return tipo;
	}
	public void setTipo(TipoElemento tipo) {
		this.tipo = tipo;
	}
	public String getFechaHoraDesde() {
		return fechaHoraDesde;
	}
	public void setFechaHoraDesde(String fechaHoraDesde) {
		this.fechaHoraDesde = fechaHoraDesde;
	}
	public String getFechaHoraHasta() {
		return fechaHoraHasta;
	}
	public void setFechaHoraHasta(String fechaHoraHasta) {
		this.fechaHoraHasta = fechaHoraHasta;
	}
	public Elemento getElemento() {
		return elemento;
	}
	public void setElemento(Elemento elemento) {
		this.elemento = elemento;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
