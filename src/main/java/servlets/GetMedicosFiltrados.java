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

import main.java.clases.Medico;
import main.java.clases.Utils;

public class GetMedicosFiltrados extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetMedicosFiltrados() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Medico> medicos = new ArrayList<Medico>();
	
		String medico = request.getParameter("medico");

		Connection con = null;
		
        Utils util = new Utils();
        String dbURL = util.urlConexion();
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(dbURL);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT med_IdMedico, med_DNI, med_Nombre, med_Apellido, med_MatriculaNacional FROM Medicos WHERE med_Apellido LIKE '%" + medico + "%'");
            while (rs.next()) {
            	Medico med = new Medico(rs.getInt("med_IdMedico"), rs.getString("med_Nombre"), rs.getString("med_Apellido"), rs.getString("med_DNI"), rs.getString("med_MatriculaNacional"));
            	medicos.add(med);
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
        String json = new Gson().toJson(medicos);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);     }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
