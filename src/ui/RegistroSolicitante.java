package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistroSolicitante extends JFrame{
    private JPanel MainPanelRS;
    private JTextField txtNombreCompleto;
    private JTextField txtCedula;
    private JComboBox cbTipoLicencia;
    private JTextField txtFechaNacimiento;
    private JTextField txtFechaSolicitud;
    private JButton guardarButton;
    private JButton limpiarButton;
    private JButton regresarButton;
    private JLabel lblRegistrarSolicitante;
    private JLabel lblRegistroUser;
    public String rol;

    public RegistroSolicitante(String rol){
        this.rol = rol;
        setTitle("");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(450, 440);
        setContentPane(MainPanelRS);
        setLocationRelativeTo(null);
        setVisible(true);

        ImageIcon registroUslogo = new ImageIcon(getClass().getResource("/icon/registraruser.png"));
        lblRegistrarSolicitante.setIcon(registroUslogo);

        // FUNCIONALIDADES DE LOS BOTONES
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("ADMIN".equalsIgnoreCase(rol)) {
                    new MenuAdministrador(rol).setVisible(true);
                } else if ("ANALISTA".equalsIgnoreCase(rol)) {
                    new MenuAnalista(rol).setVisible(true);
                }
                dispose();
            }
        });

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 1) Leer textos
                String cedula = txtCedula.getText().trim();
                if (!cedula.matches("\\d{10}")) {
                    JOptionPane.showMessageDialog(null, "La cédula debe tener exactamente 10 números.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }


                String nombre = txtNombreCompleto.getText().trim();
                String fechaTxt = txtFechaNacimiento.getText().trim();
                String tipo = (String) cbTipoLicencia.getSelectedItem();

                // 2) Errores claros (campos vacíos)
                if (cedula.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingrese la cédula.", "Error", JOptionPane.ERROR_MESSAGE); // [web:174]
                    return;
                }
                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingrese el nombre completo.", "Error", JOptionPane.ERROR_MESSAGE); // [web:174]
                    return;
                }
                if (fechaTxt.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingrese la fecha de nacimiento (yyyy-MM-dd).", "Error", JOptionPane.ERROR_MESSAGE); // [web:174]
                    return;
                }

                // 3) Convertir fecha (yyyy-MM-dd) y mostrar error bonito si está mal
                java.time.LocalDate fechaNac;
                try {
                    fechaNac = java.time.LocalDate.parse(fechaTxt); // yyyy-MM-dd (ISO) [web:221]
                } catch (java.time.format.DateTimeParseException ex) { // error al parsear [web:221]
                    JOptionPane.showMessageDialog(
                            null,
                            "Fecha inválida. Use el formato yyyy-MM-dd (ej: 2000-08-31).",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    ); // [web:174]
                    return;
                }

                // 4) Si por alguna razón viene null, usar "A" (aunque tu combo ya arranca en A)
                if (tipo == null || tipo.isBlank()) tipo = "A";

                // 5) Guardar (llamando a tu service)
                try {
                    service.SolicitanteService s = new service.SolicitanteService();

                    // Por ahora usuarioId = null (hasta que lo tengas del login)
                    s.registrar(cedula, nombre, fechaNac, tipo, null);

                    JOptionPane.showMessageDialog(null, "Guardado correctamente."); // [web:174]

                } catch (Exception ex) {
                    String msg = ex.getMessage() == null ? "" : ex.getMessage().toLowerCase();

                    // Mensaje bonito si es cédula repetida (MySQL 1062 Duplicate entry)
                    if (msg.contains("duplicate") || msg.contains("1062")) {
                        JOptionPane.showMessageDialog(null, "La cédula ya está registrada.", "Error", JOptionPane.ERROR_MESSAGE); // [web:224][web:174]
                    } else {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); // [web:174]
                    }
                }
            }
        });


        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtNombreCompleto.setText("");   // limpia texto [web:206]
                txtCedula.setText("");           // limpia texto [web:206]
                txtFechaNacimiento.setText("");  // limpia texto [web:206]

                // Si quieres mantener "A" como default, selecciona el primer item
                cbTipoLicencia.setSelectedIndex(0); // vuelve al primer elemento ("A")
            }
        });
    }
}
