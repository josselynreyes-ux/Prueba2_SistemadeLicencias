package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportesyEstadisticas extends JFrame{
    private JPanel MainPanelRE;
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton buscarButton;
    private JButton exportarCSVButton;
    private JButton verDetalleButton;
    private JLabel lblTotalLicencias;
    private JLabel lblTotalesSolicitudes;
    private JTable table1;
    private JLabel iconRE;
    private JButton btnRegresar;
    public String rol;

    public ReportesyEstadisticas(String rol){
        this.rol=rol;
        setTitle("");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(650, 600);
        setContentPane(MainPanelRE);
        setLocationRelativeTo(null);
        setVisible(true);

        ImageIcon relogo = new ImageIcon(getClass().getResource("/icon/reporteestadisticas.png"));
        iconRE.setIcon(relogo);

        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MenuAdministrador(rol);
            }
        });
    }
}
