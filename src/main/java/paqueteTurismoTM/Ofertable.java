package paqueteTurismoTM;

import java.util.ArrayList;
import java.util.Collections;

public class Ofertable {

	static ArrayList<Oferta> ofertasCopia = new ArrayList<Oferta>();

	public static void ordenarOfertas(String preferencia) {

		Collections.sort(ofertasCopia, new ComparadorDeOfertas(preferencia));
	}

	public static boolean comprobarSiHayOferta(Cliente unCliente) {
		// Reiniciar la copia por cada nuevo cliente
		ofertasCopia.removeAll(ofertasCopia);
		for (Oferta unaOferta : TurismoTM.ofertas) {
			ofertasCopia.add(unaOferta);
		}
		quitarOfertasDeItinerario(unCliente);
		return (TurismoTM.ofertas != null);
	}

	private static void quitarOfertasDeItinerario(Cliente unCliente) {
		@SuppressWarnings("unchecked")
		ArrayList<Oferta> copia = (ArrayList<Oferta>) ofertasCopia.clone();
		for (Oferta unaOferta : unCliente.itinerario.ofertasCompradas) {

			if (unaOferta instanceof Promocion) {
				Promocion unaPromo = (Promocion) unaOferta;
				ArrayList<Atraccion> atraccionesCompradas = unaPromo.getAtracciones();
				for (Atraccion unaAtraccion : atraccionesCompradas) {
					for (Oferta ofertaCopia : copia) {
						if (ofertaCopia instanceof Promocion) {
							Promocion otraPromo = (Promocion) ofertaCopia;
							ArrayList<Atraccion> atraccionesIncluidas = otraPromo.getAtracciones();
							//if atraccionesIncluidas.contains(unaAtraccion) {
							// ofertasCopia.remove(ofertaCopia)
							for (Atraccion otraAtraccion : atraccionesIncluidas) {
								if (unaAtraccion.equals(otraAtraccion)) {
									ofertasCopia.remove(ofertaCopia);//se elimina toda la promocion si incluye una de las atracciones que se compraron en una Promocion
								}
							}
						} else if (unaAtraccion.equals(ofertaCopia)) {
							ofertasCopia.remove(ofertaCopia);
						}
					}
				}
			} else
				//if copia.contains(unaOferta){
				//ofertasCopia.remove(unaOferta)
				for (Oferta b : copia) {
					if (unaOferta.getNombre().equals(b.nombre)) {
						ofertasCopia.remove(b);
					}

				}
		}
	}

	public static boolean hayOfertaDisponible(Cliente unCliente) {
		// ciclo que se repite cada vez que el cliente quiera seguir comprando
		quitarOfertasQueNoPuedeComprar(unCliente);
		quitarOfertasSinCupo();
		// devuelve un booleano para saber si existe oferta para el cliente
		return (ofertasCopia.size() > 0);
	}

	public static Oferta getOferta() {
		return ofertasCopia.get(0);
	}

	public static void quitarOfertasSinCupo() {
		@SuppressWarnings("unchecked")
		ArrayList<Oferta> copia = (ArrayList<Oferta>) ofertasCopia.clone();
		for (Oferta unaOferta : copia) {
			if (unaOferta.getCuposDisponibles() <= 0) {
				ofertasCopia.remove(unaOferta);
			}
		}
	}

	public static void quitarOfertasRechazadas() {
		ofertasCopia.remove(0);
	}

	public static void quitarOfertasCompradas() {
		@SuppressWarnings("unchecked")
		ArrayList<Oferta> copia = (ArrayList<Oferta>) ofertasCopia.clone();
		if (copia.get(0) instanceof Promocion) {
			Promocion unaPromo = (Promocion) copia.get(0);
			ArrayList<Atraccion> atraccionesCompradas = unaPromo.getAtracciones();
			for (Atraccion a : atraccionesCompradas) {
				for (Oferta b : copia) {
					if (b instanceof Promocion) {
						Promocion otraPromo = (Promocion) b;
						ArrayList<Atraccion> atraccionesIncluidas = otraPromo.getAtracciones();
						//if (atraccionesIncluidas.contains(a) {
						//ofertasCopia.remove(b)
						for (Atraccion c : atraccionesIncluidas) {
							if (a.equals(c)) {
								ofertasCopia.remove(b);
							}
						}
					} else if (a.equals(b)) {
						ofertasCopia.remove(b);
					}
				}
			}
		} else
			ofertasCopia.remove(0);
	}

	public static void quitarOfertasQueNoPuedeComprar(Cliente unCliente) {
		@SuppressWarnings("unchecked")
		ArrayList<Oferta> copia = (ArrayList<Oferta>) ofertasCopia.clone();
		for (Oferta ofertaImposible : copia) {
			if (unCliente.presupuesto < ofertaImposible.getCosto()
					|| unCliente.tiempo_disponible < ofertaImposible.getTiempo()) {
				ofertasCopia.remove(ofertaImposible);
			}
		}
	}
}