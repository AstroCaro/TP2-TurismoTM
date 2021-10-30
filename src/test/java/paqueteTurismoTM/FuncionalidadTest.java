package paqueteTurismoTM;

import static org.junit.Assert.*;

import org.junit.Test;

import dao.AtraccionDAO;
import dao.DAOFactory;
import dao.PromocionDAO;
import jdbc.ConnectionProvider;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;

public class FuncionalidadTest {
	ArrayList<Oferta> ofertas;
	ArrayList<Oferta> promociones;
	ArrayList<Atraccion> atracciones;
	
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
	public void pruebaGetCostoPromociones() {
		PromocionDAO promocionDAO  = DAOFactory.getPromocionDAO();
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		promociones = new ArrayList<Oferta>();
		atracciones = new ArrayList<Atraccion>();
		atracciones.addAll(atraccionDAO.findAll());
		promociones.addAll(promocionDAO.findAll(atracciones));
		ofertas = new ArrayList<Oferta>();
		ofertas.addAll(promociones);
		assertEquals(36, ofertas.get(0).getCosto());
		assertEquals(15, ofertas.get(1).getCosto());
		assertEquals(22, ofertas.get(2).getCosto());
		assertEquals(17, ofertas.get(3).getCosto());
		assertEquals(17, ofertas.get(4).getCosto());
		assertEquals(13, ofertas.get(5).getCosto());
	}
	@Test
	public void pruebaGetTiempoPromociones() {
		PromocionDAO promocionDAO  = DAOFactory.getPromocionDAO();
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		promociones = new ArrayList<Oferta>();
		atracciones = new ArrayList<Atraccion>();
		atracciones.addAll(atraccionDAO.findAll());
		promociones.addAll(promocionDAO.findAll(atracciones));
		ofertas = new ArrayList<Oferta>();
		ofertas.addAll(promociones);
		assertEquals(7.5, ofertas.get(0).getTiempo(), 0);
		assertEquals(5.5, ofertas.get(1).getTiempo(), 0);
		assertEquals(7.0, ofertas.get(2).getTiempo(), 0);
		assertEquals(7.0, ofertas.get(3).getTiempo(), 0);
		assertEquals(7.5, ofertas.get(4).getTiempo(), 0);
		assertEquals(9.0, ofertas.get(5).getTiempo(), 0);
	}

	@Test
	public void pruebaGetCostoAtracciones() {
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		atracciones = new ArrayList<Atraccion>();
		atracciones.addAll(atraccionDAO.findAll());
		ofertas = new ArrayList<Oferta>();
		ofertas.addAll(atracciones);
		assertEquals(10, ofertas.get(0).getCosto(), 0);
		assertEquals(3, ofertas.get(1).getCosto(), 0);
		assertEquals(25, ofertas.get(2).getCosto(), 0);
		assertEquals(15, ofertas.get(3).getCosto(), 0);
		assertEquals(8, ofertas.get(4).getCosto(), 0);
		assertEquals(3, ofertas.get(5).getCosto(), 0);
		assertEquals(35, ofertas.get(6).getCosto(), 0);
		assertEquals(10, ofertas.get(7).getCosto(), 0);
		assertEquals(5, ofertas.get(8).getCosto(), 0);
		assertEquals(12, ofertas.get(9).getCosto(), 0);
		assertEquals(5, ofertas.get(10).getCosto(), 0);
		assertEquals(20, ofertas.get(11).getCosto(), 0);
	}

	@Test
	public void pruebaGetTiempoAtracciones() {
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		atracciones = new ArrayList<Atraccion>();
		atracciones.addAll(atraccionDAO.findAll());
		ofertas = new ArrayList<Oferta>();
		ofertas.addAll(atracciones);
		assertEquals(2.0, ofertas.get(0).getTiempo(), 0);
		assertEquals(4.0, ofertas.get(1).getTiempo(), 0);
		assertEquals(3.0, ofertas.get(2).getTiempo(), 0);
		assertEquals(5.0, ofertas.get(3).getTiempo(), 0);
		assertEquals(2.0, ofertas.get(4).getTiempo(), 0);
		assertEquals(6.5, ofertas.get(5).getTiempo(), 0);
		assertEquals(1.0, ofertas.get(6).getTiempo(), 0);
		assertEquals(3.0, ofertas.get(7).getTiempo(), 0);
		assertEquals(2.0, ofertas.get(8).getTiempo(), 0);
		assertEquals(3.0, ofertas.get(9).getTiempo(), 0);
		assertEquals(2.5, ofertas.get(10).getTiempo(), 0);
		assertEquals(4.0, ofertas.get(11).getTiempo(), 0);
	}
}
