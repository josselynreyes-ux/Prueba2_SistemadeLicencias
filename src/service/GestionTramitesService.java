package service;

import dao.GestionTramitesDAO;

import javax.swing.table.DefaultTableModel;

public class GestionTramitesService {

    private final GestionTramitesDAO dao = new GestionTramitesDAO();

    public void cargar(DefaultTableModel model, String estadoSeleccionado) throws Exception {

        String estado = String.valueOf(estadoSeleccionado);
        dao.cargarTabla(model, estado);
    }
}
