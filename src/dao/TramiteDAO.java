package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TramiteDAO {

    // Devuelve el id del último trámite de esa cédula, o null si no hay
    public Integer obtenerTramiteIdPorCedula(Connection cn, String cedula) throws Exception {
        String sql =
                "SELECT tramite.id " +
                        "FROM solicitante " +
                        "INNER JOIN tramite ON tramite.solicitante_id = solicitante.id " +
                        "WHERE solicitante.cedula = ? " +
                        "ORDER BY tramite.id DESC " +
                        "LIMIT 1";

        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, cedula);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next())
                    return rs.getInt("id");
                return null;
            }
        }

    }

    public void actualizarRequisitos(Connection cn, int tramiteId, boolean certificadoMedico, boolean pago, boolean sinMultas, String observaciones, String estado) throws Exception {
        String sql = "UPDATE tramite " + "SET certificadoMedico=?, pago=?, sinMultas=?, observaciones=?, estado=? " +
                "WHERE id=?";

        try (PreparedStatement ps = cn.prepareStatement(sql)) { // [web:79]
            ps.setBoolean(1, certificadoMedico);
            ps.setBoolean(2, pago);
            ps.setBoolean(3, sinMultas);
            ps.setString(4, observaciones);
            ps.setString(5, estado);
            ps.setInt(6, tramiteId);

            ps.executeUpdate(); // aplico el UPDATE
        }
    }
}
