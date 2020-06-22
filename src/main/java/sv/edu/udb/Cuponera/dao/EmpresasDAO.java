package sv.edu.udb.Cuponera.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import sv.edu.udb.Cuponera.pojo.Empresa;

public class EmpresasDAO extends AppConnection {
	private RubrosDAO rubros = new RubrosDAO();
	
	private String generateNewId() {
		Random r = new Random();
		String letras  = "ABCDEFGHIJKLMNOPQRTUVWYZ";
		String numeros = "0123456789";
		String newId = "";
		
		for(int i = 0; i < 3; i++)
			newId += letras.charAt(r.nextInt(letras.length()));
		for(int i = 0; i < 3; i++) 
			newId += numeros.charAt(r.nextInt(numeros.length()));
		
		return newId;
	}
	
	public void insert(Empresa empresa) throws SQLException {
		String newId = generateNewId();
		while(findById(newId) != null)
			newId = generateNewId();
		
		connect();
		pstmt = conn.prepareStatement("INSERT INTO Empresas VALUES(?,?,?,?,?,?,?)");
		pstmt.setString(1, newId);
		pstmt.setString(2, empresa.getNombre());
		pstmt.setString(3, empresa.getDireccion());
		pstmt.setString(4, empresa.getTelefono());
		pstmt.setString(5, empresa.getCorreo());
		pstmt.setInt(6, empresa.getRubro().getId());
		pstmt.setDouble(7, empresa.getComision());
		pstmt.execute();
		close();
	}
	
	public void update(Empresa empresa) throws SQLException {
		connect();
		pstmt = conn.prepareStatement("UPDATE Empresas SET Nombre=?, Direccion=?, Telefono=?, Correo=?, Rubro=?, Comision=? WHERE EmpresaId=?");
		pstmt.setString(1, empresa.getNombre());
		pstmt.setString(2, empresa.getDireccion());
		pstmt.setString(3, empresa.getTelefono());
		pstmt.setString(4, empresa.getCorreo());
		pstmt.setInt(5, empresa.getRubro().getId());
		pstmt.setDouble(6, empresa.getComision());
		pstmt.setString(7, empresa.getId());
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
