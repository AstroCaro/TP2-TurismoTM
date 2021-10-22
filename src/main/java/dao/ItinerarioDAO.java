package dao;

import java.util.List;

import paqueteTurismoTM.Itinerario;
import paqueteTurismoTM.Oferta;
import paqueteTurismoTM.Promocion;

public interface ItinerarioDAO extends GenericDAO<Itinerario>{

	List<Oferta> findItinerarioPorCliente(String cliente);


}
