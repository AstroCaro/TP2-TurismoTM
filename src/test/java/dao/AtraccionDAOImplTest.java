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

public class AtraccionDAOImplTest {

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
	public void cargaDeAtraccionesTest() {
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		ArrayList<Atraccion> atraccionesReales = new ArrayList<Atraccion>();
		atraccionesReales = atraccionDAO.findAll();
		ArrayList<Atraccion> atraccionesEsperadas = new ArrayList<Atraccion>();

		Atraccion moria = new Atraccion(1, "Moria", 10, 2.0, 6, "AVENTURA");
		Atraccion bosqueNegro = new Atraccion(2, "Bosque Negro", 3, 4.0, 12, "AVENTURA");
		Atraccion mordor = new Atraccion(3, "Mordor", 25, 3.0, 4, "AVENTURA");
		Atraccion gondor = new Atraccion(4, "Gondor", 15, 5.0, 20, "AVENTURA");
		Atraccion prancingPony = new Atraccion(5, "Prancing Pony", 8, 2.0, 40, "DEGUSTACION");
		Atraccion laComarca = new Atraccion(6, "La Comarca", 3, 6.5, 150, "DEGUSTACION");
		Atraccion lothlorien = new Atraccion(7, "Lothlorien", 35, 1.0, 30, "DEGUSTACION");
		Atraccion isengard = new Atraccion(8, "Isengard", 10, 3.0, 25, "DEGUSTACION");
		Atraccion abismoDeHelm = new Atraccion(9, "Abismo de Helm", 5, 2.0, 15, "PAISAJE");
		Atraccion erebor = new Atraccion(10, "Erebor", 12, 3.0, 32, "PAISAJE");
		Atraccion minasTirith = new Atraccion(11, "Minas Tirith", 5, 2.5, 25, "PAISAJE");
		Atraccion rivendell = new Atraccion(12, "Rivendell", 20, 4.0, 9, "PAISAJE");

		atraccionesEsperadas.add(moria);
		atraccionesEsperadas.add(bosqueNegro);
		atraccionesEsperadas.add(mordor);
		atraccionesEsperadas.add(gondor);
		atraccionesEsperadas.add(prancingPony);
		atraccionesEsperadas.add(laComarca);
		atraccionesEsperadas.add(lothlorien);
		atraccionesEsperadas.add(isengard);
		atraccionesEsperadas.add(abismoDeHelm);
		atraccionesEsperadas.add(erebor);
		atraccionesEsperadas.add(minasTirith);
		atraccionesEsperadas.add(rivendell);

		System.out.println(atraccionDAO.findAll());
		System.out.println(atraccionesEsperadas);
		assertEquals(atraccionesEsperadas, atraccionesReales);

	}
	
	@Test
	public void actualizarAtraccion() {
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		Atraccion moria = new Atraccion(1, "Moria", 10, 2.0, 2, "AVENTURA");
		atraccionDAO.updateCupo(moria);
		Atraccion atraccionReal = atraccionDAO.findAtraccionPorNombre(moria.getNombre());
		
		assertEquals(moria, atraccionReal);
		
	}
	
}
