package main.java.clases;

public class Perfil {
    Integer id;  
    String nombre; 
    String admin;
    String valet;
    String coordinador;
    String permisos;
  
    Utils util = new Utils();
    
    public Perfil(Integer id, String nombre, String admin, String valet, String coordinador) 
    { 
        this.id = id; 
        this.nombre = nombre; 
        this.admin = admin;
        this.valet = valet;
        this.coordinador = coordinador;
        this.permisos = util.tipoPerfil(admin, coordinador, valet);
    } 
  
    public String getNombre() { 
        return nombre; 
    } 
  
    public Integer getId() { 
        return id; 
    }
    
    public String getAdmin() { 
        return admin; 
    } 
    
    public String getValet() { 
        return valet; 
    } 
    
    public String getCoordinador() { 
        return coordinador; 
    } 
  
    public String getPermisos() { 
        return permisos; 
    } 
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setAdmin(String admin) {
        this.admin = admin;
    }
    
    public void setValet(String valet) {
        this.valet = valet;
    }
    
    public void setCoordinador(String coordinador) {
        this.coordinador = coordinador;
    }
     
    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }
}
