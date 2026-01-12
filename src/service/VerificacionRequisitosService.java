package service;

import dao.Connectiondb;
import dao.TramiteDAO;

import java.sql.Connection;

public class VerificacionRequisitosService {

    private final TramiteDAO tramiteDAO = new TramiteDAO();

    public void aprobarPorCedula(String cedula, boolean certMed, boolean pago, boolean sinMultas, String obs) throws Exception {

        if (cedula == null || !cedula.matches("\\d{10}")) {
            throw new IllegalArgumentException("Cédula inválida. Debe tener 10 números.");
        }
        if (!certMed || !pago || !sinMultas) {
            throw new IllegalArgumentException("Para aprobar, marque: Certificado médico, Pago y Sin multas.");
        }

        // Nota: este Connection queda, aunque ahora TramiteDAO abre su propia conexión.
        // Si quieres transacción real, habría que modificar TramiteDAO para recibir Connection.
        try (Connection cn = Connectiondb.getConnection()) {
            cn.setAutoCommit(false);
            try {
                Integer tramiteId = tramiteDAO.obtenerTramiteIdPorCedula(cedula);
                if (tramiteId == null) {
                    throw new IllegalArgumentException("No existe trámite para esa cédula.");
                }

                // Actualiza SOLO requisitos (según método actual del DAO)
                boolean ok = tramiteDAO.actualizarRequisitos(tramiteId, certMed, pago, sinMultas, obs);
                if (!ok) throw new IllegalArgumentException("No se pudo actualizar requisitos.");

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

        if (cedula == null || !cedula.matches("\\d{10}")) {
            throw new IllegalArgumentException("Cédula inválida. Debe tener 10 números.");
        }

        // Regla que pusiste
        if (certMed && pago && sinMultas) {
            throw new IllegalArgumentException("No se puede rechazar si todos los requisitos están marcados. Use Aprobar.");
        }

        if (obs == null || obs.isBlank()) {
            throw new IllegalArgumentException("Escriba una observación (motivo del rechazo).");
        }

        try (Connection cn = Connectiondb.getConnection()) {
            cn.setAutoCommit(false);
            try {
                Integer tramiteId = tramiteDAO.obtenerTramiteIdPorCedula(cedula);
                if (tramiteId == null) {
                    throw new IllegalArgumentException("No existe trámite para esa cédula.");
                }

                boolean ok = tramiteDAO.actualizarRequisitos(tramiteId, certMed, pago, sinMultas, obs);
                if (!ok) throw new IllegalArgumentException("No se pudo actualizar requisitos.");

                cn.commit();
            } catch (Exception e) {
                cn.rollback();
                throw e;
            } finally {
                cn.setAutoCommit(true);
            }
        }
    }
}
