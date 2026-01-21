package giis.demo.tkrun.ingresos;

import giis.demo.util.ApplicationException;
import giis.demo.util.Database;
import giis.demo.util.Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IngresoDAO {
    private final Database db = new Database();

    public List<IngresoDTO> findHistorialIngresos(Date fechaInicio, Date fechaFin) {
        String fechaInicioStr = Util.dateTimeToIsoString(fechaInicio);
        String fechaFinStr = Util.dateTimeToIsoString(fechaFin);

        String sql = "SELECT id_ingreso AS idOperacion, tipo, concepto, fecha_hora AS fechaHora, total AS cuantiaTotal "
                   + "FROM ingresos "
                   + "WHERE fecha_hora >= ? AND fecha_hora <= ? "
                   + "ORDER BY fecha_hora DESC";

        return db.executeQueryPojo(IngresoDTO.class, sql, fechaInicioStr, fechaFinStr);
    }

    public void insertIngreso(Ingreso ingreso) {
        if (ingreso.getFechaHora() == null) {
            throw new ApplicationException("La fecha del ingreso no puede ser null");
        }
        String fechaStr = Util.dateTimeToIsoString(ingreso.getFechaHora());
        String sql = "INSERT INTO ingresos (tipo, concepto, fecha_hora, total) VALUES (?, ?, ?, ?)";
        db.executeUpdate(sql, ingreso.getTipo().name(), ingreso.getConcepto(), fechaStr, ingreso.getTotal());

        System.out.println(">>> INSERTADO ingreso:");
        System.out.println("Tipo: " + ingreso.getTipo());
        System.out.println("Concepto: " + ingreso.getConcepto());
        System.out.println("FechaHora: " + fechaStr);
        System.out.println("Total: " + ingreso.getTotal());
    }

    /**
     * Convierte DTO a objetos de negocio con enum.
     */
    public List<Ingreso> getIngresosBetween(Date fechaInicio, Date fechaFin) {
        List<IngresoDTO> dtos = findHistorialIngresos(fechaInicio, fechaFin);
        List<Ingreso> ingresos = new ArrayList<>();
        for (IngresoDTO dto : dtos) {
            String tipoStr = dto.getTipo().toUpperCase().replace(" ", "_"); // convierte "ABONO TEMPORADA" → "ABONO_TEMPORADA"
            TipoIngreso tipo = TipoIngreso.valueOf(tipoStr);
            ingresos.add(new Ingreso(
                    tipo,
                    dto.getConcepto(),
                    Util.isoStringToDate(dto.getFechaHora()),
                    dto.getCuantiaTotal()
            ));
        }
        return ingresos;
    }

    
    
    public double obtenerIngresosTotales(Date fechaInicio, Date fechaFin) {
        String sql = "SELECT SUM(total) AS totalIngresos "
                   + "FROM ingresos "
                   + "WHERE fecha_hora >= ? AND fecha_hora <= ?";

        List<Object[]> resultados = db.executeQueryArray(sql, 
            Util.dateTimeToIsoString(fechaInicio),
            Util.dateTimeToIsoString(fechaFin));

        if (resultados.isEmpty() || resultados.get(0)[0] == null) {
            return 0.0;
        }

        return ((Number) resultados.get(0)[0]).doubleValue();
    }

}
