package dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jdbc.ConnectionProvider;
import paqueteTurismoTM.Promocion;
import paqueteTurismoTM.PromocionAbsoluta;
import paqueteTurismoTM.PromocionAxB;
import paqueteTurismoTM.PromocionPorcentual;

public class PromocionDAOImplTest {

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
	public void cargaDePromocionesTest() {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		ArrayList<Promocion> promocionesReales = new ArrayList<Promocion>();
		promocionesReales = promocionDAO.findAll();
		ArrayList<Promocion> promocionesEsperadas = new ArrayList<Promocion>();
		ArrayList<String> atraccionesIncluidas1 = new ArrayList<String>();
		ArrayList<String> atraccionesIncluidas2 = new ArrayList<String>();
		ArrayList<String> atraccionesIncluidas3 = new ArrayList<String>();
		ArrayList<String> atraccionesIncluidas4 = new ArrayList<String>();
		ArrayList<String> atraccionesIncluidas5 = new ArrayList<String>();
		ArrayList<String> atraccionesIncluidas6 = new ArrayList<String>();
		
		atraccionesIncluidas1.add("La Comarca");
		atraccionesIncluidas1.add("Lothlorien");
		atraccionesIncluidas2.add("Erebor");
		atraccionesIncluidas2.add("Minas Tirith");
		atraccionesIncluidas3.add("Bosque Negro");
		atraccionesIncluidas3.add("Mordor");
		atraccionesIncluidas4.add("Moria");
		atraccionesIncluidas4.add("Gondor");
		atraccionesIncluidas5.add("Abismo de Helm");
		atraccionesIncluidas5.add("Erebor");
		atraccionesIncluidas5.add("Minas Tirith");
		atraccionesIncluidas6.add("Moria");
		atraccionesIncluidas6.add("Bosque Negro");
		atraccionesIncluidas6.add("Mordor");

		Promocion promocionAbsoluta1 = new PromocionAbsoluta(1, "PromocionAbsoluta 1","DEGUSTACION", 36, atraccionesIncluidas1);
		Promocion promocionAbsoluta2 = new PromocionAbsoluta(2, "PromocionAbsoluta 2", "PAISAJE", 15, atraccionesIncluidas2);
		Promocion promocionPorcentual1 = new PromocionPorcentual(3, "PromocionPorcentual 1", "AVENTURA", 0.2, atraccionesIncluidas3);
		Promocion promocionPorcentual2 = new PromocionPorcentual(4, "PromocionPorcentual 2", "AVENTURA", 0.3, atraccionesIncluidas4);
		Promocion promocionAxB1= new PromocionAxB(5, "PromocionAxB 1", "PAISAJE", atraccionesIncluidas5);
		Promocion promocionAxB2 = new PromocionAxB(6, "PromocionAxB 2", "AVENTURA", atraccionesIncluidas6);

		promocionesEsperadas.add(promocionAbsoluta1);
		promocionesEsperadas.add(promocionAbsoluta2);
		promocionesEsperadas.add(promocionPorcentual1);
		promocionesEsperadas.add(promocionPorcentual2);
		promocionesEsperadas.add(promocionAxB1);
		promocionesEsperadas.add(promocionAxB2);

		assertEquals(promocionesEsperadas, promocionesReales);

	}
}
