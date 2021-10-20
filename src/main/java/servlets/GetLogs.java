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
import main.java.clases.Log;
import main.java.clases.Utils;

public class GetLogs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetLogs() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Log> logs = new ArrayList<Log>();
				
		Connection con = null;
		
        Utils util = new Utils();
        String dbURL = util.urlConexion();
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(dbURL);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT log_IdLog, log_FechaLog, log_Modulo, log_Descripcion, log_UsuarioId, log_SanatorioId, (U.usu_Apellido + ', ' + U.usu_Nombre) 'Usuario', san_Nombre " + 
            								 "FROM Log JOIN Usuarios U ON log_UsuarioId = U.usu_IdUsuario JOIN Sanatorios S ON log_SanatorioId = S.san_IdSanatorio " + 
            								 "WHERE CAST(log_FechaLog as date) = CAST(GETDATE() as date) ORDER BY log_IdLog DESC");
            while (rs.next()) {
            	Log l = new Log(rs.getInt("log_IdLog"), rs.getTimestamp("log_FechaLog"), rs.getString("log_Modulo"), rs.getString("log_Descripcion"), rs.getInt("log_UsuarioId"), rs.getString("Usuario"), rs.getInt("log_SanatorioId"), rs.getString("san_Nombre"));
            	logs.add(l);
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
        request.setAttribute("logs", logs);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
