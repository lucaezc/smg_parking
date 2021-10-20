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

public class AltaEstacionamiento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AltaEstacionamiento() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String nombreEst = request.getParameter("nombreEst");
		String capacidadEst = request.getParameter("capacidadEst");
		String idSanatorioEst = request.getParameter("idSanatorioEst");
		String idEstadoEst = request.getParameter("idEstadoEst");
        Utils util = new Utils();
		String fechaSistema = util.fechaHoraActual();

				
		Connection con = null;
        String dbURL = util.urlConexion();
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(dbURL);
            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO Estacionamientos (est_Nombre, est_Capacidad, est_IdSanatorio, est_Estado, est_LugaresLibres, est_LugaresOcupados, est_LugaresExcedidos, est_ModificacionEstado) " +
            				   "VALUES ('" + nombreEst + "', " + capacidadEst + ", " + idSanatorioEst + ", " + idEstadoEst + ", " + capacidadEst + ", 0, 0, '" + fechaSistema + "')");

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
		util.loggear("ESTACIONAMIENTOS", "ALTA", usuario, sanatorio); // LOG
        
        PrintWriter out = response.getWriter();
        out.println("<script type=\"text/javascript\">");
        out.println("alert('Estacionamiento agregado correctamente');");
        out.println("location= '" + request.getContextPath() + "/menu.jsp';");
        out.println("</script>");

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
