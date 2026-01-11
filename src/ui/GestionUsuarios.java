package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private String rol;

    public GestionUsuarios(String rol){
        this.rol = rol;
        setTitle("");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(570, 500);
        setContentPane(MainPanelGU);
        setLocationRelativeTo(null);
        setVisible(true);

        ImageIcon logogestionarUser = new ImageIcon(getClass().getResource("/icon/gestionar-usuarios.png"));
        lblGestionarUsuarios.setIcon(logogestionarUser);

        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MenuAdministrador(rol);
            }
        });
    }

}
