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

import main.java.clases.Ticket;
import main.java.clases.Utils;

public class GetTicketsReportes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetTicketsReportes() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Ticket> tickets = new ArrayList<Ticket>();
				
		Connection con = null;
		
        String idSanatorio = request.getSession(false).getAttribute("sanatorio").toString();
		
        Utils util = new Utils();
        String dbURL = util.urlConexion();
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(dbURL);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT tic_IdTicket, tic_IdTipoTicket, tic_IdEstacionamiento, tic_IdTipoVehiculo, tic_IdUsuarioSanatorio, tic_FechaHoraIngreso, tic_FechaHoraEgreso, tic_IdUsuarioRegistro, (U.usu_Apellido + ', ' + U.usu_Nombre) 'UsuarioRegistro', " + 
            								 "tic_FechaHoraRegistro, tic_IdUsuarioModifica, tic_FechaHoraModifica, tic_Patente, tic_Importe, tic_NombrePaciente, tic_ApellidoPaciente, tic_DNIPaciente, " +
            								 "tic_NroEncuentroPaciente, tic_SuitePaciente, tic_MarcaVehiculo, tic_NhcPaciente, tic_IdMedico, TT.tip_Nombre, TV.tiv_Nombre, M.med_Nombre, M.med_Apellido, M.med_DNI, E.est_Nombre, " + 
            								 "tic_Estado, tic_Baja, tic_Excedido, ET.etk_Nombre, tic_IdUsuarioCierre, tic_FechaHoraCierre, (SELECT (US.usu_Apellido + ', ' + US.usu_Nombre) FROM Usuarios US WHERE US.usu_IdUsuario = T.tic_IdUsuarioCierre) 'UsuarioCierre' FROM Tickets T JOIN TiposVehiculo TV ON T.tic_IdTipoVehiculo = TV.tiv_IdTipoVehiculo " + 
            								 "JOIN TiposTicket TT ON T.tic_IdTipoTicket = TT.tip_IdTipoTicket JOIN Medicos M ON T.tic_IdMedico = M.med_IdMedico JOIN Estacionamientos E ON tic_IdEstacionamiento = E.est_IdEstacionamiento " +
            								 "JOIN Usuarios U ON T.tic_IdUsuarioRegistro = U.usu_IdUsuario " +
            								 "JOIN EstadosTickets ET ON T.tic_Estado = ET.etk_IdEstadoTicket " +			 
            								 "WHERE E.est_IdSanatorio = " + idSanatorio + " " +
            								 "UNION " +
            								 "SELECT tic_IdTicket, tic_IdTipoTicket, tic_IdEstacionamiento, tic_IdTipoVehiculo, tic_IdUsuarioSanatorio, tic_FechaHoraIngreso, tic_FechaHoraEgreso, tic_IdUsuarioRegistro, (U.usu_Apellido + ', ' + U.usu_Nombre) 'UsuarioRegistro', tic_FechaHoraRegistro, " +
            								 "tic_IdUsuarioModifica, tic_FechaHoraModifica, tic_Patente, tic_Importe, tic_NombrePaciente, tic_ApellidoPaciente, tic_DNIPaciente, tic_NroEncuentroPaciente, tic_SuitePaciente, tic_MarcaVehiculo, " +
            								 "tic_NhcPaciente, tic_IdMedico, TV.tiv_Nombre, TT.tip_Nombre, NULL, NULL, NULL, E.est_Nombre, tic_Estado, tic_Baja, tic_Excedido, ET.etk_Nombre, tic_IdUsuarioCierre, tic_FechaHoraCierre, (SELECT (US.usu_Apellido + ', ' + US.usu_Nombre) FROM Usuarios US WHERE US.usu_IdUsuario = T.tic_IdUsuarioCierre) 'UsuarioCierre' " +
            								 "FROM Tickets T JOIN TiposVehiculo TV ON T.tic_IdTipoVehiculo = TV.tiv_IdTipoVehiculo JOIN TiposTicket TT ON T.tic_IdTipoTicket = TT.tip_IdTipoTicket JOIN Estacionamientos E ON tic_IdEstacionamiento = E.est_IdEstacionamiento JOIN Usuarios U ON T.tic_IdUsuarioRegistro = U.usu_IdUsuario " +
            								 "JOIN EstadosTickets ET ON T.tic_Estado = ET.etk_IdEstadoTicket " +
            								 "WHERE tic_IdTicket NOT IN (SELECT tic_IdTicket FROM Tickets T JOIN TiposVehiculo TV ON T.tic_IdTipoVehiculo = TV.tiv_IdTipoVehiculo JOIN TiposTicket TT ON T.tic_IdTipoTicket = TT.tip_IdTipoTicket " +
            								 "JOIN Medicos M ON T.tic_IdMedico = M.med_IdMedico JOIN Estacionamientos E ON tic_IdEstacionamiento = E.est_IdEstacionamiento) AND E.est_IdSanatorio = " + idSanatorio);
            while (rs.next()) {
            	Ticket t = new Ticket(rs.getInt("tic_IdTicket"), rs.getInt("tic_IdTipoTicket"), rs.getInt("tic_IdEstacionamiento"), rs.getInt("tic_IdTipoVehiculo"), rs.getInt("tic_IdUsuarioSanatorio"),
            						  rs.getTimestamp("tic_FechaHoraIngreso"), rs.getTimestamp("tic_FechaHoraEgreso"), rs.getInt("tic_IdUsuarioRegistro"), rs.getString("UsuarioRegistro"), rs.getTimestamp("tic_FechaHoraRegistro"), rs.getInt("tic_IdUsuarioModifica"), rs.getTimestamp("tic_FechaHoraModifica"),
            						  rs.getString("tic_Patente"), rs.getDouble("tic_Importe"), rs.getString("tic_NombrePaciente"), rs.getString("tic_ApellidoPaciente"), rs.getString("tic_DNIPaciente"),
            						  rs.getString("tic_NhcPaciente"), rs.getString("tic_NroEncuentroPaciente"), rs.getString("tic_SuitePaciente"), rs.getString("tic_MarcaVehiculo"), rs.getInt("tic_IdMedico"),
            						  rs.getString("tip_Nombre"), rs.getString("tiv_Nombre"), rs.getString("med_Nombre"), rs.getString("med_Apellido"), rs.getString("med_DNI"), rs.getString("est_Nombre"), rs.getInt("tic_Estado"), rs.getString("etk_Nombre"), rs.getBoolean("tic_Baja"), rs.getBoolean("tic_Excedido"), rs.getInt("tic_IdUsuarioCierre"), rs.getString("UsuarioCierre"), rs.getTimestamp("tic_FechaHoraCierre"));
            	tickets.add(t);
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
        request.setAttribute("tickets", tickets);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
