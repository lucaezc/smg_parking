package main.java.clases;

public class TiposVehiculo {
    Integer id;  
    String nombre; 
  
    public TiposVehiculo(Integer id, String nombre) 
    { 
        this.id = id; 
        this.nombre = nombre; 
    } 
  
    public String getNombre() { 
        return nombre; 
    } 
  
    public Integer getId() { 
        return id; 
    } 
  
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
}
