package ui;

import service.*;
import service.VerificacionRequisitosService;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VerificarRequisitos extends JFrame{
    private JPanel MainPanelVR;
    private JCheckBox certificadoMédicoCheckBox;
    private JCheckBox pagoCheckBox;
    private JCheckBox sinMultasCheckBox;
    private JTextArea txtaObservaciones;
    private JButton aprobarRequisitosButton;
    private JButton rechazarRequisitosButton;
    private JButton regresarButton;
    private JLabel lblVerificarRequisitos;
    private JTextField txtCedula;
    public String rol;
    private VerificacionRequisitosService verService = new VerificacionRequisitosService();


    public VerificarRequisitos(String rol){
        this.rol = rol;
        setTitle("");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 400);
        setContentPane(MainPanelVR);
        setLocationRelativeTo(null);
        setVisible(true);



        ImageIcon verificarlogo = new ImageIcon(getClass().getResource("/icon/logo-verify.png"));
        lblVerificarRequisitos.setIcon(verificarlogo);

        regresarButton.addActionListener(new ActionListener() {
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


        aprobarRequisitosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String cedula = txtCedula.getText().trim();
                    boolean cert = certificadoMédicoCheckBox.isSelected();
                    boolean pago = pagoCheckBox.isSelected();
                    boolean sinMultas = sinMultasCheckBox.isSelected();
                    String obs = txtaObservaciones.getText().trim();

                    verService.aprobarPorCedula(cedula, cert, pago, sinMultas, obs);

                    JOptionPane.showMessageDialog(null, "Requisitos aprobados. Estado: EN_EXAMENES.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        rechazarRequisitosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String cedula = txtCedula.getText().trim();
                    boolean cert = certificadoMédicoCheckBox.isSelected();
                    boolean pago = pagoCheckBox.isSelected();
                    boolean sinMultas = sinMultasCheckBox.isSelected();
                    String obs = txtaObservaciones.getText().trim();

                    verService.rechazarPorCedula(cedula, cert, pago, sinMultas, obs);

                    JOptionPane.showMessageDialog(null, "Requisitos rechazados. Se guardó la observación.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
