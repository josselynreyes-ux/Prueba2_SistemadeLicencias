package model;

public class Usuario {
    private int id;
    private String nombre;
    private String username;
    private String cedula;
    private String contrasena; // columna BD: contrasena
    private String rol;        // "ADMIN" o "ANALISTA"
    private String estado;     // "ACTIVO" o "INACTIVO"

    public Usuario() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
