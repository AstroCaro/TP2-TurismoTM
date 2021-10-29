package paqueteTurismoTM;

import java.util.ArrayList;
import java.util.Objects;

import dao.AtraccionDAO;
import dao.DAOFactory;

public class PromocionPorcentual extends Promocion {

	private double descuento; // decimal

	public PromocionPorcentual(int id_promocion, String nombre, String tipoAtraccion, double descuento,
			ArrayList<String> atracciones) {
		super(id_promocion, nombre, tipoAtraccion, atracciones);
		this.descuento = descuento;
	}

	@Override
	public int getCosto() {
	costo = 0;
		for (String a : atracciones) {
			ArrayList<Oferta> lista_atracciones = new ArrayList<Oferta>();
			AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
			lista_atracciones.addAll(atraccionDAO.findAll());
			for (Oferta b : lista_atracciones) {
				if (a.equals(b.nombre)) {
					costo += b.getCosto();
				}
			}
		}
		return (int) (costo * (1 - descuento));
	}

	@Override
	public String toString() {
		return "" + nombre + " contiene las siguientes atracciones de tipo " + "[" + tipoAtraccion + "]:" + "\n\t"
				+ atracciones + "\n\tSu costo total es de " + this.getCosto() + " monedas de oro"
				+ "\n\tEl tiempo total necesario es de " + this.getTiempo() + " Hs.\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(descuento);
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
		PromocionPorcentual other = (PromocionPorcentual) obj;
		return Double.doubleToLongBits(descuento) == Double.doubleToLongBits(other.descuento);
	}
	
	
}