package sv.edu.udb.Cuponera.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import sv.edu.udb.Cuponera.pojo.Empresa;

public class EmpresasDAO extends AppConnection {
	private RubrosDAO rubros = new RubrosDAO();

	public void insert(Empresa empresa) throws SQLException {
		connect();
		pstmt = conn.prepareStatement("INSERT INTO Empresas VALUES(?,?,?,?,?,?,?)");
		pstmt.setString(1, empresa.getId());
		pstmt.setString(2, empresa.getNombre());
		pstmt.setString(3, empresa.getDireccion());
		pstmt.setString(4, empresa.getCorreo());
		pstmt.setInt(5, empresa.getRubro().getId());
		pstmt.setDouble(6, empresa.getComision());
		pstmt.execute();
		close();
	}
	
	public void update(Empresa empresa) throws SQLException {
		connect();
		pstmt = conn.prepareStatement("UPDATE Empresas SET Nombre=?, Direccion=?, Telefono=?, Correo=?, Rubro=?, Comision=? WHERE EmpresaId=?");
		pstmt.setString(1, empresa.getNombre());
		pstmt.setString(2, empresa.getDireccion());
		pstmt.setString(3, empresa.getCorreo());
		pstmt.setInt(4, empresa.getRubro().getId());
		pstmt.setDouble(5, empresa.getComision());
		pstmt.setString(6, empresa.getId());
		pstmt.execute();
		close();
	}
	
	public void delete(String id) throws SQLException {
		connect();
		pstmt = conn.prepareStatement("DELETE FROM Empresas WHERE EmpresaId=?");
		pstmt.setString(1, id);
		pstmt.execute();
		close();
	}
	
	public ArrayList<Empresa> findAll() throws SQLException {
		connect();
		stmt = conn.createStatement();
		resultSet = stmt.executeQuery("SELECT * FROM Empresas");
		ArrayList<Empresa> empresas = new ArrayList<Empresa>();
		while(resultSet.next()) {
			Empresa emp = new Empresa();
			emp.setId(resultSet.getString(1));
			emp.setNombre(resultSet.getString(2));
			emp.setDireccion(resultSet.getString(3));
			emp.setTelefono(resultSet.getString(4));
			emp.setCorreo(resultSet.getString(5));
			emp.setRubro(rubros.findById(resultSet.getInt(6)));
			emp.setComision(resultSet.getDouble(7));
			empresas.add(emp);
		}
		close();
		return empresas;
	}
	
	
	public Empresa findById(String id) throws SQLException {
		connect();
		pstmt = conn.prepareStatement("SELECT * FROM Empresas WHERE EmpresaId=?");
		pstmt.setString(1, id);
		resultSet = pstmt.executeQuery();
		Empresa emp = null;
		while(resultSet.next()) {
			emp = new Empresa();
			emp.setId(resultSet.getString(1));
			emp.setNombre(resultSet.getString(2));
			emp.setDireccion(resultSet.getString(3));
			emp.setTelefono(resultSet.getString(4));
			emp.setCorreo(resultSet.getString(5));
			emp.setRubro(rubros.findById(resultSet.getInt(6)));
			emp.setComision(resultSet.getDouble(7));
		}
		close();
		return emp;
		
	}

	
}
