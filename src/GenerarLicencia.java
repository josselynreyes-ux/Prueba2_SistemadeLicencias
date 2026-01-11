import javax.swing.*;

public class GenerarLicencia extends JFrame{
    private JPanel MainPanelGL;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton generarLicenciaButton;
    private JButton exportarPDFButton;
    private JButton regresarButton;
    private JLabel lblGenerarLicense;

    public GenerarLicencia(){
        setTitle("");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 300);
        setContentPane(MainPanelGL);
        setLocationRelativeTo(null);
        setVisible(true);

        ImageIcon genlicencialogo = new ImageIcon(getClass().getResource("/icon/logo-generarlicense.png"));
        lblGenerarLicense.setIcon(genlicencialogo);

    }
}
