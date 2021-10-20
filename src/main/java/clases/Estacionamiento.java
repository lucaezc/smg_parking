package main.java.clases;

import java.util.Date;

public class Estacionamiento {
    Integer id;  
    String nombre;     
    Integer capacidad; 
    Integer idSanatorio; 
    String nombreSanatorio;
    Integer idEstado;
    String nombreEstado;
    Integer lugaresLibres;
    Integer lugaresOcupados;
    Integer lugaresExcedidos;
    Date modificacionEstado;

    public Estacionamiento(Integer id, String nombre, Integer capacidad, Integer idSanatorio, String nombreSanatorio, Integer idEstado, String nombreEstado, 
    					   Integer lugaresLibres, Integer lugaresOcupados, Integer lugaresExcedidos, Date modificacionEstado) 
    { 
        this.id = id; 
        this.nombre = nombre; 
        this.capacidad = capacidad;
        this.idSanatorio = idSanatorio;
        this.nombreSanatorio = nombreSanatorio;
        this.idEstado = idEstado;
        this.nombreEstado = nombreEstado;
        this.lugaresLibres = lugaresLibres;
        this.lugaresOcupados = lugaresOcupados;
        this.lugaresExcedidos = lugaresExcedidos;
        this.modificacionEstado = modificacionEstado;
    } 
     
    public String getNombre() { 
        return nombre; 
    } 
  
    public Integer getId() { 
        return id; 
    } 
    
    public Integer getCapacidad() { 
        return capacidad; 
    } 
  
    public Integer getIdSanatorio() { 
        return idSanatorio; 
    } 
    
    public String getNombreSanatorio() { 
        return nombreSanatorio; 
    } 
    
    public Integer getIdEstado() { 
        return idEstado; 
    } 
    
    public String getNombreEstado() { 
        return nombreEstado; 
    } 
  
    public Integer getLugaresLibres() { 
        return lugaresLibres; 
    } 
    
    public Integer getLugaresOcupados() { 
        return lugaresOcupados; 
    }
    
    public Integer getLugaresExcedidos() { 
        return lugaresExcedidos; 
    } 
    
    public Date getModificacionEstado() { 
        return modificacionEstado; 
    } 
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }
    
    public void setIdSanatorio(Integer idSanatorio) {
        this.idSanatorio = idSanatorio;
    }
    
    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }
    
    public void setNombreSanatorio(String nombreSanatorio) {
        this.nombreSanatorio = nombreSanatorio;
    }
    
    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }
    
    public void setLugaresLibres(Integer lugaresLibres) {
        this.lugaresLibres = lugaresLibres;
    }
    
    public void setLugaresOcupados(Integer lugaresOcupados) {
        this.lugaresOcupados = lugaresOcupados;
    }
    
    public void setLugaresExcedidos(Integer lugaresOcupados) {
        this.lugaresOcupados = lugaresOcupados;
    }
    
    public void setModificacionEstado(Date modificacionEstado) {
        this.modificacionEstado = modificacionEstado;
    }
    
}
