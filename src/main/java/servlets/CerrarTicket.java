package main.java.servlets;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.clases.Utils;

public class CerrarTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CerrarTicket() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String idTicket = request.getParameter("idTicketCerrar");
		String diaSalida = request.getParameter("date");
		String horaSalida = request.getParameter("hour");
		String idTipoVehiculo = request.getParameter("idTipoVehiculoCerrar");
		String idEstacionamiento = request.getParameter("idEstacionamientoCerrar");

        Utils util = new Utils();

		String idUsuario = request.getSession(false).getAttribute("idUsuario").toString();
        String fechaCompleta = util.convertidorFecha(diaSalida, horaSalida);
        String fechaActual = util.fechaHoraActual();
        
		Connection con = null;
        String dbURL = util.urlConexion();

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(dbURL);
            Statement stmt = con.createStatement();
            String query = null;
            
            Integer ticketEstadoAnterior = 0;
            
            ResultSet rs = stmt.executeQuery("SELECT tic_Estado FROM Tickets WHERE tic_IdTicket = " + idTicket);
			while (rs.next()) {
				ticketEstadoAnterior = rs.getInt("tic_Estado");
			}
			
            Integer estadoAutorizado = util.getEstado("Autorizado");
            Integer estadoTicket = 0;

			if (ticketEstadoAnterior.equals(estadoAutorizado)) {
				estadoTicket = util.getEstado("Autorizado/Cerrado");
			}else {
				estadoTicket = util.getEstado("Cerrado");
			}
            
        	query = "UPDATE Tickets SET tic_FechaHoraEgreso = '" + fechaCompleta + "', tic_FechaHoraCierre = '" + fechaActual + "', tic_IdUsuarioCierre = '" + idUsuario + "', tic_Estado = " + estadoTicket + " " +
        			"WHERE tic_IdTicket = " + idTicket;

            stmt.executeUpdate(query);
            
            util.calculoLugaresEstacionamiento(idEstacionamiento, idTipoVehiculo, "Egreso");

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
		util.loggear("TICKETS", "TICKET " + idTicket + " CERRADO", usuario, sanatorio); // LOG
		util.loggear("TICKETS", "TICKET " + idTicket + " ENVIADO PARA IMPRIMIR", usuario, sanatorio); // LOG
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
