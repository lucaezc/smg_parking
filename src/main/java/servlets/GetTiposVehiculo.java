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

import main.java.clases.TiposVehiculo;
import main.java.clases.Utils;

public class GetTiposVehiculo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetTiposVehiculo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<TiposVehiculo> tiposV = new ArrayList<TiposVehiculo>();
				
		Connection con = null;
		
        Utils util = new Utils();
        String dbURL = util.urlConexion();
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(dbURL);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT tiv_IdTipoVehiculo, tiv_Nombre FROM TiposVehiculo ORDER BY tiv_Nombre ASC");
            while (rs.next()) {
            	TiposVehiculo tv = new TiposVehiculo(rs.getInt("tiv_IdTipoVehiculo"), rs.getString("tiv_Nombre"));
            	tiposV.add(tv);
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
        request.setAttribute("tiposVehiculo", tiposV);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
