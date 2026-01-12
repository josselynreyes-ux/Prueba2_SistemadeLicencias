package dao;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GestionTramitesDAO {

    public void cargarTabla(DefaultTableModel model, String estado) throws Exception {


        model.setRowCount(0);

        String sqlBase =
                "SELECT tramite.id, solicitante.cedula, solicitante.nombre, " +
                        "tramite.tipoLicencia, tramite.fechaSolicitud, tramite.estado " +
                        "FROM tramite " +
                        "INNER JOIN solicitante ON tramite.solicitante_id = solicitante.id";

        boolean filtrar = (estado != null && !estado.isBlank() && !"TODOS".equalsIgnoreCase(estado));
        String sql = filtrar ? (sqlBase + " WHERE tramite.estado = ? ORDER BY tramite.id DESC")
                : (sqlBase + " ORDER BY tramite.id DESC");

        try (Connection cn = Connectiondb.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            if (filtrar) ps.setString(1, estado);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    model.addRow(new Object[] {
                            rs.getInt("id"),
                            rs.getString("cedula"),
                            rs.getString("nombre"),
                            rs.getString("tipoLicencia"),
                            String.valueOf(rs.getTimestamp("fechaSolicitud")),
                            rs.getString("estado")
                    });
                }
            }
        }
    }
}
