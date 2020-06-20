package sv.edu.udb.Cuponera.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import sv.edu.udb.Cuponera.pojo.JustificacionRechazo;

public class JustificacionesDAO extends AppConnection {
	private PromocionesDAO promos = new PromocionesDAO();
	
	public void insert(JustificacionRechazo justif) throws SQLException {
		connect();
		pstmt = conn.prepareStatement("INSERT INTO JustificacionRechazos VALUES(NULL,?,?);");
		pstmt.setInt(1, justif.getPromo().getId());
		pstmt.setString(2, justif.getDescripcion());
		pstmt.execute();
		close();
	}
	
	public void update(JustificacionRechazo justif) throws SQLException {
		connect();
		pstmt = conn.prepareStatement("UPDATE JustificacionRechazos SET Promocion=?, Descripcion=? WHERE JustificacionId=?");
		pstmt.setInt(1, justif.getPromo().getId());
		pstmt.setString(2, justif.getDescripcion());
		pstmt.setInt(3, justif.getId());
		pstmt.execute();
		close();
	}
	
	public void delete(int id) throws SQLException {
		connect();
		pstmt = conn.prepareStatement("DELETE FROM JustificacionRechazos WHERE JustificacionId=?");
		pstmt.setInt(1, id);
		pstmt.execute();
		close();
	}
	
	public ArrayList<JustificacionRechazo> findAll() throws SQLException{
		connect();
		stmt = conn.createStatement();
		resultSet = stmt.executeQuery("SELECT * FROM JustificacionRechazos");
		ArrayList<JustificacionRechazo> justificaciones = new ArrayList<JustificacionRechazo>();
		while(resultSet.next()) {
			JustificacionRechazo jr = new JustificacionRechazo();
			jr.setId(resultSet.getInt(1));
			jr.setPromo(promos.findById(resultSet.getInt(2)));
			jr.setDescripcion(resultSet.getString(3));
			justificaciones.add(jr);
		}
		close();
		return justificaciones;
	}
	
	public JustificacionRechazo findById(int id) throws SQLException {
		connect();
		pstmt = conn.prepareStatement("SELECT * FROM JustificacionRechazos WHERE JustificacionId=?");
		pstmt.setInt(1, id);
		resultSet = pstmt.executeQuery();
		JustificacionRechazo jr = null;
		while(resultSet.next()) {
			jr = new JustificacionRechazo();
			jr.setId(resultSet.getInt(1));
			jr.setPromo(promos.findById(resultSet.getInt(2)));
			jr.setDescripcion(resultSet.getString(3));
		}
		close();
		return jr;
	}

}
