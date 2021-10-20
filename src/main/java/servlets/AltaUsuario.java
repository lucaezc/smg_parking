package main.java.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.clases.Utils;

public class AltaUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AltaUsuario() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String username = request.getParameter("usernameAdd");
		String nombre = request.getParameter("nombreAdd");
		String apellido = request.getParameter("apellidoAdd");
		String documento = request.getParameter("documentoAdd");
		String mail = request.getParameter("mailAdd");
		String password = request.getParameter("passwordAdd");
		String sanatorios = request.getParameter("sanatoriosAdd");
		String perfilId = request.getParameter("selectPerfiles");
		String[] sanatoriosArray = sanatorios.split(" ");
			
		Connection con = null;
        Utils util = new Utils();
        String dbURL = util.urlConexion();
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(dbURL);
            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO Usuarios (usu_Apellido, usu_Nombre, usu_NroDoc, usu_UserName, usu_Password, usu_Email) VALUES " +
							   "('" + apellido + "', '" + nombre + "', '" + documento + "', '" + username + "', '" + password + "', '" + mail + "')", Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            Integer usuarioId = rs.getInt(1);
            
    		for (int i = 0; i < sanatoriosArray.length; i++) {
                stmt.executeUpdate("INSERT INTO UsuariosSanatorio (usa_IdUsuario, usa_IdSanatorio, usa_IdPerfil) VALUES (" + usuarioId + ", " + sanatoriosArray[i] + ", " + perfilId + ")");
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

        Integer usuario = Integer.parseInt(request.getSession(false).getAttribute("idUsuario").toString());
        Integer sanatorio = Integer.parseInt(request.getSession(false).getAttribute("sanatorio").toString());
		util.loggear("USUARIOS", "ALTA", usuario, sanatorio); // LOG
        
        PrintWriter out = response.getWriter();
        out.println("<script type=\"text/javascript\">");
        out.println("alert('Usuario agregado correctamente');");
        out.println("location= '" + request.getContextPath() + "/menu.jsp';");
        out.println("</script>");
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
