package paqueteTurismoTM;

public class Atraccion extends Oferta {

	int id_atraccion;
	private int costo;
	private int cuposDisponibles;

	public Atraccion(int id_atraccion, String nombre, int costo, double tiempo, int cuposDisponibles, String tipoAtraccion) {
		super(nombre, tipoAtraccion);
		this.id_atraccion=id_atraccion;
		this.costo = costo;
		this.tiempo = tiempo;
		this.cuposDisponibles = cuposDisponibles;
	}

	public int getIdAtraccion() {
		return id_atraccion;
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