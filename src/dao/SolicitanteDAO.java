package dao;
import java.sql.*;
import java.time.LocalDate;


public class SolicitanteDAO {

    //VERIFICAMOS QUE LA CEDULA NO EXISTA
    public boolean existeCedula(Connection cn, String cedula) throws Exception {
        String sql = "SELECT 1 FROM solicitante WHERE cedula = ? LIMIT 1";
        try (PreparedStatement ps = cn.prepareStatement(sql)){
            ps.setString(1,cedula);
            try (ResultSet rs = ps.executeQuery()){
                return rs.next();
            }
        }
    }

    //INSERTAR SOLICITANTE
    public int insertarSolicitante(Connection cn, String cedula, String nombre, LocalDate fechaNacimiento) throws Exception{
        String sql = "INSERT INTO solicitante (cedula, nombre, fecha_nacimiento) VALUES (? ,?, ?)";
        try(PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, cedula);
            ps.setString(2, nombre);
            ps.setString(3, String.valueOf(Date.valueOf(fechaNacimiento)));
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()){
                if (keys.next()) return keys.getInt(1);
            }
        }

        throw new SQLException("El solicitante no fue encontrado");
    }

    public void insertarTramitePendiente(Connection cn, int solicitanteId, String tipoLicencia, Integer usuarioId) throws Exception {

        String sql = "INSERT INTO tramite (solicitante_id, estado, tipoLicencia, creado_por) " +
                "VALUES (?, 'PENDIENTE', ?, ?)";

        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, solicitanteId);
            ps.setString(2, tipoLicencia);


            if (usuarioId == null) ps.setNull(3, Types.INTEGER);
            else ps.setInt(3, usuarioId);

            ps.executeUpdate();
        }
    }
}
