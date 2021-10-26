package paqueteTurismoTM;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class ItinerarioTest {

	@SuppressWarnings("static-access")
	@Test
	public void test() throws IOException {
		TurismoTM turismo = new TurismoTM();
		turismo.main(null);
		System.out.println(turismo.ofertas);
//		Cliente cliente = new Cliente("Pippin", TipoAtraccion.AVENTURA, 20, 12);
//		ofertas = this.cargarPromocionAbsoluta();
//		ofertas.addAll(this.cargarAtraccion());
//		Ofertable.ofertasCopia = (ArrayList<Oferta>) ofertas.clone();
//
//		Ofertable.ordenarOfertas(cliente.preferencia);
	}

}
