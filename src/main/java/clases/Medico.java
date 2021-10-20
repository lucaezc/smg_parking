package main.java.clases;

public class Medico {
    Integer id;  
    String nombre;  
    String apellido;     
    String dni;
    String matriculaNacional;
    String apellidoNombre;

    public Medico(Integer id, String nombre, String apellido, String dni, String matriculaNacional) 
    { 
        this.id = id; 
        this.nombre = nombre; 
        this.apellido = apellido;
        this.dni = dni;
        this.matriculaNacional = matriculaNacional;
        this.apellidoNombre = apellido + ", " + nombre;
    } 
  
    public String getNombre() { 
        return nombre; 
    } 
  
    public Integer getId() { 
        return id; 
    } 
    
    public String getApellido() { 
        return apellido; 
    } 
    
    public String getDni() { 
        return dni; 
    } 
    
    public String getMatriculaNacional() { 
        return matriculaNacional; 
    } 
  
    public String getApellidoNombre() { 
        return apellidoNombre; 
    } 
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
       
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public void setDni(String dni) {
        this.dni = dni;
    }
    
    public void setMatriculaNacional(String matriculaNacional) {
        this.matriculaNacional = matriculaNacional;
    }
    
    public void setApellidoNombre(String apellidoNombre) {
        this.apellidoNombre = apellidoNombre;
    }
}
