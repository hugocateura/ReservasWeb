package entidades;

public class Elemento {
	private int id;
	private String nombre;
	private TipoElemento tipo;
	
	public Elemento(){}
	
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
	public TipoElemento getTipo() {
		return tipo;
	}
	public void setTipo(TipoElemento tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public boolean equals(Object e)
	{
		return (e instanceof Elemento) && ((((Elemento) e).getNombre().equals(this.getNombre())));
	}
	
	@Override
	public String toString()
	{
//		return	this.getNombre();
		return (this.id + " - " + this.getNombre());
	}
	
	
	
}
