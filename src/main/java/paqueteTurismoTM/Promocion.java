package paqueteTurismoTM;

import java.util.ArrayList;

public abstract class Promocion extends Oferta {
	protected int id_promocion;
	public ArrayList<String> atracciones;
	protected int costo;
	protected double tiempoTotal;
	protected int cuposDisponibles;

	public Promocion(int id_promocion, String nombre, TipoAtraccionENUM tipoAtraccion, ArrayList<String> atracciones) {
		super(nombre, tipoAtraccion);
		this.id_promocion=id_promocion;
		this.atracciones = new ArrayList<String>(atracciones);
	}

	public String getNombre() {
		return nombre;
	}

	public double getTiempo() {
		tiempoTotal = 0;
		for (String a : atracciones) {
			for (Oferta b : new LectorDeFicheros().cargarAtracciones()) {
				if (a.equals(b.nombre)) {
					tiempoTotal += b.tiempo;
				}
			}
		}
		return tiempoTotal;
	}

	public ArrayList<String> getAtracciones() {
		return this.atracciones;
	}

	public void venderCupo() {
		for (String a : atracciones) {
			for (Oferta b : TurismoTM.ofertas)
				if (a.equals(b.nombre))
					b.venderCupo();
		}
	}

	public int getCuposDisponibles() {
		int cupoDisponible = 9999;
		for (String a : atracciones) {
			for (Oferta b : TurismoTM.ofertas) {
				if (a.equals(b.nombre)) {
					if (b.getCuposDisponibles() < cupoDisponible) {
						cupoDisponible = b.getCuposDisponibles();
					}
				}
			}
		}
		return cupoDisponible;
	}

	public abstract int getCosto();
}