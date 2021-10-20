package main.java.clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class Utils {

	public String urlConexion() {
	    ResourceBundle rb = ResourceBundle.getBundle("config");
		String server = rb.getString("server");
		String dbname = rb.getString("dbname");
		String dbuser = rb.getString("dbuser");
		String dbpass = rb.getString("dbpassword");
		return "jdbc:sqlserver://" + server + ";databaseName=" + dbname + ";user=" + dbuser + ";password=" + dbpass;
	}
	
	public String getPrinter() {
	    ResourceBundle rb = ResourceBundle.getBundle("config");
	    return rb.getString("printer");
	}
	
	public String convertidorFecha(String fecha, String hora) {
		String dia = fecha.substring(0, 2);
		String mes = fecha.substring(3, 5);
		String anio = fecha.substring(6, 10);
		return anio + "-" + mes + "-" + dia + " " + hora;
	}
	
	public String fechaHoraActual() {
	    Date date = new Date();
	    String strDateFormat = "YYYY-MM-dd HH:ss";
	    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
	    String formattedDate= dateFormat.format(date);
		return formattedDate;
	}
	
	public String fechaActual() {
	    Date date = new Date();
	    String strDateFormat = "YYYY-MM-dd";
	    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
	    String formattedDate= dateFormat.format(date);
		return formattedDate;
	}
	
	public String tipoPerfil(String perAdmin, String perCoordinador, String perUser) {
        String tipoPerfil = null;
		if (perAdmin.equals("S")) {
			tipoPerfil = "Administrador";
        }else {
        	if (perCoordinador.equals("S")) {
        		tipoPerfil = "Coordinador";
        	}else {
        		if (perUser.equals("S")) {
        			tipoPerfil = "Valet Parking";
        		}else {
        			tipoPerfil = "Sin permisos";
        		}
        	}
        }
		return tipoPerfil;
	}
	
	public String[] splitFechaHora(Date fechaCompleta) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String[] fechaHora = new String[2];
		if (fechaCompleta != null) {
			String fcString = df.format(fechaCompleta);
			String fecha = fcString.substring(0, 10);
			String hora = fcString.substring(11, 16);
			fechaHora[0] = fecha;
			fechaHora[1] = hora;
		} else {
			fechaHora[0] = "";
			fechaHora[1] = "";
		}
		return fechaHora;	
	}
	
	public void calculoLugaresEstacionamiento(String estacionamientoId, String tipoVehiculo, String ingresoEgreso) {
		Connection con = null;
        String dbURL = this.urlConexion();

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(dbURL);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT est_Capacidad, est_IdSanatorio, est_Estado, est_LugaresLibres, est_LugaresOcupados, " + 
            								 "est_LugaresExcedidos, est_ModificacionEstado FROM Estacionamientos WHERE est_IdEstacionamiento = " + estacionamientoId);
            
        	Integer capacidad = 0, libres = 0, ocupados = 0, excedidos = 0;
            
            while (rs.next()) {
            	capacidad = rs.getInt("est_Capacidad");
            	libres = rs.getInt("est_LugaresLibres");
            	ocupados = rs.getInt("est_LugaresOcupados");
            	excedidos = rs.getInt("est_LugaresExcedidos");
            }

            if (ingresoEgreso.equals("Ingreso")) {
                if (tipoVehiculo.equals("3")) { // si es "CAMIONETA XL" ocupa 2 lugares
                    if (capacidad.equals(ocupados)) {
                    	excedidos = excedidos + 2;
                    }else {
                    	libres = libres - 2;
                    	ocupados = ocupados + 2;
                    }	
                }else {
                    if (capacidad.equals(ocupados)) {
                    	excedidos = excedidos + 1;
                    }else {
                    	libres = libres - 1;
                    	ocupados = ocupados + 1;
                    }	
                }
            }else {
                if (tipoVehiculo.equals("3")) { // si es "CAMIONETA XL" ocupa 2 lugares
                    if (capacidad.equals(ocupados)) {
                    	excedidos = excedidos - 2;
                    }else {
                    	libres = libres + 2;
                    	ocupados = ocupados - 2;
                    }	
                }else {
                    if (capacidad.equals(ocupados)) {
                    	excedidos = excedidos - 1;
                    }else {
                    	libres = libres + 1;
                    	ocupados = ocupados - 1;
                    }	
                }
            }

            stmt.executeUpdate("UPDATE Estacionamientos SET est_LugaresLibres = " + libres + ", est_LugaresOcupados = " + ocupados + ", est_LugaresExcedidos = " + excedidos + " WHERE est_IdEstacionamiento = " + estacionamientoId);

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
	
	public Integer getEstado(String descEstado) {
		Connection con = null;
        String dbURL = this.urlConexion();
        Integer idEstado = 0; 
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(dbURL);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT etk_IdEstadoTicket FROM EstadosTickets WHERE etk_Nombre = '" + descEstado + "'");
                        
            while (rs.next()) {
            	idEstado = rs.getInt("etk_IdEstadoTicket");
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
		return idEstado;
	}
	
	public void loggear(String modulo, String descripcion, Integer usuarioId, Integer sanatorioId) {
		Connection con = null;
        String dbURL = this.urlConexion();
		String fechaSistema = fechaHoraActual();

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(dbURL);
            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO Log (log_FechaLog, log_Modulo, log_Descripcion, log_UsuarioId, log_SanatorioId) VALUES ('" + fechaSistema + "', '" + modulo + "', '" + descripcion + "', " + usuarioId + ", " + sanatorioId + ")");

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
	
	public String normalizeSymbolsAndAccents(String str) {
		str = org.apache.commons.lang.StringUtils.defaultString(str);
		String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(nfdNormalizedString).replaceAll("");
	}
	
//	public void imprimirTicketCierre(String idTicket, String nombreUsuario, String nombreSanatorio) {
//		Connection con = null;
//        String dbURL = this.urlConexion();
//        
//        Date fechaHoraIngreso = null, fechaHoraEgreso = null; 
//        String fechaHoraIngresoS = null; String fechaHoraEgresoS = null; String[] fechaHora = new String[2];
//        String patente = null, nombrePaciente = null, apellidoPaciente = null, dniPaciente = null, nhcPaciente = null, nroEncPaciente = null, suite = null, marcaVehiculo = null, tipoVehiculo = null, nombreMedico = null, apellidoMedico = null, dniMedico = null, estacionamiento = null, matriculaMedico = null;
//        double importe = 0.0; Integer tipoTicket = 0;
//        
//        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            con = DriverManager.getConnection(dbURL);
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT tic_IdTipoTicket, tic_FechaHoraIngreso, tic_FechaHoraEgreso, tic_Patente, tic_Importe, tic_NombrePaciente, tic_ApellidoPaciente, tic_DNIPaciente, tic_NroEncuentroPaciente, " +
//            		"tic_SuitePaciente, tic_MarcaVehiculo, tic_NhcPaciente, TV.tiv_Nombre, M.med_Nombre, M.med_Apellido, M.med_DNI, M.med_MatriculaNacional, E.est_Nombre FROM Tickets T JOIN TiposVehiculo TV ON T.tic_IdTipoVehiculo = TV.tiv_IdTipoVehiculo " + 
//            		"JOIN Medicos M ON T.tic_IdMedico = M.med_IdMedico JOIN Estacionamientos E ON tic_IdEstacionamiento = E.est_IdEstacionamiento WHERE tic_IdTicket = " + idTicket + " " +
//            		"UNION SELECT tic_IdTipoTicket, tic_FechaHoraIngreso, tic_FechaHoraEgreso, tic_Patente, tic_Importe, tic_NombrePaciente, tic_ApellidoPaciente, tic_DNIPaciente, tic_NroEncuentroPaciente, tic_SuitePaciente, tic_MarcaVehiculo, tic_NhcPaciente, TV.tiv_Nombre, NULL, NULL, NULL, NULL, " + 
//            		"E.est_Nombre FROM Tickets T JOIN TiposVehiculo TV ON T.tic_IdTipoVehiculo = TV.tiv_IdTipoVehiculo JOIN Estacionamientos E ON tic_IdEstacionamiento = E.est_IdEstacionamiento " + 
//            		"WHERE tic_IdTicket NOT IN (SELECT tic_IdTicket FROM Tickets T JOIN TiposVehiculo TV ON T.tic_IdTipoVehiculo = TV.tiv_IdTipoVehiculo JOIN Medicos M ON T.tic_IdMedico = M.med_IdMedico JOIN Estacionamientos E ON tic_IdEstacionamiento = E.est_IdEstacionamiento) AND tic_IdTicket = " + idTicket);
//                        
//            while (rs.next()) {
//            	tipoTicket = rs.getInt("tic_IdTipoTicket");
//            	fechaHoraIngreso = rs.getTimestamp("tic_FechaHoraIngreso");
//				fechaHoraEgreso = rs.getTimestamp("tic_FechaHoraEgreso");
//		        fechaHora = splitFechaHora(fechaHoraIngreso);
//				fechaHoraIngresoS = convertidorFecha(fechaHora[0], fechaHora[1]);
//		        fechaHora = splitFechaHora(fechaHoraEgreso);
//		        fechaHoraEgresoS = convertidorFecha(fechaHora[0], fechaHora[1]);
//				patente = rs.getString("tic_Patente");
//				importe = rs.getDouble("tic_Importe");
//				nombrePaciente = normalizeSymbolsAndAccents(rs.getString("tic_NombrePaciente")); 
//				apellidoPaciente = normalizeSymbolsAndAccents(rs.getString("tic_ApellidoPaciente"));
//				dniPaciente = rs.getString("tic_DNIPaciente");
//				nhcPaciente = rs.getString("tic_NhcPaciente");
//				nroEncPaciente = rs.getString("tic_NroEncuentroPaciente");
//				suite = rs.getString("tic_SuitePaciente");
//				marcaVehiculo = normalizeSymbolsAndAccents(rs.getString("tic_MarcaVehiculo"));
//				tipoVehiculo = rs.getString("tiv_Nombre");
//				nombreMedico = normalizeSymbolsAndAccents(rs.getString("med_Nombre"));
//				apellidoMedico = normalizeSymbolsAndAccents(rs.getString("med_Apellido"));
//				dniMedico = rs.getString("med_DNI");
//				matriculaMedico = rs.getString("med_MatriculaNacional");
//				estacionamiento = normalizeSymbolsAndAccents(rs.getString("est_Nombre"));          
//			}
//            
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} finally {
//            try {
//                if (con != null && !con.isClosed()) {
//                    con.close();
//                }
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//        }
//        	
//		PrinterService ps = new PrinterService();
//		
//		ps.alineacionCentro();
//		ps.letraNegritaON();
//		ps.imprimirTexto(normalizeSymbolsAndAccents(nombreSanatorio.toUpperCase())); ps.saltoLinea();
//		ps.imprimirTexto("ESTACIONAMIENTO"); ps.saltoLinea();
//		ps.imprimirTexto("COCHERA: " + estacionamiento);
//		ps.renglon();
//		ps.letraNegritaOFF();
//		ps.alineacionDerecha();
//		ps.imprimirTexto("TICKET NRO.:" + idTicket.toString()); ps.renglon();
//		ps.alineacionIzquierda();
//		ps.imprimirTexto("ENTRADA  : " + fechaHoraIngresoS); ps.saltoLinea();
//		ps.imprimirTexto("Vehiculo : " + tipoVehiculo); ps.saltoLinea();
//		ps.imprimirTexto("Patente  : " + patente); ps.saltoLinea();
//		
//		if (tipoTicket.equals(1)) { 
//			ps.imprimirTexto("Paciente : " + apellidoPaciente + ", " + nombrePaciente); ps.saltoLinea();
//			ps.imprimirTexto("DNI : " + dniPaciente); ps.saltoLinea();
//			ps.imprimirTexto("NHC : " + nhcPaciente); ps.saltoLinea();
//			ps.imprimirTexto("Nro encuentro : " + nroEncPaciente); ps.saltoLinea();	
//		}else {
//			if (tipoTicket.equals(2)) {
//				ps.imprimirTexto("Profesional : " + apellidoMedico + ", " + nombreMedico); ps.saltoLinea();
//				ps.imprimirTexto("DNI : " + dniMedico); ps.saltoLinea();
//				ps.imprimirTexto("Matricula : " + matriculaMedico); ps.saltoLinea();
//			}else {
//				ps.imprimirTexto("Autorizado : " + apellidoPaciente + ", " + nombrePaciente); ps.saltoLinea();
//				ps.imprimirTexto("DNI : " + dniPaciente); ps.saltoLinea();
//			}
//		}
//		
//		ps.imprimirTexto("SALIDA  : " + fechaHoraEgresoS); ps.renglon();
//		
//		if (tipoTicket.equals(1)) { 
//			ps.imprimirTexto("Firma Paciente: _______________________");
//		}else {
//			if (tipoTicket.equals(2)) {
//				ps.imprimirTexto("Firma Medico: _______________________");
//			}else {
//				ps.imprimirTexto("Firma Autorizado: _______________________");
//			}
//		}
//		ps.renglon();
//		ps.alineacionIzquierda();
//		ps.imprimirTexto(nombreSanatorio + " no se responsabiliza por perdidas, \n sustracciones, deterioros que pudiera sufrir su automovil."); 
//		ps.cortarPapel();
//	}
}
