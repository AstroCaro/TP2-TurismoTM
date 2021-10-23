package paqueteTurismoTM;

public abstract class Oferta {
	protected String nombre = "";
	protected double tiempo;
	protected String tipoAtraccion;

	public Oferta(String nombre, String tipoAtraccion) {
		this.nombre = nombre;
		this.tipoAtraccion = tipoAtraccion;
	}

	public String getNombre() {
		return nombre;
	}

	public String getTipoAtraccion() {
		return this.tipoAtraccion;
	}

	protected abstract int getCuposDisponibles();

	public abstract void venderCupo();

	protected abstract int getCosto();

	protected abstract double getTiempo();
}