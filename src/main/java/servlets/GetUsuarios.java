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

import main.java.clases.Usuario;
import main.java.clases.Utils;

public class GetUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetUsuarios() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Usuario> usuarios = new ArrayList<Usuario>();
				
		Connection con = null;
		
        Utils util = new Utils();
        String dbURL = util.urlConexion();
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(dbURL);
            Statement stmt = con.createStatement();
            Statement stmt2 = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT usu_IdUsuario, usu_Apellido, usu_Nombre, usu_NroDoc, usu_Password, usu_Email, usu_Username FROM Usuarios");
            while (rs.next()) {
            	Integer userId = rs.getInt("usu_IdUsuario");
            	String apellido = rs.getString("usu_Apellido");
            	String nombre = rs.getString("usu_Nombre");
            	String username = rs.getString("usu_Username");
            	String nroDoc = rs.getString("usu_NroDoc");
            	String mail = rs.getString("usu_Email");
            	String password = rs.getString("usu_Password");
            	String sanatorios = "";
            	
            	ResultSet rs2 = stmt2.executeQuery("SELECT S.san_Nombre FROM UsuariosSanatorio US JOIN Sanatorios S ON US.usa_IdSanatorio = S.san_IdSanatorio WHERE US.usa_IdUsuario = " + userId);
                while (rs2.next()) {
                	sanatorios = rs2.getString("san_Nombre") + " | " + sanatorios;
                }
        		if (sanatorios == null || sanatorios.isEmpty()) {
        			sanatorios = "No tiene sanatorios asociados";
        		}else {
                    sanatorios = sanatorios.substring(0, sanatorios.length() - 3);        			
        		}
            	
            	Usuario usr = new Usuario(userId, username, nombre, apellido, nroDoc, mail, sanatorios, password);
            	usuarios.add(usr);
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
        request.setAttribute("usuarios", usuarios);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
