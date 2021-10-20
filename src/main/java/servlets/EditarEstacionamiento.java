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

public class EditarEstacionamiento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditarEstacionamiento() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String idEstacionamiento = request.getParameter("idEstEdit");
		String nombreEst = request.getParameter("nombreEstEdit");
		String capacidadEst = request.getParameter("capacidadEstEdit");
		String idSanatorioEst = request.getParameter("idSanatorioEst");
		String idEstadoEst = request.getParameter("idEstadoEst");

		Connection con = null;
        Utils util = new Utils();
        String dbURL = util.urlConexion();

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(dbURL);
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE Estacionamientos SET est_Nombre = '" + nombreEst + "', est_Capacidad = " + capacidadEst + ", est_IdSanatorio = " + idSanatorioEst + ", est_Estado = " + idEstadoEst +
            				  " WHERE est_IdEstacionamiento = " + idEstacionamiento);

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
		util.loggear("ESTACIONAMIENTOS", "MODIFICACION DEL ESTACIONAMIENTO " + idEstacionamiento, usuario, sanatorio); // LOG
        
        PrintWriter out = response.getWriter();
        out.println("<script type=\"text/javascript\">");
        out.println("alert('Estacionamiento " + idEstacionamiento + " modificado correctamente');");
        out.println("location= '" + request.getContextPath() + "/menu.jsp';");
        out.println("</script>");
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
