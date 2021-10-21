package app;

import dao.ClienteDAO;
import dao.DAOFactory;

public class App {
	
	public static void main(String[] args) {

		/*************** APP ClienteDAO ****************/
		ClienteDAO clienteDAO = DAOFactory.getClienteDAO();
		System.out.println(clienteDAO.findAll());

	}

}
