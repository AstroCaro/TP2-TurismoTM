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
import paqueteTurismoTM.Promocion;
import paqueteTurismoTM.PromocionPorcentual;

public class ItinerarioDAOImplTest {

	public ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();
	public static ArrayList<Oferta> ofertas = new ArrayList<Oferta>();
	Ofertable turismo;
	@Before
	public void setUp() throws SQLException {
		Connection conexion = ConnectionProvider.getConnection();
		conexion.setAutoCommit(false);
		turismo = new Ofertable();
		turismo.atracciones.clear();
		turismo.ofertas.clear();
	}

	@After
	public void tearDown() throws SQLException {
		turismo.atracciones.clear();
		turismo.ofertas.clear();
		Connection conexion = ConnectionProvider.getConnection();
		conexion.rollback();
		conexion.setAutoCommit(true);
	}
//
//	@Test
//	public void insertarAtraccionYFindItinerarioPorClienteTest() {
//		ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();
//		Ofertable turismo = new Ofertable(); 
//
//		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
//		turismo.atracciones.addAll(atraccionDAO.findAll());
//		turismo.ofertas.addAll(turismo.atracciones);
//
//		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
//		turismo.ofertas.addAll(promocionDAO.findAll(turismo.atracciones));
//
//		ArrayList<Oferta> itinerarioReal = new ArrayList<Oferta>();
//		Atraccion unaAtraccion = new Atraccion(12, "Rivendell", 20, 4, 9, "PAISAJE");
//		itinerarioDAO.insertAtraccion(2, unaAtraccion);
//		itinerarioReal = itinerarioDAO.findItinerarioPorCliente(2);
//
//		//System.out.println(itinerarioDAO.findItinerarioPorCliente(2));
//
//		ArrayList<Oferta> itinerarioEsperado = new ArrayList<Oferta>();
//		itinerarioEsperado.add(unaAtraccion);
//		//System.out.println(itinerarioEsperado);
//
//		assertEquals(itinerarioEsperado, itinerarioReal);
//
//	}

	@Test
	public void insertarPromocionYFindItinerarioPorClienteTest() {

		ArrayList<Oferta> itinerarioEsperado = new ArrayList<Oferta>();
		ArrayList<Oferta> itinerarioReal = new ArrayList<Oferta>();
		ArrayList<Atraccion> atraccionesIncluidas3 = new ArrayList<Atraccion>();

		ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();

		turismo.atracciones.addAll(atraccionDAO.findAll());
		turismo.ofertas.addAll(atracciones);
		turismo.ofertas.addAll(promocionDAO.findAll(turismo.atracciones));

		Atraccion bosqueNegro = turismo.atracciones.get(1);
		Atraccion mordor = turismo.atracciones.get(2);

		atraccionesIncluidas3.add(bosqueNegro);
		atraccionesIncluidas3.add(mordor);
		Oferta unaOferta = new PromocionPorcentual(3, "PromocionPorcentual 1", "AVENTURA", 0.2, atraccionesIncluidas3);

		Promocion unaPromocion = (Promocion) unaOferta;
		itinerarioEsperado.add(unaOferta);
		itinerarioDAO.insertPromocion(2, unaPromocion);
		itinerarioReal = itinerarioDAO.findItinerarioPorCliente(2);

		System.out.println(itinerarioDAO.findItinerarioPorCliente(2));
		System.out.println(itinerarioEsperado);

		assertEquals(itinerarioEsperado, itinerarioReal);

	}

}
