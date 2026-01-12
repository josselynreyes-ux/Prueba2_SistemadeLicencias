package service;

import dao.TramiteDAO;
import model.TramiteRow;

import javax.swing.JTable;
import javax.swing.table.TableModel;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

public class ReportesService {

    private final TramiteDAO dao = new TramiteDAO();

    public List<TramiteRow> buscarTramites(String fi, String ff, String estado, String tipo) {
        // Si en combos usas "-" como "sin filtro"
        if (estado != null && estado.equals("-")) estado = "";
        if (tipo != null && tipo.equals("-")) tipo = "";
        return dao.listarParaReporte(fi, ff, estado, tipo);
    }

    public void exportarTablaACSV(JTable tabla, String rutaArchivo) throws Exception {
        TableModel model = tabla.getModel();

        try (PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(rutaArchivo), "UTF-8"))) {

            // Encabezados
            for (int c = 0; c < model.getColumnCount(); c++) {
                pw.print(model.getColumnName(c));
                if (c < model.getColumnCount() - 1) pw.print(",");
            }
            pw.println();

            // Filas
            for (int r = 0; r < model.getRowCount(); r++) {
                for (int c = 0; c < model.getColumnCount(); c++) {
                    Object val = model.getValueAt(r, c);
                    String texto = (val == null) ? "" : val.toString();

                    // CSV seguro
                    texto = texto.replace("\"", "\"\"");
                    if (texto.contains(",") || texto.contains("\"")) texto = "\"" + texto + "\"";

                    pw.print(texto);
                    if (c < model.getColumnCount() - 1) pw.print(",");
                }
                pw.println();
            }
        }
    }
}
