package ui;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class GenerarLicencia extends JFrame{
    private JPanel MainPanelGL;
    private JTextField txtCedula;
    private JTextField txtFechaEmision;
    private JTextField txtFechaVencimiento;
    private JButton generarLicenciaButton;
    private JButton exportarPDFButton;
    private JButton regresarButton;
    private JLabel lblGenerarLicense;
    public String rol;
    private final service.LicenciaService licenciaService = new service.LicenciaService();
    private String numeroGenerado = null;


    PdfPCell celda(String text, Font font, BaseColor bg) {
        PdfPCell c = new PdfPCell(new Phrase(text, font));
        c.setPadding(8);
        if (bg != null) c.setBackgroundColor(bg);
        return c;
    }

    public GenerarLicencia(String rol){
        this.rol=rol;
        setTitle("");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 300);
        setContentPane(MainPanelGL);
        setLocationRelativeTo(null);
        setVisible(true);

        txtFechaEmision.setText(java.time.LocalDate.now().toString());            // AAAA-MM-DD
        txtFechaVencimiento.setText(java.time.LocalDate.now().plusYears(5).toString()); // +5 años
        exportarPDFButton.setEnabled(false);


        ImageIcon genlicencialogo = new ImageIcon(getClass().getResource("/icon/logo-generarlicense.png"));
        lblGenerarLicense.setIcon(genlicencialogo);

        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("ADMIN".equalsIgnoreCase(rol)) {
                    new GestionTramites(rol).setVisible(true);
                } else if ("ANALISTA".equalsIgnoreCase(rol)) {
                    new GestionTramites(rol).setVisible(true);
                }
                dispose();
            }
        });


        generarLicenciaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String cedula = txtCedula.getText().trim();
                    if (cedula.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Ingrese la cédula.");
                        return;
                    }


                    numeroGenerado = "LIC-" + cedula + "-" + System.currentTimeMillis();

                    LocalDate emision = LocalDate.parse(txtFechaEmision.getText().trim());       // AAAA-MM-DD
                    LocalDate venc = LocalDate.parse(txtFechaVencimiento.getText().trim());     // AAAA-MM-DD

                    boolean ok = licenciaService.generarLicenciaPorCedula(cedula, numeroGenerado, emision, venc);

                    if (ok) {
                        JOptionPane.showMessageDialog(null, "Licencia generada y trámite actualizado a LICENCIA_EMITIDA.");
                        exportarPDFButton.setEnabled(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró trámite para esa cédula (o error al guardar).");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });



        exportarPDFButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    String cedula = txtCedula.getText().trim();
                    if (cedula.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Ingrese la cédula.");
                        return;
                    }
                    if (numeroGenerado == null) {
                        JOptionPane.showMessageDialog(null, "Primero presione 'Generar Licencia'.");
                        return;
                    }

                    String[] datos = licenciaService.datosSolicitante(cedula);
                    if (datos == null) {
                        JOptionPane.showMessageDialog(null, "No existe solicitante con esa cédula.");
                        return;
                    }
                    String nombre = datos[0];
                    String fechaNac = datos[1];

                    JFileChooser chooser = new JFileChooser();
                    chooser.setSelectedFile(new java.io.File("Licencia_" + cedula + ".pdf"));
                    if (chooser.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) return;

                    String ruta = chooser.getSelectedFile().getAbsolutePath();

                    Document doc = new Document(PageSize.A4);
                    PdfWriter.getInstance(doc, new java.io.FileOutputStream(ruta));
                    doc.open();

                    Font fTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
                    Font fLabel  = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11);
                    Font fValue  = FontFactory.getFont(FontFactory.HELVETICA, 11);

                    Paragraph titulo = new Paragraph("LICENCIA DE CONDUCIR", fTitulo);
                    titulo.setAlignment(Element.ALIGN_CENTER);
                    doc.add(titulo);
                    doc.add(new Paragraph(" "));

                    PdfPTable tabla = new PdfPTable(2);
                    tabla.setWidthPercentage(90);
                    tabla.setWidths(new float[]{35f, 65f}); // etiqueta / valor


                    BaseColor gris = new BaseColor(230, 230, 230);

                    tabla.addCell(celda("Cédula", fLabel, gris));
                    tabla.addCell(celda(cedula, fValue, null));

                    tabla.addCell(celda("Nombre", fLabel, gris));
                    tabla.addCell(celda(nombre, fValue, null));

                    tabla.addCell(celda("Fecha nacimiento", fLabel, gris));
                    tabla.addCell(celda(fechaNac, fValue, null));

                    tabla.addCell(celda("Número licencia", fLabel, gris));
                    tabla.addCell(celda(numeroGenerado, fValue, null));

                    tabla.addCell(celda("Fecha emisión", fLabel, gris));
                    tabla.addCell(celda(txtFechaEmision.getText().trim(), fValue, null));

                    tabla.addCell(celda("Fecha vencimiento", fLabel, gris));
                    tabla.addCell(celda(txtFechaVencimiento.getText().trim(), fValue, null));

                    tabla.addCell(celda("Estado trámite", fLabel, gris));
                    tabla.addCell(celda("LICENCIA_EMITIDA", fValue, null));

                    doc.add(tabla);
                    doc.add(new Paragraph(" "));
                    Paragraph pie = new Paragraph("Documento generado automáticamente.", FontFactory.getFont(FontFactory.HELVETICA, 9));
                    pie.setAlignment(Element.ALIGN_CENTER);
                    doc.add(pie);

                    doc.close();

                    JOptionPane.showMessageDialog(null, "PDF generado en:\n" + ruta);

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error PDF: " + ex.getMessage());
                }

            }
        });
    }
}
