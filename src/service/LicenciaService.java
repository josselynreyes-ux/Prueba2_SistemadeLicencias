package service;

import dao.LicenciaDAO;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class LicenciaService {

    private LicenciaDAO dao = new LicenciaDAO();

    // Genera: guarda licencia + cambia estado del tramite
    public boolean generarLicenciaPorCedula(String cedula, String numeroLicencia,
                                            LocalDate fechaEmision, LocalDate fechaVencimiento) {

        Integer tramiteId = dao.obtenerTramiteIdPorCedula(cedula);
        if (tramiteId == null) return false;

        Timestamp emisionTs = Timestamp.valueOf(fechaEmision.atStartOfDay());
        Timestamp vencTs = Timestamp.valueOf(fechaVencimiento.atStartOfDay());

        return dao.insertarLicenciaYActualizarTramite(tramiteId, numeroLicencia, emisionTs, vencTs);
    }

    public String[] datosSolicitante(String cedula) {
        return dao.obtenerDatosSolicitantePorCedula(cedula);
    }
}
