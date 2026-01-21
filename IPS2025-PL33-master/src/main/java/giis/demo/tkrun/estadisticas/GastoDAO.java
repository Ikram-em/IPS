package giis.demo.tkrun.estadisticas;

import giis.demo.util.Database;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

/**
 * Acceso a datos de gastos del club.
 * 
 * Preparada para aceptar cualquier formato de fecha:
 * - timestamp en milisegundos (13 dígitos)
 * - timestamp en segundos (10 dígitos)
 * - yyyy-MM-dd
 * - yyyy-MM-dd HH:mm:ss
 * - dd/MM/yyyy
 * - dd/MM/yyyy HH:mm:ss
 * - ISO 8601 (yyyy-MM-dd'T'HH:mm:ss y yyyy-MM-dd'T'HH:mm:ss.SSS)
 */
public class GastoDAO {

    private final Database db = new Database();

    /**
     * Parser de fecha flexible que acepta timestamps y varios formatos comunes.
     */
    private Date parseFechaFlexible(String fechaStr) throws Exception {

        if (fechaStr == null || fechaStr.isEmpty()) {
            throw new IllegalArgumentException("Fecha vacía");
        }

        // 1️⃣ Si es un número → timestamp
        if (fechaStr.matches("\\d+")) {
            long value = Long.parseLong(fechaStr);

            if (fechaStr.length() == 13) {          // milisegundos
                return new Date(value);
            } else if (fechaStr.length() == 10) {  // segundos
                return new Date(value * 1000);
            } else {                               // otros números → asumir milisegundos
                return new Date(value);
            }
        }

        // 2️⃣ Intentar formatos comunes
        String[] formatos = {
            "yyyy-MM-dd",
            "yyyy-MM-dd HH:mm:ss",
            "dd/MM/yyyy",
            "dd/MM/yyyy HH:mm:ss",
            "yyyy-MM-dd'T'HH:mm:ss",
            "yyyy-MM-dd'T'HH:mm:ss.SSS"
        };

        for (String f : formatos) {
            try {
                return new SimpleDateFormat(f).parse(fechaStr);
            } catch (Exception ignored) {}
        }

        // 3️⃣ Fallo total si no coincide ningún formato
        throw new IllegalArgumentException("Formato de fecha no reconocido: " + fechaStr);
    }

    /**
     * Devuelve todos los gastos de la base de datos.
     */
    public List<Gasto> getAllGastos() {
        List<Gasto> lista = new ArrayList<>();
        String sql = "SELECT tipo, concepto, fecha_hora, total FROM Gasto";

        try {
            List<Object[]> result = db.executeQueryArray(sql);

            for (Object[] row : result) {
                String tipo = row[0].toString();
                String concepto = row[1].toString();
                String fechaStr = row[2].toString();
                double total = Double.parseDouble(row[3].toString());

                // Usar parser flexible
                Date fecha = parseFechaFlexible(fechaStr);

                lista.add(new Gasto(tipo, concepto, fecha, total));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar gastos: " + e.getMessage());
            e.printStackTrace();
        }

        return lista;
    }

    /**
     * Devuelve los gastos filtrados por año.
     */
    public List<Gasto> getGastosByYear(int año) {
        List<Gasto> lista = new ArrayList<>();
        List<Gasto> todos = getAllGastos();

        for (Gasto g : todos) {
            @SuppressWarnings("deprecation")
            int anioGasto = g.getFechaHora().getYear() + 1900;

            if (anioGasto == año) {
                lista.add(g);
            }
        }

        return lista;
    }
}