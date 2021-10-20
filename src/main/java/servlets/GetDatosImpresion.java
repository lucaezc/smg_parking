package main.java.servlets;

import java.io.IOException;
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
import com.google.gson.Gson;
import main.java.clases.DatosImpresion;
import main.java.clases.Utils;

public class GetDatosImpresion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetDatosImpresion() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idTicket = request.getParameter("idTicket");
		String nombreUsuario = request.getSession(false).getAttribute("nombreUsuario").toString();
		String nombreSanatorio = request.getSession(false).getAttribute("nombreSanatorio").toString();
		
		Connection con = null;
        Utils util = new Utils();
        String dbURL = util.urlConexion();
        
        Date fechaHoraIngreso = null;
        Date fechaHoraEgreso = null; 
        String fechaHoraIngresoS = null; String fechaHoraEgresoS = null; String[] fechaHora = new String[2];
        DatosImpresion dt = null;
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(dbURL);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT tic_IdTipoTicket, tic_FechaHoraIngreso, tic_FechaHoraEgreso, tic_Patente, tic_Importe, tic_NombrePaciente, tic_ApellidoPaciente, tic_DNIPaciente, tic_NroEncuentroPaciente, " +
            		"tic_SuitePaciente, tic_MarcaVehiculo, tic_NhcPaciente, TV.tiv_Nombre, M.med_Nombre, M.med_Apellido, M.med_DNI, M.med_MatriculaNacional, E.est_Nombre FROM Tickets T JOIN TiposVehiculo TV ON T.tic_IdTipoVehiculo = TV.tiv_IdTipoVehiculo " + 
            		"JOIN Medicos M ON T.tic_IdMedico = M.med_IdMedico JOIN Estacionamientos E ON tic_IdEstacionamiento = E.est_IdEstacionamiento WHERE tic_IdTicket = " + idTicket + " " +
            		"UNION SELECT tic_IdTipoTicket, tic_FechaHoraIngreso, tic_FechaHoraEgreso, tic_Patente, tic_Importe, tic_NombrePaciente, tic_ApellidoPaciente, tic_DNIPaciente, tic_NroEncuentroPaciente, tic_SuitePaciente, tic_MarcaVehiculo, tic_NhcPaciente, TV.tiv_Nombre, NULL, NULL, NULL, NULL, " + 
            		"E.est_Nombre FROM Tickets T JOIN TiposVehiculo TV ON T.tic_IdTipoVehiculo = TV.tiv_IdTipoVehiculo JOIN Estacionamientos E ON tic_IdEstacionamiento = E.est_IdEstacionamiento " + 
            		"WHERE tic_IdTicket NOT IN (SELECT tic_IdTicket FROM Tickets T JOIN TiposVehiculo TV ON T.tic_IdTipoVehiculo = TV.tiv_IdTipoVehiculo JOIN Medicos M ON T.tic_IdMedico = M.med_IdMedico JOIN Estacionamientos E ON tic_IdEstacionamiento = E.est_IdEstacionamiento) AND tic_IdTicket = " + idTicket);
                        
            while (rs.next()) {
            	fechaHoraIngreso = rs.getTimestamp("tic_FechaHoraIngreso");
				fechaHoraEgreso = rs.getTimestamp("tic_FechaHoraEgreso");
		        fechaHora = util.splitFechaHora(fechaHoraIngreso);
				fechaHoraIngresoS = util.convertidorFecha(fechaHora[0], fechaHora[1]);
				if (fechaHoraEgreso == null) {
					fechaHoraEgresoS = null;
				}else {
			        fechaHora = util.splitFechaHora(fechaHoraEgreso);
			        fechaHoraEgresoS = util.convertidorFecha(fechaHora[0], fechaHora[1]);
				}

            	dt = new DatosImpresion(idTicket, rs.getInt("tic_IdTipoTicket"), fechaHoraIngresoS, fechaHoraEgresoS, rs.getString("tic_Patente"), rs.getDouble("tic_Importe"), util.normalizeSymbolsAndAccents(rs.getString("tic_NombrePaciente")), util.normalizeSymbolsAndAccents(rs.getString("tic_ApellidoPaciente")),
									    rs.getString("tic_DNIPaciente"), rs.getString("tic_NhcPaciente"), rs.getString("tic_NroEncuentroPaciente"), rs.getString("tic_SuitePaciente"), util.normalizeSymbolsAndAccents(rs.getString("tic_MarcaVehiculo")), rs.getString("tiv_Nombre"), util.normalizeSymbolsAndAccents(rs.getString("med_Nombre")),
									    util.normalizeSymbolsAndAccents(rs.getString("med_Apellido")), rs.getString("med_DNI"), rs.getString("med_MatriculaNacional"), util.normalizeSymbolsAndAccents(rs.getString("est_Nombre")), nombreUsuario, nombreSanatorio.toUpperCase());
          
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
        String json = new Gson().toJson(dt);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);     
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
