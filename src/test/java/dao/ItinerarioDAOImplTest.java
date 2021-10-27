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
import paqueteTurismoTM.Promocion;
import paqueteTurismoTM.PromocionAbsoluta;
import paqueteTurismoTM.TurismoTM;

public class ItinerarioDAOImplTest {

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

	@SuppressWarnings("static-access")
	@Test
	public void insertarAtraccionYFindItinerarioPorClienteTest() {
		ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();
		TurismoTM turismo = new TurismoTM();

		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		turismo.atracciones.addAll(atraccionDAO.findAll());
		turismo.ofertas.addAll(atraccionDAO.findAll());

		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		turismo.ofertas.addAll(promocionDAO.findAll());

		ArrayList<Oferta> itinerarioReal = new ArrayList<Oferta>();
		Atraccion unaAtraccion = new Atraccion(12, "Rivendell", 20, 4, 9, "PAISAJE");
		itinerarioDAO.insertAtraccion(2, unaAtraccion);
		itinerarioReal = itinerarioDAO.findItinerarioPorCliente(2);

		System.out.println(itinerarioDAO.findItinerarioPorCliente(2));

		ArrayList<Oferta> itinerarioEsperado = new ArrayList<Oferta>();
		itinerarioEsperado.add(unaAtraccion);
		System.out.println(itinerarioEsperado);

		assertEquals(itinerarioEsperado, itinerarioReal);

	}

	@SuppressWarnings("static-access")
	public void insertarPromocionYFindItinerarioPorClienteTest() {
		ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();
		TurismoTM turismo = new TurismoTM();

		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		turismo.atracciones.addAll(atraccionDAO.findAll());
		turismo.ofertas.addAll(atraccionDAO.findAll());

		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		turismo.ofertas.addAll(promocionDAO.findAll());

		ArrayList<Oferta> itinerarioReal = new ArrayList<Oferta>();

		ArrayList<String> atraccionesIncluidas1 = new ArrayList<String>();

		atraccionesIncluidas1.add("La Comarca");
		atraccionesIncluidas1.add("Lothlorien");
		Promocion unaPromocion = new PromocionAbsoluta(1, "PromocionAbsoluta 1", "DEGUSTACION", 36,
				atraccionesIncluidas1);

		itinerarioDAO.insertPromocion(2, unaPromocion);

		itinerarioReal = itinerarioDAO.findItinerarioPorCliente(2);

		System.out.println(itinerarioDAO.findItinerarioPorCliente(2));

		ArrayList<Oferta> itinerarioEsperado = new ArrayList<Oferta>();
		itinerarioEsperado.add(unaPromocion);
		System.out.println(itinerarioEsperado);

		assertEquals(itinerarioEsperado, itinerarioReal);

	}

//	@Test
//	public void encontrarItinerarioPorClienteTest() {
//		ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();
//
//		
//	}

}
