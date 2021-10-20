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

public class AltaTicketAutorizado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AltaTicketAutorizado() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String nombresAutorizado = request.getParameter("nombresAutorizado");
		String apellidoAutorizado = request.getParameter("apellidoAutorizado");
		String dni = request.getParameter("dni");
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
		
		String permisos = request.getSession(false).getAttribute("permisos").toString();
		String idSanatorio = request.getSession(false).getAttribute("sanatorio").toString();
		Integer estadoTicket = 0;
		String nomApeAutorizado = apellidoAutorizado + ", " + nombresAutorizado;
		
		if (permisos.equals("Valet Parking")) {
			estadoTicket = util.getEstado("Pendiente de autorización");
		}else {
			estadoTicket = util.getEstado("Abierto");
		}

		Connection con = null;
        String dbURL = util.urlConexion();
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(dbURL);
            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO Tickets (tic_IdTipoTicket, tic_IdEstacionamiento, tic_IdTipoVehiculo, tic_IdUsuarioSanatorio, tic_FechaHoraIngreso, tic_FechaHoraEgreso, " + 
            				   "tic_IdUsuarioRegistro, tic_FechaHoraRegistro, tic_IdUsuarioModifica, tic_FechaHoraModifica, tic_Patente, tic_Importe, tic_NombrePaciente, tic_ApellidoPaciente, " + 
            				   "tic_DNIPaciente, tic_NroEncuentroPaciente, tic_SuitePaciente, tic_MarcaVehiculo, tic_IdMedico, tic_NhcPaciente, tic_Estado, tic_Excedido) VALUES (3, " + idEstacionamiento + ", " + idVehiculo + ", " + 
            				   idUsuarioSanatorio + ", '" + fechaIngreso + "', NULL, " + idUsuario + ", '" + fechaSistema + "', NULL, NULL, '" + nroPatente + "', NULL, '" +
            				   nombresAutorizado + "', '" + apellidoAutorizado + "', '" + dni + "', NULL, NULL, NULL, NULL, NULL, " + estadoTicket + ", " + excedido + ")", Statement.RETURN_GENERATED_KEYS);

            util.calculoLugaresEstacionamiento(idEstacionamiento, idVehiculo, "Ingreso");
            
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            Integer idTicket = rs.getInt(1);
            
    		if (permisos.equals("Valet Parking")) {
                stmt.executeUpdate("INSERT INTO Autorizaciones (aut_IdSanatorio, aut_IdEstacionamiento, aut_IdUsuarioSolicitante, aut_FechaPedido, aut_NomApeAutorizado, aut_IdUsuarioAutorizante, aut_FechaAutorizacion, aut_Autorizado, aut_Observaciones, aut_Ticket) VALUES (" +
                				   idSanatorio + ", " + idEstacionamiento + ", " + idUsuario + ", '" + fechaSistema + "','" + nomApeAutorizado + "' , NULL, NULL, 'N', NULL, " + idTicket + ")");	
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
		util.loggear("TICKET PARA AUTORIZADO", "INGRESO DE TICKET", usuario, sanatorio); // LOG
        
        PrintWriter out = response.getWriter();
        out.println("<script type=\"text/javascript\">");
        out.println("alert('Ticket emitido correctamente');");
        out.println("location= '" + request.getContextPath() + "/menu.jsp';");
        out.println("</script>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
