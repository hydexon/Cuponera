package sv.edu.udb.cuponera.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import sv.edu.udb.cuponera.pojo.Rol;

public class RolesDAO extends AppConnection {
	public ArrayList<Rol> findAll() throws SQLException {
		connect();
		stmt = conn.createStatement();
		resultSet = stmt.executeQuery("SELECT * FROM Roles");
		ArrayList<Rol> roles = new ArrayList<Rol>();
		while(resultSet.next()) {
			Rol tmp = new Rol();
			tmp.setId(resultSet.getInt(1));
			tmp.setRol(resultSet.getString(2));
			tmp.setRolVal(resultSet.getInt(3));
			
			roles.add(tmp);
		}
		
		close();
		return roles;
	}
	
	public Rol findById(int id) throws SQLException {
		connect();
		pstmt = conn.prepareStatement("SELECT * FROM Roles WHERE RolId=?");
		pstmt.setInt(1, id);
		resultSet = pstmt.executeQuery();
		
		Rol rol = new Rol();
		while(resultSet.next())
		{
			rol.setId(resultSet.getInt(1));
			rol.setRol(resultSet.getString(2));
			rol.setRolVal(resultSet.getInt(3));
		}
		
		close();
		return rol;
	}
}
