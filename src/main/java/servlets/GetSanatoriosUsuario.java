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

import main.java.clases.Sanatorio;
import main.java.clases.Utils;

public class GetSanatoriosUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetSanatoriosUsuario() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        
		String usuario = request.getParameter("usuario");

		List<Sanatorio> sanatorios = new ArrayList<Sanatorio>();
		List<Sanatorio> sanatoriosTodos = new ArrayList<Sanatorio>();

				
		Connection con = null;
		
        Utils util = new Utils();
        String dbURL = util.urlConexion();
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(dbURL);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT usu_IdUsuario FROM Usuarios WHERE usu_UserName = '" + usuario + "'");
            
            Integer usuarioId = 0;
            while (rs.next()) {
            	usuarioId = rs.getInt("usu_IdUsuario");
            }
            
            rs = stmt.executeQuery("SELECT san_IdSanatorio, san_Nombre FROM Sanatorios S JOIN UsuariosSanatorio US ON S.san_IdSanatorio = US.usa_IdSanatorio WHERE US.usa_IdUsuario = " + usuarioId + "ORDER BY san_Nombre ASC");
            while (rs.next()) {
            	Sanatorio s = new Sanatorio(rs.getInt("san_IdSanatorio"), rs.getString("san_Nombre"));
            	sanatorios.add(s);
            }
            
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT san_IdSanatorio, san_Nombre FROM Sanatorios ORDER BY san_Nombre ASC");
            while (rs.next()) {
            	Sanatorio s = new Sanatorio(rs.getInt("san_IdSanatorio"), rs.getString("san_Nombre"));
            	sanatoriosTodos.add(s);
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
        
        if (sanatorios.size() == 0) {
        	sanatorios = sanatoriosTodos;
        }
        
        String json = new Gson().toJson(sanatorios);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
 