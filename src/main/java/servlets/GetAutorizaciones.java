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

import main.java.clases.Autorizacion;
import main.java.clases.Utils;

public class GetAutorizaciones extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetAutorizaciones() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Autorizacion> autorizaciones = new ArrayList<Autorizacion>();	
		
        Connection con = null;
        Utils util = new Utils();
        String dbURL = util.urlConexion();
        
        String idSanatorio = request.getSession(false).getAttribute("sanatorio").toString();
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(dbURL);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT aut_IdAutorizacion, aut_IdSanatorio, aut_IdEstacionamiento, E.est_Nombre, aut_IdUsuarioSolicitante, aut_FechaPedido, aut_NomApeAutorizado, aut_IdUsuarioAutorizante, (U.usu_Apellido + ', ' + U.usu_Nombre) 'UsuarioSolicitante', " + 
            								 "aut_FechaAutorizacion, aut_Autorizado, aut_Observaciones, aut_Ticket FROM Autorizaciones A JOIN Usuarios U ON A.aut_IdUsuarioSolicitante = U.usu_IdUsuario JOIN Estacionamientos E ON E.est_IdEstacionamiento = A.aut_IdEstacionamiento " + 
            								 "WHERE aut_Autorizado = 'N' AND aut_IdSanatorio = " + idSanatorio);
            while (rs.next()) {
            	Autorizacion aut = new Autorizacion(rs.getInt("aut_IdAutorizacion"), rs.getInt("aut_IdSanatorio"), rs.getInt("aut_IdEstacionamiento"), rs.getString("est_Nombre"), rs.getInt("aut_IdUsuarioSolicitante"), rs.getString("UsuarioSolicitante"), 
				            						rs.getTimestamp("aut_FechaPedido"), rs.getInt("aut_IdUsuarioAutorizante"), null, rs.getTimestamp("aut_FechaAutorizacion"), rs.getString("aut_NomApeAutorizado"),
				            						rs.getString("aut_Autorizado"), rs.getString("aut_Observaciones"), rs.getInt("aut_Ticket"));
            	autorizaciones.add(aut);
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
        request.setAttribute("autorizaciones", autorizaciones);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
