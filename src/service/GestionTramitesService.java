package service;

import dao.GestionTramitesDAO;

import javax.swing.table.DefaultTableModel;

public class GestionTramitesService {

    private final GestionTramitesDAO dao = new GestionTramitesDAO();

    public void cargar(DefaultTableModel model, String estadoSeleccionado) throws Exception {
        // Convertimos a texto seguro (por si viene null)
        String estado = String.valueOf(estadoSeleccionado); // [web:122]
        dao.cargarTabla(model, estado);
    }
}
