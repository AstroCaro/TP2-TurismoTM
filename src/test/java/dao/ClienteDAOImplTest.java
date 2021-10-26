package dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jdbc.ConnectionProvider;
import paqueteTurismoTM.Cliente;

public class ClienteDAOImplTest {

	@Before
	public void setUp() throws SQLException {
		Connection conexion = ConnectionProvider.getConnection();
		conexion.setAutoCommit(false);
	}

	@After
	public void tearDown() throws SQLException {
		Connection conexion = ConnectionProvider.getConnection();
		conexion.rollback();
		conexion.setAutoCommit(true);
	}

	@Test
	public void cargaDeClientesTest() {
		ClienteDAO clienteDAO = DAOFactory.getClienteDAO();
		ArrayList<Cliente> clientesReales = new ArrayList<Cliente>();
		clientesReales = clienteDAO.findAll();
		ArrayList<Cliente> clientesEsperados = new ArrayList<Cliente>();

		Cliente eowyn = new Cliente(1, "Eowyn", "AVENTURA", 10, 8.0);
		Cliente gandalf = new Cliente(2, "Gandalf", "PAISAJE", 100, 5.0);
		Cliente sam = new Cliente(3, "Sam", "DEGUSTACION", 360, 80.0);
		Cliente galadriel = new Cliente(4, "Galadriel", "PAISAJE", 120, 5.0);
		Cliente gimli = new Cliente(5, "Gimli", "AVENTURA", 250, 20.0);
		Cliente theoden = new Cliente(6, "Theoden", "PAISAJE", 50, 10.0);
		Cliente pippin = new Cliente(7, "Pippin", "DEGUSTACION", 65, 10.0);
		Cliente aragorn = new Cliente(8, "Aragorn", "AVENTURA", 200, 15.0);
		Cliente arwen = new Cliente(9, "Arwen", "PAISAJE", 60, 10.0);
		Cliente merry = new Cliente(10, "Merry", "DEGUSTACION", 40, 12.0);
		clientesEsperados.add(eowyn);
		clientesEsperados.add(gandalf);
		clientesEsperados.add(sam);
		clientesEsperados.add(galadriel);
		clientesEsperados.add(gimli);
		clientesEsperados.add(theoden);
		clientesEsperados.add(pippin);
		clientesEsperados.add(aragorn);
		clientesEsperados.add(arwen);
		clientesEsperados.add(merry);

		assertEquals(clientesEsperados, clientesReales);

	}

	@Test
	public void actualizarCliente() {
		ClienteDAO clienteDAO = DAOFactory.getClienteDAO();
		Cliente eowyn = new Cliente(1, "Eowyn", "AVENTURA", 8, 5.0);
		clienteDAO.update(eowyn);
		Cliente clienteReal = clienteDAO.findClientePorID(eowyn.getId_cliente());
		assertEquals(eowyn, clienteReal);
	}
}
