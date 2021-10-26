package paqueteTurismoTM;

import java.util.ArrayList;

public class PromocionAbsoluta extends Promocion {

	public PromocionAbsoluta(int id_promocion,String nombre, String tipoAtraccion, int costo, ArrayList<String> atracciones) {
		super(id_promocion, nombre, tipoAtraccion, atracciones);
		this.costo = costo;
	}

	public int getCosto() {
		return costo;
	}

	@Override
	public String toString() {
		return "" + nombre + " contiene las siguientes atracciones de tipo " + "[" + tipoAtraccion + "]:"+ "\n\t"
				+ this.atracciones + "\n\tSu costo total es de " + this.getCosto() + " monedas de oro"
				+ "\n\tEl tiempo total necesario es de " + this.getTiempo() + " Hs.\n";
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}
}