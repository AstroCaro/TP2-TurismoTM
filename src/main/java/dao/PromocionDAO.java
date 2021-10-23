package dao;

import java.util.ArrayList;

import paqueteTurismoTM.Promocion;

public interface PromocionDAO extends GenericDAO<Promocion> {
	public abstract ArrayList<String> listarAtraccionesIncluidas(String nombrePromo);

	public abstract ArrayList<Promocion> findAllPromosAbsolutas();

	public abstract ArrayList<Promocion> findAllPromosPorcentuales();

	public abstract ArrayList<Promocion> findAllPromosAxB();
	
	public abstract int findIdPorNombre(String nombrePromocion);
}
