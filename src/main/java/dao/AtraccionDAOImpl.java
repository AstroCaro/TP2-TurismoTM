package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import jdbc.ConnectionProvider;
import paqueteTurismoTM.Atraccion;


public class AtraccionDAOImpl implements AtraccionDAO {

	@Override
	public ArrayList<Atraccion> findAll() {
		try {
			String sql = "SELECT nombre, costo, tiempo, cupos_disponibles " + "FROM atracciones "
					+ "JOIN \"tipo atraccion\" ON \"tipo atraccion\".id_tipoatraccion = atracciones.fk_tipoatraccion ";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();
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
			return new Atraccion(resultados.getString("nombre"),
					resultados.getInt("costo"), resultados.getDouble("tiempo"), resultados.getInt("cupos_disponibles"),
					resultados.getString("tipo_atraccion"));

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
			statement.setString(2, atraccion.getNombre());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	public int findIdPorNombre(Atraccion unaAtraccion) {
		try {			
			String sql = "SELECT id_atraccion " + "FROM atracciones "
					+ "WHERE nombre LIKE ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();
			int id_atraccion = 0;
			if (resultados.next()) {
				id_atraccion = resultados.getInt("id_atraccion");
			}

			return id_atraccion;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
}
