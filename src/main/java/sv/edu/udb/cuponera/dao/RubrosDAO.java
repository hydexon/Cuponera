package sv.edu.udb.cuponera.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import sv.edu.udb.cuponera.pojo.Rubro;

public class RubrosDAO extends AppConnection {
	public void insert(Rubro rubro) throws SQLException {
		connect();
		pstmt = conn.prepareStatement("INSERT INTO Rubros VALUES(NULL, ?)");
		pstmt.setString(1, rubro.getRubro());
		pstmt.execute();
		close();
	}
	
	public void update(Rubro rubro) throws SQLException {
		connect();
		pstmt = conn.prepareStatement("UPDATE Rubros SET Rubro=? WHERE RubroId=?");
		pstmt.setString(1, rubro.getRubro());
		pstmt.setInt(2, rubro.getId());
		pstmt.execute();
		close();
	}
	
	public void delete(int id) throws SQLException {
		connect();
		pstmt = conn.prepareStatement("DELETE FROM Rubros WHERE RubroId=?");
		pstmt.setInt(1, id);
		pstmt.execute();
		close();
	}
	
	public ArrayList<Rubro> findAll() throws SQLException {
		connect();
		stmt = conn.createStatement();
		resultSet = stmt.executeQuery("SELECT * FROM Rubros");
		ArrayList<Rubro> rubros = new ArrayList<Rubro>();
		while(resultSet.next()) {
			Rubro rubro = new Rubro();
			rubro.setId(resultSet.getInt(1));
			rubro.setRubro(resultSet.getString(2));
			rubros.add(rubro);
		}
		close();
		return rubros;
	}
	
	public Rubro findById(int id) throws SQLException {
		connect();
		pstmt = conn.prepareStatement("SELECT * FROM Rubros WHERE RubroId=?");
		pstmt.setInt(1, id);
		resultSet = pstmt.executeQuery();
		Rubro rubro = null;
		while(resultSet.next()) {
			rubro = new Rubro();
			rubro.setId(resultSet.getInt(1));
			rubro.setRubro(resultSet.getString(2));
		}
		close();
		return rubro;
	}
}
