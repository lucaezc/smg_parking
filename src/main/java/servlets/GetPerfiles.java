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

import main.java.clases.Perfil;
import main.java.clases.Utils;

public class GetPerfiles extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetPerfiles() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Perfil> perfiles = new ArrayList<Perfil>();
				
		Connection con = null;
		
        Utils util = new Utils();
        String dbURL = util.urlConexion();
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(dbURL);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT per_IdPerfil, per_Nombre, per_Admin, per_User, per_Coordinador FROM Perfiles ORDER BY per_Nombre ASC");
            while (rs.next()) {
            	Perfil p = new Perfil(rs.getInt("per_IdPerfil"), rs.getString("per_Nombre"), rs.getString("per_Admin"), rs.getString("per_User"), rs.getString("per_Coordinador"));
            	perfiles.add(p);
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
        request.setAttribute("perfiles", perfiles);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
