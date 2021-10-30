package paqueteTurismoTM;

import java.util.ArrayList;
import java.util.Objects;

public class PromocionAxB extends Promocion {
	// supondremos que una promoción AxB solo puede tener una atracción gratis, y se
	// corresponde con el último espacio del ArrayList
	public Atraccion atraccionGratis;

	public PromocionAxB(int id_promocion, String nombre, String tipoAtraccion, ArrayList<Atraccion> atracciones) {
		super(id_promocion, nombre, tipoAtraccion, atracciones);
		this.atraccionGratis = atracciones.get(atracciones.size() - 1);
	}

	@Override
	public int getCosto() {
		costo = 0;
		for (int i = 0; i < atracciones.size() - 1; i++) {
			costo += atracciones.get(i).getCosto();
		}
		return costo;
	}

	//Se va???

//	@Override
//	public int getCuposDisponibles() {
//		int cupoDisponible = 9999;
//		for (String a : atracciones) {
//			ArrayList<Oferta> lista_atracciones = new ArrayList<Oferta>();
//			AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
//			lista_atracciones.addAll(atraccionDAO.findAll());
//			for (Oferta b : lista_atracciones) {
//				if (a.equals(b.nombre)) {
//					if (b.getCuposDisponibles() < cupoDisponible) {
//						cupoDisponible = b.getCuposDisponibles();
//					}
//				}
//			}
//		}
//		return cupoDisponible;
//	}

	@Override
	public String toString() {
		return "" + this.nombre + " contiene las siguientes atracciones de tipo" + "[" + tipoAtraccion + "]:" + "\n\t"
				+ this.getNombreAtracciones() + "\n\tSu precio total es de " + this.getCosto() + " monedas de oro."
				+ "\n\t Siendo la atracción gratis:\n\t" + this.atraccionGratis.getNombre() + "\n\tTiempo Total es de "
				+ this.getTiempo() + "hs.\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(atraccionGratis);
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
		PromocionAxB other = (PromocionAxB) obj;
		return Objects.equals(atraccionGratis, other.atraccionGratis);
	}

	
}