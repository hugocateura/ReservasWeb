package entidades;

public class TipoElemento {
	private int id;
	private String nombre;
	private int cant_max_reservas;
	private Boolean reservaEncargado;
	private int limiteMaxHorasReserva;
	private int cantMaxDiasAnticipacion;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCant_max_reservas() {
		return cant_max_reservas;
	}
	public void setCant_max_reservas(int cant_max_reservas) {
		this.cant_max_reservas = cant_max_reservas;
	}
	
	public Boolean getReservaEncargado() {
		return reservaEncargado;
	}
	public void setReservaEncargado(Boolean reservaEncargado) {
		this.reservaEncargado = reservaEncargado;
	}
	public int getLimiteMaxHorasReserva() {
		return limiteMaxHorasReserva;
	}
	public void setLimiteMaxHorasReserva(int limiteMaxHorasReserva) {
		this.limiteMaxHorasReserva = limiteMaxHorasReserva;
	}
	public int getCantMaxDiasAnticipacion() {
		return cantMaxDiasAnticipacion;
	}
	public void setCantMaxDiasAnticipacion(int cantMaxDiasAnticipacion) {
		this.cantMaxDiasAnticipacion = cantMaxDiasAnticipacion;
	}
	@Override
	public String toString()
	{
//		return	this.getNombre();
		return (this.id + " - " + this.getNombre());
	}
	
	@Override
	public boolean equals(Object tp)
	{
		return (tp instanceof TipoElemento) && ((TipoElemento) tp).getId()==(this.getId());
	}

}
