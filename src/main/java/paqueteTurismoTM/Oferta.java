package paqueteTurismoTM;

public abstract class Oferta {
	protected String nombre = "";
	protected double tiempo;
	protected String tipoAtraccion;

	public Oferta(String nombre, String tipoAtraccion2) {
		this.nombre = nombre;
		this.tipoAtraccion = tipoAtraccion2;
	}

	public String getNombre() {
		return nombre;
	}

	public String getTipoAtraccion() {
		return tipoAtraccion;
	}

	protected abstract int getCuposDisponibles();

	public abstract void venderCupo();

	protected abstract int getCosto();

	protected abstract double getTiempo();
}