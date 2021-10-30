package paqueteTurismoTM;

import java.util.ArrayList;

public class PromocionAbsoluta extends Promocion {

	public PromocionAbsoluta(int id_promocion,String nombre, String tipoAtraccion, int costo, ArrayList<Atraccion> atracciones) {
		super(id_promocion, nombre, tipoAtraccion, atracciones);
		this.costo = costo;
	}
	
	@Override
	public int getCosto() {
		return costo;
	}

	@Override
	public String toString() {
		return "" + nombre + " contiene las siguientes atracciones de tipo " + "[" + tipoAtraccion + "]:"+ "\n\t"
				+ this.getAtracciones() + "\n\tSu costo total es de " + this.getCosto() + " monedas de oro"
				+ "\n\tEl tiempo total necesario es de " + this.getTiempo() + " Hs.\n";
	}

	@Override
	public int hashCode() {
		return super.hashCode();
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
		return true;
	}

}