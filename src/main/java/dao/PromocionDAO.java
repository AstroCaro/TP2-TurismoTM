package dao;

import java.util.ArrayList;
import java.util.List;

import paqueteTurismoTM.Oferta;
import paqueteTurismoTM.Promocion;


public interface PromocionDAO extends GenericDAO<Promocion>{
	public abstract ArrayList<String> listarAtraccionesIncluidas(String nombrePromo);

	public abstract List<Oferta> findAllPromosAbsolutas();
	
	public abstract List<Oferta> findAllPromosPorcentuales();
	
	public abstract List<Oferta> findAllPromosAxB();
}

