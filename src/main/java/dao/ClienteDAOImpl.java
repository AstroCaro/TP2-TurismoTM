package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import jdbc.ConnectionProvider;
import paqueteTurismoTM.Cliente;

public class ClienteDAOImpl implements ClienteDAO {

	@Override
	public ArrayList<Cliente> findAll() {
		try {
			String sql = "SELECT id_cliente, nombre, tipo_atraccion, presupuesto, tiempo_disponible " + "FROM clientes "
					+ "JOIN \"tipo atraccion\" ON \"tipo atraccion\".id_tipoatraccion = clientes.fk_tipoatraccion ";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			ArrayList<Cliente> clientes = new ArrayList<Cliente>();
			while (resultados.next()) {
				clientes.add(toCliente(resultados));
			}

			return clientes;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Cliente toCliente(ResultSet resultados) {
		try {
			return new Cliente(resultados.getInt("id_cliente"), resultados.getString("nombre"), resultados.getString("tipo_atraccion"),
					resultados.getInt("presupuesto"), resultados.getDouble("tiempo_disponible"));

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(Cliente cliente) {
		try {
			String sql = "UPDATE CLIENTES SET PRESUPUESTO = ?, TIEMPO_DISPONIBLE = ? WHERE NOMBRE = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, cliente.getPresupuesto());
			statement.setDouble(2, cliente.getTiempo_disponible());
			statement.setInt(3, cliente.getId_cliente());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

}
