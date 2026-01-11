package ui;

import javax.swing.*;

public class GestionUsuarios extends JFrame{
    private JPanel MainPanelGU;
    private JTextField textField2;
    private JTextField textField3;
    private JPasswordField passwordField1;
    private JComboBox comboBox1;
    private JCheckBox activoCheckBox;
    private JButton guardarButton;
    private JButton actualizarButton;
    private JButton buscarButton;
    private JButton limpiarButton;
    private JButton regresarButton;
    private JLabel lblGestionarUsuarios;
    private JTextField textField1;
    private JTextField textField4;

    public GestionUsuarios(){
        setTitle("");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(570, 500);
        setContentPane(MainPanelGU);
        setLocationRelativeTo(null);
        setVisible(true);

        ImageIcon logogestionarUser = new ImageIcon(getClass().getResource("/icon/gestionar-usuarios.png"));
        lblGestionarUsuarios.setIcon(logogestionarUser);
    }

}
