package dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jdbc.ConnectionProvider;
import paqueteTurismoTM.Atraccion;
import paqueteTurismoTM.Oferta;
import paqueteTurismoTM.Ofertable;

public class ItinerarioDAOImplTest {

	public ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();
	public ArrayList<Atraccion> promociones = new ArrayList<Atraccion>();
	public static ArrayList<Oferta> ofertas = new ArrayList<Oferta>();
	Ofertable turismo;

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
	public void insertarAtraccionYFindItinerarioPorClienteTest() {

		turismo = new Ofertable();

		ArrayList<Oferta> itinerarioReal = new ArrayList<Oferta>();
		ArrayList<Oferta> itinerarioEsperado = new ArrayList<Oferta>();

		ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();

		turismo.atracciones.addAll(atraccionDAO.findAll());
		turismo.ofertas.addAll(turismo.atracciones);

		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		turismo.ofertas.addAll(promocionDAO.findAll(turismo.atracciones));

		Atraccion unaAtraccion = new Atraccion(12, "Rivendell", 20, 4, 9, "PAISAJE");
		itinerarioDAO.insertAtraccion(2, unaAtraccion);
		itinerarioReal = itinerarioDAO.findItinerarioPorCliente(2);

		System.out.println(itinerarioDAO.findItinerarioPorCliente(2));

		itinerarioEsperado.add(unaAtraccion);
		System.out.println(itinerarioEsperado);

		assertEquals(itinerarioEsperado, itinerarioReal);

	}

}
