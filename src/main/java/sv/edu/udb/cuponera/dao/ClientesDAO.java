package sv.edu.udb.cuponera.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import sv.edu.udb.cuponera.pojo.Cliente;

public class ClientesDAO extends AppConnection {
	public void insert(Cliente cliente) throws SQLException {
		connect();
		pstmt = conn.prepareStatement("INSERT INTO Clientes VALUES(NULL,?,?,?,?,?,?,?,?)");
		pstmt.setString(1, cliente.getUsername());
		pstmt.setString(2, cliente.getNombres());
		pstmt.setString(3, cliente.getApellidos());
		pstmt.setString(4, cliente.getDui());
		pstmt.setString(5, cliente.getTelefono());
		pstmt.setString(6, cliente.getCorreo());
		pstmt.setString(7, cliente.getContrasena());
		pstmt.setInt(8, cliente.getConfirmado());
		pstmt.execute();
		close();
	}
	
	public void update(Cliente cliente) throws SQLException {
		connect();
		pstmt = conn.prepareStatement("UPDATE Clientes FROM Username=?, Nombres=?, Apellidos=?, DUI=?, Telefono=?, Correo=?, Contrasena=?, Confirmado=? WHERE ClienteId=?");
		pstmt.setString(1, cliente.getUsername());
		pstmt.setString(2, cliente.getNombres());
		pstmt.setString(3, cliente.getApellidos());
		pstmt.setString(4, cliente.getDui());
		pstmt.setString(5, cliente.getTelefono());
		pstmt.setString(6, cliente.getCorreo());
		pstmt.setString(7, cliente.getContrasena());
		pstmt.setInt(8, cliente.getConfirmado());
		pstmt.setInt(9, cliente.getId());
		pstmt.execute();
		close();
	}
	
	public void delete(int id) throws SQLException {
		connect();
		pstmt = conn.prepareStatement("DELETE FROM Clientes WHERE ClienteId=?");
		pstmt.setInt(1, id);
		pstmt.execute();
		close();
	}
	
	public ArrayList<Cliente> findAll() throws SQLException {
		connect();
		stmt = conn.createStatement();
		resultSet = stmt.executeQuery("SELECT * FROM Clientes");
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		while(resultSet.next()) {
			Cliente c = new Cliente();
			c.setId(resultSet.getInt(1));
			c.setUsername(resultSet.getString(2));
			c.setNombres(resultSet.getString(3));
			c.setApellidos(resultSet.getString(4));
			c.setDui(resultSet.getString(5));
			c.setTelefono(resultSet.getString(6));
			c.setCorreo(resultSet.getString(7));
			c.setContrasena(resultSet.getString(8));
			clientes.add(c);
		}
		close();
		return clientes;
	}
	
	public Cliente findById(int id) throws SQLException {
		connect();
		pstmt = conn.prepareStatement("SELECT * FROM Clientes WHERE ClienteId=?");
		pstmt.setInt(1, id);
		resultSet = pstmt.executeQuery();
		Cliente c = new Cliente();
		while(resultSet.next()) {
			c.setId(resultSet.getInt(1));
			c.setUsername(resultSet.getString(2));
			c.setNombres(resultSet.getString(3));
			c.setApellidos(resultSet.getString(4));
			c.setDui(resultSet.getString(5));
			c.setTelefono(resultSet.getString(6));
			c.setCorreo(resultSet.getString(7));
			c.setContrasena(resultSet.getString(8));
		}
		close();
		return c;
	}
	
	
	
	

}
