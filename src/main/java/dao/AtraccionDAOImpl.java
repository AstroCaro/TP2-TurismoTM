package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;
import paqueteTurismoTM.Atraccion;
import paqueteTurismoTM.Cliente;

public class AtraccionDAOImpl implements AtraccionDAO {

	@Override
	public List<Atraccion> findAll() {
		try {
			String sql = "SELECT nombre, costo, tiempo, cupos_disponibles " + "FROM atracciones "
					+ "JOIN \"tipo atraccion\" ON \"tipo atraccion\".id_tipoatraccion = atracciones.fk_tipoatraccion ";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Atraccion> atracciones = new LinkedList<Atraccion>();
			while (resultados.next()) {
				atracciones.add(toAtraccion(resultados));
			}

			return atracciones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Atraccion toAtraccion(ResultSet resultados) {
		try {
			return new Atraccion(resultados.getInt("id_atraccion"), resultados.getString("nombre"), resultados.getInt("costo"),
					resultados.getDouble("tiempo"), resultados.getInt("cupos_disponibles"), resultados.getString("tipo_atraccion"));

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int Update(Atraccion atraccion) {
		try {
			String sql = "UPDATE ATRACCIONES SET CUPOS_DISPONIBLES = ? WHERE NOMBRE = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, atraccion.getCuposDisponibles());
			statement.setInt(2, atraccion.getIdAtraccion());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

}
