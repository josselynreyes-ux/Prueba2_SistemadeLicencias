package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestionTramites extends JFrame{
    private JPanel MainPanelGT;
    private JTable table1;
    private JComboBox comboBox1;
    private JButton filtrarButton;
    private JButton registrarExamenButton;
    private JButton generarLicenciaButton;
    private JLabel lblGestionarTramites;
    private JTextField textField1;
    private JLabel lblNombreCompleto;
    private JLabel lblCedula;
    private JLabel lblTipoLicencia;
    private JLabel lblDechaSolicitud;
    private JLabel lblEstado;
    private JButton limpiarButton;
    private JButton btnRegresar;
    public String rol;
    private final service.GestionTramitesService gtService = new service.GestionTramitesService();
    private DefaultTableModel model;


    public GestionTramites(String rol){
        this.rol=rol;
        setTitle("");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(960, 700);
        setContentPane(MainPanelGT);
        setLocationRelativeTo(null);
        setVisible(true);

        // 1) Crear el modelo de la tabla con las columnas
        model = new javax.swing.table.DefaultTableModel(
                new Object[]{"ID", "Cédula", "Nombre", "Tipo", "Fecha", "Estado"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // no permite editar [web:584]
            }
        };

        table1.setModel(model);


        table1.getSelectionModel().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting())
                return;
            // evita que se dispare dos veces mientras seleccionas [web:535]

            int row = table1.getSelectedRow();
            if (row == -1)
                return;

            // estos índices dependen del orden de columnas
            // {"ID", "Cédula", "Nombre", "Tipo", "Fecha", "Estado"}
            String cedula = table1.getValueAt(row, 1).toString();
            String nombre = table1.getValueAt(row, 2).toString();
            String tipo   = table1.getValueAt(row, 3).toString();
            String fecha  = table1.getValueAt(row, 4).toString();
            String estado = table1.getValueAt(row, 5).toString();

            // Llenar panel de detalle
            lblCedula.setText(cedula);            // cambia texto del JLabel
            lblNombreCompleto.setText(nombre);
            lblTipoLicencia.setText(tipo);
            lblDechaSolicitud.setText(fecha);
            lblEstado.setText(estado);
        });

        //cargar tabla al iniciar
        try {
            gtService.cargar(model, "TODOS");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        ImageIcon gestiontramitlogo = new ImageIcon(getClass().getResource("/icon/logo-gestiontramites.png"));
        lblGestionarTramites.setIcon(gestiontramitlogo);

        registrarExamenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("ADMIN".equalsIgnoreCase(rol)) {
                    new MenuAdministrador(rol).setVisible(true);
                } else if ("ANALISTA".equalsIgnoreCase(rol)) {
                    new MenuAnalista(rol).setVisible(true);
                }
                dispose();
            }
        });
        filtrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String estadoSeleccionado = String.valueOf(comboBox1.getSelectedItem()); // [web:122]
                    gtService.cargar(model, estadoSeleccionado);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Limpiar los labels del detalle
                lblNombreCompleto.setText("");
                lblCedula.setText("");
                lblTipoLicencia.setText("");
                lblDechaSolicitud.setText("");
                lblEstado.setText("");

                // quitar selección de la tabla
                table1.clearSelection();
            }
        });

        generarLicenciaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("ADMIN".equalsIgnoreCase(rol)) {
                    new GenerarLicencia(rol).setVisible(true);
                } else if ("ANALISTA".equalsIgnoreCase(rol)) {
                    new GenerarLicencia(rol).setVisible(true);
                }
                dispose();
            }
        });

        registrarExamenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("ADMIN".equalsIgnoreCase(rol)) {
                    new RegistrarExamenes(rol).setVisible(true);
                } else if ("ANALISTA".equalsIgnoreCase(rol)) {
                    new RegistrarExamenes(rol).setVisible(true);
                }
                dispose();
            }
        });
    }
}
