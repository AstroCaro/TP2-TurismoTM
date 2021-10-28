package paqueteTurismoTM;

import java.util.ArrayList;
import java.util.Objects;

import dao.AtraccionDAO;
import dao.DAOFactory;

public class PromocionAxB extends Promocion {

	public String atraccionGratis;
	
	public PromocionAxB(int id_promocion, String nombre, String tipoAtraccion, ArrayList<String> atracciones) {
		super(id_promocion,nombre, tipoAtraccion, atracciones);
		this.atraccionGratis = atracciones.get(atracciones.size()-1);
	}

	@Override
	public int getCosto() {
		costo = 0;
		for (int i = 0; i < atracciones.size()-1; i++) {
			ArrayList<Oferta> lista_atracciones = new ArrayList<Oferta>();
			AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
			lista_atracciones.addAll(atraccionDAO.findAll());
			for (Oferta b : lista_atracciones) {
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
			ArrayList<Oferta> lista_atracciones = new ArrayList<Oferta>();
			AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
			lista_atracciones.addAll(atraccionDAO.findAll());
			for (Oferta b : lista_atracciones) {
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