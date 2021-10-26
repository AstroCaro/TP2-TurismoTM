package paqueteTurismoTM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class Cliente {
	protected int id_cliente;
	protected String nombre;
	protected String preferencia;
	protected int presupuesto;
	protected double tiempo_disponible;
	protected Itinerario itinerario;

	public Cliente(int id_cliente, String nombre, String preferencia, int presupuesto, double tiempo) {
		this.id_cliente = id_cliente;
		this.nombre = nombre;
		this.preferencia = preferencia;
		this.presupuesto = presupuesto;
		this.tiempo_disponible = tiempo;
		itinerario = new Itinerario();
	}

	public void comprarOferta(Oferta unaOferta) {
		descontarOroYTiempo(unaOferta);
		this.itinerario.agregarAlItinerario(unaOferta);

	}

	private void descontarOroYTiempo(Oferta unaOferta) {
		this.presupuesto -= unaOferta.getCosto();
		this.tiempo_disponible -= unaOferta.getTiempo();
	}

	@Override
	public String toString() {
		return "Cliente [nombre=" + nombre + ", preferencia=" + preferencia + ", presupuesto=" + presupuesto
				+ ", tiempo=" + tiempo_disponible + "]\n";
	}

	public boolean responderPregunta() throws IOException {

		String laRespuesta = null;
		boolean r = false;

		InputStreamReader sr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(sr);

		laRespuesta = br.readLine();
		laRespuesta = laRespuesta.toLowerCase();

		int intentos = 3;
		int ans = -1;

		while (intentos >= 1 && ans == -1) {
			if (laRespuesta.equals("si") || laRespuesta.equals("s")) {
				ans = 1;
				r = true;
			} else if (laRespuesta.equals("no") || laRespuesta.equals("n")) {
				ans = 0;
				r = false;
			} else if (intentos > 1) {
				System.out.println(
						".. la respuesta ingresada es incorrecta, debe contestar con 'Si' o 'No' (intentos restantes "
								+ (intentos - 1) + ")");
				laRespuesta = br.readLine();
				laRespuesta = laRespuesta.toLowerCase();
			} else {
				System.out.println(".. intentos agotados, la oferta se rechazara automaticamente .. ");
				ans = 0;
				r = false;
			}
			intentos--;
		}
		return r;
	}

	public int getPresupuesto() {
		return presupuesto;
	}

	public String getNombre() {
		return nombre;
	}

	public double getTiempo_disponible() {
		return tiempo_disponible;
	}

	public int getId_cliente() {
		return id_cliente;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_cliente, nombre, preferencia, presupuesto, tiempo_disponible);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return id_cliente == other.id_cliente && Objects.equals(nombre, other.nombre)
				&& Objects.equals(preferencia, other.preferencia) && presupuesto == other.presupuesto
				&& Double.doubleToLongBits(tiempo_disponible) == Double.doubleToLongBits(other.tiempo_disponible);
	}

	
	
}