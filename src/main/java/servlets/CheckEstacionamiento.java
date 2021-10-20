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

import com.google.gson.Gson;

import main.java.clases.Estacionamiento;
import main.java.clases.Utils;

public class CheckEstacionamiento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckEstacionamiento() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idEstacionamiento = request.getParameter("idEstacionamiento");
		
        Connection con = null;
        Utils util = new Utils();
        String dbURL = util.urlConexion();
        Estacionamiento est = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(dbURL);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT est_IdEstacionamiento, est_Nombre, est_Capacidad, est_IdSanatorio, est_Estado, S.san_Nombre, EST.estado_Nombre, est_LugaresLibres, est_LugaresOcupados, " + 
            								 "est_LugaresExcedidos, est_ModificacionEstado FROM Estacionamientos E JOIN Sanatorios S ON E.est_IdSanatorio = S.san_IdSanatorio JOIN EstadosEstacionamientos EST ON E.est_Estado = EST.estado_Id " + 
            								 "WHERE est_IdEstacionamiento = " + idEstacionamiento);
            
            while (rs.next()) {
            	 est = new Estacionamiento(rs.getInt("est_IdEstacionamiento"), rs.getString("est_Nombre"), rs.getInt("est_Capacidad"), rs.getInt("est_IdSanatorio"), 
	            						   rs.getString("san_Nombre"), rs.getInt("est_Estado"), rs.getString("estado_Nombre"), rs.getInt("est_LugaresLibres"), rs.getInt("est_LugaresOcupados"),
	            						   rs.getInt("est_LugaresExcedidos"), rs.getTimestamp("est_ModificacionEstado"));
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
        String json = new Gson().toJson(est);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
