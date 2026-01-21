package giis.demo.tkrun.teammanagement.entrenamiento;

import java.util.List;

import giis.demo.tkrun.jardineria.LaborJardineriaDTO;
import giis.demo.util.Database;

public class EntrenamientoDAO {
	private Database database = new Database();
	
	private static String SQL_EXISTS_FACILITY_OVERLAP = "SELECT e.id_entrenamiento AS id" +
			" FROM Entrenamiento e WHERE e.fecha = ?" + 
			" AND e.hora_inicio < ? AND e.hora_fin > ? AND e.id_instalacion = ?";
	
	private static String SQL_EXISTS_TEAM_TRAINING_OVERLAP = "SELECT e.id_entrenamiento AS id" +
			" FROM Entrenamiento e WHERE e.fecha = ?" + 
			" AND e.hora_inicio < ? AND e.hora_fin > ? AND e.id_equipo = ?";
	
	private static String SQL_EXISTS_GARDENING_OVERLAP = "SELECT l.id FROM LaborJardineria l WHERE" +
			" l.fecha = ? AND l.hora_inicio < ? AND l.hora_fin > ? AND l.id_instalacion = ?";
	
	private static String SQL_INSERT_TRAINING = "INSERT INTO Entrenamiento (id_equipo, fecha, hora_inicio, hora_fin, id_instalacion)" +
			" VALUES (?, ?, ?, ?, ?)";
	
	private static String SQL_DELETE_OVERLAPPING_INTERVIEWS = "DELETE FROM FranjaEntrevista" + 
			" WHERE id_jugador IN (SELECT p.id_jugador FROM Plantilla p WHERE p.id_equipo = ?)" +
			" AND fecha = ? AND hora_inicio < ? AND hora_fin > ?";
	
	private static final String FIND_ALL_PARTIDOS = "SELECT id_entrenamiento, id_equipo, fecha, hora_inicio, hora_fin, id_instalacion FROM Entrenamiento";
	
	private static final String SQL_FIND_BY_ID = 
	        "SELECT id_entrenamiento, id_equipo, fecha, hora_inicio, hora_fin, id_instalacion " +
	        "FROM Entrenamiento WHERE id_entrenamiento = ?";

	public String hasOverlap(String fecha, String horaInicio, String horaFin, int idInstalacion, int idEquipo) {
		List<EntrenamientoDTO> overlappingTrainingInFacility = database.executeQueryPojo(EntrenamientoDTO.class, SQL_EXISTS_FACILITY_OVERLAP, fecha, horaFin, horaInicio, idInstalacion);
		List<LaborJardineriaDTO> overlappingGardeningSession = database.executeQueryPojo(LaborJardineriaDTO.class, SQL_EXISTS_GARDENING_OVERLAP, fecha, horaFin, horaInicio, idInstalacion);
		List<EntrenamientoDTO> overlappingTrainingOfTeam = database.executeQueryPojo(EntrenamientoDTO.class, SQL_EXISTS_TEAM_TRAINING_OVERLAP, fecha, horaFin, horaInicio, idEquipo);
		
		if (overlappingTrainingInFacility.size() > 0) {
			return "No se puede crear el entrenamiento porque la instalación se encuentra ocupada en el momento indicado";
		}
		
		if (overlappingTrainingOfTeam.size() > 0) {
			return "No se puede crear el entrenamiento porque el equipo ya tiene un entrenamiento en el horario indicado";
		}
		
		if (overlappingGardeningSession.size() > 0) {
			return "No se puede crear el entrenamiento porque la instalación estará bajo mantenimiento de jardineria en el momento indicado";
		}
		
		return null;
	}
	
	public int createTraining(int idEquipo, String fecha, String horaInicio, String horaFin, int idInstalacion) {
		Number id_entreno = database.executeInsertAndReturnId(SQL_INSERT_TRAINING, idEquipo, fecha, horaInicio, horaFin,
				idInstalacion);
		database.executeUpdate(SQL_DELETE_OVERLAPPING_INTERVIEWS, idEquipo, fecha, horaFin, horaInicio);
		return id_entreno.intValue();
	}

	public List<EntrenamientoDTO> getTodosEntrenamientos() {
		return database.executeQueryPojo(EntrenamientoDTO.class, FIND_ALL_PARTIDOS);
	}

	public EntrenamientoDTO getEntrenamiento(Integer id_entreno) {
		List<EntrenamientoDTO> list = database.executeQueryPojo(
	            EntrenamientoDTO.class, SQL_FIND_BY_ID, id_entreno);

	    // Si no hay resultados, devolvemos null
	    if (list.isEmpty()) {
	        return null;
	    }

	    return list.get(0);
	}
}
