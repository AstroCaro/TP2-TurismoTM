package paqueteTurismoTM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import dao.AtraccionDAO;
import dao.ClienteDAO;
import dao.DAOFactory;
import dao.ItinerarioDAO;
import dao.PromocionDAO;

public class TurismoTM {

	ArrayList<Oferta> ofertasCopia = new ArrayList<Oferta>();

	public ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	public ArrayList<Oferta> ofertas = new ArrayList<Oferta>();
	public ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();
	public ArrayList<Promocion> promociones = new ArrayList<Promocion>();
	ClienteDAO clienteDAO = DAOFactory.getClienteDAO();
	AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
	PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();

	public TurismoTM() {
		this.clientes.addAll(clienteDAO.findAll());
		this.atracciones.addAll(atraccionDAO.findAll());
		this.promociones.addAll(promocionDAO.findAll(atracciones));
		this.ofertas.addAll(atracciones);
		this.ofertas.addAll(promociones);
	}

	public ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public ArrayList<Oferta> getOfertas() {
		return ofertas;
	}

	public void sugerenciaCliente() throws IOException {

		mensajeInicial();

		for (Cliente unCliente : clientes) {
			boolean seguirOfreciendo = true;
			Oferta unaOferta;
			mensajeBienvenida(unCliente);
			cargarItinerario(unCliente);
			if (comprobarSiHayOferta(unCliente)) {
				ordenarOfertas(unCliente.preferencia);
				while (seguirOfreciendo) {
					if (hayOfertaDisponible(unCliente)) {
						unaOferta = getOferta();
						mensajeQuieresComprarEsto(unaOferta);
						if (unCliente.responderPregunta()) {
							unCliente.comprarOferta(unaOferta);
							System.out.print("Compra exitosa!");
							quitarOfertasCompradas();
							actualizarCupos(unCliente, unaOferta);
							insertarEnItineario(unCliente, unaOferta);
							mensajeQuieresVerOtraOferta();
							seguirOfreciendo = unCliente.responderPregunta();
						} else {
							quitarOfertasRechazadas();
							mensajeQuieresVerOtraOferta();
							seguirOfreciendo = unCliente.responderPregunta();
						}
					} else {
						mensajeNoPuedeComprarMas();
						seguirOfreciendo = false;
					}
				}
			} else {
				mensajeNoHayMasCupos();
			}
			mensajeItinerario(unCliente);
		}
		mensajeFinDelPrograma();

	}

	public boolean comprobarSiHayOferta(Cliente unCliente) {
		// Reiniciar la copia por cada nuevo cliente
		ofertasCopia.removeAll(ofertasCopia);
		for (Oferta unaOferta : ofertas) {
			ofertasCopia.add(unaOferta);
		}
		quitarOfertasDeItinerario(unCliente);
		return (ofertas != null);
	}

	public void ordenarOfertas(String preferencia) {
		Collections.sort(ofertasCopia, new ComparadorDeOfertas(preferencia));
	}

	public boolean hayOfertaDisponible(Cliente unCliente) {
		// ciclo que se repite cada vez que el cliente quiera seguir comprando
		quitarOfertasQueNoPuedeComprar(unCliente);
		quitarOfertasSinCupo();
		return (ofertasCopia.size() > 0);
	}

	public void quitarOfertasQueNoPuedeComprar(Cliente unCliente) {
		@SuppressWarnings("unchecked")
		ArrayList<Oferta> copia = (ArrayList<Oferta>) ofertasCopia.clone();
		for (Oferta ofertaImposible : copia) {
			if (unCliente.presupuesto < ofertaImposible.getCosto()
					|| unCliente.tiempo_disponible < ofertaImposible.getTiempo()) {
				ofertasCopia.remove(ofertaImposible);
			}
		}
	}

	public void quitarOfertasSinCupo() {
		@SuppressWarnings("unchecked")
		ArrayList<Oferta> copia = (ArrayList<Oferta>) ofertasCopia.clone();
		for (Oferta unaOferta : copia) {
			if (unaOferta.getCuposDisponibles() <= 0) {
				ofertasCopia.remove(unaOferta);
			}
		}
	}

	public Oferta getOferta() {
		return ofertasCopia.get(0);
	}

	private void quitarOfertasDeItinerario(Cliente unCliente) {
		@SuppressWarnings("unchecked")
		ArrayList<Oferta> copia = (ArrayList<Oferta>) ofertasCopia.clone();
		for (Oferta unaOferta : unCliente.itinerario.ofertasCompradas) {

			if (unaOferta instanceof Promocion) {
				Promocion unaPromo = (Promocion) unaOferta;
				ArrayList<String> atraccionesCompradas = unaPromo.getNombreAtracciones();
				for (String unaAtraccion : atraccionesCompradas) {
					quitarAtraccionesDeItinerario(copia, unaAtraccion);
				}
			} else if (unaOferta instanceof Atraccion) {
				Atraccion otraAtraccion = (Atraccion) unaOferta;
				quitarAtraccionesDeItinerario(copia, otraAtraccion.getNombre());
			}
		}
	}

	public void quitarAtraccionesDeItinerario(ArrayList<Oferta> copia, String unaAtraccion) {
		for (Oferta ofertaCopia : copia) {
			if (ofertaCopia instanceof Promocion) {
				Promocion otraPromo = (Promocion) ofertaCopia;
				ArrayList<String> atraccionesIncluidas = otraPromo.getNombreAtracciones();
				if (atraccionesIncluidas.contains(unaAtraccion)) {
					ofertasCopia.remove(ofertaCopia);
				}
			} else if (unaAtraccion.equals(ofertaCopia.getNombre())) {
				ofertasCopia.remove(ofertaCopia);
			}
		}
	}

	public void quitarOfertasCompradas() {
		@SuppressWarnings("unchecked")
		ArrayList<Oferta> copia = (ArrayList<Oferta>) ofertasCopia.clone();
		if (copia.get(0) instanceof Promocion) {
			Promocion unaPromo = (Promocion) copia.get(0);
			ArrayList<String> atraccionesCompradas = unaPromo.getNombreAtracciones();
			for (String a : atraccionesCompradas) {
				for (Oferta b : copia) {
					if (b instanceof Promocion) {
						Promocion otraPromo = (Promocion) b;
						ArrayList<String> atraccionesIncluidas = otraPromo.getNombreAtracciones();
						if (atraccionesIncluidas.contains(a)) {
							ofertasCopia.remove(b);
						}
					} else if (a.equals(b.nombre)) {
						ofertasCopia.remove(b);
					}
				}
			}
		} else
			ofertasCopia.remove(0);
	}

	public void quitarOfertasRechazadas() {
		ofertasCopia.remove(0);
	}

	private void actualizarCupos(Cliente unCliente, Oferta unaOferta) {
		ClienteDAO clienteDAO = DAOFactory.getClienteDAO();
		clienteDAO.update(unCliente);
		if (unaOferta instanceof Promocion) {
			Promocion unaPromocion = (Promocion) unaOferta;
			for (Atraccion atraccionComprada : unaPromocion.getAtracciones()) {
				for (Oferta oferta : ofertas) {
					if (oferta.getNombre().equals(atraccionComprada.getNombre())) {
						oferta.venderCupo();
						actualizarCupoDeAtraccion(oferta);
					}
				}
			}
		} else if (unaOferta instanceof Atraccion) {
			unaOferta.venderCupo();
			actualizarCupoDeAtraccion(unaOferta);
		}

	}

	private void actualizarCupoDeAtraccion(Oferta unaOferta) {
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		Atraccion unaAtraccion = (Atraccion) unaOferta;
		atraccionDAO.updateCupo(unaAtraccion);
	}

	private void cargarItinerario(Cliente unCliente) {
		ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();
		unCliente.itinerario.ofertasCompradas = itinerarioDAO.findItinerarioPorCliente(unCliente.id_cliente);

	}

	private void insertarEnItineario(Cliente unCliente, Oferta unaOferta) {
		ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();
		if (unaOferta instanceof Promocion) {
			Promocion unaPromocion = (Promocion) unaOferta;
			itinerarioDAO.insertPromocion(unCliente.id_cliente, unaPromocion);
		} else if (unaOferta instanceof Atraccion) {
			Atraccion unaAtraccion = (Atraccion) unaOferta;
			itinerarioDAO.insertAtraccion(unCliente.id_cliente, unaAtraccion);
		}

	}

	private void mensajeItinerario(Cliente unCliente) {
		System.out.println("----------------8<-------------------------------------------");
		System.out.println("Este será su itinerario: ");
		System.out.println(unCliente.itinerario);
		System.out.println();
		System.out.println("----------------8<-------------------------------------------");
	}

	private void mensajeNoPuedeComprarMas() {
		System.out.println("¡No puedes comprar más!");
	}

	private void mensajeNoHayMasCupos() {
		System.out.println("Atracción sin cupo disponible, lo sentimos.");
	}

	private void mensajeQuieresVerOtraOferta() {
		System.out.println("¿Quieres ver otra oferta? S/N");

	}

	private void mensajeQuieresComprarEsto(Oferta unaOferta) {
		System.out.println("Te recomendamos esta oferta\n" + unaOferta + "\n\n¿Quieres comprarla? S/N");
	}

	private void mensajeBienvenida(Cliente unCliente) {
		System.out.println();

		System.out.println("<<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>>");
		System.out.print("Hola " + unCliente.nombre);
		System.out.println(", ¡Te damos la bienvenida a Turismo en la Tierra Media!");
	}

	private void mensajeInicial() {
		System.out.println("  .-----------------------------------------------------------------.\n"
				+ " /  .-.                                                         .-.  \\\n"
				+ "|  /   \\                                                       /   \\  |\n"
				+ "| |\\_.  |                                                     |    /| |\n"
				+ "|\\|  | /|             TURISMO EN LA TIERRA MEDIA              |\\  | |/|\n"
				+ "| `---' |                                                     | `---' |\n"
				+ "|       |                                                     |       | \n"
				+ "|       |-----------------------------------------------------|       |\n"
				+ "\\       |                                                     |       /\n"
				+ " \\     /                                                       \\     /\n"
				+ "  `---'                                                         `---'\n" + "");

	}

	private void mensajeFinDelPrograma() {
		System.out.println("¡Fin del programa!");
		System.out.println("                  .\n" + "                 8\n" + "                8\n"
				+ "               8'\n" + "              88\n" + "              88\n" + "              88\n"
				+ "              88\n"
				+ "             ,8P                                                                                                                               ...\n"
				+ "             d8                                                                                                                              .'.' :\n"
				+ "       .     88                            .                                                     :                 .                        .'  '''\n"
				+ "     '.:.'   88                          '.:.'                                                   '               '.:.'                     .'\n"
				+ "             88                                                                        ,d88888888b.       .8                              .'                d@@b.\n"
				+ "            ,8P                                                                        8'        `Y88888888P                             .'                d@P'\n"
				+ "   ,8888b   88                                                 ,d               ,d                                                      .'                d@P'\n"
				+ "  ,88   \"   88,od88b.     ,d88b.  ,d88b.  ,d88b.,od88888888888888,od88888888888888.od88b.    ,d88b.  ,d88b.od88b.    ,d88b.  ,od8888888888888bo.          d@P'\n"
				+ " ,88       ,88    `88b  ,d8  `8b,d8  `8b,d8  `8b  ,8P'  ,8P'  ,88  ,8P'  ,8P'  ,88     88  ,d8  `8b,d8  `8b    88  ,d8  `8b,'      ,dP'                  d@P'\n"
				+ " :88      ,d88     888,d8'    888'    888'    88 ,88   ,88   d888 ,88   ,88   d888     88,d8'    888'    88    88,d8'    88      ,dP'                    d@P\n"
				+ " `88     ,d888     888'       88      88     ,8P 88   d88   dP'88 88   d88   dP'88     88'       88      88    88'       88     ,dP'                   d@@P' d@@b.\n"
				+ "  `88  ,d8P 88    ,888    ,  88'  ,  88'    ,8P  `88 dP`88 dP' 88 `88 dP`88 dP' 88     88        88      88    88    ,  88'    ,dP'      .        .d@@@@P'  d@P'\n"
				+ "   `Y8888P',88  ,d8P'     `Y88    `Y88     ,8P    `Y8P' `Y8P' ,88  `Y8P' `Y8P' ,88    ,88oooooooo88oooooo88P' ,88    `Y88      88         'b               d@P'\n"
				+ "           88'                            d8,d88b.            88'              88'    88'                     88'              88         ,8'   .d@@@@@b d@P'\n"
				+ "           88                            d8d8  `8b            88               88     88                      88               `Yb.     ,dP'          Y@@@P\n"
				+ "           88                           88'    ,88            88               88     88                      88                 `Y88888P'\n"
				+ "           88                                 ,88'            88               88     88                      88\n"
				+ "          ,8P                                ,8P'            ,8P              ,8P    ,8P                     ,8P\n"
				+ "          d8                                ,d8'             d8               d8     d8                      d8\n"
				+ "          88                               ,d8'              88               88     88                      88\n"
				+ "          88                              ,d8'               88               88     88                      88\n"
				+ "         ,8                              ,8'                ,8               ,8     ,8                      ,8\n"
				+ "         8                              ,8'                 8                8      8                       8\n"
				+ "        8                              ,8'                 8                8      8                       8\n"
				+ "       \"                               \"                  \"                \"      \"                       \"");
	}

}