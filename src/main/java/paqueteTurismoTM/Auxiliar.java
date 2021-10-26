package paqueteTurismoTM;

import java.util.ArrayList;

import dao.AtraccionDAO;
import dao.ClienteDAO;
import dao.DAOFactory;
import dao.PromocionDAO;

public class Auxiliar {

	public ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	public ArrayList<Oferta> promociones = new ArrayList<Oferta>();
	public ArrayList<Oferta> atracciones = new ArrayList<Oferta>();

	public void cargarClientes() {
		ClienteDAO clienteDAO = DAOFactory.getClienteDAO();
		clientes.addAll(clienteDAO.findAll());
	}

	public void cargarAtracciones() {
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		atracciones.addAll(atraccionDAO.findAll());
		
	}

	public void cargarPromociones() {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		promociones.addAll(promocionDAO.findAll());
	}

}
