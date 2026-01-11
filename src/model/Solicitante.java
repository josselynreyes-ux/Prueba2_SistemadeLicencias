package model;

import java.time.LocalDate;

public class Solicitante {
// VARIABLES QUE DEBE TENER NUESTRA CLASE SOLICITANTE
    private int id;
    private String nombre;
    private String cedula;
    private LocalDate fechaNacimiento;

    // METODOS GET Y SET PARA PODER TRABAJAR DE FORMA MAS ORDENADA Y CONTROLADA
    //CON LAS VARIABLES DE SOLICITANTE

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

    public String getNombre() {

        return nombre;
    }
    public void setNombre(String nombre) {

        this.nombre = nombre;
    }

    public LocalDate getFechaNacimiento() {

        return fechaNacimiento;
    }
    public void setFechaNacimiento(LocalDate fechaNacimiento) {

        this.fechaNacimiento = fechaNacimiento;
    }
}
