package dao;

import paqueteTurismoTM.Cliente;

public interface ClienteDAO extends GenericDAO<Cliente> {

	public abstract int update(Cliente t);

	public abstract Cliente findClientePorID(int id_cliente);

}
