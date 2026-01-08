package ui;

import javax.swing.*;
import java.awt.*;

public class Login extends JFrame{
    private JPanel MainPanel;
    private JLabel iconLogo;
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JLabel userLogo;
    private JLabel contraLogo;
    private JLabel icon;


    public Login(){

        setTitle("Inicio de sesion");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1100, 750);
        setContentPane(MainPanel);
        setLocationRelativeTo(null);
        setVisible(true);
        ImageIcon jujopass = new ImageIcon(getClass().getResource("/icon/LoginLogo01.png"));
        iconLogo.setIcon(jujopass);
        txtUsuario.setBorder(null);
        txtPassword.setBorder(null);

        ImageIcon userlogo = new ImageIcon(getClass().getResource("/icon/usuario.png"));
        userLogo.setIcon(userlogo);

        ImageIcon contralogo = new ImageIcon(getClass().getResource("/icon/clave.png"));
        contraLogo.setIcon(contralogo);

        txtUsuario.setBorder(
                BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE)
        );

        txtPassword.setBorder(
                BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE)
        );


        //btnLogin.addActionListener(e -> Ingresar());

//        void Ingresar(){
//
//            String user = txtUsuario.getText();
//            String clave = pClave.getText();
//
//            try{
//               Connection cn = Connectiondb.getConnection();
//               PreparedStatement ps = cn.prepareStatement( "select * from usuarios where nombre = ? and contraseña=?");
//               ps.setString(1,user);
//               ps.setString(2 ,clave);
//
//               //Ejecuta la consulta en la base de d datos y guarda dicha consulta en rs
//               ResultSet rs = ps.executeQuery();
//
//               //comprobamos que exista dicho usuario devolviendo true or false
//               if (rs.next()){
//                   String rol = rs.getString("rol");
//
//                   this.setVisible(false);
//                   if (rol.equals(""))
//               }
//
//            }

        }


    }
