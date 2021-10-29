package dao;

import java.util.ArrayList;

import paqueteTurismoTM.Atraccion;
import paqueteTurismoTM.Promocion;

public interface PromocionDAO extends GenericDAO<Promocion> {
	public abstract ArrayList<Atraccion> listarAtraccionesIncluidas(String nombrePromo);

	public abstract Promocion findPromocionPorNombre(String nombrePromocion);
}
