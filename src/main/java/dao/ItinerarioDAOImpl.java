package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;
import model.User;
import paqueteTurismoTM.Cliente;
import paqueteTurismoTM.Itinerario;
import paqueteTurismoTM.Oferta;

public class ItinerarioDAOImpl implements ItinerarioDAO {

	@Override
	public List<Oferta> findItinerarioPorCliente(String nombreCliente) {
		List<Oferta> ofertasCompradas = new LinkedList<Oferta>();
		try {
			String sql = "SELECT clientes.nombre, coalesce(promociones.nombre, atracciones.nombre)AS 'compras'"
					+ "FROM itinerarios"
					+ "LEFT JOIN \"promociones\" ON \"promociones\".id_promocion = itinerarios.fk_promocion"
					+ "LEFT JOIN \"atracciones\" ON \"atracciones\".id_atraccion = itinerarios.fk_atraccion"
					+ "LEFT JOIN \"clientes\" ON \"clientes\".id_cliente=itinerarios.fk_cliente"
					+"WHERE clientes.id_cliente = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombreCliente);
			ResultSet resultados = statement.executeQuery();

			ArrayList<String> compras = new ArrayList<>();
			while (resultados.next()) {
				compras.add(resultados.getString("compras"));
			
			
			for (String compra : compras) {
				for (Oferta unaOferta : turismoTM.ofertas) {	
					if (compra.equals(unaOferta.getNombre())) {
						ofertasCompradas.add(unaOferta);
					}
			}
			}
			}
			
		
			return ofertasCompradas;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
		}
	public int insert(Itinerario itinerario) {
		try {
			//atraccion COMPLETAR- FALTA PROMOCION- agregar id a cliente
			String sql = "INSERT INTO itinerarios (fk_cliente, fk_atracion, costo, tiempo) VALUES (?, ?, ?, ?,)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, cliente.getId());
			statement.setString(2, atraccion.getNombre());
			statement.setString(2, atraccion.getCosto());
			statement.setString(2, atraccion.getTiempo());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public List<Itinerario> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}