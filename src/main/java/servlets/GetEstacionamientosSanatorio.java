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

import main.java.clases.Estacionamiento;
import main.java.clases.Utils;

public class GetEstacionamientosSanatorio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetEstacionamientosSanatorio() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Estacionamiento> estacionamientos = new ArrayList<Estacionamiento>();	
		
        Connection con = null;
        Utils util = new Utils();
        String dbURL = util.urlConexion();
        
        String idSanatorio = request.getSession(false).getAttribute("sanatorio").toString();

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(dbURL);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT est_IdEstacionamiento, est_Nombre, est_Capacidad, est_IdSanatorio, est_Estado, S.san_Nombre, EST.estado_Nombre, est_LugaresLibres, est_LugaresOcupados, " + 
					 						 "est_LugaresExcedidos, est_ModificacionEstado FROM Estacionamientos E JOIN Sanatorios S ON E.est_IdSanatorio = S.san_IdSanatorio JOIN EstadosEstacionamientos EST ON E.est_Estado = EST.estado_Id " + 
					 						 "WHERE est_IdSanatorio = " + idSanatorio + " AND est_Estado = 1 ORDER BY est_Nombre ASC");
            while (rs.next()) {
            	Estacionamiento e = new Estacionamiento(rs.getInt("est_IdEstacionamiento"), rs.getString("est_Nombre"), rs.getInt("est_Capacidad"), rs.getInt("est_IdSanatorio"), 
														rs.getString("san_Nombre"), rs.getInt("est_Estado"), rs.getString("estado_Nombre"), rs.getInt("est_LugaresLibres"), rs.getInt("est_LugaresOcupados"),
														rs.getInt("est_LugaresExcedidos"), rs.getTimestamp("est_ModificacionEstado"));            	
            	estacionamientos.add(e);
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
        request.setAttribute("estacionamientos", estacionamientos);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
