package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestionUsuarios extends JFrame {
    private JPanel MainPanelGU;
    private JTextField txtNombreCompleto;
    private JTextField txtCedula;
    private JPasswordField passContra;
    private JComboBox cbRol;
    private JCheckBox ckbEstado;
    private JButton guardarButton;
    private JButton actualizarButton;
    private JButton buscarButton;
    private JButton limpiarButton;
    private JButton regresarButton;
    private JLabel lblGestionarUsuarios;
    private JTextField txtUsername;
    private JTextField txtCedulaBuscar;
    private String rol;

    service.UsuarioService userService = new service.UsuarioService();

    public GestionUsuarios(String rol) {
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

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    model.Usuario u = new model.Usuario();
                    u.setNombre(txtNombreCompleto.getText().trim());
                    u.setCedula(txtCedula.getText().trim());
                    u.setUsername(txtUsername.getText().trim());
                    u.setContrasena(new String(passContra.getPassword()));
                    u.setRol(mapRolUIaBD(String.valueOf(cbRol.getSelectedItem())));
                    u.setEstado(ckbEstado.isSelected() ? "ACTIVO" : "INACTIVO");

                    userService.guardar(u);
                    JOptionPane.showMessageDialog(null, "Usuario guardado.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    model.Usuario u = new model.Usuario();
                    u.setNombre(txtNombreCompleto.getText().trim());
                    u.setCedula(txtCedula.getText().trim());
                    u.setUsername(txtUsername.getText().trim());
                    u.setContrasena(new String(passContra.getPassword()));
                    u.setRol(mapRolUIaBD(String.valueOf(cbRol.getSelectedItem())));
                    u.setEstado(ckbEstado.isSelected() ? "ACTIVO" : "INACTIVO");

                    userService.actualizar(u);
                    JOptionPane.showMessageDialog(null, "Usuario actualizado.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtCedulaBuscar.setText("");
                txtNombreCompleto.setText("");
                txtCedula.setText("");
                txtUsername.setText("");
                passContra.setText("");
                cbRol.setSelectedIndex(-1);
                ckbEstado.setSelected(false);
            }
        });

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String cedulaBuscar = txtCedulaBuscar.getText().strip();

                    if (!cedulaBuscar.matches("^\\d{10}$")) {
                        JOptionPane.showMessageDialog(null, "Cédula inválida (10 dígitos).");
                        return;
                    }

                    model.Usuario u = userService.buscarPorCedula(cedulaBuscar);

                    if (u == null) {
                        JOptionPane.showMessageDialog(null, "No existe usuario con esa cédula.", "Info", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                    txtNombreCompleto.setText(u.getNombre());
                    txtCedula.setText(u.getCedula());
                    txtUsername.setText(u.getUsername());
                    passContra.setText(u.getContrasena());
                    cbRol.setSelectedItem(mapRolBDaUI(u.getRol()));
                    ckbEstado.setSelected("ACTIVO".equals(u.getEstado()));

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private String mapRolUIaBD(String rolUI) {
        if ("Administrador".equalsIgnoreCase(rolUI)) return "ADMIN";
        if ("Analista".equalsIgnoreCase(rolUI)) return "ANALISTA";
        return rolUI;
    }

    private String mapRolBDaUI(String rolBD) {
        if ("ADMIN".equalsIgnoreCase(rolBD)) return "Administrador";
        if ("ANALISTA".equalsIgnoreCase(rolBD)) return "Analista";
        return rolBD;
    }
}
