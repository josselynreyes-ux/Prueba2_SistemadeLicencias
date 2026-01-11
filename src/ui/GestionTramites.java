package ui;

import javax.swing.*;
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
    private JButton verificarRequisitosButton;
    private JButton verDetalleButton;
    private JTextField textField1;
    private JLabel lblNombreCompleto;
    private JLabel lblCedula;
    private JLabel lblTipoLicencia;
    private JLabel lblDechaSolicitud;
    private JLabel lblEstado;
    private JButton limpiarButton;
    private JButton btnRegresar;
    public String rol;

    public GestionTramites(String rol){
        this.rol=rol;
        setTitle("");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(960, 700);
        setContentPane(MainPanelGT);
        setLocationRelativeTo(null);
        setVisible(true);

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
    }
}
