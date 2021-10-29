package paqueteTurismoTM;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Promocion extends Oferta {
	protected int id_promocion;
	public ArrayList<Atraccion> atracciones;
	protected int costo;
	protected double tiempoTotal;
	protected int cuposDisponibles;

	public Promocion(int id_promocion, String nombre, String tipoAtraccion, ArrayList<Atraccion> atracciones) {
		super(nombre, tipoAtraccion);
		this.atracciones = atracciones;
		this.id_promocion = id_promocion;
	}

	public String getNombre() {
		return nombre;
	}

	public int getId_promocion() {
		return id_promocion;
	}

	public double getTiempo() {
		tiempoTotal = 0;
		for (Atraccion unaAtraccion : atracciones) {
			tiempoTotal += unaAtraccion.getTiempo();
		}
		return tiempoTotal;
	}

	public ArrayList<Atraccion> getAtracciones() {
		return this.atracciones;
	}

	public ArrayList<String> getNombreAtracciones() {
		ArrayList<String> nombreAtracciones = new ArrayList<String>();
		for (Atraccion unaAtraccion : atracciones) {
			nombreAtracciones.add(unaAtraccion.getNombre());
		}
		return nombreAtracciones;
	}

	public void venderCupo() {// se puede meter un booleano que vea que hay una promo que se quedo sin cupo
		for (Atraccion unaAtraccion : atracciones) {
			if (unaAtraccion.getCuposDisponibles() > 0)
				unaAtraccion.venderCupo();
		}
	}

	public int getCuposDisponibles() {
		int cupoDisponible = 9999;
		for (Atraccion unaAtraccion : atracciones) {
			if (unaAtraccion.getCuposDisponibles() < cupoDisponible) {
				cupoDisponible = unaAtraccion.getCuposDisponibles();
			}
		}
		return cupoDisponible;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(atracciones, costo, cuposDisponibles, id_promocion, tiempoTotal);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Promocion other = (Promocion) obj;
		return Objects.equals(atracciones, other.atracciones) && costo == other.costo
				&& cuposDisponibles == other.cuposDisponibles && id_promocion == other.id_promocion
				&& Double.doubleToLongBits(tiempoTotal) == Double.doubleToLongBits(other.tiempoTotal);
	}

	public abstract int getCosto();

}