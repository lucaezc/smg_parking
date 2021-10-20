package main.java.servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.java.clases.Utils;

public class Autorizar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Autorizar() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String idAut = request.getParameter("idAut");
		String idTicket = request.getParameter("idTicket");
        String accion = request.getParameter("accion");

        Utils util = new Utils();

		String idUsuario = request.getSession(false).getAttribute("idUsuario").toString();
        String fechaActual = util.fechaHoraActual();
		Connection con = null;
        String dbURL = util.urlConexion();

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(dbURL);
            Statement stmt = con.createStatement();
            String query = null, query2 = null, query3 = null;
            Date fechaEgreso = null;
            Integer estadoTicket = 0;
            
            if (accion.equals("Autorizar")) {
            	query = "UPDATE Autorizaciones SET aut_IdUsuarioAutorizante = " + idUsuario + ", aut_FechaAutorizacion = '" + fechaActual + "', aut_Autorizado = 'S' WHERE aut_IdAutorizacion = " + idAut;
                stmt.executeUpdate(query);
                
                ResultSet rs = stmt.executeQuery("SELECT tic_FechaHoraEgreso FROM Tickets WHERE tic_IdTicket = " + idTicket);
    			while (rs.next()) {
    				fechaEgreso = rs.getTimestamp("tic_FechaHoraEgreso");
    			}
    			
    			if (fechaEgreso == null) {
    				estadoTicket = util.getEstado("Autorizado");
    			} else {
    				estadoTicket = util.getEstado("Autorizado/Cerrado");
    			}
    			
            	query2 = "UPDATE Tickets SET tic_Estado = " + estadoTicket + " WHERE tic_IdTicket = " + idTicket;
                stmt.executeUpdate(query2);
            }else {
            	estadoTicket = util.getEstado("Abierto/Rechazado");
            	query3 = "UPDATE Tickets SET tic_Estado = " + estadoTicket + " WHERE tic_IdTicket = " + idTicket;
                stmt.executeUpdate(query3);
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
        Integer usuario = Integer.parseInt(request.getSession(false).getAttribute("idUsuario").toString());
        Integer sanatorio = Integer.parseInt(request.getSession(false).getAttribute("sanatorio").toString());
        PrintWriter out = response.getWriter();
        out.println("<script type=\"text/javascript\">");
        if (accion.equals("Autorizar")) {
    		util.loggear("AUTORIZACIONES", "TICKET " + idTicket + " AUTORIZADO", usuario, sanatorio); // LOG
        	out.println("alert('Autorizado con éxito.');");
        }else {
    		util.loggear("AUTORIZACIONES", "TICKET " + idTicket + " RECHAZADO", usuario, sanatorio); // LOG
        	out.println("alert('Rechazado con éxito.');");	
        }
        out.println("location= '" + request.getContextPath() + "/menu.jsp';");
        out.println("</script>");
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
