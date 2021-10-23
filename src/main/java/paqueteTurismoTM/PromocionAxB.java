package paqueteTurismoTM;

import java.util.ArrayList;

public class PromocionAxB extends Promocion {

	public String atraccionGratis;
	
	public PromocionAxB(String nombre, String tipoAtraccion, ArrayList<String> atracciones) {
		super(nombre, tipoAtraccion, atracciones);
		this.atraccionGratis = atracciones.get(atracciones.size()-1);
	}


	@Override
	public int getCosto() {
		costo = 0;
		for (int i = 0; i < atracciones.size()-1; i++) {			
			for (Oferta b : TurismoTM.atracciones) {
				if (atracciones.get(i).equals(b.nombre)) {
					costo += b.getCosto();
				}
			}
		}
		return (int) costo;
	}

	
	@Override
	public int getCuposDisponibles() {
		int cupoDisponible = 9999;
		for (String a : atracciones) {
			for (Oferta b : TurismoTM.atracciones) {
				if (a.equals(b.nombre)) {
					if (b.getCuposDisponibles() < cupoDisponible) {
						cupoDisponible = b.getCuposDisponibles();
					}
				}
			}
		}
		return cupoDisponible;
	}

	@Override
	public String toString() {
		return "" + this.nombre + " contiene las siguientes atracciones de tipo" + "[" + tipoAtraccion + "]:" + "\n\t"
				+ this.atracciones + "\n\tSu precio total es de " + this.getCosto() + " monedas de oro."
				+ "\n\t Siendo la atracción gratis:\n\t" + this.atraccionGratis + "\n\tTiempo Total es de "
				+ this.getTiempo() + "hs.";
	}
}