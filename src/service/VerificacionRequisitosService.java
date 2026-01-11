package service;
import dao.Connectiondb;
import dao.TramiteDAO;

import java.sql.Connection;

public class VerificacionRequisitosService {

    private TramiteDAO tramiteDAO = new TramiteDAO();

    public void aprobarPorCedula(String cedula, boolean certMed, boolean pago, boolean sinMultas, String obs) throws Exception {

        if (cedula == null || !cedula.matches("\\d{10}")) { // 10 dígitos
            throw new IllegalArgumentException("Cédula inválida. Debe tener 10 números.");
        }
        if (!certMed || !pago || !sinMultas) {
            throw new IllegalArgumentException("Para aprobar, marque: Certificado médico, Pago y Sin multas.");
        }


        try (Connection cn = Connectiondb.getConnection()) {
            cn.setAutoCommit(false);
            try {
                Integer tramiteId = tramiteDAO.obtenerTramiteIdPorCedula(cn, cedula);
                if (tramiteId == null) throw new IllegalArgumentException("No existe trámite para esa cédula.");

                tramiteDAO.actualizarRequisitos(cn, tramiteId, certMed, pago, sinMultas, obs, "EN_EXAMENES");
                cn.commit();
            } catch (Exception e) {
                cn.rollback();
                throw e;
            } finally {
                cn.setAutoCommit(true);
            }
        }
    }

    public void rechazarPorCedula(String cedula, boolean certMed, boolean pago, boolean sinMultas, String obs) throws Exception {

        if (cedula == null || !cedula.matches("\\d{10}")) { // 10 dígitos
            throw new IllegalArgumentException("Cédula inválida. Debe tener 10 números.");
        }

        if (certMed || pago || sinMultas) {
            throw new IllegalArgumentException("No se puede rechazar si todos los requisitos están marcados. Use Aprobar.");
        }

        if (obs == null || obs.isBlank()) {
            throw new IllegalArgumentException("Escriba una observación (motivo del rechazo).");
        }

        try (Connection cn = Connectiondb.getConnection()) {
            cn.setAutoCommit(false);
            try {
                Integer tramiteId = tramiteDAO.obtenerTramiteIdPorCedula(cn, cedula);
                if (tramiteId == null) throw new IllegalArgumentException("No existe trámite para esa cédula.");

                // Si rechazas, puedes dejarlo en PENDIENTE (o crear un estado RECHAZADO si lo deseas)
                tramiteDAO.actualizarRequisitos(cn, tramiteId, certMed, pago, sinMultas, obs, "PENDIENTE");
                cn.commit(); // [web:54]
            } catch (Exception e) {
                cn.rollback(); // [web:54]
                throw e;
            } finally {
                cn.setAutoCommit(true);
            }
        }
    }
}
