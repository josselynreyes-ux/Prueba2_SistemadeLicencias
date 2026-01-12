package model;

public class TramiteRow {
    private int id;
    private String cedula;
    private String nombre;
    private String tipoLicencia;
    private String fechaSolicitud;
    private String estado;

    public TramiteRow() {}

    public TramiteRow(int id, String cedula, String nombre, String tipoLicencia, String fechaSolicitud, String estado) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.tipoLicencia = tipoLicencia;
        this.fechaSolicitud = fechaSolicitud;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipoLicencia() { return tipoLicencia; }
    public void setTipoLicencia(String tipoLicencia) { this.tipoLicencia = tipoLicencia; }

    public String getFechaSolicitud() { return fechaSolicitud; }
    public void setFechaSolicitud(String fechaSolicitud) { this.fechaSolicitud = fechaSolicitud; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
