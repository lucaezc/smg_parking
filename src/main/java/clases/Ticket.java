package main.java.clases;

import java.util.Date;

public class Ticket {
    Integer id;  
    Integer idTipo; 
    Integer idEstacionamiento;
    Integer idTipoVehiculo;
    Integer idUsuarioSanatorio;
    Date fechaHoraIngreso;
    String fechaIngreso;
    String horaIngreso;
    Date fechaHoraEgreso;
    String fechaEgreso;
    String horaEgreso;
    Integer idUsuarioRegistro;
    String usuarioRegistro;
    Date fechaHoraRegistro;
    Integer idUsuarioModificacion;
    Date fechaHoraModificacion;
    String fechaModificacion;
    String horaModificacion;
    String patente;
    Double importe;
    String nombrePaciente;
    String apellidoPaciente;
    String dniPaciente;
    String nhcPaciente;
    String nroEncuentroPaciente;
    String suitePaciente;
    String marcaVehiculo;
    Integer idMedico;
    String tipoTicket;
    String tipoVehiculo;
    String medicoNombre;
    String medicoApellido;
    String medicoDNI;
    String nombreEstacionamiento;
    Integer idEstado;
    String estado;
    String nombreCompleto;
    String dni;
    Boolean bajaLogica;
    Boolean excedido;
    String facturadoExcedido;
    String fechaIngresoExcedido;
    String horaIngresoExcedido;
    String fechaEgresoExcedido;
    String horaEgresoExcedido;
    Integer idUsuarioCierre;
    String usuarioCierre;
    Date fechaHoraCierre;
    String fechaCierre;
    String horaCierre;
    
    Utils util = new Utils();
    
    public Ticket(Integer id, Integer idTipo, Integer idEstacionamiento, Integer idTipoVehiculo, Integer idUsuarioSanatorio, Date fechaHoraIngreso, Date fechaHoraEgreso, Integer idUsuarioRegistro, String usuarioRegistro,  
    			  Date fechaHoraRegistro, Integer idUsuarioModificacion, Date fechaHoraModificacion, String patente, Double importe, String nombrePaciente, String apellidoPaciente, String dniPaciente,
    			  String nhcPaciente, String nroEncuentroPaciente, String suitePaciente, String marcaVehiculo, Integer idMedico, String tipoTicket, String tipoVehiculo, String medicoNombre, String medicoApellido,
    			  String medicoDNI, String nombreEstacionamiento, Integer idEstado, String estado, Boolean bajaLogica, Boolean excedido, Integer idUsuarioCierre, String usuarioCierre, Date fechaHoraCierre) 
    { 
        this.id = id; 
        this.idTipo = idTipo;
        this.idEstacionamiento = idEstacionamiento;
        this.idTipoVehiculo = idTipoVehiculo;
        this.idUsuarioSanatorio = idUsuarioSanatorio;
        this.fechaHoraIngreso = fechaHoraIngreso;
        String[] fechaHora = new String[2];
        fechaHora = util.splitFechaHora(fechaHoraIngreso);
        this.fechaIngreso = fechaHora[0];
        this.horaIngreso = fechaHora[1];
        fechaHora = util.splitFechaHora(fechaHoraEgreso);
        this.fechaEgreso = fechaHora[0];
        this.horaEgreso = fechaHora[1];
        this.idUsuarioRegistro = idUsuarioRegistro;
        this.usuarioRegistro = usuarioRegistro;
        this.fechaHoraRegistro = fechaHoraRegistro;
        this.idUsuarioModificacion = idUsuarioModificacion;
        this.fechaHoraModificacion = fechaHoraModificacion;
        fechaHora = util.splitFechaHora(fechaHoraModificacion);
        this.fechaModificacion = fechaHora[0];
        this.horaModificacion = fechaHora[1];
        this.patente = patente;
        this.importe = importe;
        this.nombrePaciente = nombrePaciente;
        this.apellidoPaciente = apellidoPaciente;
        this.dniPaciente = dniPaciente;
        this.nhcPaciente = nhcPaciente;
        this.nroEncuentroPaciente = nroEncuentroPaciente;
        this.suitePaciente = suitePaciente;
        this.marcaVehiculo = marcaVehiculo;
        this.idMedico = idMedico;
        this.tipoTicket = tipoTicket;
        this.tipoVehiculo = tipoVehiculo;
        this.medicoNombre = medicoNombre;
        this.medicoApellido = medicoApellido;
        this.medicoDNI = medicoDNI;
        this.nombreEstacionamiento = nombreEstacionamiento;
        this.idEstado = idEstado;
        this.estado = estado;
        if (dniPaciente == null || dniPaciente.isEmpty()) {
        	this.nombreCompleto = medicoApellido + ", " + medicoNombre;
        	this.dni = medicoDNI;
        }else {
        	this.nombreCompleto = apellidoPaciente + ", " + nombrePaciente;
        	this.dni = dniPaciente;
            this.tipoTicket = tipoVehiculo; // fix para error raro
            this.tipoVehiculo = tipoTicket; // fix para error raro
        }
        this.bajaLogica = bajaLogica;
        this.excedido = excedido;
        if (excedido) {
        	this.facturadoExcedido = "SI";
        	fechaHora = util.splitFechaHora(fechaHoraIngreso);
        	this.fechaIngresoExcedido = fechaHora[0];
        	this.horaIngresoExcedido = fechaHora[1];
            fechaHora = util.splitFechaHora(fechaHoraEgreso);
            this.fechaEgresoExcedido = fechaHora[0];
            this.horaEgresoExcedido = fechaHora[1];
        }else {
        	this.facturadoExcedido = "NO";
        	this.fechaIngresoExcedido = null;
        	this.horaIngresoExcedido = null;
            this.fechaEgresoExcedido = null;
            this.horaEgresoExcedido = null;
        }
        this.idUsuarioCierre = idUsuarioCierre;
        this.usuarioCierre = usuarioCierre;
        this.fechaHoraCierre = fechaHoraCierre;
        fechaHora = util.splitFechaHora(fechaHoraCierre);
        this.fechaCierre = fechaHora[0];
        this.horaCierre = fechaHora[1];
    } 
   
    public Integer getId() { 
        return id; 
    } 

    public Integer getIdTipo() { 
        return idTipo; 
    } 
    
    public Integer getIdEstacionamiento() { 
        return idEstacionamiento; 
    } 
    
    public Integer getIdTipoVehiculo() { 
        return idTipoVehiculo; 
    } 
    
    public Integer getIdUsuarioSanatorio() { 
        return idUsuarioSanatorio; 
    } 
    
    public Date getFechaHoraIngreso() { 
        return fechaHoraIngreso; 
    } 
    
    public String getFechaIngreso() { 
        return fechaIngreso; 
    } 
    
    public String getHoraIngreso() { 
        return horaIngreso; 
    } 
    
    public Date getFechaHoraEgreso() { 
        return fechaHoraEgreso; 
    } 
    
    public String getFechaEgreso() { 
        return fechaEgreso; 
    } 
    
    public String getHoraEgreso() { 
        return horaEgreso; 
    } 
    
    public Integer getIdUsuarioRegistro() { 
        return idUsuarioRegistro; 
    } 
    
    public String getUsuarioRegistro() { 
        return usuarioRegistro; 
    } 
    
    public Date getFechaHoraRegistro() { 
        return fechaHoraRegistro; 
    } 
    
    public Integer getIdUsuarioModificacion() { 
        return idUsuarioModificacion; 
    } 
    
    public Date getFechaHoraModificacion() { 
        return fechaHoraModificacion; 
    } 
    
    public String getFechaModificacion() { 
        return fechaModificacion; 
    } 
    
    public String getHoraModificacion() { 
        return horaModificacion; 
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
    
    public String getNroEncuentroPaciente() { 
        return nroEncuentroPaciente; 
    } 
    
    public String getSuitePaciente() { 
        return suitePaciente; 
    } 
    
    public String getMarcaVehiculo() { 
        return marcaVehiculo; 
    } 
    
    public Integer getIdMedico() { 
        return idMedico; 
    } 
    
    public String getTipoTicket() { 
        return tipoTicket; 
    } 
    
    public String getTipoVehiculo() { 
        return tipoVehiculo; 
    } 
    
    public String getMedicoNombre() { 
        return medicoNombre; 
    } 
    
    public String getMedicoApellido() { 
        return medicoApellido; 
    } 
    
    public String getMedicoDNI() { 
        return medicoDNI; 
    } 

    public String getNombreEstacionamiento() { 
        return nombreEstacionamiento; 
    } 
    
    public String getEstado() { 
        return estado; 
    }
    
    public Integer getIdEstado() { 
        return idEstado; 
    }
    
    public String getNombreCompleto() { 
        return nombreCompleto; 
    } 
    
    public String getDni() { 
        return dni; 
    } 
    
    public Boolean getBajaLogica() { 
        return bajaLogica; 
    } 
    
    public Boolean getExcedido() { 
        return excedido; 
    } 
    
    public String getFacturadoExcedido() { 
        return facturadoExcedido; 
    } 
    
    public String getFechaIngresoExcedido() { 
        return fechaIngresoExcedido; 
    } 
    
    public String getHoraIngresoExcedido() { 
        return horaIngresoExcedido; 
    } 
    
    public String getFechaEgresoExcedido() { 
        return fechaEgresoExcedido; 
    } 
    
    public String getHoraEgresoExcedido() { 
        return horaEgresoExcedido; 
    } 
    
    public Integer getIdUsuarioCierre() { 
        return idUsuarioCierre; 
    } 
    
    public String getUsuarioCierre() { 
        return usuarioCierre; 
    } 
    
    public Date getFechaHoraCierre() { 
        return fechaHoraCierre; 
    } 
    
    public String getFechaCierre() { 
        return fechaCierre; 
    } 
    
    public String getHoraCierre() { 
        return horaCierre; 
    } 
       
    void setId(Integer id) {
        this.id = id;
    }
    
    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }
    
    public void setIdEstacionamiento(Integer idEstacionamiento) {
        this.idEstacionamiento = idEstacionamiento;
    }
    
    public void setIdTipoVehiculo(Integer idTipoVehiculo) {
        this.idTipoVehiculo = idTipoVehiculo;
    }
    
    public void setIdUsuarioSanatorio(Integer idUsuarioSanatorio) {
        this.idUsuarioSanatorio = idUsuarioSanatorio;
    }
    
    public void setFechaHoraIngreso(Date fechaHoraIngreso) {
        this.fechaHoraIngreso = fechaHoraIngreso;
    }
    
    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
    
    public void setHoraIngreso(String horaIngreso) {
        this.horaIngreso = horaIngreso;
    }
    
    public void setFechaHoraEgreso(Date fechaHoraEgreso) {
        this.fechaHoraEgreso = fechaHoraEgreso;
    }
    
    public void setFechaEgreso(String fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }
    
    public void setHoraEgreso(String horaEgreso) {
        this.horaEgreso = horaEgreso;
    }
    
    public void setIdUsuarioRegistro(Integer idUsuarioRegistro) {
        this.idUsuarioRegistro = idUsuarioRegistro;
    }
    
    public void setUsuarioRegistro(String usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }
    
    public void setFechaHoraRegistro(Date fechaHoraRegistro) {
        this.fechaHoraRegistro = fechaHoraRegistro;
    }
    
    public void setIdUsuarioModificacion(Integer idUsuarioModificacion) {
        this.idUsuarioModificacion = idUsuarioModificacion;
    }
    
    public void setFechaHoraModificacion(Date fechaHoraModificacion) {
        this.fechaHoraModificacion = fechaHoraModificacion;
    }
    
    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
    
    public void setHoraModificacion(String horaModificacion) {
        this.horaModificacion = horaModificacion;
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
    
    public void setNroEncuentroPaciente(String nroEncuentroPaciente) {
        this.nroEncuentroPaciente = nroEncuentroPaciente;
    }
    
    public void setSuitePaciente(String suitePaciente) {
        this.suitePaciente = suitePaciente;
    }
    
    public void setMarcaVehiculo(String marcaVehiculo) {
        this.marcaVehiculo = marcaVehiculo;
    }
    
    public void setIdMedico(Integer idMedico) {
        this.idMedico = idMedico;
    }
    
    public void setTipoTicket(String tipoTicket) {
        this.tipoTicket = tipoTicket;
    }
    
    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }
    
    public void setMedicoNombre(String medicoNombre) {
        this.medicoNombre = medicoNombre;
    }
    
    public void setMedicoApellido(String medicoApellido) {
        this.medicoApellido = medicoApellido;
    }
    
    public void setMedicoDNI(String medicoDNI) {
        this.medicoDNI = medicoDNI;
    }
    
    public void setNombreEstacionamiento(String nombreEstacionamiento) {
        this.nombreEstacionamiento = nombreEstacionamiento;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }
    
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    
    public void setDni(String dni) {
        this.dni = dni;
    }
    
    public void setBajaLogica(Boolean bajaLogica) {
        this.bajaLogica = bajaLogica;
    }
    
    public void setExcedido(Boolean excedido) {
        this.excedido = excedido;
    }
    
    public void setFacturadoExcedido(String facturadoExcedido) {
        this.facturadoExcedido = facturadoExcedido;
    }
    
    public void setFechaIngresoExcedido(String fechaIngresoExcedido) {
        this.fechaIngresoExcedido = fechaIngresoExcedido;
    }
    
    public void setHoraIngresoExcedido(String horaIngresoExcedido) {
        this.horaIngresoExcedido = horaIngresoExcedido;
    }
    
    public void setFechaEgresoExcedido(String fechaEgresoExcedido) {
        this.fechaEgresoExcedido = fechaEgresoExcedido;
    }
    
    public void setHoraEgresoExcedido(String horaEgresoExcedido) {
        this.horaEgresoExcedido = horaEgresoExcedido;
    }
    
    public void setIdUsuarioCierre(Integer idUsuarioCierre) {
        this.idUsuarioCierre = idUsuarioCierre;
    }
    
    public void setUsuarioCierre(String usuarioCierre) {
        this.usuarioCierre = usuarioCierre;
    }
    
    public void setFechaHoraCierre(Date fechaHoraCierre) {
        this.fechaHoraCierre = fechaHoraCierre;
    }
    
    public void setFechaCierre(String fechaCierre) {
        this.fechaCierre = fechaCierre;
    }
    
    public void setHoraCierre(String horaCierre) {
        this.horaCierre = horaCierre;
    }
    
}
