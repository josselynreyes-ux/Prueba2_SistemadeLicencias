package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerarLicencia extends JFrame{
    private JPanel MainPanelGL;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton generarLicenciaButton;
    private JButton exportarPDFButton;
    private JButton regresarButton;
    private JLabel lblGenerarLicense;
    public String rol;

    public GenerarLicencia(String rol){
        this.rol=rol;
        setTitle("");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 300);
        setContentPane(MainPanelGL);
        setLocationRelativeTo(null);
        setVisible(true);

        ImageIcon genlicencialogo = new ImageIcon(getClass().getResource("/icon/logo-generarlicense.png"));
        lblGenerarLicense.setIcon(genlicencialogo);

        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("ADMIN".equalsIgnoreCase(rol)) {
                    new GestionTramites(rol).setVisible(true);
                } else if ("ANALISTA".equalsIgnoreCase(rol)) {
                    new GestionTramites(rol).setVisible(true);
                }
                dispose();
            }
        });
    }
}
