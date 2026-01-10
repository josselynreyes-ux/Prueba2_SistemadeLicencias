package ui;

import javax.swing.*;
import java.awt.*;

public class MenuAdministrador extends JFrame{

    private JPanel MainPanel;
    private JButton btnRegistrarSolicitante;
    private JButton btnVerificarRequisitos;
    private JButton btnRegistrarExamenes;
    private JButton btnGestionTramite;
    private JButton btnGenerarLicencia;
    private JButton btnGestionarUsuarios;
    private JButton btnReportes;
    private JButton btnCerrarSesion;
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
        setTitle("");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1100, 750);
        setContentPane(MainPanel);
        setLocationRelativeTo(null);
        setVisible(true);

        btnRegistrarSolicitante.setBorder(
                BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE)
        );

        btnVerificarRequisitos.setBorder(
                BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE)
        );

        btnRegistrarExamenes.setBorder(
                BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE)
        );

        btnGestionTramite.setBorder(
                BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE)
        );

        btnGenerarLicencia.setBorder(
                BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE)
        );
        btnCerrarSesion.setBorder(
                BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE)
        );

        btnGestionarUsuarios.setBorder(
                BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE)
        );

        btnReportes.setBorder(
                BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE)
        );

        ImageIcon admilogo = new ImageIcon(getClass().getResource("/icon/logLicFlow.png"));
        logoLicenseFlow.setIcon(admilogo);

        ImageIcon rolLogo = new ImageIcon(getClass().getResource("/icon/rol.png"));
        lblRol.setIcon(rolLogo);

        ImageIcon registrarSoliLogo = new ImageIcon(getClass().getResource("/icon/user-add.png"));
        lblRegistrarSoli.setIcon(registrarSoliLogo);

        ImageIcon VerificarSoliLogo = new ImageIcon(getClass().getResource("/icon/logo-checklist.png"));
        lblVerificarRequi.setIcon(VerificarSoliLogo);

        ImageIcon registrarExamlLogo = new ImageIcon(getClass().getResource("/icon/license.png"));
        lblRegistrarExam.setIcon(registrarExamlLogo);

        ImageIcon gestionarTramiteLogo = new ImageIcon(getClass().getResource("/icon/process.png"));
        lblGestionarTramite.setIcon(gestionarTramiteLogo);

        ImageIcon GenerarLicLogo = new ImageIcon(getClass().getResource("/icon/student-alt.png"));
        lblGenerarLicencia.setIcon(GenerarLicLogo);

        ImageIcon CerrarSesionLogo = new ImageIcon(getClass().getResource("/icon/power.png"));
        lblCerrarSesion.setIcon(CerrarSesionLogo);

        ImageIcon GestionarUserLogo = new ImageIcon(getClass().getResource("/icon/gestionar-usuarios.png"));
        lblGestionarUsuarios.setIcon(GestionarUserLogo);

        ImageIcon ReportesLogo = new ImageIcon(getClass().getResource("/icon/report-logo.png"));
        lblReportes.setIcon(ReportesLogo);

    }
}
