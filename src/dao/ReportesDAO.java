package dao;

import model.TramiteRow;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportesDAO {

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
        if (estado != null && !estado.equals("-") && !estado.isEmpty()) {
            sql.append(" AND t.estado = ? ");
            params.add(estado);
        }
        if (tipo != null && !tipo.equals("-") && !tipo.isEmpty()) {
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
                t.setFechaSolicitud(rs.getString("fechaSolicitud")); // texto
                t.setEstado(rs.getString("estado"));
                list.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
