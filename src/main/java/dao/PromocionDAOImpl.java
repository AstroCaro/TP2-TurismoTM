package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import jdbc.ConnectionProvider;
import paqueteTurismoTM.Atraccion;
import paqueteTurismoTM.Promocion;
import paqueteTurismoTM.PromocionAbsoluta;
import paqueteTurismoTM.PromocionAxB;
import paqueteTurismoTM.PromocionPorcentual;

public class PromocionDAOImpl implements PromocionDAO {

	@Override
	public ArrayList<Atraccion> listarAtraccionesIncluidas(String nombrePromo, ArrayList<Atraccion> atracciones) {
		try {
			AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
			String sql = "SELECT atracciones.nombre " + "FROM \"promocion-atraccion\" "
					+ "JOIN promociones ON \"promocion-atraccion\".fk_promocion = promociones.id_promocion "
					+ "JOIN atracciones ON \"promocion-atraccion\".fk_atraccion = atracciones.id_atraccion "
					+ "WHERE promociones.nombre = ?;";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombrePromo);
			ResultSet resultados = statement.executeQuery();
			ArrayList<Atraccion> atraccionesIncluidas = new ArrayList<Atraccion>();
			while (resultados.next()) {
				if (atracciones.contains(atraccionDAO.findAtraccionPorNombre(resultados.getString("nombre")))) {
					int indice = atracciones.indexOf(atraccionDAO.findAtraccionPorNombre(resultados.getString("nombre")));
					atraccionesIncluidas.add(atracciones.get(indice));
				}
				
			}
			return atraccionesIncluidas;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}



	private Promocion toPromocion(ResultSet resultados, ArrayList<Atraccion> atracciones) {
		try {
			Promocion unaPromocion = null;
			ArrayList<Atraccion> arrayDeAtracciones = listarAtraccionesIncluidas(resultados.getString("nombre"), atracciones);
			String tipoPromocion = resultados.getString("tipo_promocion");
			switch (tipoPromocion) {
			case "ABSOLUTA":
				unaPromocion = new PromocionAbsoluta(resultados.getInt("id_promocion"), resultados.getString("nombre"),
						resultados.getString("tipo_atraccion"), resultados.getInt("costo"), arrayDeAtracciones);
				break;
			case "PORCENTUAL":
				unaPromocion = new PromocionPorcentual(resultados.getInt("id_promocion"),
						resultados.getString("nombre"), resultados.getString("tipo_atraccion"),
						resultados.getDouble("descuento"), arrayDeAtracciones);
				break;
			case "AxB":
				unaPromocion = new PromocionAxB(resultados.getInt("id_promocion"), resultados.getString("nombre"),
						resultados.getString("tipo_atraccion"), arrayDeAtracciones);
				break;
			}
			return unaPromocion;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public ArrayList<Promocion> findAll(ArrayList<Atraccion> atracciones) {
		try {
			String sql = "SELECT id_promocion, nombre, tipo_promocion, tipo_atraccion, costo, descuento, atraccion_gratis "
					+ "FROM promociones "
					+ "JOIN \"tipo atraccion\" ON \"tipo atraccion\".id_tipoatraccion = promociones.fk_tipoatraccion "
					+ "JOIN \"tipo promocion\" ON \"tipo promocion\".id_tipopromocion = promociones.fk_tipopromocion;";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			ArrayList<Promocion> promociones = new ArrayList<Promocion>();
			while (resultados.next()) {
				promociones.add(toPromocion(resultados, atracciones));
			}

			return promociones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public Promocion findPromocionPorNombre(String nombrePromocion, ArrayList<Atraccion> atracciones) {// el nombre de las promociones es UNICO
		try {
			String sql = "SELECT id_promocion, nombre, tipo_promocion, tipo_atraccion, costo, descuento, atraccion_gratis "
					+ "FROM promociones "
					+ "JOIN \"tipo atraccion\" ON \"tipo atraccion\".id_tipoatraccion = promociones.fk_tipoatraccion "
					+ "JOIN \"tipo promocion\" ON \"tipo promocion\".id_tipopromocion = promociones.fk_tipopromocion "
					+ "WHERE nombre LIKE ? ;";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombrePromocion);
			ResultSet resultados = statement.executeQuery();

			Promocion promocion = null;
			if (resultados.next()) {
				promocion = toPromocion(resultados, atracciones);
			}
			return promocion;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}



	@Override
	public ArrayList<Promocion> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


}
