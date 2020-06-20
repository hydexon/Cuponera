package sv.edu.udb.Cuponera.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import sv.edu.udb.Cuponera.pojo.Cupon;

public class CuponesDAO extends AppConnection {
	private PromocionesDAO promos = new PromocionesDAO();
	private ClientesDAO clientes  = new ClientesDAO();
	
	public void insert(Cupon cupon) throws SQLException{
		Random rand = new Random();
		String newId = cupon.getPromo().getEmpresa().getId() + "-" + String.format("%04d", rand.nextInt(10000));
		connect();
		pstmt = conn.prepareStatement("INSERT INTO Cupones VALUES(?,?,?,?)");
		pstmt.setString(1, newId);
		pstmt.setInt(2, cupon.getPromo().getId());
		pstmt.setInt(3, cupon.getCliente().getId());
		pstmt.setInt(4, cupon.getEstado());
		pstmt.execute();
		close();
		
	}
	public void update(Cupon cupon) throws SQLException{
		connect();
		pstmt = conn.prepareStatement("UPDATE Cupones SET Promocion=?, Cliente=?, Estado=? WHERE CuponId=?");
		pstmt.setInt(1, cupon.getPromo().getId());
		pstmt.setInt(2, cupon.getCliente().getId());
		pstmt.setInt(3, cupon.getEstado());
		pstmt.setString(4, cupon.getId());
		pstmt.execute();
		close();
		
	}
	public void delete(String id) throws SQLException{
		connect();
		pstmt = conn.prepareStatement("DELETE FROM Cupones WHERE CuponId=?");
		pstmt.execute();
		close();
	}
	
	public ArrayList<Cupon> findAll() throws SQLException {
		connect();
		stmt = conn.createStatement();
		resultSet = stmt.executeQuery("SELECT * FROM Cupones");
		ArrayList<Cupon> cupones = new ArrayList<Cupon>();
		while(resultSet.next()) {
			Cupon cup = new Cupon();
			cup.setId(resultSet.getString(1));
			cup.setPromo(promos.findById(resultSet.getInt(2)));
			cup.setCliente(clientes.findById(resultSet.getInt(3)));
			cup.setEstado(resultSet.getInt(4));
			cupones.add(cup);
		}
		close();
		return cupones;
	}
	
	public ArrayList<Cupon> findByStatus(int status) throws SQLException {
		connect();
		pstmt = conn.prepareStatement("SELECT * FROM Cupones WHERE Estado=?");
		pstmt.setInt(1, status);
		ArrayList<Cupon> cupones = new ArrayList<Cupon>();
		while(resultSet.next()) {
			Cupon cup = new Cupon();
			cup.setId(resultSet.getString(1));
			cup.setPromo(promos.findById(resultSet.getInt(2)));
			cup.setCliente(clientes.findById(resultSet.getInt(3)));
			cup.setEstado(resultSet.getInt(4));
			cupones.add(cup);
		}
		close();
		return cupones;
	}

	
	public Cupon findById(String id) throws SQLException {
		connect();
		pstmt = conn.prepareStatement("SELECT * FROM Cupones WHERE CuponId=?");
		pstmt.setString(1, id);
		resultSet = pstmt.executeQuery();
		Cupon cup = new Cupon();
		while(resultSet.next()) {
			cup.setId(resultSet.getString(1));
			cup.setPromo(promos.findById(resultSet.getInt(2)));
			cup.setCliente(clientes.findById(resultSet.getInt(3)));
			cup.setEstado(resultSet.getInt(4));
		}
		close();
		return cup;
	}

}
