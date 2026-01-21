package giis.demo.tkrun.abonos;

import giis.demo.util.Database;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;

public class AbonoDAO {
    private final Database db = new Database();

  
    public boolean insertAbono(AbonoDTO abono) {
        if (clienteYaTieneAbono(abono.getDniCliente())) {
            JOptionPane.showMessageDialog(null, "Este cliente ya tiene un abono.");
            return false;
        }

        String sqlPurchase = "INSERT INTO PurchaseAbono (dni_cliente, idAsiento, tribuna, seccion, fila, precio, fechaCompra) "
                + "VALUES (?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";


        String sqlAbono = "INSERT INTO Abono (dni_cliente, id_tribune, id_section, fila, id_asiento, fecha_inicio, fecha_fin, precio_total) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            LocalDate inicio = (abono.getFechaInicio() != null && !abono.getFechaInicio().isEmpty())
                    ? LocalDate.parse(abono.getFechaInicio())
                    : LocalDate.now();
            LocalDate fin = (abono.getFechaFin() != null && !abono.getFechaFin().isEmpty())
                    ? LocalDate.parse(abono.getFechaFin())
                    : inicio.plusYears(1);

            // Ejecutar INSERTs con dniCliente
            db.executeUpdate(sqlPurchase,
                    abono.getDniCliente(),
                    abono.getAsiento(),
                    abono.getTribunaNombre(),
                    abono.getSeccionNombre(),
                    abono.getFila(),
                    abono.getPrecioTotal());

            db.executeUpdate(sqlAbono,
                    abono.getDniCliente(),
                    abono.getTribunaNombre(),
                    abono.getSeccionNombre(),
                    abono.getFila(),
                    abono.getAsiento(),
                    Date.valueOf(inicio),
                    Date.valueOf(fin),
                    abono.getPrecioTotal());

            return true;

        } catch (Exception e) {
            String msg = e.getMessage();
            if (msg != null && msg.contains("UNIQUE")) {
                JOptionPane.showMessageDialog(null, "No se puede crear el abono: asiento ocupado o restricción de cliente.");
            } else {
                JOptionPane.showMessageDialog(null, "Error al insertar el abono: " + msg);
            }
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Comprueba si un cliente ya tiene un abono según su DNI.
     */
    public boolean clienteYaTieneAbono(String dniCliente) {
        String sql = "SELECT COUNT(*) FROM PurchaseAbono WHERE dni_cliente = ?";
        List<Object[]> result = db.executeQueryArray(sql, dniCliente);
        return !result.isEmpty() && Integer.parseInt(result.get(0)[0].toString()) > 0;
    }


}

