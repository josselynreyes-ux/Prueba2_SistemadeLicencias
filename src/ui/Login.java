package ui;

import dao.Connectiondb;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame{
    private JPanel MainPanel;
    private JLabel iconLogo;
    private JTextField txtUser;
    private JPasswordField txtPass;
    private JButton btnLogin;
    private JLabel userLogo;
    private JLabel contraLogo;
    private JLabel icon;
    private int intentos = 0;



    public Login(){

        setTitle("");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1100, 750);
        setContentPane(MainPanel);
        setLocationRelativeTo(null);
        setVisible(true);


        ImageIcon jujopass = new ImageIcon(getClass().getResource("/icon/LoginLogo01.png"));
        iconLogo.setIcon(jujopass);

        txtUser.setBorder(null);
        txtPass.setBorder(null);

        ImageIcon userlogo = new ImageIcon(getClass().getResource("/icon/usuario.png"));
        userLogo.setIcon(userlogo);

        ImageIcon contralogo = new ImageIcon(getClass().getResource("/icon/clave.png"));
        contraLogo.setIcon(contralogo);

        txtUser.setBorder(
                BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE)
        );

        txtPass.setBorder(
                BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE)
        );

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                realizarLogin();
            }
        });

        setVisible(true);
    }

        // ---- LÓGICA DE LOGIN ----
        private void realizarLogin() {
            String user = txtUser.getText().trim();
            String pass = new String(txtPass.getPassword());

            if (user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese usuario y contraseña");
                return;
            }

            try (Connection cn = Connectiondb.getConnection()) {

                String sql = "SELECT contrasena, rol, estado " +
                        "FROM usuario WHERE username = ?";
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setString(1, user);
                ResultSet rs = ps.executeQuery();

                if (!rs.next()) {
                    manejarErrorCredenciales();
                    return;
                }

                String passBD = rs.getString("contrasena");
                String rol    = rs.getString("rol");
                String estado = rs.getString("estado");

                // Comparación simple (texto plano)
                if (!pass.equals(passBD)) {
                    manejarErrorCredenciales();
                    return;
                }

                if (!"ACTIVO".equalsIgnoreCase(estado)) {
                    JOptionPane.showMessageDialog(this, "Usuario inactivo");
                    return;
                }

                abrirFormularioPorRol(rol);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error de conexión: " + ex.getMessage());
            }


        }

        private void manejarErrorCredenciales() {
            intentos++;
            if (intentos >= 3) {
                JOptionPane.showMessageDialog(this, "Máximo de intentos alcanzado");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Usuario o contraseña incorrectos. Intento " + intentos + " de 3");
            }
        }

    private void abrirFormularioPorRol(String rol) {
        if ("ADMIN".equalsIgnoreCase(rol)) {
            new MenuAdministrador(rol).setVisible(true);
        } else if ("ANALISTA".equalsIgnoreCase(rol)) {
            new MenuAnalista(rol).setVisible(true);  // <--- aquí usas el nuevo constructor
        } else {
            JOptionPane.showMessageDialog(null, "Rol no reconocido: " + rol);
            return;
        }
        dispose();
    }

}


