package giis.demo.tkrun.abonos;

import giis.demo.tkrun.partidos.entradas.ZoneDTO;
import giis.demo.util.Database;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

public class AbonoModel {

    private Database db = new Database();
    private AbonoDAO abonoDAO = new AbonoDAO();


    public boolean estaAsientoBloqueadoTemporada(int idAsiento, int fila, ZoneDTO tribuna, ZoneDTO seccion) {
        String sql = "SELECT COUNT(*) FROM purchaseAbono "
                   + "WHERE idAsiento = ? AND fila = ? AND tribuna = ? AND seccion = ?";
        List<Object[]> result = db.executeQueryArray(sql, idAsiento, fila, tribuna.getNombre(), seccion.getNombre());
        return !result.isEmpty() && Integer.parseInt(result.get(0)[0].toString()) > 0;
    }

    public Map<Integer, Boolean> getAsientosOcupadosExactos(ZoneDTO tribuna, ZoneDTO seccion, int fila) {
        Map<Integer, Boolean> ocupados = new HashMap<>();
        String sql = "SELECT idAsiento FROM PurchaseAbono WHERE tribuna = ? AND seccion = ? AND fila = ?";
        List<Object[]> rows = db.executeQueryArray(sql, tribuna.getNombre(), seccion.getNombre(), fila);
        for (Object[] row : rows) {
            int asiento = Integer.parseInt(row[0].toString());
            ocupados.put(asiento, true);
        }
        return ocupados;
    }
    public AbonoDTO crearAbono(String nombreCliente, String dniCliente, int fila, int idAsiento, ZoneDTO tribuna, ZoneDTO seccion) {
        if (abonoDAO.clienteYaTieneAbono(dniCliente)) {
            JOptionPane.showMessageDialog(null, "Este cliente ya tiene un abono.");
            return null;
        }
        if (estaAsientoBloqueadoTemporada(idAsiento, fila, tribuna, seccion)) {
            JOptionPane.showMessageDialog(null, "Ese asiento ya está reservado en esa fila, tribuna y sección.");
            return null;
        }
        AbonoDTO abono = new AbonoDTO();
        abono.setNombreCliente(nombreCliente);
        abono.setDniCliente(dniCliente);          
        abono.setFila(fila);
        abono.setAsiento(idAsiento);
        abono.setPrecioTotal(calcularPrecioAbono(tribuna.getNombre(), seccion.getNombre()));
        abono.setTribunaNombre(tribuna.getNombre());
        abono.setSeccionNombre(seccion.getNombre());

        boolean insertado = abonoDAO.insertAbono(abono);

        if (!insertado) {
            JOptionPane.showMessageDialog(null, "Error al crear el abono.");
            return null;
        }

        return abono;
    }

  
    public double calcularPrecioAbono(String tribuna, String seccion) {
        double precioBase = 100.0;

        switch (tribuna.toUpperCase()) {
            case "A": precioBase += 100; break;
            case "B": precioBase += 80; break;
            case "C": precioBase += 60; break;
            case "D": precioBase += 40; break;
        }

        switch (seccion.toUpperCase()) {
            case "A": precioBase += 50; break;
            case "B": precioBase += 40; break;
            case "C": precioBase += 30; break;
            case "D": precioBase += 20; break;
            case "E": precioBase += 10; break;
            case "F": precioBase += 0; break;
        }

        return precioBase;
    }

    public List<ZoneDTO> getTribunes() {
        String sql = "SELECT id, nombre FROM Tribune";
        return db.executeQueryPojo(ZoneDTO.class, sql);
    }

    public List<ZoneDTO> getSections() {
        String sql = "SELECT id, nombre FROM Section";
        return db.executeQueryPojo(ZoneDTO.class, sql);
    }
    
   
    

}
