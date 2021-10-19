package paqueteTurismoTM;

public class TipoAtraccion {
	
	private int id;
	private String nombre;
	
	
	public TipoAtraccion(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}


	public int getId() {
		return id;
	}


	public String getNombre() {
		return nombre;
	}


	@Override
	public String toString() {
		return "TipoAtraccion [id=" + id + ", nombre=" + nombre + "]";
	}
	

}
