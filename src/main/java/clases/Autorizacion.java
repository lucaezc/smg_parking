package main.java.clases;

import java.util.Date;

public class Autorizacion {
    Integer id;  
    Integer idSanatorio;     
    Integer idEstacionamiento;
    String estacionamiento;
    Integer idUsuarioSolicitante; 
    String usuarioSolicitante; 
    Date fechaHoraPedido;
    String fechaPedido;
    String horaPedido;
    String nomApeAutorizado;
    Integer idUsuarioAutorizante;
    String usuarioAutorizante;
    Date fechaHoraAutorizacion;
    String fechaAutorizacion;
    String horaAutorizacion;
    String autorizado;
    String observaciones;
    Integer ticketId;

    Utils util = new Utils();
    
    public Autorizacion(Integer id, Integer idSanatorio, Integer idEstacionamiento, String estacionamiento, Integer idUsuarioSolicitante, String usuarioSolicitante, Date fechaHoraPedido, Integer idUsuarioAutorizante, String usuarioAutorizante, 
    					Date fechaHoraAutorizacion, String nomApeAutorizado, String autorizado, String observaciones, Integer ticketId) 
    { 
        this.id = id; 
        this.idSanatorio = idSanatorio; 
        this.idEstacionamiento = idEstacionamiento;
        this.estacionamiento = estacionamiento;
        this.idUsuarioSolicitante = idUsuarioSolicitante;
        this.usuarioSolicitante = usuarioSolicitante;
        this.fechaHoraPedido = fechaHoraPedido;
        String[] fechaHoraPedidoAux = new String[2];
        fechaHoraPedidoAux = util.splitFechaHora(fechaHoraPedido);
        this.fechaPedido = fechaHoraPedidoAux[0];
        this.horaPedido = fechaHoraPedidoAux[1];
        this.idUsuarioAutorizante = idUsuarioAutorizante;
        this.usuarioAutorizante = usuarioAutorizante;
        this.fechaHoraAutorizacion = fechaHoraAutorizacion;
        this.nomApeAutorizado = nomApeAutorizado;
        String[] fechaHoraAutAux = new String[2];
        fechaHoraAutAux = util.splitFechaHora(fechaHoraAutorizacion);
        this.fechaAutorizacion = fechaHoraAutAux[0];
        this.horaAutorizacion = fechaHoraAutAux[1];
        this.autorizado = autorizado;
        this.observaciones = observaciones;
        this.ticketId = ticketId;
    } 
  
    public Integer getId() { 
        return id; 
    } 
     
    public Integer getIdEstacionamiento() { 
        return idEstacionamiento; 
    } 
    
    public String getEstacionamiento() { 
        return estacionamiento; 
    } 
    
    public Integer getIdSanatorio() { 
        return idSanatorio; 
    } 
    
    public Integer getIdUsuarioSolicitante() { 
        return idUsuarioSolicitante; 
    } 
    
    public String getUsuarioSolicitante() { 
        return usuarioSolicitante; 
    } 
    
    public Date getFechaHoraPedido() { 
        return fechaHoraPedido; 
    } 
    
    public String getFechaPedido() { 
        return fechaPedido; 
    } 
    
    public String getHoraPedido() { 
        return horaPedido; 
    } 
  
    public String getNomApeAutorizado() { 
        return nomApeAutorizado; 
    } 
    
    public Integer getIdUsuarioAutorizante() { 
        return idUsuarioAutorizante; 
    } 
    
    public String getUsuarioAutorizante() { 
        return usuarioAutorizante; 
    }
    
    public Date getFechaHoraAutorizacion() { 
        return fechaHoraAutorizacion; 
    } 
    
    public String getFechaAutorizacion() { 
        return fechaAutorizacion; 
    } 
    
    public String getHoraAutorizacion() { 
        return horaAutorizacion; 
    } 
    
    public String getAutorizado() { 
        return autorizado; 
    } 
    
    public String getObservaciones() { 
        return observaciones; 
    } 
    
    public Integer getTicketId() { 
        return ticketId; 
    } 
    
    public void setId(Integer id) {
        this.id = id;
    }
        
    public void setIdSanatorio(Integer idSanatorio) {
        this.idSanatorio = idSanatorio;
    }
    
    public void setIdEstacionamiento(Integer idEstacionamiento) {
        this.idEstacionamiento = idEstacionamiento;
    }
    
    public void setEstacionamiento(String estacionamiento) {
        this.estacionamiento = estacionamiento;
    }
    
    public void setIdUsuarioSolicitante(Integer idUsuarioSolicitante) {
        this.idUsuarioSolicitante = idUsuarioSolicitante;
    }
    
    public void setUsuarioSolicitante(String usuarioSolicitante) {
        this.usuarioSolicitante = usuarioSolicitante;
    }
    
    public void setNomApeAutorizado(String nomApeAutorizado) {
        this.nomApeAutorizado = nomApeAutorizado;
    }
    
    public void setFechaHoraPedido(Date fechaHoraPedido) {
        this.fechaHoraPedido = fechaHoraPedido;
    }
    
    public void setIdUsuarioAutorizante(Integer idUsuarioAutorizante) {
        this.idUsuarioAutorizante = idUsuarioAutorizante;
    }
    
    public void setUsuarioAutorizante(String usuarioAutorizante) {
        this.usuarioAutorizante = usuarioAutorizante;
    }
    
    public void setFechaHoraAutorizacion(Date fechaHoraAutorizacion) {
        this.fechaHoraAutorizacion = fechaHoraAutorizacion;
    }
    
    public void setAutorizado(String autorizado) {
        this.autorizado = autorizado;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }
}
