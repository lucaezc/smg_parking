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

import com.google.gson.Gson;

import main.java.clases.Ticket;
import main.java.clases.Utils;

public class GetTicketsFiltrados extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetTicketsFiltrados() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Ticket> tickets = new ArrayList<Ticket>();
		Utils util = new Utils();

		String fechaDesde = request.getParameter("fechaDesde");
		String fechaHasta = request.getParameter("fechaHasta");
		String filtros = request.getParameter("filtros");
		String[] filtrosArray = filtros.split(" ");
		String tiposVehiculo = "";
		String tiposTicket = "";
		String estadosTicket = "";
		
		for (int i = 0; i < filtrosArray.length; i++) {
			if (filtrosArray[i].length() > 1) {
				String prefijo = filtrosArray[i].substring(0, 2);
				String valor = filtrosArray[i].substring(3, filtrosArray[i].length());
				
				if (valor.equals("CamionetaXL")) { valor = "Camioneta XL"; }
				if (valor.equals("PendienteAut")) { valor = "Pendiente de autorización"; }
				if (valor.equals("AbiertoRechazado")) { valor = "Abierto/Rechazado"; }
				if (valor.equals("AutCerrado")) { valor = "Autorizado/Cerrado"; }
				
				if (prefijo.equals("tv")) {
					tiposVehiculo = "'" + valor + "', " + tiposVehiculo; 
				}
				if (prefijo.equals("tt")) {
					tiposTicket = "'" + valor + "', " + tiposTicket; 
				}
				if (prefijo.equals("et")) {
					estadosTicket = util.getEstado(valor) + ", " + estadosTicket; 
				}
			}
		}
		
		String fDesde = null, fHasta = null;
		if (fechaDesde != null && !fechaDesde.isEmpty()) { fDesde = util.convertidorFecha(fechaDesde, "").trim() + " 00:00:00"; }
		if (fechaHasta != null && !fechaHasta.isEmpty()) { fHasta = util.convertidorFecha(fechaHasta, "").trim() + " 23:59:00"; }
		
		if (tiposVehiculo != null && !tiposVehiculo.isEmpty()) { tiposVehiculo = tiposVehiculo.substring(0, tiposVehiculo.length()-2);}
		if (tiposTicket != null && !tiposTicket.isEmpty()) { tiposTicket = tiposTicket.substring(0, tiposTicket.length()-2);}
		if (estadosTicket != null && !estadosTicket.isEmpty()) { estadosTicket = estadosTicket.substring(0, estadosTicket.length()-2);}	
			
		Connection con = null;
		
        String idSanatorio = request.getSession(false).getAttribute("sanatorio").toString();
		
        String dbURL = util.urlConexion();
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(dbURL);
            Statement stmt = con.createStatement();
            String query = null;
            
            query = "SELECT tic_IdTicket, tic_IdTipoTicket, tic_IdEstacionamiento, tic_IdTipoVehiculo, tic_IdUsuarioSanatorio, tic_FechaHoraIngreso, tic_FechaHoraEgreso, tic_IdUsuarioRegistro, " +
            		"(U.usu_Apellido + ', ' + U.usu_Nombre) 'UsuarioRegistro', tic_FechaHoraRegistro, tic_IdUsuarioModifica, tic_FechaHoraModifica, tic_Patente, tic_Importe, tic_NombrePaciente, " +
            		"tic_ApellidoPaciente, tic_DNIPaciente, tic_NroEncuentroPaciente, tic_SuitePaciente, tic_MarcaVehiculo, tic_NhcPaciente, tic_IdMedico, TT.tip_Nombre, TV.tiv_Nombre, M.med_Nombre, " + 
            		"M.med_Apellido, M.med_DNI, E.est_Nombre, tic_Estado, tic_Baja, tic_Excedido, ET.etk_Nombre, tic_IdUsuarioCierre, tic_FechaHoraCierre, (SELECT (US.usu_Apellido + ', ' + US.usu_Nombre) FROM Usuarios US WHERE US.usu_IdUsuario = T.tic_IdUsuarioCierre) 'UsuarioCierre' FROM Tickets T JOIN TiposVehiculo TV ON T.tic_IdTipoVehiculo = TV.tiv_IdTipoVehiculo " + 
					"JOIN TiposTicket TT ON T.tic_IdTipoTicket = TT.tip_IdTipoTicket JOIN Medicos M ON T.tic_IdMedico = M.med_IdMedico JOIN Estacionamientos E ON tic_IdEstacionamiento = E.est_IdEstacionamiento " +
					"JOIN Usuarios U ON T.tic_IdUsuarioRegistro = U.usu_IdUsuario JOIN EstadosTickets ET ON T.tic_Estado = ET.etk_IdEstadoTicket WHERE E.est_IdSanatorio = " + idSanatorio;
            
            if (tiposVehiculo != null && !tiposVehiculo.isEmpty()) { query = query + " AND TV.tiv_Nombre IN (" + tiposVehiculo + ")"; }
            if (tiposTicket != null && !tiposTicket.isEmpty()) { query = query + " AND TT.tip_Nombre IN (" + tiposTicket + ")"; }
            if (estadosTicket != null && !estadosTicket.isEmpty()) { query = query + " AND tic_Estado IN (" + estadosTicket + ")"; }
    		if (fechaDesde != null && !fechaDesde.isEmpty()) { query = query + " AND tic_FechaHoraIngreso >= '" + fDesde + "'"; }
    		if (fechaHasta != null && !fechaHasta.isEmpty()) { query = query + " AND tic_FechaHoraIngreso <= '" + fHasta + "'"; }
            
            query = query + "UNION SELECT tic_IdTicket, tic_IdTipoTicket, tic_IdEstacionamiento, tic_IdTipoVehiculo, tic_IdUsuarioSanatorio, tic_FechaHoraIngreso, tic_FechaHoraEgreso, tic_IdUsuarioRegistro, " + 
            				"(U.usu_Apellido + ', ' + U.usu_Nombre) 'UsuarioRegistro', tic_FechaHoraRegistro, tic_IdUsuarioModifica, tic_FechaHoraModifica, tic_Patente, tic_Importe, tic_NombrePaciente, " + 
            				"tic_ApellidoPaciente, tic_DNIPaciente, tic_NroEncuentroPaciente, tic_SuitePaciente, tic_MarcaVehiculo, tic_NhcPaciente, tic_IdMedico, TV.tiv_Nombre, TT.tip_Nombre, NULL, NULL, NULL, " + 
            				"E.est_Nombre, tic_Estado, tic_Baja, tic_Excedido, ET.etk_Nombre, tic_IdUsuarioCierre, tic_FechaHoraCierre, (SELECT (US.usu_Apellido + ', ' + US.usu_Nombre) FROM Usuarios US WHERE US.usu_IdUsuario = T.tic_IdUsuarioCierre) 'UsuarioCierre' FROM Tickets T JOIN TiposVehiculo TV ON T.tic_IdTipoVehiculo = TV.tiv_IdTipoVehiculo JOIN TiposTicket TT ON T.tic_IdTipoTicket = " + 
            				"TT.tip_IdTipoTicket JOIN Estacionamientos E ON tic_IdEstacionamiento = E.est_IdEstacionamiento JOIN Usuarios U ON T.tic_IdUsuarioRegistro = U.usu_IdUsuario JOIN EstadosTickets ET ON T.tic_Estado = ET.etk_IdEstadoTicket " + 
            				"WHERE tic_IdTicket NOT IN (SELECT tic_IdTicket FROM Tickets T JOIN TiposVehiculo TV ON T.tic_IdTipoVehiculo = TV.tiv_IdTipoVehiculo JOIN TiposTicket TT ON T.tic_IdTipoTicket = TT.tip_IdTipoTicket " +
            				"JOIN Medicos M ON T.tic_IdMedico = M.med_IdMedico JOIN Estacionamientos E ON tic_IdEstacionamiento = E.est_IdEstacionamiento) AND E.est_IdSanatorio = " + idSanatorio;
            
            if (tiposVehiculo != null && !tiposVehiculo.isEmpty()) { query = query + " AND TV.tiv_Nombre IN (" + tiposVehiculo + ")"; }
            if (tiposTicket != null && !tiposTicket.isEmpty()) { query = query + " AND TT.tip_Nombre IN (" + tiposTicket + ")"; }
            if (estadosTicket != null && !estadosTicket.isEmpty()) { query = query + " AND tic_Estado IN (" + estadosTicket + ")"; }
    		if (fechaDesde != null && !fechaDesde.isEmpty()) { query = query + " AND tic_FechaHoraIngreso >= '" + fDesde + "'"; }
    		if (fechaHasta != null && !fechaHasta.isEmpty()) { query = query + " AND tic_FechaHoraIngreso <= '" + fHasta + "'"; }

    		ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
            	Ticket t = new Ticket(rs.getInt("tic_IdTicket"), rs.getInt("tic_IdTipoTicket"), rs.getInt("tic_IdEstacionamiento"), rs.getInt("tic_IdTipoVehiculo"), rs.getInt("tic_IdUsuarioSanatorio"),
            						  rs.getTimestamp("tic_FechaHoraIngreso"), rs.getTimestamp("tic_FechaHoraEgreso"), rs.getInt("tic_IdUsuarioRegistro"), rs.getString("UsuarioRegistro"), rs.getTimestamp("tic_FechaHoraRegistro"), 
            						  rs.getInt("tic_IdUsuarioModifica"), rs.getTimestamp("tic_FechaHoraModifica"), rs.getString("tic_Patente"), rs.getDouble("tic_Importe"), rs.getString("tic_NombrePaciente"), 
            						  rs.getString("tic_ApellidoPaciente"), rs.getString("tic_DNIPaciente"), rs.getString("tic_NhcPaciente"), rs.getString("tic_NroEncuentroPaciente"), rs.getString("tic_SuitePaciente"), 
            						  rs.getString("tic_MarcaVehiculo"), rs.getInt("tic_IdMedico"), rs.getString("tip_Nombre"), rs.getString("tiv_Nombre"), rs.getString("med_Nombre"), rs.getString("med_Apellido"), 
            						  rs.getString("med_DNI"), rs.getString("est_Nombre"), rs.getInt("tic_Estado"), rs.getString("etk_Nombre"), rs.getBoolean("tic_Baja"), rs.getBoolean("tic_Excedido"), rs.getInt("tic_IdUsuarioCierre"), rs.getString("UsuarioCierre"), rs.getTimestamp("tic_FechaHoraCierre"));            	
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
        String json = new Gson().toJson(tickets);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);    
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
