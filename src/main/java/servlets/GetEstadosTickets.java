package main.java.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.clases.EstadoTicket;
import main.java.clases.Utils;

public class GetEstadosTickets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetEstadosTickets() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<EstadoTicket> estados = new ArrayList<EstadoTicket>();
				
		Connection con = null;
		
        Utils util = new Utils();
        String dbURL = util.urlConexion();
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(dbURL);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT etk_IdEstadoTicket, etk_Nombre FROM EstadosTickets ORDER BY etk_Nombre ASC");
            while (rs.next()) {
            	EstadoTicket est = new EstadoTicket(rs.getInt("etk_IdEstadoTicket"), rs.getString("etk_Nombre"));
            	estados.add(est);
            }
 
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        request.setAttribute("estados", estados);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
