package paqueteTurismoTM;

import java.io.IOException;
import java.util.ArrayList;

import dao.ClienteDAO;
import dao.DAOFactory;

public class TurismoTM {

	/*
	 * public static ArrayList<Cliente> clientes = new ArrayList<Cliente>(); public
	 * static ArrayList<Oferta> ofertas = new ArrayList<Oferta>();
	 * 
	 * public static void main(String[] args) throws IOException {
	 * 
	 * LectorDeFicheros lector = new LectorDeFicheros(); clientes =
	 * lector.cargarClientes(); ofertas = lector.cargarAtracciones();
	 * 
	 * ofertas.addAll(lector.cargarPromocionAbsoluta());
	 * ofertas.addAll(lector.cargarPromocionAxB());
	 * ofertas.addAll(lector.cargarPromocionPorcentual());
	 * 
	 * sugerenciaCliente(); }
	 * 
	 * public static void sugerenciaCliente() throws IOException {
	 * 
	 * mensajeInicial();
	 * 
	 * for (Cliente unCliente : clientes) { boolean seguirOfreciendo = true; Oferta
	 * unaOferta; mensajeBienvenida(unCliente); if
	 * (Ofertable.comprobarSiHayOferta()) {
	 * Ofertable.ordenarOfertas(unCliente.preferencia); while (seguirOfreciendo) {
	 * if (Ofertable.hayOfertaDisponible(unCliente)) { unaOferta =
	 * Ofertable.getOferta(); mensajeQuieresComprarEsto(unaOferta); if
	 * (unCliente.responderPregunta()) { unCliente.comprarOferta(unaOferta);
	 * System.out.print("¡Compra exitosa!"); Ofertable.quitarOfertasCompradas();
	 * unaOferta.venderCupo(); mensajeQuieresVerOtraOferta(); seguirOfreciendo =
	 * unCliente.responderPregunta(); } else { Ofertable.quitarOfertasRechazadas();
	 * mensajeQuieresVerOtraOferta(); seguirOfreciendo =
	 * unCliente.responderPregunta(); } } else { mensajeNoPuedeComprarMas();
	 * seguirOfreciendo = false; } } } else { mensajeNoHayMasCupos(); }
	 * mensajeItinerario(unCliente); } mensajeFinDelPrograma(); }
	 * 
	 * private static void mensajeItinerario(Cliente unCliente) throws IOException {
	 * System.out.println(
	 * "----------------8<-------------------------------------------");
	 * System.out.println("Este será su itinerario: ");
	 * System.out.println(unCliente.itinerario); System.out.println();
	 * System.out.println(
	 * "----------------8<-------------------------------------------");
	 * LectorDeFicheros lector = new LectorDeFicheros();
	 * lector.generarTicket(unCliente); }
	 * 
	 * private static void mensajeNoPuedeComprarMas() {
	 * System.out.println("¡No puedes comprar más!"); }
	 * 
	 * private static void mensajeNoHayMasCupos() {
	 * System.out.println("Atracción sin cupo disponible, lo sentimos."); }
	 * 
	 * private static void mensajeQuieresVerOtraOferta() {
	 * System.out.println("¿Quieres ver otra oferta? S/N");
	 * 
	 * }
	 * 
	 * private static void mensajeQuieresComprarEsto(Oferta unaOferta) {
	 * System.out.println("Te recomendamos esta oferta\n" + unaOferta +
	 * "\n\n¿Quieres comprarla? S/N"); }
	 * 
	 * private static void mensajeBienvenida(Cliente unCliente) {
	 * System.out.println();
	 * 
	 * System.out.println(
	 * "<<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>>");
	 * System.out.print("Hola " + unCliente.nombre);
	 * System.out.println(", ¡Te damos la bienvenida a Turismo en la Tierra Media!"
	 * ); }
	 * 
	 * private static void mensajeInicial() { System.out.
	 * println("  .-----------------------------------------------------------------.\n"
	 * + " /  .-.                                                         .-.  \\\n"
	 * +
	 * "|  /   \\                                                       /   \\  |\n"
	 * +
	 * "| |\\_.  |                                                     |    /| |\n"
	 * +
	 * "|\\|  | /|             TURISMO EN LA TIERRA MEDIA              |\\  | |/|\n"
	 * + "| `---' |                                                     | `---' |\n"
	 * +
	 * "|       |                                                     |       | \n"
	 * + "|       |-----------------------------------------------------|       |\n"
	 * +
	 * "\\       |                                                     |       /\n"
	 * +
	 * " \\     /                                                       \\     /\n"
	 * + "  `---'                                                         `---'\n" +
	 * "");
	 * 
	 * }
	 * 
	 * private static void mensajeFinDelPrograma() {
	 * System.out.println("¡Fin del programa!");
	 * System.out.println("                  .\n" + "                 8\n" +
	 * "                8\n" + "               8'\n" + "              88\n" +
	 * "              88\n" + "              88\n" + "              88\n" +
	 * "             ,8P                                                                                                                               ...\n"
	 * +
	 * "             d8                                                                                                                              .'.' :\n"
	 * +
	 * "       .     88                            .                                                     :                 .                        .'  '''\n"
	 * +
	 * "     '.:.'   88                          '.:.'                                                   '               '.:.'                     .'\n"
	 * +
	 * "             88                                                                        ,d88888888b.       .8                              .'                d@@b.\n"
	 * +
	 * "            ,8P                                                                        8'        `Y88888888P                             .'                d@P'\n"
	 * +
	 * "   ,8888b   88                                                 ,d               ,d                                                      .'                d@P'\n"
	 * +
	 * "  ,88   \"   88,od88b.     ,d88b.  ,d88b.  ,d88b.,od88888888888888,od88888888888888.od88b.    ,d88b.  ,d88b.od88b.    ,d88b.  ,od8888888888888bo.          d@P'\n"
	 * +
	 * " ,88       ,88    `88b  ,d8  `8b,d8  `8b,d8  `8b  ,8P'  ,8P'  ,88  ,8P'  ,8P'  ,88     88  ,d8  `8b,d8  `8b    88  ,d8  `8b,'      ,dP'                  d@P'\n"
	 * +
	 * " :88      ,d88     888,d8'    888'    888'    88 ,88   ,88   d888 ,88   ,88   d888     88,d8'    888'    88    88,d8'    88      ,dP'                    d@P\n"
	 * +
	 * " `88     ,d888     888'       88      88     ,8P 88   d88   dP'88 88   d88   dP'88     88'       88      88    88'       88     ,dP'                   d@@P' d@@b.\n"
	 * +
	 * "  `88  ,d8P 88    ,888    ,  88'  ,  88'    ,8P  `88 dP`88 dP' 88 `88 dP`88 dP' 88     88        88      88    88    ,  88'    ,dP'      .        .d@@@@P'  d@P'\n"
	 * +
	 * "   `Y8888P',88  ,d8P'     `Y88    `Y88     ,8P    `Y8P' `Y8P' ,88  `Y8P' `Y8P' ,88    ,88oooooooo88oooooo88P' ,88    `Y88      88         'b               d@P'\n"
	 * +
	 * "           88'                            d8,d88b.            88'              88'    88'                     88'              88         ,8'   .d@@@@@b d@P'\n"
	 * +
	 * "           88                            d8d8  `8b            88               88     88                      88               `Yb.     ,dP'          Y@@@P\n"
	 * +
	 * "           88                           88'    ,88            88               88     88                      88                 `Y88888P'\n"
	 * +
	 * "           88                                 ,88'            88               88     88                      88\n"
	 * +
	 * "          ,8P                                ,8P'            ,8P              ,8P    ,8P                     ,8P\n"
	 * +
	 * "          d8                                ,d8'             d8               d8     d8                      d8\n"
	 * +
	 * "          88                               ,d8'              88               88     88                      88\n"
	 * +
	 * "          88                              ,d8'               88               88     88                      88\n"
	 * +
	 * "         ,8                              ,8'                ,8               ,8     ,8                      ,8\n"
	 * +
	 * "         8                              ,8'                 8                8      8                       8\n"
	 * +
	 * "        8                              ,8'                 8                8      8                       8\n"
	 * +
	 * "       \"                               \"                  \"                \"      \"                       \""
	 * ); }
	 */
	
	public static void main(String[] args) {

		/*************** APP ClienteDAO ****************/
		ClienteDAO clienteDAO = DAOFactory.getClienteDAO();
		System.out.println(clienteDAO.findAll());

	}
}