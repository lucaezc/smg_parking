package main.java.clases;

import java.util.Date;

public class Log {
    Integer id;  
    Date fechaHora; 
    String fecha;
    String hora;
    String modulo;
    String descripcion;
    Integer usuarioId;
    String usuario;
    Integer sanatorioId;
    String sanatorio;
  
    Utils util = new Utils();
    
    public Log(Integer id, Date fechaHora, String modulo, String descripcion, Integer usuarioId, String usuario, Integer sanatorioId, String sanatorio) 
    { 
        this.id = id; 
        this.fechaHora = fechaHora; 
        String[] fechaHoraAux = new String[2];
        fechaHoraAux = util.splitFechaHora(fechaHora);
        this.fecha = fechaHoraAux[0];
        this.hora = fechaHoraAux[1];
        this.modulo = modulo;
        this.descripcion = descripcion;
        this.usuarioId = usuarioId;
        this.usuario = usuario;
        this.sanatorioId = sanatorioId;
        this.sanatorio = sanatorio;
    } 
  
    public Integer getId() { 
        return id; 
    }
    
    public Date getFechaHora() { 
        return fechaHora; 
    } 
    
    public String getFecha() { 
        return fecha; 
    } 
    
    public String getHora() { 
        return hora; 
    } 
  
    public String getModulo() { 
        return modulo; 
    } 
    
    public String getDescripcion() { 
        return descripcion; 
    } 
    
    public Integer getUsuarioId() { 
        return usuarioId; 
    } 
    
    public String getUsuario() { 
        return usuario; 
    } 
    
    public Integer getSanatorioId() { 
        return sanatorioId; 
    } 
    
    public String getSanatorio() { 
        return sanatorio; 
    } 
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public void setHora(String hora) {
        this.hora = hora;
    }
    
    public void setModulo(String modulo) {
        this.modulo = modulo;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
     
    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public void setSanatorioId(Integer sanatorioId) {
        this.sanatorioId = sanatorioId;
    }
    
    public void setSanatorio(String sanatorio) {
        this.sanatorio = sanatorio;
    }
}
