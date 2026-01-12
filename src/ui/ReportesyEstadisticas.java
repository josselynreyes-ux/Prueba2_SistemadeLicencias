package ui;

import model.TramiteRow;
import service.ReportesService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.util.List;

public class ReportesyEstadisticas extends JFrame {
    private JPanel MainPanelRE;
    private JTextField txtFechaInicio;
    private JTextField txtFechaFin;
    private JComboBox comboBoxEstado;
    private JComboBox comboBoxTipoLicencia;
    private JButton buscarButton;
    private JButton exportarCSVButton;
    private JButton verDetalleButton;
    private JLabel lblMostrarTotalLicencias;
    private JTable table1;
    private JLabel iconRE;
    private JButton btnRegresar;

    public String rol;

    private final ReportesService service = new ReportesService();

    public ReportesyEstadisticas(String rol) {
        this.rol = rol;

        setTitle("Reportes y Estadísticas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(650, 600);
        setContentPane(MainPanelRE);
        setLocationRelativeTo(null);

        ImageIcon relogo = new ImageIcon(getClass().getResource("/icon/reporteestadisticas.png"));
        iconRE.setIcon(relogo);

        // Modelo de tabla con columnas exactas
        table1.setModel(new DefaultTableModel(
                new Object[]{"ID", "CEDULA", "NOMBRE", "TIPO", "FECHA SOLICITUD", "ESTADO"}, 0
        ));

        // Total inicia en 0
        lblMostrarTotalLicencias.setText("0");

        // Listeners
        btnRegresar.addActionListener(e -> {
            dispose();
            new MenuAdministrador(rol);
        });

        buscarButton.addActionListener(e -> buscar());

        exportarCSVButton.addActionListener(e -> exportarCSV());

        verDetalleButton.addActionListener(e -> verDetalle());

        setVisible(true);
    }

    private void buscar() {
        try {
            String fi = txtFechaInicio.getText().trim();
            String ff = txtFechaFin.getText().trim();
            String estado = (String) comboBoxEstado.getSelectedItem();
            String tipo = (String) comboBoxTipoLicencia.getSelectedItem();

            List<TramiteRow> rows = service.buscarTramites(fi, ff, estado, tipo);

            DefaultTableModel model = (DefaultTableModel) table1.getModel();
            model.setRowCount(0); // limpiar [web:147]

            for (TramiteRow t : rows) {
                model.addRow(new Object[]{
                        t.getId(),
                        t.getCedula(),
                        t.getNombre(),
                        t.getTipoLicencia(),
                        t.getFechaSolicitud(),
                        t.getEstado()
                });
            }

            lblMostrarTotalLicencias.setText(String.valueOf(rows.size()));
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al buscar: " + ex.getMessage());
        }
    }

    private void exportarCSV() {
        try {
            if (table1.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "No hay datos. Primero presiona Buscar.");
                return;
            }

            JFileChooser chooser = new JFileChooser();
            chooser.setSelectedFile(new File("Reporte_Tramites.csv"));
            int opcion = chooser.showSaveDialog(this);
            if (opcion != JFileChooser.APPROVE_OPTION) return;

            service.exportarTablaACSV(table1, chooser.getSelectedFile().getAbsolutePath());
            JOptionPane.showMessageDialog(this, "CSV generado correctamente.");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al exportar: " + ex.getMessage());
        }
    }

    private void verDetalle() {
        int row = table1.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una fila.");
            return;
        }
        Object id = table1.getValueAt(row, 0);
        JOptionPane.showMessageDialog(this, "ID seleccionado: " + id);
        // Aquí conectas tu ventana detalle cuando la tengas.
    }
}
