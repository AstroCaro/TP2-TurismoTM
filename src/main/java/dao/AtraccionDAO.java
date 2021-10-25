package dao;

import paqueteTurismoTM.Atraccion;

public interface AtraccionDAO extends GenericDAO<Atraccion> {

	public abstract int updateCupo(Atraccion t);

	public abstract Atraccion findAtraccionPorNombre(String nombreAtraccion);
}
