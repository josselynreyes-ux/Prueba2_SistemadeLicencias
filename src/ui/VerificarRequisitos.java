package ui;

import javax.swing.*;
import java.awt.*;

public class VerificarRequisitos extends JFrame{
    private JPanel MainPanelVR;
    private JCheckBox certificadoMédicoCheckBox;
    private JCheckBox pagoCheckBox;
    private JCheckBox multasCheckBox;
    private JTextArea txtaObservaciones;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JLabel lblVerificarRequisitos;

    public VerificarRequisitos(){
        setTitle("");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 600);
        setContentPane(MainPanelVR);
        setLocationRelativeTo(null);
        setVisible(true);



        ImageIcon verificarlogo = new ImageIcon(getClass().getResource("/icon/logo-verify.png"));
        lblVerificarRequisitos.setIcon(verificarlogo);

    }
}
