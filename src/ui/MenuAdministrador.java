package ui;

import javax.swing.*;

public class MenuAdministrador extends JFrame{

    private JPanel MainPanel;
    private JButton btnRegistrarSolicitante;
    private JButton verificarRequisitosButton;
    private JButton registrarExamenesButton;
    private JButton gestionarTrámitesButton;
    private JButton generarLicenciaButton;
    private JButton gestionarUsuariosButton;
    private JButton reportesYEstadísticasButton;
    private JButton cerrarSesionButton;
    private JLabel lblRol;
    private JLabel logoLicenseFlow;
    private JLabel lblRegistrarSoli;
    private JLabel lblVerificarRequi;
    private JLabel lblRegistrarExam;
    private JLabel lblGestionarTramite;
    private JLabel lblGenerarLicencia;
    private JLabel lblCerrarSesion;
    private JLabel lblGestionarUsuarios;
    private JLabel lblReportes;

    public MenuAdministrador(){
        setTitle("Menu");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1100, 750);
        setContentPane(MainPanel);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
