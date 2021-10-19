package paqueteTurismoTM;

public abstract class Oferta {
	protected String nombre = "";
	protected double tiempo;
	protected TipoAtraccion tipoAtraccion;

	public Oferta(String nombre, TipoAtraccion tipoAtraccion) {
		this.nombre = nombre;
		this.tipoAtraccion = tipoAtraccion;
	}

	public String getNombre() {
		return nombre;
	}

	public TipoAtraccion getTipoAtraccion() {
		return tipoAtraccion;
	}

	protected abstract int getCuposDisponibles();

	public abstract void venderCupo();

	protected abstract int getCosto();

	protected abstract double getTiempo();
}