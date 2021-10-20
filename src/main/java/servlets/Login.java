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
import javax.servlet.http.HttpSession;

import main.java.clases.Utils;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L; 

    public Login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String usuario = request.getParameter("usuario");
		String password = request.getParameter("password");
		String idSanatorio = request.getParameter("sanatorio");
				
		Connection con = null;
        Utils util = new Utils();
        String dbURL = util.urlConexion();
		 
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(dbURL);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT usu_IdUsuario, usu_Password, usu_Apellido, usu_Nombre FROM Usuarios WHERE usu_UserName = '" + usuario + "'");
            
            Integer usu = 0, usuSanatorio = 0;
            String pass = null, nombreUsu = null, apellidoUsu = null, sanatorio = null, mensajeError = null, perAdmin = "N", perCoordinador = "N", perUser = "N", permisos = null;
            while (rs.next()) {
            	usu = rs.getInt("usu_IdUsuario");
            	pass = rs.getString("usu_Password");
            	nombreUsu = rs.getString("usu_Nombre");
            	apellidoUsu = rs.getString("usu_Apellido");
            }
            
            rs = stmt.executeQuery("SELECT san_Nombre FROM Sanatorios WHERE san_IdSanatorio = " + idSanatorio);
            while (rs.next()) {
            	sanatorio = rs.getString("san_Nombre");
            }
            
            rs = stmt.executeQuery("SELECT usa_IdUsuarioSanatorio, per_Admin, per_User, per_Coordinador FROM UsuariosSanatorio US JOIN Perfiles P ON US.usa_IdPerfil = P.per_IdPerfil WHERE usa_IdUsuario = " + usu + " AND usa_IdSanatorio = " + idSanatorio);
            while (rs.next()) {
            	usuSanatorio = rs.getInt("usa_IdUsuarioSanatorio");
            	perAdmin = rs.getString("per_Admin");
            	perCoordinador = rs.getString("per_Coordinador");
            	perUser = rs.getString("per_User");
            	permisos = util.tipoPerfil(perAdmin, perCoordinador, perUser);
            }
 
            if (!usu.equals(0)) {
            	
            	if (password.equals(pass)) {
            		String nomApeUsuario = apellidoUsu + " " + nombreUsu;
            		request.setAttribute("usuario", nomApeUsuario);
            		request.setAttribute("sanatorio", sanatorio);
            		
            		// ESTABLECE VARIABLES DE SESIÓN
            		HttpSession session = request.getSession();
            		session.setAttribute("usuario", usuario);
            		session.setAttribute("idUsuario", usu);
            		session.setAttribute("idUsuarioSanatorio", usuSanatorio);
            		session.setAttribute("sanatorio", idSanatorio);
            		session.setAttribute("permisos", permisos);
            		session.setAttribute("nombreUsuario", nomApeUsuario);
            		session.setAttribute("nombreSanatorio", sanatorio);

            		util.loggear("LOGIN", "INGRESO AL SISTEMA", usu, Integer.parseInt(idSanatorio)); // LOG
            		response.sendRedirect(request.getContextPath() + "/menu.jsp");
            	}else {
            		mensajeError = "La contraseña ingresada para ese usuario es incorrecta.";
            		request.setAttribute("mensajeError", mensajeError);
            		response.sendRedirect(request.getContextPath() + "/index.jsp");
            	}
            }else {
        		mensajeError = "El usuario ingresado no existe.";
        		request.setAttribute("mensajeError", mensajeError);
        		response.sendRedirect(request.getContextPath() + "/index.jsp");
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
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
