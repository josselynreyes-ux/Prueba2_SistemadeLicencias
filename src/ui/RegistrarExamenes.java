package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrarExamenes extends JFrame {
    private JPanel MainPanelRE;
    private JTextField txtCedula;
    private JTextField txtNotaTeorica;
    private JTextField txtNotaPractica;
    private JButton guardarButton;
    private JButton regresarButton;
    private JLabel lblRegistrarExam;
    public String rol;

    public RegistrarExamenes(String rol){
        this.rol = rol;

        setTitle("");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 300);
        setContentPane(MainPanelRE);
        setLocationRelativeTo(null);
        setVisible(true);

        ImageIcon registrarexamlogo = new ImageIcon(getClass().getResource("/icon/logo-RegistrarExam.png"));
        lblRegistrarExam.setIcon(registrarexamlogo);

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


        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    String cedula = txtCedula.getText().trim();
                    String ntTxt = txtNotaTeorica.getText().trim();
                    String npTxt = txtNotaPractica.getText().trim();

                    // VERIFICAR QUE SE INGRESEN TODOS LOS CAMPOS
                    if (cedula.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Ingrese la cédula.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (ntTxt.isEmpty() || npTxt.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Ingrese las dos notas.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Convertir notas a número
                    double notaTeorica, notaPractica;
                    try {
                        notaTeorica = Double.parseDouble(ntTxt);
                        notaPractica = Double.parseDouble(npTxt);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Las notas deben ser números entre 0 y 20.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Llamar al service preguntar
                    service.ExamenService exService = new service.ExamenService();
                    String estado = exService.guardarPorCedula(cedula, notaTeorica, notaPractica);

                    JOptionPane.showMessageDialog(null, "Guardado. Estado: " + estado);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
