package dao;

public class DAOFactory {
	public static AtraccionDAO getAtraccionDAO() {
		return new AtraccionDAOImpl();
	}
	public static ClienteDAO getClienteDAO() {
		return new ClienteDAOImpl();
	}
	
	public static ItinerarioDAO getItinerarioDAO() {
		return new ItinerarioDAOImpl();
	}
}
