package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import jdbc.ConnectionProvider;
import paqueteTurismoTM.Promocion;
import paqueteTurismoTM.PromocionAbsoluta;
import paqueteTurismoTM.PromocionAxB;
import paqueteTurismoTM.PromocionPorcentual;

public class PromocionDAOImpl implements PromocionDAO {

	@Override
	public ArrayList<String> listarAtraccionesIncluidas(String nombrePromo) {
		try {
			String sql = "SELECT atracciones.nombre " + "FROM \"promocion-atraccion\" "
					+ "JOIN promociones ON \"promocion-atraccion\".fk_promocion = promociones.id_promocion "
					+ "JOIN atracciones ON \"promocion-atraccion\".fk_atraccion = atracciones.id_atraccion "
					+ "WHERE promociones.nombre = ?;";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombrePromo);
			ResultSet resultados = statement.executeQuery();
			ArrayList<String> atraccionesIncluidas = new ArrayList<>();
			while (resultados.next()) {
				atraccionesIncluidas.add(resultados.getString("nombre"));
			}
			return atraccionesIncluidas;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public ArrayList<Promocion> findAllPromosAbsolutas() {
		try {
			String sql = "SELECT nombre, tipo_atraccion, costo " + "FROM promociones "
					+ "JOIN \"tipo atraccion\" ON \"tipo atraccion\".id_tipoatraccion = promociones.fk_tipoatraccion "
					+ "JOIN \"tipo promocion\" ON \"tipo promocion\".id_tipopromocion = promociones.fk_tipopromocion "
					+ "WHERE \"tipo promocion\".tipo_promocion='ABSOLUTA';";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			ArrayList<Promocion> promociones = new ArrayList<Promocion>();
			while (resultados.next()) {
				promociones.add(toPromocionAbsoluta(resultados));
			}

			return promociones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public ArrayList<Promocion> findAllPromosPorcentuales() {
		try {
			String sql = "SELECT nombre, tipo_atraccion, descuento " + "FROM promociones "
					+ "JOIN \"tipo atraccion\" ON \"tipo atraccion\".id_tipoatraccion = promociones.fk_tipoatraccion "
					+ "JOIN \"tipo promocion\" ON \"tipo promocion\".id_tipopromocion = promociones.fk_tipopromocion "
					+ "WHERE \"tipo promocion\".tipo_promocion='PORCENTUAL';";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			ArrayList<Promocion> promociones = new ArrayList<Promocion>();
			while (resultados.next()) {
				promociones.add(toPromocionPorcentuales(resultados));
			}

			return promociones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public ArrayList<Promocion> findAllPromosAxB() {
		try {
			String sql = "SELECT nombre, tipo_atraccion " + "FROM promociones "
					+ "JOIN \"tipo atraccion\" ON \"tipo atraccion\".id_tipoatraccion = promociones.fk_tipoatraccion "
					+ "JOIN \"tipo promocion\" ON \"tipo promocion\".id_tipopromocion = promociones.fk_tipopromocion "
					+ "WHERE \"tipo promocion\".tipo_promocion='AxB';";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			ArrayList<Promocion> promociones = new ArrayList<Promocion>();
			while (resultados.next()) {
				promociones.add(toPromocionAxB(resultados));
			}

			return promociones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Promocion toPromocionAbsoluta(ResultSet resultados) {
		try {
			ArrayList<String> arrayDeAtracciones = listarAtraccionesIncluidas(resultados.getString("nombre"));
			return new PromocionAbsoluta(resultados.getString("nombre"), resultados.getString("tipo_atraccion"),
					resultados.getInt("costo"), arrayDeAtracciones);
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Promocion toPromocionPorcentuales(ResultSet resultados) {
		try {
			ArrayList<String> arrayDeAtracciones = listarAtraccionesIncluidas(resultados.getString("nombre"));
			return new PromocionPorcentual(resultados.getString("nombre"), resultados.getString("tipo_atraccion"),
					resultados.getDouble("descuento"), arrayDeAtracciones);
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Promocion toPromocionAxB(ResultSet resultados) {
		try {
			ArrayList<String> arrayDeAtracciones = listarAtraccionesIncluidas(resultados.getString("nombre"));
			return new PromocionAxB(resultados.getString("nombre"), resultados.getString("tipo_atraccion"),
					arrayDeAtracciones);
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public ArrayList<Promocion> findAll() {
		ArrayList<Promocion> promociones = new ArrayList<Promocion>();
		promociones.addAll(findAllPromosAbsolutas());
		promociones.addAll(findAllPromosPorcentuales());
		promociones.addAll(findAllPromosAxB());
		return promociones;
	}

	public int findIdPorNombre(Promocion unaPromocion) {
		try {

			String sql = "SELECT id_promocion " + "FROM promociones " + "WHERE nombre LIKE ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();
			int id_promocion = 0;
			if (resultados.next()) {
				id_promocion = resultados.getInt("id_promocion");
			}

			return id_promocion;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

}
