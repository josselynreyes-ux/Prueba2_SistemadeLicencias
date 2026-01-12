package dao;

import java.sql.*;

public class LicenciaDAO {

    // 1) Obtener el tramite.id por cédula (del solicitante)
    public Integer obtenerTramiteIdPorCedula(String cedula) {
        String sql =
                "SELECT t.id " +
                        "FROM tramite t " +
                        "JOIN solicitante s ON s.id = t.solicitante_id " +
                        "WHERE s.cedula = ? " +
                        "ORDER BY t.id DESC " +
                        "LIMIT 1";

        try (Connection cn = Connectiondb.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, cedula);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 2) Insertar licencia y actualizar estado del trámite
    public boolean insertarLicenciaYActualizarTramite(Integer tramiteId,
                                                      String numeroLicencia,
                                                      Timestamp fechaEmision,
                                                      Timestamp fechaVencimiento) {
        String sqlInsert =
                "INSERT INTO licencia (tramite_id, numero_licencia, fecha_emision, fecha_vencimiento) " +
                        "VALUES (?, ?, ?, ?)";

        String sqlUpdate =
                "UPDATE tramite SET estado = 'LICENCIA_EMITIDA' WHERE id = ?";

        try (Connection cn = Connectiondb.getConnection()) {
            cn.setAutoCommit(false);

            try (PreparedStatement ps1 = cn.prepareStatement(sqlInsert);
                 PreparedStatement ps2 = cn.prepareStatement(sqlUpdate)) {

                ps1.setInt(1, tramiteId);
                ps1.setString(2, numeroLicencia);
                ps1.setTimestamp(3, fechaEmision);
                ps1.setTimestamp(4, fechaVencimiento);
                ps1.executeUpdate();

                ps2.setInt(1, tramiteId);
                ps2.executeUpdate();

                cn.commit();
                return true;
            } catch (SQLException e) {
                cn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 3) Datos del solicitante para el PDF (por cédula)
    public String[] obtenerDatosSolicitantePorCedula(String cedula) {
        String sql = "SELECT nombre, fecha_nacimiento FROM solicitante WHERE cedula = ?";

        try (Connection cn = Connectiondb.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, cedula);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new String[] {
                            rs.getString("nombre"),
                            rs.getDate("fecha_nacimiento").toString()
                    };
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
