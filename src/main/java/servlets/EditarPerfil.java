package main.java.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.clases.Utils;

public class EditarPerfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditarPerfil() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String idPerfil = request.getParameter("idPerfil");
		String nomPerfil = request.getParameter("nomPerfil");
		String permisos = request.getParameter("permisos");
		
		String per_Admin = null, per_Coordinador = null, per_User = null;
		if (permisos.equals("admin")) {
			per_Admin = "S";
			per_Coordinador = "N";
			per_User = "N";
		}
		if (permisos.equals("coordinador")) {
			per_Admin = "N";
			per_Coordinador = "S";
			per_User = "N";
		}		
		if (permisos.equals("user")) {
			per_Admin = "N";
			per_Coordinador = "N";
			per_User = "S";
		}	

		Connection con = null;
        Utils util = new Utils();
        String dbURL = util.urlConexion();

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(dbURL);
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE Perfiles SET per_Nombre = '" + nomPerfil + "', per_Admin = '" + per_Admin + "', per_Coordinador = '" + per_Coordinador + "', per_User = '" + per_User + "' WHERE per_IdPerfil = " + idPerfil);

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
        
        Integer usuario = Integer.parseInt(request.getSession(false).getAttribute("idUsuario").toString());
        Integer sanatorio = Integer.parseInt(request.getSession(false).getAttribute("sanatorio").toString());
		util.loggear("PERFILES", "MODIFICACION DEL PERFIL " + idPerfil, usuario, sanatorio); // LOG
        
        PrintWriter out = response.getWriter();
        out.println("<script type=\"text/javascript\">");
        out.println("alert('Perfil " + idPerfil + " modificado correctamente');");
        out.println("location= '" + request.getContextPath() + "/menu.jsp';");
        out.println("</script>");
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
