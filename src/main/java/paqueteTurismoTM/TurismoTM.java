package paqueteTurismoTM;

import java.io.IOException;
import java.util.ArrayList;

import dao.AtraccionDAO;
import dao.ClienteDAO;
import dao.DAOFactory;
import dao.ItinerarioDAO;

public class TurismoTM {

	public ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	public ArrayList<Oferta> ofertas = new ArrayList<Oferta>();
	
	public void main(String[] args) throws IOException{

		Auxiliar aux = new Auxiliar();
		aux.cargarClientes();
		aux.cargarAtracciones();
		aux.cargarPromociones();
		
		clientes.addAll(aux.clientes);
		
		ofertas.addAll(aux.atracciones);
		ofertas.addAll(aux.promociones);
		
		sugerenciaCliente();
	}

	public void sugerenciaCliente() throws IOException {

		mensajeInicial();

		for (Cliente unCliente : clientes) {
			boolean seguirOfreciendo = true;
			Oferta unaOferta;
			mensajeBienvenida(unCliente);
			cargarItinerario(unCliente);
			if (Ofertable.comprobarSiHayOferta(unCliente)) {
				Ofertable.ordenarOfertas(unCliente.preferencia);
				while (seguirOfreciendo) {
					if (Ofertable.hayOfertaDisponible(unCliente)) {
						unaOferta = Ofertable.getOferta();
						mensajeQuieresComprarEsto(unaOferta);
						if (unCliente.responderPregunta()) {
							unCliente.comprarOferta(unaOferta);
							System.out.print("Compra exitosa!");
							Ofertable.quitarOfertasCompradas();
							unaOferta.venderCupo();
							insertarEnItineario(unCliente, unaOferta);
							actualizarCupos(unCliente,unaOferta);
							mensajeQuieresVerOtraOferta();
							seguirOfreciendo = unCliente.responderPregunta();
						} else {
							Ofertable.quitarOfertasRechazadas();
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

	private void actualizarCupos(Cliente unCliente, Oferta unaOferta) {
		ClienteDAO clienteDAO = DAOFactory.getClienteDAO();
		clienteDAO.update(unCliente);
		if (unaOferta instanceof Promocion) {
			Promocion unaPromocion = (Promocion) unaOferta;
			for (String atraccionComprada : unaPromocion.getAtracciones()) {
				for (Oferta b : ofertas) {
					if (atraccionComprada.equals(b.nombre)) {
						actualizarCupoDeAtraccion(b);
					}
				}
			}
			} else if (unaOferta instanceof Atraccion) {
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
//		LectorDeFicheros lector = new LectorDeFicheros();
//		lector.generarTicket(unCliente);
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

	private static void mensajeQuieresComprarEsto(Oferta unaOferta) {
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
	}

}