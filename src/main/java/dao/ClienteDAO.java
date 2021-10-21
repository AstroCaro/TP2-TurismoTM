package dao;

import java.util.ArrayList;
import java.util.List;

import paqueteTurismoTM.Cliente;
import paqueteTurismoTM.Promocion;


public interface ClienteDAO extends GenericDAO<Cliente>{
	

	public int update(Cliente t);

}
