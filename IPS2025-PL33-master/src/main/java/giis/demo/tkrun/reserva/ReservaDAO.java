package giis.demo.tkrun.reserva;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import giis.demo.util.Database;
import giis.demo.util.Util;

public class ReservaDAO {
    private final Database db = new Database();
  

    /**
     * Devuelve una lista de todos los periodos en los que la instalación está
     * ocupada (por empleados o reservas externas).
     */
    public List<HorarioOcupadoDTO> findHorariosOcupados(int id_instalacion, Date fecha) {
        String fechaStr = Util.dateToIsoString(fecha);
        List<HorarioOcupadoDTO> ocupados = new ArrayList<>();

        // ---- Entrenamientos ----
        String sqlEntrenamientos = "SELECT hora_inicio, hora_fin, 1 AS esEmpleado "
                + "FROM Entrenamiento "
                + "WHERE fecha = ? AND id_instalacion = ?";
        ocupados.addAll(db.executeQueryPojo(HorarioOcupadoDTO.class, sqlEntrenamientos, fechaStr, id_instalacion));

        // ---- Partidos ----
        String sqlPartidos = "SELECT hora_inicio, hora_fin, 1 AS esEmpleado "
                + "FROM Partido "
                + "WHERE fecha = ? AND id_instalacion = ?";
        ocupados.addAll(db.executeQueryPojo(HorarioOcupadoDTO.class, sqlPartidos, fechaStr, id_instalacion));

        // ---- Reservas externas ----
        String sqlReservas = "SELECT hora_inicio, hora_fin, 0 AS esEmpleado "
                + "FROM ReservaExterna "
                + "WHERE fecha = ? AND id_instalacion = ?";
        ocupados.addAll(db.executeQueryPojo(HorarioOcupadoDTO.class, sqlReservas, fechaStr, id_instalacion));

        // ---- Eliminar duplicados ----
        List<HorarioOcupadoDTO> filtrados = new ArrayList<>();
        for (HorarioOcupadoDTO h : ocupados) {
            boolean existe = filtrados.stream().anyMatch(f ->
                f.getHora_inicio().equals(h.getHora_inicio()) &&
                f.getHora_fin().equals(h.getHora_fin())
            );
            if (!existe) filtrados.add(h);
        }

        return filtrados;
    }

    

    /**
     * Inserta una nueva reserva externa en la base de datos y registra el ingreso.
     */
	public Integer insertReserva(ReservaDTO reserva) {
        String sql = "INSERT INTO ReservaExterna (nombre_usuario, num_tarjeta, id_instalacion, fecha, hora_inicio, hora_fin, precio_total) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";

		return db.executeInsertAndReturnId(sql,
                         reserva.getNombreUsuario(),
                         reserva.getNumTarjeta(),
                         reserva.getId_instalacion(),
                         Util.dateToIsoString(reserva.getFecha()), 
                         Util.timeToTimeString(reserva.getHoraInicio()),
                         Util.timeToTimeString(reserva.getHoraFin()),
				reserva.getPrecioTotal()).intValue();
    }

}
