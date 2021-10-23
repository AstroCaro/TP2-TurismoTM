package dao;

import paqueteTurismoTM.Atraccion;


public interface AtraccionDAO extends GenericDAO<Atraccion> {
	
	public abstract int Update(Atraccion t);
	public abstract int findIdPorNombre(Atraccion unaAtraccion);

}
