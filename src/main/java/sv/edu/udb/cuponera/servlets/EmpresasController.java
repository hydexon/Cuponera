package sv.edu.udb.cuponera.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sv.edu.udb.cuponera.dao.EmpresasDAO;
import sv.edu.udb.cuponera.dao.RubrosDAO;
import sv.edu.udb.cuponera.pojo.Empresa;

import javax.servlet.annotation.WebServlet;
/**
 * Servlet implementation class EmpresasController
 */

public class EmpresasController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EmpresasDAO emprDAO = new EmpresasDAO();
	RubrosDAO   rubDAO  = new RubrosDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpresasController() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Empresa emp = null;
		if(request.getParameter("id") == "")
			emp = new Empresa();
		else
			try {
			emp = emprDAO.findById(request.getParameter("id"));
			} catch(SQLException e) {
				e.printStackTrace();
			}
		
		
		
	}

}
