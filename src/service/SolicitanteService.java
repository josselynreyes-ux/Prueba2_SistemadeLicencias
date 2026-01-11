package service;
import dao.Connectiondb;
import dao.SolicitanteDAO;

import java.sql.Connection;
import  java.time.LocalDate;
import java.time.Period;

public class SolicitanteService {
    private SolicitanteDAO solicitanteDAO = new SolicitanteDAO();

    //METODO PARA LLAMAR BOTON GUARDAR
    public void registrar(String cedula,String nombre, LocalDate fechaNacimiento, String tipoLicencia, Integer usuarioId) throws Exception{
        //VALIDACIONES PARA QUE LOS ESPACIOS NO QUEDEN VACIOS
        if (cedula == null || cedula.isBlank()) throw new IllegalArgumentException("Ingrese una cédula.");
        // Cédula: solo números y exactamente 10 dígitos
        if (!cedula.matches("\\d{10}")) {
            throw new IllegalArgumentException("La cédula debe tener exactamente 10 números.");
        }

        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("Ingrese un nombre.");
        if (fechaNacimiento == null) throw new IllegalArgumentException("Ingrese una fecha de nacimiento.");
        if (tipoLicencia == null || tipoLicencia.isBlank()) {
            tipoLicencia = "A";
        }

        //VALIDACIONES DE EDAD
        int edad = Period.between(fechaNacimiento, LocalDate.now()).getYears(); //calcula años
        if (edad<18) throw new IllegalArgumentException("Debe ser mayor de edad para solicitar una licencia.");

        //GUARDAR EN LA BASE DE DATOS
        try(Connection cn = Connectiondb.getConnection()){
            cn.setAutoCommit(false); // debe guardarse todo o no se guardara nada
            try{
                //si la cedula existe , se detiene (se llama el metodo que se creo en dao)
                if (solicitanteDAO.existeCedula(cn, cedula)){
                    throw new IllegalArgumentException("Solicitante existente.");
                }

                //INSERTAMOS EL SOLICITANTE
                int solicitanteId = solicitanteDAO.insertarSolicitante(cn, cedula, nombre, fechaNacimiento);

                // INSERTAR EL ESTADO COMO PENDIENTE
                solicitanteDAO.insertarTramitePendiente(cn, solicitanteId, tipoLicencia, usuarioId);
                cn.commit(); // confirma

            } catch (Exception e) {
                cn.rollback();
                throw e;
            } finally {
            cn.setAutoCommit(true);
            }
        }


    }
}
