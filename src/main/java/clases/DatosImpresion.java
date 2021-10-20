package main.java.clases;

public class DatosImpresion {
    String idTicket;  
    Integer tipoTicket; 
    String fechaHoraIngreso;
    String fechaHoraEgreso;
    String patente;
    Double importe;
    String nombrePaciente;
    String apellidoPaciente;
    String dniPaciente;
    String nhcPaciente;
    String nroEncPaciente;
    String suite;
    String marcaVehiculo;
    String tipoVehiculo;
    String nombreMedico;
    String apellidoMedico;
    String dniMedico;
    String matriculaMedico;
    String estacionamiento;
    String nombreUsuario;
    String nombreSanatorio;
  
    Utils util = new Utils();
    
    public DatosImpresion(String idTicket, Integer tipoTicket, String fechaHoraIngreso, String fechaHoraEgreso, String patente, Double importe, String nombrePaciente, String apellidoPaciente,
    					  String dniPaciente, String nhcPaciente, String nroEncPaciente, String suite, String marcaVehiculo, String tipoVehiculo, String nombreMedico, String apellidoMedico, 
    					  String dniMedico, String matriculaMedico, String estacionamiento, String nombreUsuario, String nombreSanatorio) 
    { 
        this.idTicket = idTicket; 
        this.tipoTicket = tipoTicket; 
        this.fechaHoraIngreso = fechaHoraIngreso;
        this.fechaHoraEgreso = fechaHoraEgreso;
        this.patente = patente;
        this.importe = importe;
        this.nombrePaciente = nombrePaciente;
        this.apellidoPaciente = apellidoPaciente;
        this.dniPaciente = dniPaciente;
        this.nhcPaciente = nhcPaciente;
        this.nroEncPaciente = nroEncPaciente;
        this.suite = suite;
        this.marcaVehiculo = marcaVehiculo;
        this.tipoVehiculo = tipoVehiculo;
        this.nombreMedico = nombreMedico;
        this.apellidoMedico = apellidoMedico;
        this.dniMedico = dniMedico;
        this.matriculaMedico = matriculaMedico;
        this.estacionamiento = estacionamiento;
        this.nombreUsuario = nombreUsuario;
        this.nombreSanatorio = nombreSanatorio;
    } 
  
    public String getIdTicket() { 
        return idTicket; 
    } 
    
    public Integer getTipoTicket() { 
        return tipoTicket; 
    } 
    
    public String getFechaHoraIngreso() { 
        return fechaHoraIngreso; 
    } 
    
    public String getFechaHoraEgreso() { 
        return fechaHoraEgreso; 
    } 
    
    public String getPatente() { 
        return patente; 
    } 
    
    public Double getImporte() { 
        return importe; 
    }
    
    public String getNombrePaciente() { 
        return nombrePaciente; 
    } 
    
    public String getApellidoPaciente() { 
        return apellidoPaciente; 
    } 
    
    public String getDniPaciente() { 
        return dniPaciente; 
    } 
    
    public String getNhcPaciente() { 
        return nhcPaciente; 
    } 
    
    public String getNroEncPaciente() { 
        return nroEncPaciente; 
    } 
    
    public String getSuite() { 
        return suite; 
    } 
    
    public String getMarcaVehiculo() { 
        return marcaVehiculo; 
    } 
    
    public String getTipoVehiculo() { 
        return tipoVehiculo; 
    } 
    
    public String getNombreMedico() { 
        return nombreMedico; 
    } 
    
    public String getApellidoMedico() { 
        return apellidoMedico; 
    } 
    
    public String getDniMedico() { 
        return dniMedico; 
    } 
    
    public String getMatriculaMedico() { 
        return matriculaMedico; 
    } 
    
    public String getEstacionamiento() { 
        return estacionamiento; 
    } 
    
    public String getNombreUsuario() { 
        return nombreUsuario; 
    } 
    
    public String getNombreSanatorio() { 
        return nombreSanatorio; 
    } 
    
    public void setIdTicket(String idTicket) {
        this.idTicket = idTicket;
    }

    public void setTipoTicket(Integer tipoTicket) {
        this.tipoTicket = tipoTicket;
    }
    
    public void setFechaHoraIngreso(String fechaHoraIngreso) {
        this.fechaHoraIngreso = fechaHoraIngreso;
    }
    
    public void setFechaHoraEgreso(String fechaHoraEgreso) {
        this.fechaHoraEgreso = fechaHoraEgreso;
    }
    
    public void setPatente(String patente) {
        this.patente = patente;
    }
    
    public void setImporte(Double importe) {
        this.importe = importe;
    }
    
    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }
    
    public void setApellidoPaciente(String apellidoPaciente) {
        this.apellidoPaciente = apellidoPaciente;
    }
    
    public void setDniPaciente(String dniPaciente) {
        this.dniPaciente = dniPaciente;
    }
    
    public void setNhcPaciente(String nhcPaciente) {
        this.nhcPaciente = nhcPaciente;
    }
    
    public void setNroEncPaciente(String nroEncPaciente) {
        this.nroEncPaciente = nroEncPaciente;
    }
    
    public void setSuite(String suite) {
        this.suite = suite;
    }
    
    public void setMarcaVehiculo(String marcaVehiculo) {
        this.marcaVehiculo = marcaVehiculo;
    }
    
    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }
    
    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }
    
    public void setApellidoMedico(String apellidoMedico) {
        this.apellidoMedico = apellidoMedico;
    }
    
    public void setDniMedico(String dniMedico) {
        this.dniMedico = dniMedico;
    }
    
    public void setMatriculaMedico(String matriculaMedico) {
        this.matriculaMedico = matriculaMedico;
    }
    
    public void setEstacionamiento(String estacionamiento) {
        this.estacionamiento = estacionamiento;
    }
    
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
    public void setNombreSanatorio(String nombreSanatorio) {
        this.nombreSanatorio = nombreSanatorio;
    }
}
