import dbc.Connectiondb;

import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class Login extends JFrame{
    private JPanel MainPanel;
    private JTextField txtUsuario;
    private JPasswordField pClave;
    private JButton btnLogin;
    private JLabel icon;


    public Login(){

        setTitle("Inicio de sesion");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 750);
        setContentPane(MainPanel);
        setLocationRelativeTo(null);
        setVisible(true);

        ImageIcon jujopass = new ImageIcon(getClass().getResource("/icon/jujopass.png"));
        icon.setIcon(jujopass);



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
