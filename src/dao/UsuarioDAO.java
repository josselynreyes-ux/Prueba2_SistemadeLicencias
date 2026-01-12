package dao;

import model.Usuario;
import java.sql.*;

public class UsuarioDAO {

    public Usuario buscarPorCedula(String cedula) {
        String sql = "SELECT id, nombre, username, cedula, contrasena, rol, estado FROM usuario WHERE cedula = ?";

        try (Connection cn = Connectiondb.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, cedula);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNombre(rs.getString("nombre"));
                u.setUsername(rs.getString("username"));
                u.setCedula(rs.getString("cedula"));
                u.setContrasena(rs.getString("contrasena")); // OJO
                u.setRol(rs.getString("rol"));
                u.setEstado(rs.getString("estado"));
                return u;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insertar(Usuario u) {
        String sql = "INSERT INTO usuario (nombre, username, cedula, contrasena, rol, estado) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection cn = Connectiondb.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, u.getNombre());
            ps.setString(2, u.getUsername());
            ps.setString(3, u.getCedula());
            ps.setString(4, u.getContrasena()); // OJO
            ps.setString(5, u.getRol());
            ps.setString(6, u.getEstado());

            return ps.executeUpdate() > 0; // patrón JDBC [web:163]
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(Usuario u) {
        String sql = "UPDATE usuario SET nombre = ?, username = ?, contrasena = ?, rol = ?, estado = ? WHERE cedula = ?";

        try (Connection cn = Connectiondb.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, u.getNombre());
            ps.setString(2, u.getUsername());
            ps.setString(3, u.getContrasena()); // OJO
            ps.setString(4, u.getRol());
            ps.setString(5, u.getEstado());
            ps.setString(6, u.getCedula());

            return ps.executeUpdate() > 0; // patrón JDBC [web:163]
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
