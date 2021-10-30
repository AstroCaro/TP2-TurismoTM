package paqueteTurismoTM;

import java.util.ArrayList;
import java.util.Objects;

public class PromocionPorcentual extends Promocion {

	private double descuento; // decimal

	public PromocionPorcentual(int id_promocion, String nombre, String tipoAtraccion, double descuento,
			ArrayList<Atraccion> atracciones) {
		super(id_promocion, nombre, tipoAtraccion, atracciones);
		this.descuento = descuento;
	}

	@Override
	public int getCosto() {
	costo = 0;
		for (Atraccion unaAtraccion : atracciones) {
			costo += unaAtraccion.getCosto();
		}
		return (int) (costo * (1 - descuento));
	}

	@Override
	public String toString() {
		return "" + nombre + " contiene las siguientes atracciones de tipo " + "[" + tipoAtraccion + "]:" + "\n\t"
				+ this.getNombreAtracciones()+ "\n\tSu costo total es de " + this.getCosto() + " monedas de oro"
				+ "\n\tEl tiempo total necesario es de " + this.getTiempo() + " Hs.\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(descuento);
		return result;
	}

	
	public boolean equals(Object obj) {
		if (!super.equals(obj)) {
			return false;
		}
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PromocionPorcentual other = (PromocionPorcentual) obj;
		return Double.doubleToLongBits(descuento) == Double.doubleToLongBits(other.descuento);
	}


	
}