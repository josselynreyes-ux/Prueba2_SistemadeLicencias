package ui;

import javax.swing.*;

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

    public GestionTramites(){
        setTitle("");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(960, 700);
        setContentPane(MainPanelGT);
        setLocationRelativeTo(null);
        setVisible(true);

        ImageIcon gestiontramitlogo = new ImageIcon(getClass().getResource("/icon/logo-gestiontramites.png"));
        lblGestionarTramites.setIcon(gestiontramitlogo);
    }
}
