package app;

import dao.ClienteDAO;
import dao.DAOFactory;
import dao.PromocionDAO;

public class App {
	public static void main(String[] args) {
		/*************** APP PromocionDAO ****************/
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		String promo = "PromocionAbsoluta 1";
		System.out.println(promocionDAO.listarAtraccionesIncluidas(promo));
		System.out.println(promocionDAO.findAllPromosAbsolutas());
		System.out.println(promocionDAO.findAllPromosPorcentuales());
		System.out.println(promocionDAO.findAllPromosAxB());
		System.out.println(promocionDAO.findAll());
		
		/*************** APP ClienteDAO ****************/
		ClienteDAO clienteDAO = DAOFactory.getClienteDAO();

		System.out.println(clienteDAO.findAll());
		
	}
	
}
