package service;

import dao.UsuarioDAO;
import model.Usuario;

public class UsuarioService {

    private final UsuarioDAO dao = new UsuarioDAO();

    public Usuario buscarPorCedula(String cedula) {
        if (cedula == null || !cedula.matches("\\d{10}")) {
            throw new IllegalArgumentException("Cédula inválida (10 dígitos).");
        }
        return dao.buscarPorCedula(cedula);
    }

    public void guardar(Usuario u) {
        validar(u);
        boolean ok = dao.insertar(u);
        if (!ok) throw new IllegalArgumentException("No se pudo guardar el usuario.");
    }

    public void actualizar(Usuario u) {
        validar(u);
        boolean ok = dao.actualizar(u);
        if (!ok) throw new IllegalArgumentException("No se pudo actualizar el usuario.");
    }

    private void validar(Usuario u) {
        if (u.getCedula() == null || !u.getCedula().matches("\\d{10}")) {
            throw new IllegalArgumentException("Cédula inválida (10 dígitos).");
        }
        if (u.getNombre() == null || u.getNombre().isBlank()) {
            throw new IllegalArgumentException("Nombre es obligatorio.");
        }
        if (u.getUsername() == null || u.getUsername().isBlank()) {
            throw new IllegalArgumentException("Username es obligatorio.");
        }
        if (u.getContrasena() == null || u.getContrasena().isBlank()) {
            throw new IllegalArgumentException("Contraseña es obligatoria.");
        }
        if (u.getRol() == null || u.getRol().isBlank()) {
            throw new IllegalArgumentException("Rol es obligatorio.");
        }
        if (u.getEstado() == null || u.getEstado().isBlank()) {
            throw new IllegalArgumentException("Estado es obligatorio.");
        }
    }
}
