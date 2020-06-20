package sv.edu.udb.Cuponera.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import sv.edu.udb.Cuponera.pojo.Usuario;

public class UsuariosDAO extends AppConnection {
	private RolesDAO roles = new RolesDAO();
	
	public void insert(Usuario user) throws SQLException {
		connect();
		pstmt = conn.prepareStatement("INSERT INTO Usuarios VALUES(NULL, ?, ?, ?,  ?, ?)");
		pstmt.setString(1, user.getUsername());
		pstmt.setString(2, user.getNombreCompleto());
		pstmt.setString(3, user.getCorreo());
		pstmt.setString(4, user.getContrasena());
		pstmt.setInt(5, user.getRol().getId());
		pstmt.execute();
		close();
	}
	
	public void update(Usuario user) throws SQLException {
		connect();
		pstmt = conn.prepareStatement("UPDATE Usuarios SET NombreUsuario=?, NombreCompleto=?, Correo=?, Contrasena=?, Rol=? WHERE UserId=?");
		pstmt.setString(1, user.getUsername());
		pstmt.setString(2, user.getNombreCompleto());
		pstmt.setString(3, user.getCorreo());
		pstmt.setString(4, user.getContrasena());
		pstmt.setInt(5, user.getRol().getId());
		pstmt.setInt(6, user.getId());
		pstmt.execute();
		close();
	}
	
	public void delete(int id) throws SQLException {
		connect();
		pstmt = conn.prepareStatement("DELETE FROM Usuarios WHERE UserId=?");
		pstmt.setInt(1, id);
		pstmt.execute();
		close();
	}
	
	public ArrayList<Usuario> findAll() throws SQLException {
		connect();
		stmt = conn.createStatement();
		resultSet = stmt.executeQuery("SELECT * FROM Usuarios");
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		while(resultSet.next()) {
			Usuario usr = new Usuario();
			usr.setId(resultSet.getInt(1));
			usr.setUsername(resultSet.getString(2));
			usr.setNombreCompleto(resultSet.getString(3));
			usr.setCorreo(resultSet.getString(4));
			usr.setContrasena(resultSet.getString(5));
			usr.setRol(roles.findById(resultSet.getInt(6)));
		}
		
		close();
		return usuarios;
	}
	
	public Usuario findById(int id) throws SQLException {
		connect();
		pstmt = conn.prepareStatement("SELECT * FROM Usuarios WHERE UserId=?");
		pstmt.setInt(1, id);
		resultSet = pstmt.executeQuery();
		Usuario usr = null;
		while(resultSet.next()) {
			usr =  new Usuario();
			usr.setId(resultSet.getInt(1));
			usr.setUsername(resultSet.getString(2));
			usr.setNombreCompleto(resultSet.getString(3));
			usr.setCorreo(resultSet.getString(4));
			usr.setContrasena(resultSet.getString(5));
			usr.setRol(roles.findById(resultSet.getInt(6)));
		}
		return usr;
	}
	
	

}
