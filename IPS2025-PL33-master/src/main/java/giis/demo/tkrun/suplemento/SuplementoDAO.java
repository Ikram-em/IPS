package giis.demo.tkrun.suplemento;

import giis.demo.util.Database;
import java.util.ArrayList;
import java.util.List;

public class SuplementoDAO {

    private Database db = new Database();

    public String[] getAbonos() {
        String sql = "SELECT id_abono, dni_cliente FROM Abono\r\n"
        		+ "";
        List<Object[]> rows = db.executeQueryArray(sql);

        List<String> abonos = new ArrayList<>();
        for (Object[] row : rows) {
            int idAbono = Integer.parseInt(row[0].toString());
            String dni = (row[1] != null) ? row[1].toString() : "DESCONOCIDO";
            abonos.add(idAbono + " - " + dni);
        }

        return abonos.toArray(new String[0]);
    }



    public String[] getPartidosConSuplemento() {
        String sql = 
            "SELECT p.id_partido, " +
            "       el.nombre AS equipo_local, " +
            "       ee.nombre AS equipo_externo, " +
            "       p.fecha " +
            "FROM Partido p " +
            "LEFT JOIN Equipo el ON p.id_equipo = el.id_equipo " +
            "LEFT JOIN EquipoExterno ee ON p.id_equipo_externo = ee.id_equipo " +
            "WHERE p.tieneSuplemento = 1 " +
            "ORDER BY p.fecha";

        List<Object[]> rows = db.executeQueryArray(sql);
        List<String> partidos = new ArrayList<>();

        for (Object[] row : rows) {
            int idPartido = Integer.parseInt(row[0].toString());
            String equipoLocal = (row[1] != null) ? row[1].toString() : "Equipo Local";
            String equipoExterno = (row[2] != null) ? row[2].toString() : "Equipo Visitante";
            String fecha = (row[3] != null) ? row[3].toString() : "";

            partidos.add(idPartido + " - " + equipoLocal + " vs " + equipoExterno + 
                         (fecha.isEmpty() ? "" : " (" + fecha + ")"));
        }

        return partidos.toArray(new String[0]);
    }


    /** Inserta un pago de suplemento, evitando duplicados */
    public boolean pagarSuplemento(int idAbono, int idPartido, double precio) {
        // Comprueba primero si ya existe
        String sqlCheck = "SELECT COUNT(*) FROM Suplemento WHERE idAbono = ? AND idPartido = ?";
        List<Object[]> rows = db.executeQueryArray(sqlCheck, idAbono, idPartido);
        int count = ((Number) rows.get(0)[0]).intValue();

        if (count > 0) {
            return false;
        }
        String sqlInsert = "INSERT INTO Suplemento (idAbono, idPartido, precio, fechaCompra) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
        db.executeUpdate(sqlInsert, idAbono, idPartido, precio);
        return true;
    }


}

