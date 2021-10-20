package main.java.clases;

public class Usuario {
    Integer id;  
    String username;
    String apellido;
    String nombre; 
    String dni;
    String mail;
    String sanatorios;
    String password;
  
    public Usuario(Integer id, String username, String nombre, String apellido, String dni, String mail, String sanatorios, String password) 
    { 
        this.id = id; 
        this.username = username;
        this.nombre = nombre; 
        this.apellido = apellido;
        this.dni = dni;
        this.mail = mail;
        this.sanatorios = sanatorios;
        this.password = password;
    } 
  
    public Integer getId() { 
        return id; 
    } 
    
    public String getUsername() { 
        return username; 
    } 
    
    public String getNombre() { 
        return nombre; 
    } 
    
    public String getApellido() { 
        return apellido; 
    } 
    
    public String getDni() { 
        return dni; 
    } 
    
    public String getMail() { 
        return mail; 
    } 
    
    public String getSanatorios() { 
        return sanatorios; 
    } 
    
    public String getPassword() { 
        return password; 
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
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setDni(String dni) {
        this.dni = dni;
    }
    
    public void setMail(String mail) {
        this.mail = mail;
    }
    
    public void setSanatorios(String sanatorios) {
        this.sanatorios = sanatorios;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}
