package paqueteTurismoTM;

import java.util.ArrayList;

public class Itinerario {
	

	public ArrayList<Oferta> ofertasCompradas;
	protected int montoTotal;
	protected double horasTotales;
	protected int idCliente;
	public Itinerario() {
		ofertasCompradas = new ArrayList<Oferta>();
	}
	public Itinerario(ArrayList<Oferta> ofertasCompradas, int montoTotal, double horasTotales, int idCliente) {
		super();
		this.ofertasCompradas = ofertasCompradas;
		this.montoTotal = montoTotal;
		this.horasTotales = horasTotales;
		this.idCliente = idCliente;
	}
	public int getMontoTotal() {
		montoTotal=0;
		for (Oferta oferta : ofertasCompradas) {
			montoTotal += oferta.getCosto();
		}
		return montoTotal;
	}

	public double getHorasTotales() {
		horasTotales=0;
		for (Oferta oferta : ofertasCompradas) {
			horasTotales += oferta.getTiempo();
		}
		return horasTotales;
	}

	public void agregarAlItinerario(Oferta unaOferta) {
		this.ofertasCompradas.add(unaOferta);
	}

	@Override
	public String toString() {
		return "Ofertas Compradas: " + "\n"+ofertasCompradas + "\nMonto Total: " + getMontoTotal() + ", Horas Totales: "
				+ getHorasTotales();
	}
}