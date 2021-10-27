package paqueteTurismoTM;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import dao.AtraccionDAO;
import dao.DAOFactory;
import dao.PromocionDAO;

public class OperadorTest {

//	@SuppressWarnings({ "static-access", "unchecked" })
//	@Test
//	public void ordenarOfertasTest(){
//		TurismoTM turismo = new TurismoTM();
//		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
//		turismo.atracciones.addAll(atraccionDAO.findAll());
//		turismo.ofertas.addAll(atraccionDAO.findAll());
//		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
//		turismo.ofertas.addAll(promocionDAO.findAll());
//		System.out.println(turismo.ofertas);
//		Ofertable.ofertasCopia = (ArrayList<Oferta>) turismo.ofertas.clone();
//
//		Cliente gimli = new Cliente(5, "Gimli", "AVENTURA", 250, 20.0);
//		Ofertable.ordenarOfertas(gimli.preferencia);
//
//		ArrayList<Oferta> ofertasOrdenadas = (ArrayList<Oferta>) Ofertable.ofertasCopia.clone();
//		ArrayList<Oferta> ofertasEsperadas = new ArrayList<Oferta>();
//		System.out.println(ofertasOrdenadas);
//		// Promociones de la preferencia
//		ofertasEsperadas.add(turismo.ofertas.get(14));// PromocionPorcentual 1 AVENTURA Costo: 22 Tiempo: 7
//		ofertasEsperadas.add(turismo.ofertas.get(15));// PromocionPorcentual 2 AVENTURA Costo: 17 Tiempo: 7
//		ofertasEsperadas.add(turismo.ofertas.get(17));// PromocionAxB 2 AVENTURA Costo: 13 Tiempo:9
//		// Atracciones de la preferencia
//		ofertasEsperadas.add(turismo.ofertas.get(2));// Mordor AVENTURA Costo: 25 Tiempo: 3
//		ofertasEsperadas.add(turismo.ofertas.get(3));// Gondor AVENTURA Costo: 15 Tiempo: 5
//		ofertasEsperadas.add(turismo.ofertas.get(0));// Moria AVENTURA Costo: 10 Tiempo: 2
//		ofertasEsperadas.add(turismo.ofertas.get(1));// Bosque Negro AVENTURA Costo: 3 Tiempo: 4
//		// Promociones que no son de la preferencia
//		ofertasEsperadas.add(turismo.ofertas.get(12));// PromocionAbsoluta1 DEGUSTACION Costo: 36 Tiempo: 7.5
//		ofertasEsperadas.add(turismo.ofertas.get(16));// PromocionAxB 1 PAISAJE Costo: 17 Tiempo: 7.5
//		ofertasEsperadas.add(turismo.ofertas.get(13));// PromocionAbsoluta2 PAISAJE Costo: 15 Tiempo: 5.5
//		// Atracciones que no son de la preferencia
//		ofertasEsperadas.add(turismo.ofertas.get(6));// Lothlorien DEGUSTACION Costo: 35 Tiempo: 1
//		ofertasEsperadas.add(turismo.ofertas.get(11));// Rivendell PAISAJE Costo: 20 Tiempo: 4
//		ofertasEsperadas.add(turismo.ofertas.get(9));// Erebor PAISAJE Costo: 12 Tiempo: 3
//		ofertasEsperadas.add(turismo.ofertas.get(7));// Isengard DEGUSTACION Costo: 10 Tiempo: 3
//		ofertasEsperadas.add(turismo.ofertas.get(4));// Prancing Pony DEGUSTACION Costo: 8 Tiempo: 2
//		// Si tienen el mismo costo ordena por tiempo
//		ofertasEsperadas.add(turismo.ofertas.get(10));// Minas Tirith PAISAJE Costo: 5 Tiempo: 2.5
//		ofertasEsperadas.add(turismo.ofertas.get(8));// Abismo de Helm PAISAJE Costo: 5 Tiempo: 2
//		ofertasEsperadas.add(turismo.ofertas.get(5));// La Comarca DEGUSTACION Costo: 3 Tiempo: 6.5
//
//		assertEquals(ofertasEsperadas, ofertasOrdenadas);
//	}

	@SuppressWarnings({ "unchecked", "static-access" })
	@Test
	public void filtrosTest(){		
		TurismoTM turismo = new TurismoTM();
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		turismo.atracciones.addAll(atraccionDAO.findAll());
		turismo.ofertas.addAll(atraccionDAO.findAll());
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		turismo.ofertas.addAll(promocionDAO.findAll());
		Ofertable.ofertasCopia = (ArrayList<Oferta>) turismo.ofertas.clone();

		Cliente gimli = new Cliente(5, "Gimli", "AVENTURA", 20, 7);
		Ofertable.ordenarOfertas(gimli.preferencia);

		ArrayList<Oferta> ofertasEsperadas = (ArrayList<Oferta>) Ofertable.ofertasCopia.clone();
		ArrayList<Oferta> ofertasReales = Ofertable.ofertasCopia;
			
		//El sistema ordena las ofertas 
		Ofertable.ordenarOfertas(gimli.preferencia);

		//Se quitan las ofertas que no puede comprar por oro o por tiempo
		Ofertable.hayOfertaDisponible(gimli);
		
		System.out.println(Ofertable.ofertasCopia);
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
		
//		// Si tienen el mismo costo ordena por tiempo
		//9 Minas Tirith PAISAJE Costo: 5 Tiempo: 2.5 >>>>> Puede comprar
		//10 Abismo de Helm PAISAJE Costo: 5 Tiempo: 2
		//11 La Comarca DEGUSTACION Costo: 3 Tiempo: 6.5 >>>>> Puede comprar
		assertEquals(ofertasEsperadas, ofertasReales);
		}
	}

