package ui;

import javax.swing.*;

public class VerificarRequisitos extends JFrame{
    private JPanel MainPanelVR;
    private JCheckBox certificadoMédicoCheckBox;
    private JCheckBox pagoCheckBox;
    private JCheckBox multasCheckBox;
    private JTextArea txtaObservaciones;
    private JButton aprobarRequisitosButton;
    private JButton rechazarRequisitosButton;
    private JButton regresarButton;
    private JLabel lblVerificarRequisitos;

    public VerificarRequisitos(){
        setTitle("");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 400);
        setContentPane(MainPanelVR);
        setLocationRelativeTo(null);
        setVisible(true);



        ImageIcon verificarlogo = new ImageIcon(getClass().getResource("/icon/logo-verify.png"));
        lblVerificarRequisitos.setIcon(verificarlogo);

    }
}
