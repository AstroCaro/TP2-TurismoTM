package dao;

import java.util.ArrayList;

import paqueteTurismoTM.Atraccion;
import paqueteTurismoTM.Itinerario;
import paqueteTurismoTM.Oferta;
import paqueteTurismoTM.Promocion;


public interface ItinerarioDAO extends GenericDAO<Itinerario>{

	ArrayList<Oferta> findItinerarioPorCliente(int id_cliente);
	
	public abstract int insertPromocion(int id_cliente, Promocion unaPromocion);
	
	public abstract int insertAtraccion(int id_cliente, Atraccion unaAtraccion);


}
