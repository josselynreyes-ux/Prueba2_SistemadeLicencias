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

    // ======= LOGIN CON BLOQUEO A LOS 3 INTENTOS =======

    // ---- LÓGICA DE LOGIN ----
    private void realizarLogin() {
        String user = txtUser.getText().trim();
        String pass = new String(txtPass.getPassword());

        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese usuario y contraseña");
            return;
        }

        try (java.sql.Connection cn = dao.Connectiondb.getConnection()) {

            String sql = "SELECT contrasena, rol, estado FROM usuario WHERE username = ?";
            java.sql.PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, user);
            java.sql.ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                manejarErrorCredenciales(user);
                return;
            }

            String passBD = rs.getString("contrasena");
            String rol    = rs.getString("rol");
            String estado = rs.getString("estado");

            // 1) Si está INACTIVO, bloquear de una (no comparar contraseña)
            if (!"ACTIVO".equalsIgnoreCase(estado)) {
                JOptionPane.showMessageDialog(null, "Usuario inactivo. Contacte al administrador.");
                return;
            }

            // 2) Comparación simple (texto plano)
            if (!pass.equals(passBD)) {
                manejarErrorCredenciales(user);
                return;
            }

            // Login correcto: reset intentos y abrir
            intentos = 0;
            abrirFormularioPorRol(rol);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error de conexión: " + ex.getMessage());
        }
    }

    private void manejarErrorCredenciales(String username) {
        intentos++;

        if (intentos >= 3) {
            bloquearUsuario(username);
            JOptionPane.showMessageDialog(null,
                    "Máximo de intentos alcanzado.\nUsuario bloqueado (INACTIVO). Contacte al administrador.");
            dispose();
        } else {
            JOptionPane.showMessageDialog(null,
                    "Usuario o contraseña incorrectos. Intento " + intentos + " de 3");
        }
    }

    private void bloquearUsuario(String username) {
        try (java.sql.Connection cn = dao.Connectiondb.getConnection()) {

            String sql = "UPDATE usuario SET estado = 'INACTIVO' WHERE username = ?";
            java.sql.PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, username);
            ps.executeUpdate(); // UPDATE con JDBC [web:162]

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo bloquear el usuario: " + e.getMessage());
        }
    }

    private void abrirFormularioPorRol(String rol) {
        if ("ADMIN".equalsIgnoreCase(rol)) {
            new MenuAdministrador(rol).setVisible(true);
        } else if ("ANALISTA".equalsIgnoreCase(rol)) {
            new MenuAnalista(rol).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Rol no reconocido: " + rol);
            return;
        }
        dispose();
    }


}


