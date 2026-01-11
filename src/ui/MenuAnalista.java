package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuAnalista extends JFrame{
    private JPanel MainPanel;
    private JButton btnRegistrarSolicitante;
    private JButton btnCerrarSesion;
    private JButton btnVerificarRequisitos;
    private JButton btnGestionTramite;
    private JLabel logoLicenseFlow;
    private JLabel lblRol;
    private JLabel lblRegistrarSoli;
    private JLabel lblVerificarRequi;
    private JLabel lblGestionarTramite;
    private JLabel lblCerrarSesion;
    private JLabel lblMenu;
    private String rol;



    public MenuAnalista(String rol) {
        this.rol=rol;
        setTitle("Inicio de sesion");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1030, 750);
        setContentPane(MainPanel);
        setLocationRelativeTo(null);
        setVisible(true);


        btnRegistrarSolicitante.setBorder(
                BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE)
        );

        btnVerificarRequisitos.setBorder(
                BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE)
        );


        btnGestionTramite.setBorder(
                BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE)
        );

        btnCerrarSesion.setBorder(
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

        ImageIcon gestionarTramiteLogo = new ImageIcon(getClass().getResource("/icon/process.png"));
        lblGestionarTramite.setIcon(gestionarTramiteLogo);

        ImageIcon CerrarSesionLogo = new ImageIcon(getClass().getResource("/icon/power.png"));
        lblCerrarSesion.setIcon(CerrarSesionLogo);

        ImageIcon MenuLogo = new ImageIcon(getClass().getResource("/icon/Resumen-Menu.png"));
        lblMenu.setIcon(MenuLogo);


        btnRegistrarSolicitante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new RegistroSolicitante(rol);
            }
        });

        btnVerificarRequisitos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new VerificarRequisitos(rol);
            }
        });

        btnGestionTramite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new GestionTramites(rol);
            }
        });

        btnCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Login();
            }
        });
    }
}
