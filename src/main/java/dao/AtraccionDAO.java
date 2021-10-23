package dao;

import paqueteTurismoTM.Atraccion;

public interface AtraccionDAO extends GenericDAO<Atraccion> {

	public abstract int update(Atraccion t);

	public abstract int findIdPorNombre(String nombreAtraccion);
}
