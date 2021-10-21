package paqueteTurismoTM;

public abstract class Oferta {
	protected String nombre = "";
	protected double tiempo;
	protected TipoAtraccionENUM tipoAtraccion;

	public Oferta(String nombre, TipoAtraccionENUM tipoAtraccion) {
		this.nombre = nombre;
		this.tipoAtraccion = tipoAtraccion;
	}

	public String getNombre() {
		return nombre;
	}

	public TipoAtraccionENUM getTipoAtraccion() {
		return tipoAtraccion;
	}

	protected abstract int getCuposDisponibles();

	public abstract void venderCupo();

	protected abstract int getCosto();

	protected abstract double getTiempo();
}