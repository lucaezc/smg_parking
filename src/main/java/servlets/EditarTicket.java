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

public class EditarTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditarTicket() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String idTicket = request.getParameter("idTicketE");
		String fechaIngreso = request.getParameter("fechaIngresoE");
		String horaIngreso = request.getParameter("horaIngresoE");
		String idTipoTicket = request.getParameter("selectTiposTicketE");
		String idEstacionamiento = request.getParameter("selectEstacionamientoE");
		String idTipoVehiculo = request.getParameter("selectVehiculoE");
		String patente = request.getParameter("patenteE");
		String dni = request.getParameter("dniE");
		String nroEncuentro = request.getParameter("nroEncE");
		String suite = request.getParameter("suiteE");
		String nhc = request.getParameter("nhcE");
		String marcaVehiculo = request.getParameter("marcaVehiculoE");
		String nombre = request.getParameter("nombreE");
		String apellido = request.getParameter("apellidoE");
        Utils util = new Utils();

		String idUsuario = request.getSession(false).getAttribute("idUsuario").toString();
        String fechaCompleta = util.convertidorFecha(fechaIngreso, horaIngreso);
        String fechaActual = util.fechaHoraActual();
        
		Connection con = null;
        String dbURL = util.urlConexion();

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(dbURL);
            Statement stmt = con.createStatement();
            String query = null;
        	if (idTipoTicket.equals("1")) {
            	query = "UPDATE Tickets SET tic_FechaHoraIngreso = '" + fechaCompleta + "', tic_IdTipoTicket = " + idTipoTicket + ", tic_IdEstacionamiento = " + idEstacionamiento + ", " + 
            			"tic_IdTipoVehiculo = " + idTipoVehiculo + ", tic_FechaHoraModifica = '" + fechaActual + "', tic_IdUsuarioModifica = '" + idUsuario + "', tic_Patente = '" + patente + "', " + 
            			"tic_NombrePaciente = '" + nombre + "', tic_ApellidoPaciente = '" + apellido + "', tic_DNIPaciente = '" + dni + "', tic_NroEncuentroPaciente = '" + nroEncuentro + "', " + 
            			"tic_SuitePaciente = '" + suite + "', tic_MarcaVehiculo = '" + marcaVehiculo + "', tic_NhcPaciente = '" + nhc + "' WHERE tic_IdTicket = " + idTicket;
        	}
        	
        	if (idTipoTicket.equals("2") || idTipoTicket.equals("3")) {
        		query = "UPDATE Tickets SET tic_FechaHoraIngreso = '" + fechaCompleta + "', tic_IdTipoTicket = " + idTipoTicket + ", tic_IdEstacionamiento = " + idEstacionamiento + ", " + 
            			"tic_IdTipoVehiculo = " + idTipoVehiculo + ", tic_FechaHoraModifica = '" + fechaActual + "', tic_IdUsuarioModifica = '" + idUsuario + "', tic_Patente = '" + patente + "' " +
        				"WHERE tic_IdTicket = " + idTicket;
        	}         	

            stmt.executeUpdate(query);

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
		util.loggear("TICKETS", "MODIFICACION DEL TICKET " + idTicket, usuario, sanatorio); // LOG
        
        PrintWriter out = response.getWriter();
        out.println("<script type=\"text/javascript\">");
        out.println("alert('Ticket " + idTicket + " modificado correctamente');");
        out.println("location= '" + request.getContextPath() + "/menu.jsp';");
        out.println("</script>");
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
