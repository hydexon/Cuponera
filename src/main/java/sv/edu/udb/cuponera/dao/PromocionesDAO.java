package sv.edu.udb.cuponera.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import sv.edu.udb.cuponera.pojo.Promocion;

public class PromocionesDAO extends AppConnection{
	private EmpresasDAO empresas = new EmpresasDAO();
	
	public void insert(Promocion promo) throws SQLException {
		connect();
		pstmt = conn.prepareStatement("INSERT INTO Promociones VALUES(NULL,?,?,?,?,?,?,?,?,?)");
		pstmt.setDouble(1, promo.getPrecioRegular());
		pstmt.setDouble(2, promo.getPrecioOferta());
		pstmt.setDate(3, promo.getFechaInicio());
		pstmt.setDate(4, promo.getFechaFinal());
		pstmt.setDate(5, promo.getFechaLimite());
		pstmt.setInt(6, promo.getCantidad());
		pstmt.setString(7, promo.getDescripcion());
		pstmt.setString(8, promo.getEmpresa().getId());
		pstmt.setInt(9, promo.getStatus());
		pstmt.execute();
		close();
	}
	
	public void update(Promocion promo) throws SQLException {
		connect();
		pstmt = conn.prepareStatement("UPDATE Promociones SET PrecioRegular=?, PrecioOferta=?, FechaInicio=?, FechaFinal=?, FechaLimite=?, CantidadLimite=?, Descripcion=?, Empresa=?, StatusCupon=? WHERE PromoId=?");
		pstmt.setDouble(1, promo.getPrecioRegular());
		pstmt.setDouble(2, promo.getPrecioOferta());
		pstmt.setDate(3, promo.getFechaInicio());
		pstmt.setDate(4, promo.getFechaFinal());
		pstmt.setDate(5, promo.getFechaLimite());
		pstmt.setInt(6, promo.getCantidad());
		pstmt.setString(7, promo.getDescripcion());
		pstmt.setString(8, promo.getEmpresa().getId());
		pstmt.setInt(9, promo.getStatus());
		pstmt.setInt(10, promo.getId());
		pstmt.execute();
		close();
	}
	
	public void delete(int id) throws SQLException {
		connect();
		pstmt = conn.prepareStatement("DELETE FROM Promociones WHERE PromoId=?");
		pstmt.setInt(1, id);
		pstmt.execute();
		close();
	}
	
	public ArrayList<Promocion> findAll() throws SQLException {
		connect();
		ArrayList<Promocion> promociones = new ArrayList<Promocion>();
		stmt = conn.createStatement();
		resultSet = stmt.executeQuery("SELECT * FROM Promociones");
		while(resultSet.next()) {
			Promocion promo = new Promocion();
			promo.setId(resultSet.getInt(1));
			promo.setPrecioRegular(resultSet.getDouble(2));
			promo.setPrecioOferta(resultSet.getDouble(3));
			promo.setFechaInicio(resultSet.getDate(4));
			promo.setFechaFinal(resultSet.getDate(5));
			promo.setFechaLimite(resultSet.getDate(6));
			promo.setCantidad(resultSet.getInt(7));
			promo.setDescripcion(resultSet.getString(8));
			promo.setEmpresa(empresas.findById(resultSet.getString(9)));
			promo.setStatus(resultSet.getInt(10));
			promociones.add(promo);
		}
		close();
		return promociones;
	}

	public Promocion findById(int id) throws SQLException {
		connect();
		pstmt = conn.prepareStatement("SELECT * FROM Promociones WHERE PromoId=?");
		pstmt.setInt(1, id);
		resultSet = pstmt.executeQuery();
		Promocion promo = null;
		while(resultSet.next()) {
			promo = new Promocion();
			promo.setId(resultSet.getInt(1));
			promo.setPrecioRegular(resultSet.getDouble(2));
			promo.setPrecioOferta(resultSet.getDouble(3));
			promo.setFechaInicio(resultSet.getDate(4));
			promo.setFechaFinal(resultSet.getDate(5));
			promo.setFechaLimite(resultSet.getDate(6));
			promo.setCantidad(resultSet.getInt(7));
			promo.setDescripcion(resultSet.getString(8));
			promo.setEmpresa(empresas.findById(resultSet.getString(9)));
			promo.setStatus(resultSet.getInt(10));
		}
		close();
		return promo;
	}
	


}
