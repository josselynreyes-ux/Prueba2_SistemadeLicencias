package dao;

import model.TramiteRow;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TramiteDAO {

    // Reportes: lista de trámites para JTable
    public List<TramiteRow> listarParaReporte(String fechaInicio, String fechaFin, String estado, String tipo) {
        List<TramiteRow> list = new ArrayList<>();

        StringBuilder sql = new StringBuilder(
                "SELECT t.id, s.cedula, s.nombre, t.tipoLicencia, t.fechaSolicitud, t.estado " +
                        "FROM tramite t " +
                        "JOIN solicitante s ON s.id = t.solicitante_id " +
                        "WHERE 1=1 "
        );

        List<Object> params = new ArrayList<>();

        if (fechaInicio != null && !fechaInicio.isEmpty()) {
            sql.append(" AND DATE(t.fechaSolicitud) >= ? ");
            params.add(fechaInicio);
        }
        if (fechaFin != null && !fechaFin.isEmpty()) {
            sql.append(" AND DATE(t.fechaSolicitud) <= ? ");
            params.add(fechaFin);
        }
        if (estado != null && !estado.isEmpty()) {
            sql.append(" AND t.estado = ? ");
            params.add(estado);
        }
        if (tipo != null && !tipo.isEmpty()) {
            sql.append(" AND t.tipoLicencia = ? ");
            params.add(tipo);
        }

        sql.append(" ORDER BY t.id DESC ");

        try (Connection cn = Connectiondb.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) ps.setObject(i + 1, params.get(i));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TramiteRow t = new TramiteRow();
                t.setId(rs.getInt("id"));
                t.setCedula(rs.getString("cedula"));
                t.setNombre(rs.getString("nombre"));
                t.setTipoLicencia(rs.getString("tipoLicencia"));
                t.setFechaSolicitud(rs.getString("fechaSolicitud"));
                t.setEstado(rs.getString("estado"));
                list.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // Para otros módulos: obtener tramite.id por cédula (abre su propia conexión)
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
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Overload: para código que ya usa transacciones y pasa Connection (para que NO te falle ExamenService)
    public Integer obtenerTramiteIdPorCedula(Connection cn, String cedula) {
        String sql =
                "SELECT t.id " +
                        "FROM tramite t " +
                        "JOIN solicitante s ON s.id = t.solicitante_id " +
                        "WHERE s.cedula = ? " +
                        "ORDER BY t.id DESC " +
                        "LIMIT 1";

        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, cedula);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Actualizar requisitos (abre su propia conexión)
    public boolean actualizarRequisitos(int tramiteId,
                                        boolean certificadoMedico,
                                        boolean pago,
                                        boolean sinMultas,
                                        String observaciones) {
        String sql =
                "UPDATE tramite SET certificadoMedico = ?, pago = ?, sinMultas = ?, observaciones = ? " +
                        "WHERE id = ?";

        try (Connection cn = Connectiondb.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setBoolean(1, certificadoMedico);
            ps.setBoolean(2, pago);
            ps.setBoolean(3, sinMultas);
            ps.setString(4, observaciones);
            ps.setInt(5, tramiteId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Overload: para transacciones (usa la MISMA Connection)
    public boolean actualizarRequisitos(Connection cn,
                                        int tramiteId,
                                        boolean certificadoMedico,
                                        boolean pago,
                                        boolean sinMultas,
                                        String observaciones) {
        String sql =
                "UPDATE tramite SET certificadoMedico = ?, pago = ?, sinMultas = ?, observaciones = ? " +
                        "WHERE id = ?";

        try (PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setBoolean(1, certificadoMedico);
            ps.setBoolean(2, pago);
            ps.setBoolean(3, sinMultas);
            ps.setString(4, observaciones);
            ps.setInt(5, tramiteId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
