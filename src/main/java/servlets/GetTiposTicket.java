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

import main.java.clases.TiposTicket;
import main.java.clases.Utils;

public class GetTiposTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetTiposTicket() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<TiposTicket> tiposTickets = new ArrayList<TiposTicket>();
				
		Connection con = null;
		
        Utils util = new Utils();
        String dbURL = util.urlConexion();
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(dbURL);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT tip_IdTipoTicket, tip_Nombre FROM TiposTicket ORDER BY tip_Nombre ASC");
            while (rs.next()) {
            	TiposTicket tt = new TiposTicket(rs.getInt("tip_IdTipoTicket"), rs.getString("tip_Nombre"));
            	tiposTickets.add(tt);
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
        request.setAttribute("tiposTickets", tiposTickets);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
