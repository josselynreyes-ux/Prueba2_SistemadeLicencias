package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuAdministrador extends JFrame{

    private JPanel MainPanel;
    private JButton btnRegistrarSolicitante;
    private JButton btnVerificarRequisitos;
    private JButton btnGestionTramite;
    private JButton btnGestionarUsuarios;
    private JButton btnReportes;
    private JButton btnCerrarSesion;
    private JLabel lblRol;
    private JLabel logoLicenseFlow;
    private JLabel lblRegistrarSoli;
    private JLabel lblVerificarRequi;
    private JLabel lblGestionarTramite;
    private JLabel lblCerrarSesion;
    private JLabel lblGestionarUsuarios;
    private JLabel lblReportes;
    private JLabel lblMenu;
    public String rol;

    public MenuAdministrador(String rol){
        this.rol=rol;
        setTitle("");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1030, 750);
        setContentPane(MainPanel);
        setLocationRelativeTo(null);
        setVisible(true);

        //ESTILOS QUE SE DIERON A LOS BOTONES
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

        btnGestionarUsuarios.setBorder(
                BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE)
        );

        btnReportes.setBorder(
                BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE)
        );

        // IMAGENES QUE SE USARON PARA EL MENU
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

        ImageIcon GestionarUserLogo = new ImageIcon(getClass().getResource("/icon/gestionar-usuarios.png"));
        lblGestionarUsuarios.setIcon(GestionarUserLogo);

        ImageIcon ReportesLogo = new ImageIcon(getClass().getResource("/icon/report-logo.png"));
        lblReportes.setIcon(ReportesLogo);

        ImageIcon MenuLogo = new ImageIcon(getClass().getResource("/icon/Resumen-Menu.png"));
        lblMenu.setIcon(MenuLogo);

        //ACCIONES QUE REALIZARAN LOS BOTONES DEL FORMULARIO ADMINISTRADOR

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


        btnGestionarUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new GestionUsuarios(rol);
            }
        });

        btnReportes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ReportesyEstadisticas(rol);
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
