package ui;

import javax.swing.*;

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

    public ReportesyEstadisticas(){
        setTitle("");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(650, 600);
        setContentPane(MainPanelRE);
        setLocationRelativeTo(null);
        setVisible(true);

        ImageIcon relogo = new ImageIcon(getClass().getResource("/icon/reporteestadisticas.png"));
        iconRE.setIcon(relogo);
    }
}
