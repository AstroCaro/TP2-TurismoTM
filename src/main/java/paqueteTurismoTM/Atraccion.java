package paqueteTurismoTM;

public class Atraccion extends Oferta {

	private int costo;
	private int cuposDisponibles;

	public Atraccion(String nombre, int costo, double tiempo, int cuposDisponibles, TipoAtraccion tipoAtraccion) {
		super(nombre, tipoAtraccion);
		this.costo = costo;
		this.tiempo = tiempo;
		this.cuposDisponibles = cuposDisponibles;
	}

	public int getCosto() {
		return costo;
	}

	public double getTiempo() {
		return tiempo;
	}

	public void venderCupo() {
		this.cuposDisponibles--;
	}

	public int getCuposDisponibles() {
		return cuposDisponibles;
	}

	@Override
	public String toString() {
		return "\nAtraccion: " + nombre + "\nCosto: " + costo + "\nDuración: " + tiempo + "\nTipo: " + tipoAtraccion
				+ "\nCupos Disponibles: " + cuposDisponibles;
	}
}