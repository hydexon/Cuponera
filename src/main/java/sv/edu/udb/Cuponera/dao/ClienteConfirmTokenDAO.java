package sv.edu.udb.Cuponera.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import sv.edu.udb.Cuponera.pojo.ClienteConfirmacionToken;

public class ClienteConfirmTokenDAO extends AppConnection {
	private ClientesDAO clientes = new ClientesDAO();
	
	public void insert(ClienteConfirmacionToken cct) throws SQLException {
		connect();
		pstmt = conn.prepareStatement("INSERT INTO ClienteConfirmToken VALUES(NULL, ?, ?)");
		pstmt.setInt(1, cct.getCliente().getId());
		pstmt.setString(2, cct.getToken());
		pstmt.execute();
		close();
	}
	
	public void update(ClienteConfirmacionToken cct) throws SQLException {
		connect();
		pstmt = conn.prepareStatement("UPDATE ClienteConfirmToken SET Cliente=?, Token=? WHERE TokenId=?");
		pstmt.setInt(1, cct.getCliente().getId());
		pstmt.setString(2, cct.getToken());
		pstmt.setInt(3, cct.getId());
		pstmt.execute();
		close();
	}

	public void delete(int id) throws SQLException {
		connect();
		pstmt = conn.prepareStatement("DELETE FROM ClienteConfirmToken WHERE TokenId=?");
		pstmt.setInt(1, id);
		pstmt.execute();
		close();
	}
	
	public ArrayList<ClienteConfirmacionToken> findAll() throws SQLException {
		connect();
		stmt = conn.createStatement();
		resultSet = stmt.executeQuery("SELECT * FROM ClienteConfirmToken");
		ArrayList<ClienteConfirmacionToken> roles = new ArrayList<ClienteConfirmacionToken>();
		while(resultSet.next()) {
			ClienteConfirmacionToken tmp = new ClienteConfirmacionToken();
			tmp.setId(resultSet.getInt(1));
			tmp.setCliente(clientes.findById(resultSet.getInt(2)));
			tmp.setToken(resultSet.getString(3));
			
			roles.add(tmp);
		}
		
		close();
		return roles;
	}
	
	public ClienteConfirmacionToken findById(int id) throws SQLException {
		connect();
		pstmt = conn.prepareStatement("SELECT * FROM ClienteConfirmToken WHERE TokenId=?");
		pstmt.setInt(1, id);
		resultSet = pstmt.executeQuery();
		
		ClienteConfirmacionToken cct = new ClienteConfirmacionToken();
		while(resultSet.next())
		{
			cct.setId(resultSet.getInt(1));
			cct.setCliente(clientes.findById(resultSet.getInt(2)));
			cct.setToken(resultSet.getString(3));
		}
		
		close();
		return cct;
	}
	public ClienteConfirmacionToken findByToken(String token) throws SQLException {
		connect();
		pstmt = conn.prepareStatement("SELECT * FROM ClienteConfirmToken WHERE Token=?");
		pstmt.setString(1, token);
		resultSet = pstmt.executeQuery();
		
		ClienteConfirmacionToken cct = null;
		while(resultSet.next())
		{
			cct = new ClienteConfirmacionToken();
			cct.setId(resultSet.getInt(1));
			cct.setCliente(clientes.findById(resultSet.getInt(2)));
			cct.setToken(resultSet.getString(3));
		}
		
		close();
		return cct;
	}

}
