package dao;

import java.util.ArrayList;
import java.util.List;

import paqueteTurismoTM.Promocion;

public interface PromocionDAO extends GenericDAO<Promocion> {
	public abstract ArrayList<String> listarAtraccionesIncluidas(String nombrePromo);

	public abstract List<Promocion> findAllPromosAbsolutas();

	public abstract List<Promocion> findAllPromosPorcentuales();

	public abstract List<Promocion> findAllPromosAxB();
}
