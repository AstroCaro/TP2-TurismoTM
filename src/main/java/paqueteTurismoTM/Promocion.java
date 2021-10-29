package paqueteTurismoTM;

import java.util.ArrayList;
import java.util.Objects;

import dao.AtraccionDAO;
import dao.DAOFactory;

public abstract class Promocion extends Oferta {
	protected int id_promocion;
	public ArrayList<String> atracciones;
	protected int costo;
	protected double tiempoTotal;
	protected int cuposDisponibles;
	
	public Promocion(int id_promocion, String nombre, String tipoAtraccion, ArrayList<String> atracciones) {
		super(nombre, tipoAtraccion);
		this.atracciones = new ArrayList<String>(atracciones);
		this.id_promocion = id_promocion;
	}

	public String getNombre() {
		return nombre;
	}
	
	public int getId_promocion() {
		return id_promocion;
	}

	public double getTiempo() {
		tiempoTotal = 0;
		for (String a : atracciones) {
				ArrayList<Oferta> lista_atracciones = new ArrayList<Oferta>();
				AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
				lista_atracciones.addAll(atraccionDAO.findAll());
				for (Oferta b : lista_atracciones) {
					if (a.equals(b.nombre)) {
						tiempoTotal += b.tiempo;
					}
			}
			}
		
		return tiempoTotal;
	}
	
	

	public ArrayList<String> getAtracciones() {
		return this.atracciones;
	}

	public void venderCupo() {
		for (String a : atracciones) {
			for (Oferta b : TurismoTM.ofertas)
				if (a.equals(b.nombre))
					b.venderCupo();
		}
	}

	public int getCuposDisponibles() {
		int cupoDisponible = 9999;
		for (String a : atracciones) {
			for (Oferta b : TurismoTM.atracciones) {
				if (a.equals(b.nombre)) {
					if (b.getCuposDisponibles() < cupoDisponible) {
						cupoDisponible = b.getCuposDisponibles();
					}
				}
			}
		}
		return cupoDisponible;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(atracciones, costo, cuposDisponibles, id_promocion, tiempoTotal);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Promocion other = (Promocion) obj;
		return Objects.equals(atracciones, other.atracciones) && costo == other.costo
				&& cuposDisponibles == other.cuposDisponibles && id_promocion == other.id_promocion
				&& Double.doubleToLongBits(tiempoTotal) == Double.doubleToLongBits(other.tiempoTotal);
	}

	public abstract int getCosto();

}