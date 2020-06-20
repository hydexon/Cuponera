package sv.edu.udb.cuponera.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import sv.edu.udb.cuponera.pojo.Dependiente;

public class DependientesDAO extends AppConnection {	
	public void insert(Dependiente dep) throws SQLException {
		connect();
		pstmt = conn.prepareStatement("INSERT INTO Dependientes VALUES(NULL,?,?,?,?,?)");
		pstmt.setString(1, dep.getNombre());
		pstmt.setString(2, dep.getApellidos());
		pstmt.setString(3, dep.getCorreo());
		pstmt.setString(4, dep.getContrasena());
		pstmt.setString(5, dep.getUsername());
		pstmt.execute();
		close();
	}
	
	public void update(Dependiente dep) throws SQLException {
		connect();
		pstmt = conn.prepareStatement("UPDATE Dependientes SET Nombre=?, Apellidos=?, Correo=?, Contrasena=?, Username=? WHERE DependienteId=?");
		pstmt.setString(1, dep.getNombre());
		pstmt.setString(2, dep.getApellidos());
		pstmt.setString(3, dep.getCorreo());
		pstmt.setString(4, dep.getContrasena());
		pstmt.setString(5, dep.getUsername());
		pstmt.setInt(6, dep.getId());
		pstmt.execute();
		close();
	}

	public void delete(int id) throws SQLException {
		connect();
		pstmt = conn.prepareStatement("DELETE FROM Dependientes WHERE DependienteId=?");
		pstmt.setInt(1, id);
		pstmt.execute();
		close();
	}
	
	public ArrayList<Dependiente> findAll() throws SQLException {
		connect();
		stmt = conn.createStatement();
		resultSet = stmt.executeQuery("SELECT * FROM Dependientes");
		ArrayList<Dependiente> deps = new ArrayList<Dependiente>();
		while(resultSet.next()) {
			Dependiente dep = new Dependiente();
			dep.setId(resultSet.getInt(1));
			dep.setNombre(resultSet.getString(2));
			dep.setApellidos(resultSet.getString(3));
			dep.setCorreo(resultSet.getString(4));
			dep.setContrasena(resultSet.getString(5));
			dep.setUsername(resultSet.getString(6));
			
			deps.add(dep);
		}
		close();
		return deps;
	}
	
	public Dependiente findById(int id) throws SQLException {
		connect();
		pstmt = conn.prepareStatement("SELECT * FROM Dependientes WHERE DependienteId=?");
		pstmt.setInt(1, id);
		resultSet = pstmt.executeQuery();
		Dependiente dep = null;
		while(resultSet.next()) {
			dep = new Dependiente();
			dep.setId(resultSet.getInt(1));
			dep.setNombre(resultSet.getString(2));
			dep.setApellidos(resultSet.getString(3));
			dep.setCorreo(resultSet.getString(4));
			dep.setContrasena(resultSet.getString(5));
			dep.setUsername(resultSet.getString(6));
		}
		close();
		return dep;
	}



}
