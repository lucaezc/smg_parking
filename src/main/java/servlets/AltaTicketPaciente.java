package main.java.servlets;

import java.io.IOException;
import java.io.PrintWriter;
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

public class AltaTicketPaciente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AltaTicketPaciente() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String apellidoPaciente = request.getParameter("apellidoPaciente");
		String nombresPaciente = request.getParameter("nombresPaciente");
		String dniPaciente = request.getParameter("dni");
		String nhcPaciente = request.getParameter("nhc");
		String nroEncuentro = request.getParameter("encuentro");
		String suite = request.getParameter("suite");
		String marcaVehiculo = request.getParameter("marca");
		String idVehiculo = request.getParameter("selectVehiculo");
		String nroPatenteM = request.getParameter("patenteMercosur");
		String nroPatenteN = request.getParameter("patenteNacional");
		String nroPatente = null;
		
		if (nroPatenteM == null || nroPatenteM.isEmpty()) {
			nroPatente = nroPatenteN;
		}else {
			nroPatente = nroPatenteM;
		}
		
		String diaEntrada = request.getParameter("date");
		String horaEntrada = request.getParameter("hour");
        Utils util = new Utils();
		String fechaIngreso = util.convertidorFecha(diaEntrada, horaEntrada);
		String idEstacionamiento = request.getParameter("selectEstacionamiento");
		String idUsuarioSanatorio = request.getSession(false).getAttribute("idUsuarioSanatorio").toString();
		String idUsuario = request.getSession(false).getAttribute("idUsuario").toString();
		String fechaSistema = util.fechaHoraActual();
		String excedido = request.getParameter("excedido");

        Integer usuario = Integer.parseInt(request.getSession(false).getAttribute("idUsuario").toString());
        Integer sanatorio = Integer.parseInt(request.getSession(false).getAttribute("sanatorio").toString());
		
		if (validacionTicketExistente(dniPaciente) == false) {
			Connection con = null;
	        String dbURL = util.urlConexion();
	        
	        try {
	            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	            con = DriverManager.getConnection(dbURL);
	            Statement stmt = con.createStatement();
	            
	            Integer estadoTicket = util.getEstado("Abierto");
	            
	            stmt.executeUpdate("INSERT INTO Tickets (tic_IdTipoTicket, tic_IdEstacionamiento, tic_IdTipoVehiculo, tic_IdUsuarioSanatorio, tic_FechaHoraIngreso, tic_FechaHoraEgreso, " + 
	            				   "tic_IdUsuarioRegistro, tic_FechaHoraRegistro, tic_IdUsuarioModifica, tic_FechaHoraModifica, tic_Patente, tic_Importe, tic_NombrePaciente, tic_ApellidoPaciente, " + 
	            				   "tic_DNIPaciente, tic_NroEncuentroPaciente, tic_SuitePaciente, tic_MarcaVehiculo, tic_IdMedico, tic_NhcPaciente, tic_Estado, tic_Excedido) VALUES (1, " + idEstacionamiento + ", " + idVehiculo + ", " + 
	            				   idUsuarioSanatorio + ", '" + fechaIngreso + "', NULL, " + idUsuario + ", '" + fechaSistema + "', NULL, NULL, '" + nroPatente + "', NULL, '" +
	            				   nombresPaciente + "', '" + apellidoPaciente + "', '" + dniPaciente + "', '" + nroEncuentro + "', '" + suite + "', '" + marcaVehiculo + "', NULL, '" + nhcPaciente + "', " + estadoTicket + ", " + excedido + ")");

	            util.calculoLugaresEstacionamiento(idEstacionamiento, idVehiculo, "Ingreso");
	            
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
	        
			util.loggear("TICKET PARA PACIENTE", "INGRESO DE TICKET", usuario, sanatorio); // LOG
	        
	        PrintWriter out = response.getWriter();
	        out.println("<script type=\"text/javascript\">");
	        out.println("alert('Ticket emitido correctamente');");
	        out.println("location= '" + request.getContextPath() + "/menu.jsp';");
	        out.println("</script>");
		}else {
			
			util.loggear("TICKET PARA PACIENTE", "INGRESO DE TICKET: Ya hay un ticket abierto para el DNI ingresado", usuario, sanatorio); // LOG

	        PrintWriter out = response.getWriter();
	        out.println("<script type=\"text/javascript\">");
	        out.println("alert('Ya hay un ticket abierto para el DNI ingresado');");
	        out.println("location= '" + request.getContextPath() + "/menu.jsp';");
	        out.println("</script>");
		}
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private boolean validacionTicketExistente(String dniPaciente) {
		Connection con = null;
        Utils util = new Utils();
        String dbURL = util.urlConexion();
        Integer idTicket = 0;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(dbURL);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT tic_IdTicket FROM Tickets WHERE tic_DNIPaciente = '" + dniPaciente + "' AND tic_FechaHoraEgreso IS NULL");
            while (rs.next()) {
            	idTicket = rs.getInt("tic_IdTicket");
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
		
		if (idTicket.equals(0)) {
			return false;
		}else {
			return true;
		}
	}
}
