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
import paqueteTurismoTM.Ofertable;
import paqueteTurismoTM.Promocion;
import paqueteTurismoTM.PromocionAbsoluta;
import paqueteTurismoTM.PromocionAxB;
import paqueteTurismoTM.PromocionPorcentual;


public class PromocionDAOImplTest {

	Ofertable turismo;
	public ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();
	@Before
	public void setUp() throws SQLException {
		Connection conexion = ConnectionProvider.getConnection();
		conexion.setAutoCommit(false);

		turismo = new Ofertable();

		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		atracciones.addAll(atraccionDAO.findAll());
		turismo.ofertas.addAll(atracciones);
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		turismo.ofertas.addAll(promocionDAO.findAll(atracciones));

	}

	@After
	public void tearDown() throws SQLException {
		turismo.ofertas.clear();
		turismo.atracciones.clear();
		Connection conexion = ConnectionProvider.getConnection();
		conexion.rollback();
		conexion.setAutoCommit(true);
	}

	@Test
	public void cargaDePromocionesTest() {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		ArrayList<Promocion> promocionesReales = new ArrayList<Promocion>();
		promocionesReales = promocionDAO.findAll(atracciones);
		ArrayList<Promocion> promocionesEsperadas = new ArrayList<Promocion>();
		ArrayList<Atraccion> atraccionesIncluidas1 = new ArrayList<Atraccion>();
		ArrayList<Atraccion> atraccionesIncluidas2 = new ArrayList<Atraccion>();
		ArrayList<Atraccion> atraccionesIncluidas3 = new ArrayList<Atraccion>();
		ArrayList<Atraccion> atraccionesIncluidas4 = new ArrayList<Atraccion>();
		ArrayList<Atraccion> atraccionesIncluidas5 = new ArrayList<Atraccion>();
		ArrayList<Atraccion> atraccionesIncluidas6 = new ArrayList<Atraccion>();

		Atraccion moria = new Atraccion(1, "Moria", 10, 2.0, 6, "AVENTURA");
		Atraccion bosqueNegro = new Atraccion(2, "Bosque Negro", 3, 4.0, 12, "AVENTURA");
		Atraccion mordor = new Atraccion(3, "Mordor", 25, 3.0, 4, "AVENTURA");
		Atraccion gondor = new Atraccion(4, "Gondor", 15, 5.0, 20, "AVENTURA");
		Atraccion laComarca = new Atraccion(6, "La Comarca", 3, 6.5, 150, "DEGUSTACION");
		Atraccion lothlorien = new Atraccion(7, "Lothlorien", 35, 1.0, 30, "DEGUSTACION");
		Atraccion abismoDeHelm = new Atraccion(9, "Abismo de Helm", 5, 2.0, 15, "PAISAJE");
		Atraccion erebor = new Atraccion(10, "Erebor", 12, 3.0, 32, "PAISAJE");
		Atraccion minasTirith = new Atraccion(11, "Minas Tirith", 5, 2.5, 25, "PAISAJE");

		atraccionesIncluidas1.add(laComarca);
		atraccionesIncluidas1.add(lothlorien);
		atraccionesIncluidas2.add(erebor);
		atraccionesIncluidas2.add(minasTirith);
		atraccionesIncluidas3.add(bosqueNegro);
		atraccionesIncluidas3.add(mordor);
		atraccionesIncluidas4.add(moria);
		atraccionesIncluidas4.add(gondor);
		atraccionesIncluidas5.add(abismoDeHelm);
		atraccionesIncluidas5.add(erebor);
		atraccionesIncluidas5.add(minasTirith);
		atraccionesIncluidas6.add(moria);
		atraccionesIncluidas6.add(bosqueNegro);
		atraccionesIncluidas6.add(mordor);

		Promocion promocionAbsoluta1 = new PromocionAbsoluta(1, "PromocionAbsoluta 1", "DEGUSTACION", 36,
				atraccionesIncluidas1);
		Promocion promocionAbsoluta2 = new PromocionAbsoluta(2, "PromocionAbsoluta 2", "PAISAJE", 15,
				atraccionesIncluidas2);
		Promocion promocionPorcentual1 = new PromocionPorcentual(3, "PromocionPorcentual 1", "AVENTURA", 0.2,
				atraccionesIncluidas3);
		Promocion promocionPorcentual2 = new PromocionPorcentual(4, "PromocionPorcentual 2", "AVENTURA", 0.3,
				atraccionesIncluidas4);
		Promocion promocionAxB1 = new PromocionAxB(5, "PromocionAxB 1", "PAISAJE", atraccionesIncluidas5);
		Promocion promocionAxB2 = new PromocionAxB(6, "PromocionAxB 2", "AVENTURA", atraccionesIncluidas6);

		promocionesEsperadas.add(promocionAbsoluta1);
		promocionesEsperadas.add(promocionAbsoluta2);
		promocionesEsperadas.add(promocionPorcentual1);
		promocionesEsperadas.add(promocionPorcentual2);
		promocionesEsperadas.add(promocionAxB1);
		promocionesEsperadas.add(promocionAxB2);

		assertEquals(promocionesEsperadas, promocionesReales);

	}

	@Test
	public void listaAtraccionesIncluidasTest() {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		ArrayList<Atraccion> atraccionesReales = new ArrayList<Atraccion>();
		String nombrePromo = "PromocionAxB 1";
		atraccionesReales = promocionDAO.listarAtraccionesIncluidas(nombrePromo,atracciones);

		ArrayList<Atraccion> atraccionesEsperadas = new ArrayList<Atraccion>();
		atraccionesEsperadas.add(new Atraccion(9, "Abismo de Helm", 5, 2.0, 15, "PAISAJE"));
		atraccionesEsperadas.add(new Atraccion(10, "Erebor", 12, 3.0, 32, "PAISAJE"));
		atraccionesEsperadas.add(new Atraccion(11, "Minas Tirith", 5, 2.5, 25, "PAISAJE"));

		assertEquals(atraccionesEsperadas, atraccionesReales);

	}

}
