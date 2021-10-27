package paqueteTurismoTM;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import dao.AtraccionDAO;
import dao.DAOFactory;
import dao.PromocionDAO;

@RunWith(Enclosed.class)
public class OperadorTest {
	
	public static class testConOfertasDeBaseDeDatos{ //utiliza el @Before y el @After
	TurismoTM turismo;
	@SuppressWarnings({ "unchecked", "static-access" })
	@Before
	public void setup() {
		turismo = new TurismoTM();

		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		turismo.atracciones.addAll(atraccionDAO.findAll());
		turismo.ofertas.addAll(atraccionDAO.findAll());
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		turismo.ofertas.addAll(promocionDAO.findAll());

		Ofertable.ofertasCopia = (ArrayList<Oferta>) turismo.ofertas.clone();
	}
	
	@SuppressWarnings("static-access")
	@After
	public void TearDown() {
		turismo.ofertas.clear();
		turismo.atracciones.clear();
		Ofertable.ofertasCopia.clear();
	}

	@SuppressWarnings({ "static-access", "unchecked" })
	@Test
	public void ordenarOfertasTest(){

		Cliente gimli = new Cliente(5, "Gimli", "AVENTURA", 250, 20.0);
		Ofertable.ordenarOfertas(gimli.preferencia);

		ArrayList<Oferta> ofertasOrdenadas = (ArrayList<Oferta>) Ofertable.ofertasCopia.clone();
		ArrayList<Oferta> ofertasEsperadas = new ArrayList<Oferta>();

		// Promociones de la preferencia
		ofertasEsperadas.add(turismo.ofertas.get(14));// PromocionPorcentual 1 AVENTURA Costo: 22 Tiempo: 7
		ofertasEsperadas.add(turismo.ofertas.get(15));// PromocionPorcentual 2 AVENTURA Costo: 17 Tiempo: 7
		ofertasEsperadas.add(turismo.ofertas.get(17));// PromocionAxB 2 AVENTURA Costo: 13 Tiempo:9
		// Atracciones de la preferencia
		ofertasEsperadas.add(turismo.ofertas.get(2));// Mordor AVENTURA Costo: 25 Tiempo: 3
		ofertasEsperadas.add(turismo.ofertas.get(3));// Gondor AVENTURA Costo: 15 Tiempo: 5
		ofertasEsperadas.add(turismo.ofertas.get(0));// Moria AVENTURA Costo: 10 Tiempo: 2
		ofertasEsperadas.add(turismo.ofertas.get(1));// Bosque Negro AVENTURA Costo: 3 Tiempo: 4
		// Promociones que no son de la preferencia
		ofertasEsperadas.add(turismo.ofertas.get(12));// PromocionAbsoluta1 DEGUSTACION Costo: 36 Tiempo: 7.5
		ofertasEsperadas.add(turismo.ofertas.get(16));// PromocionAxB 1 PAISAJE Costo: 17 Tiempo: 7.5
		ofertasEsperadas.add(turismo.ofertas.get(13));// PromocionAbsoluta2 PAISAJE Costo: 15 Tiempo: 5.5
		// Atracciones que no son de la preferencia
		ofertasEsperadas.add(turismo.ofertas.get(6));// Lothlorien DEGUSTACION Costo: 35 Tiempo: 1
		ofertasEsperadas.add(turismo.ofertas.get(11));// Rivendell PAISAJE Costo: 20 Tiempo: 4
		ofertasEsperadas.add(turismo.ofertas.get(9));// Erebor PAISAJE Costo: 12 Tiempo: 3
		ofertasEsperadas.add(turismo.ofertas.get(7));// Isengard DEGUSTACION Costo: 10 Tiempo: 3
		ofertasEsperadas.add(turismo.ofertas.get(4));// Prancing Pony DEGUSTACION Costo: 8 Tiempo: 2
		// Si tienen el mismo costo ordena por tiempo
		ofertasEsperadas.add(turismo.ofertas.get(10));// Minas Tirith PAISAJE Costo: 5 Tiempo: 2.5
		ofertasEsperadas.add(turismo.ofertas.get(8));// Abismo de Helm PAISAJE Costo: 5 Tiempo: 2
		ofertasEsperadas.add(turismo.ofertas.get(5));// La Comarca DEGUSTACION Costo: 3 Tiempo: 6.5

		assertEquals(ofertasEsperadas, ofertasOrdenadas);
	}
	
	@SuppressWarnings({ "unchecked" })
	@Test
	public void filtrosDeOroYTiempoTest(){		

		Cliente gimli = new Cliente(5, "Gimli", "AVENTURA", 20, 7);
		Ofertable.ordenarOfertas(gimli.preferencia);
		ArrayList<Oferta> ofertasEsperadas = (ArrayList<Oferta>) Ofertable.ofertasCopia.clone();
		Ofertable.ordenarOfertas(gimli.preferencia);
		Ofertable.hayOfertaDisponible(gimli);
		ArrayList<Oferta> ofertasReales = (ArrayList<Oferta>) Ofertable.ofertasCopia.clone();

		//El sistema ordena las ofertas 

		//Se quitan las ofertas que no puede comprar por oro o por tiempo
		//Considerando que Gimli tiene un presupuesto de 20 y 7 hs disponibles
		// Promociones de la preferencia 
		ofertasEsperadas.remove(0);// xxxxx PromocionPorcentual 1 AVENTURA Costo: 22 Tiempo: 7 xxxxx No le alcanza el presupuesto 
		// 0 PromocionPorcentual 2 AVENTURA Costo: 17 Tiempo: 7 >>>>> Puede comprar
		ofertasEsperadas.remove(1);// xxxxx PromocionAxB 2 AVENTURA Costo: 13 Tiempo:9  xxxxx No le alcanza el tiempo
		
		// Atracciones de la preferencia 
		ofertasEsperadas.remove(1);// xxxxx Mordor AVENTURA Costo: 25 Tiempo: 3 xxxxx No le alcanza el presupuesto 
		// 1 Gondor AVENTURA Costo: 15 Tiempo: 5 >>>>> Puede comprar
		// 2 Moria AVENTURA Costo: 10 Tiempo: 2 >>>>> Puede comprar
		// 3 Bosque Negro AVENTURA Costo: 3 Tiempo: 4 >>>>> Puede comprar
		
		// Promociones que no son de la preferencia
		ofertasEsperadas.remove(4);//xxxxx PromocionAbsoluta1 DEGUSTACION Costo: 36 Tiempo: 7.5 xxxxx No le alcanza el presupuesto y el tiempo
		ofertasEsperadas.remove(4);// xxxxx PromocionAxB 1 PAISAJE Costo: 17 Tiempo: 7.5 xxxxx No le alcanza el tiempo
		// 4 PromocionAbsoluta2 PAISAJE Costo: 15 Tiempo: 5.5 >>>>> Puede comprar
		
		// Atracciones que no son de la preferencia
		ofertasEsperadas.remove(5);// xxxxx Lothlorien DEGUSTACION Costo: 35 Tiempo: 1 xxxxx No le alcanza el presupuesto 
		//5 Rivendell PAISAJE Costo: 20 Tiempo: 4 >>>>> Puede comprar
		//6 Erebor PAISAJE Costo: 12 Tiempo: 3 >>>>> Puede comprar
		//7 Isengard DEGUSTACION Costo: 10 Tiempo: 3 >>>>> Puede comprar
		//8 Prancing Pony DEGUSTACION Costo: 8 Tiempo: 2>>>>> Puede comprar
		
		// Si tienen el mismo costo ordena por tiempo
		//9 Minas Tirith PAISAJE Costo: 5 Tiempo: 2.5 >>>>> Puede comprar
		//10 Abismo de Helm PAISAJE Costo: 5 Tiempo: 2
		//11 La Comarca DEGUSTACION Costo: 3 Tiempo: 6.5 >>>>> Puede comprar
		assertEquals(ofertasEsperadas, ofertasReales);
		}
	}
	
	public static class testConOtrasOfertas{//se inserta en turismo.oferta otras ofertas
	
	@SuppressWarnings({ "static-access", "unchecked" })
	@Test
	public void filtroDeOfertasSinCupoTest(){
		TurismoTM turismo = new TurismoTM();
		ArrayList<String> atraccionesIncluidas1 = new ArrayList<String>();
		ArrayList<String> atraccionesIncluidas2 = new ArrayList<String>();
		Atraccion laComarca = new Atraccion(6, "La Comarca", 3, 6.5, 0, "DEGUSTACION"); //sin cupo
		Atraccion lothlorien = new Atraccion(7, "Lothlorien", 35, 1.0, 30, "DEGUSTACION");
		Atraccion erebor = new Atraccion(10, "Erebor", 12, 3.0, 32, "PAISAJE");
		Atraccion minasTirith = new Atraccion(11, "Minas Tirith", 5, 2.5, 25, "PAISAJE");
		atraccionesIncluidas1.add("La Comarca");
		atraccionesIncluidas1.add("Lothlorien");
		atraccionesIncluidas2.add("Erebor");
		atraccionesIncluidas2.add("Minas Tirith");
		Promocion promocionAbsoluta1 = new PromocionAbsoluta(1, "PromocionAbsoluta 1", "DEGUSTACION", 36,
				atraccionesIncluidas1);//sin cupo
		Promocion promocionAbsoluta2 = new PromocionAbsoluta(2, "PromocionAbsoluta 2", "PAISAJE", 15,
				atraccionesIncluidas2);
		turismo.atracciones.add(laComarca);
		turismo.atracciones.add(lothlorien);
		turismo.atracciones.add(erebor);
		turismo.atracciones.add(minasTirith);
		turismo.ofertas.add(promocionAbsoluta1);
		turismo.ofertas.add(promocionAbsoluta2);
		turismo.ofertas.addAll(turismo.atracciones);
		Ofertable.ofertasCopia = (ArrayList<Oferta>) turismo.ofertas.clone();
		Ofertable.quitarOfertasSinCupo();
		ArrayList<Oferta> ofertasReales = (ArrayList<Oferta>) Ofertable.ofertasCopia.clone();
		ArrayList<Oferta> ofertasEsperadas = new ArrayList<Oferta>();
		ofertasEsperadas.add(promocionAbsoluta2);
		ofertasEsperadas.add(lothlorien);
		ofertasEsperadas.add(erebor);
		ofertasEsperadas.add(minasTirith);
		assertEquals(ofertasEsperadas, ofertasReales);
		
	}
	}
}
