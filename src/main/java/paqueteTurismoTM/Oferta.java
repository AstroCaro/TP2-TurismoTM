package paqueteTurismoTM;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(nombre, tiempo, tipoAtraccion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Oferta other = (Oferta) obj;
		return Objects.equals(nombre, other.nombre)
				&& Double.doubleToLongBits(tiempo) == Double.doubleToLongBits(other.tiempo)
				&& Objects.equals(tipoAtraccion, other.tipoAtraccion);
	}

	protected abstract int getCuposDisponibles();

	public abstract void venderCupo();

	protected abstract int getCosto();

	protected abstract double getTiempo();
}