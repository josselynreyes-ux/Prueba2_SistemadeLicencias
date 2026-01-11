package dao;
import java.sql.*;
public class ExamenDAO {

    //GUARDAR EXAMEN
    public void insertarExamen(Connection cn, int tramiteId, double notaTeorica, double notaPractica) throws Exception {
        String sql =
                "INSERT INTO examen (tramite_id, nota_teorica, nota_practica) " +
                        "VALUES (?,?,?) " +
                        "ON DUPLICATE KEY UPDATE " +
                        "nota_teorica = VALUES(nota_teorica), " +
                        "nota_practica = VALUES(nota_practica)";

        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, tramiteId);
            ps.setDouble(2, notaTeorica);
            ps.setDouble(3, notaPractica);
            ps.executeUpdate();
        }
    }


    //ACTUALIZAR ESTADO EN TRAMITE APROBADO/REPROBADO
    public void actualizarEstadoTramite(Connection cn, int tramiteId, String estado) throws Exception {
        String sql = "UPDATE tramite SET estado=? WHERE id=?";
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, estado);
            ps.setInt(2, tramiteId);
            ps.executeUpdate();
        }
    }
}
