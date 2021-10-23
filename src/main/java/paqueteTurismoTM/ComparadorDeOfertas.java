package paqueteTurismoTM;

import java.util.Comparator;

public class ComparadorDeOfertas implements Comparator<Oferta> {

	private String preferencia;

	public ComparadorDeOfertas(String preferencia) {
		this.preferencia = preferencia;
	}

	public int compare(Oferta oferta, Oferta otraOferta) {
		// de las dos ofertas que se comparan solo una es de la preferencia
		if (this.preferencia.equals(oferta.getTipoAtraccion()) && !this.preferencia.equals(otraOferta.getTipoAtraccion()))
			return -1;
		else if (!this.preferencia.equals(oferta.getTipoAtraccion()) && this.preferencia.equals(otraOferta.getTipoAtraccion()))
			return 1;
		// ambas son de preferencia del cliente o ninguna es de la preferencia del
		// cliente
		else if (oferta instanceof Promocion && otraOferta instanceof Atraccion)
			return -1;
		else if (oferta instanceof Atraccion && otraOferta instanceof Promocion)
			return 1;
		// ambas son Promociones/Atraccion
		else if (oferta.getCosto() > otraOferta.getCosto())
			return -1;
		else if (oferta.getCosto() < otraOferta.getCosto())
			return 1;
		else
			// ambas tienen el mismo Costo
			return -1 * Double.compare(oferta.getTiempo(), otraOferta.getTiempo());
	}
}