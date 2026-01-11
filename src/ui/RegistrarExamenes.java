package ui;

import javax.swing.*;

public class RegistrarExamenes extends JFrame {
    private JPanel MainPanelRE;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton guardarButton;
    private JButton regresarButton;
    private JLabel lblRegistrarExam;

    public RegistrarExamenes(){
        setTitle("");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 300);
        setContentPane(MainPanelRE);
        setLocationRelativeTo(null);
        setVisible(true);

        ImageIcon registrarexamlogo = new ImageIcon(getClass().getResource("/icon/logo-RegistrarExam.png"));
        lblRegistrarExam.setIcon(registrarexamlogo);
    }
}
