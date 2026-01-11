package service;
import dao.Connectiondb;
import dao.ExamenDAO;
import dao.TramiteDAO;
import java.sql.Connection;

public class ExamenService {
    private  TramiteDAO tramiteDAO = new TramiteDAO();
    private  ExamenDAO examenDAO = new ExamenDAO();

    // Devuelve "APROBADO" o "REPROBADO" para que el UI lo muestre
    public String guardarPorCedula(String cedula, double notaTeorica, double notaPractica) throws Exception {

        if (cedula == null || !cedula.matches("\\d{10}")) {
            throw new IllegalArgumentException("Cédula inválida. Debe tener 10 números.");
        }

        //VALIDAR NOTAS
        if (notaTeorica < 0 || notaTeorica > 20) throw new IllegalArgumentException("Nota teórica debe estar entre 0 y 20.");
        if (notaPractica < 0 || notaPractica > 20) throw new IllegalArgumentException("Nota práctica debe estar entre 0 y 20.");

        String estadoFinal = (notaTeorica >= 14 && notaPractica >= 14) ? "APROBADO" : "REPROBADO";


        try (Connection cn = Connectiondb.getConnection()) {
            cn.setAutoCommit(false);
            try {
                Integer tramiteId = tramiteDAO.obtenerTramiteIdPorCedula(cn, cedula);
                if (tramiteId == null) throw new IllegalArgumentException("No existe trámite para esa cédula.");

                examenDAO.insertarExamen(cn, tramiteId, notaTeorica, notaPractica);
                examenDAO.actualizarEstadoTramite(cn, tramiteId, estadoFinal);

                cn.commit();
                return estadoFinal;
            } catch (Exception e) {
                cn.rollback();
                throw e;
            } finally {
                cn.setAutoCommit(true);
            }
        }
    }
}
