package ui;

import javax.swing.*;

public class RegistroSolicitante extends JFrame{
    private JPanel MainPanelRS;
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox comboBox1;
    private JTextField textField3;
    private JTextField textField4;
    private JButton guardarButton;
    private JButton limpiarButton;
    private JButton regresarButton;
    private JLabel lblRegistroUser;

    public RegistroSolicitante(){
        setTitle("");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 600);
        setContentPane(MainPanelRS);
        setLocationRelativeTo(null);
        setVisible(true);

        ImageIcon registroUslogo = new ImageIcon(getClass().getResource("/icon/registraruser.png"));
        lblRegistroUser.setIcon(registroUslogo);
    }
}
