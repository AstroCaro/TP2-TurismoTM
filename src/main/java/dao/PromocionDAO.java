package dao;

import java.util.ArrayList;

import paqueteTurismoTM.Promocion;

public interface PromocionDAO extends GenericDAO<Promocion> {
	public abstract ArrayList<String> listarAtraccionesIncluidas(String nombrePromo);

	public abstract Promocion findPromocionPorNombre(String nombrePromocion);
}
