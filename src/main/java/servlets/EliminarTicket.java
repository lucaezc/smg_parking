package main.java.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.clases.Utils;

public class EliminarTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EliminarTicket() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String idTicketE = request.getParameter("idTicketE");

		Connection con = null;
        Utils util = new Utils();
        String dbURL = util.urlConexion();
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(dbURL);
            Statement stmt = con.createStatement();
            
            Integer estadoTicket = util.getEstado("Eliminado");
            
            stmt.executeUpdate("UPDATE Tickets SET tic_Baja = 1, tic_Estado = " + estadoTicket + " WHERE tic_IdTicket = " + idTicketE);

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
        
        Integer usuario = Integer.parseInt(request.getSession(false).getAttribute("idUsuario").toString());
        Integer sanatorio = Integer.parseInt(request.getSession(false).getAttribute("sanatorio").toString());
		util.loggear("TICKETS", "ELIMINACION DE TICKET " + idTicketE, usuario, sanatorio); // LOG
        
        PrintWriter out = response.getWriter();
        out.println("<script type=\"text/javascript\">");
        out.println("alert('Ticket eliminado correctamente');");
        out.println("location= '" + request.getContextPath() + "/menu.jsp';");
        out.println("</script>");
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
