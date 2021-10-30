package dao;

import java.util.ArrayList;

import paqueteTurismoTM.Atraccion;
import paqueteTurismoTM.Promocion;

public interface PromocionDAO extends GenericDAO<Promocion> {


	public abstract Promocion findPromocionPorNombre(String nombrePromocion, ArrayList<Atraccion> atracciones);

	public abstract ArrayList<Promocion> findAll(ArrayList<Atraccion> atracciones);

	public abstract ArrayList<Atraccion> listarAtraccionesIncluidas(String nombrePromo, ArrayList<Atraccion> atracciones);

}
