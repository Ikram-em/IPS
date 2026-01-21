package giis.demo.tkrun.medico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import giis.demo.tkrun.employee.EmployeeViewDTO;
import giis.demo.tkrun.partidos.Partido;
import giis.demo.tkrun.teammanagement.EquipoViewDTO;
import giis.demo.tkrun.teammanagement.entrenamiento.EntrenamientoDTO;
import giis.demo.util.Database;

public class LesionesModel {

	private Database dataBase = new Database();

	private final String FIND_JUGADORES_LESIONADOS = "SELECT e.id_empleado, e.nombre, e.apellido, l.diagnostico, l.prioridad "
			+ "FROM EmpleadoDeportivo ed " + "JOIN Empleado e ON ed.id_empleado = e.id_empleado "
			+ "JOIN Lesion l ON l.id_jugador = e.id_empleado ORDER BY alta_medica ASC, fecha_lesion DESC";

	private final String FIND_EQUIPOS = "SELECT e.id_equipo, e.nombre, e.id_primer_entrenador, e.id_segundo_entrenador, "
			+ "e.tipo_equipo, e.categoria_equipo FROM Equipo e";

	private final String FIND_JUGADORES = "SELECT e.id_empleado, e.dni, e.nombre, e.apellido, "
			+ "e.fecha_nacimiento, e.telefono, e.salario_anual, e.fecha_creacion, e.id_instalacion FROM Empleado e, Plantilla p WHERE e.id_empleado = p.id_jugador";

	private final String FIND_JUGADORES_EQUIPO = "SELECT e.id_empleado, e.dni, e.nombre, e.apellido, "
			+ "e.fecha_nacimiento, e.telefono, e.salario_anual, e.fecha_creacion, e.id_instalacion FROM Empleado e, Plantilla p WHERE "
			+ "e.id_empleado = p.id_jugador AND p.id_equipo = ?";

	private final String FIND_JUGADORES_LESIONADOS_EQUIPO = "SELECT e.id_empleado, e.nombre, e.apellido, l.diagnostico, l.prioridad "
			+ "FROM Plantilla p " + "JOIN EmpleadoDeportivo ed ON p.id_jugador = ed.id_empleado "
			+ "JOIN Empleado e ON e.id_empleado = ed.id_empleado " + "JOIN Lesion l ON l.id_jugador = e.id_empleado "
			+ "WHERE l.alta_medica = 0 AND p.id_equipo = ?";

	private final String FIND_JUGADOR_LESIONADO = "SELECT e.id_empleado, e.nombre, e.apellido, l.diagnostico, l.prioridad, l.alta_medica, l.fecha_lesion "
			+ "FROM EmpleadoDeportivo ed " + "JOIN Empleado e ON ed.id_empleado = e.id_empleado "
			+ "JOIN Lesion l ON l.id_jugador = e.id_empleado " + "WHERE e.id_empleado = ?";

	private final String FIND_LESIONES_JUGADOR = "SELECT l.id_lesion, l.id_jugador, l.id_partido, l.id_entreno, "
			+ "l.prioridad, l.diagnostico, l.alta_medica, l.fecha_lesion, " + "e.nombre, e.apellido "
			+ "FROM EmpleadoDeportivo ed " + "JOIN Empleado e ON ed.id_empleado = e.id_empleado "
			+ "JOIN Lesion l ON l.id_jugador = e.id_empleado " + "WHERE e.id_empleado = ? "
			+ "ORDER BY l.fecha_lesion DESC";
	
	private final String INSERT_LESION =
	        "INSERT INTO Lesion (id_jugador, diagnostico, prioridad, alta_medica, fecha_lesion, id_entreno, id_partido) "
	      + "VALUES (?, ?, ?, 0, CURRENT_TIMESTAMP, ?, ?)";

	private final String INSERT_SESION_LESION = "INSERT INTO SesionLesion (id_lesion, accion, prioridad, diagnostico, alta_medica) "
			+ "VALUES (?, ?, ?, ?, ?)";

	private final String UPDATE_DAR_DE_ALTA = "UPDATE Lesion SET alta_medica = 1 WHERE id_lesion = ?";

	private final String UPDATE_LESION = "UPDATE Lesion SET prioridad=?, diagnostico=? WHERE id_lesion=?";

	private final String FIND_HISTORIAL_LESION = "SELECT fecha_hora, accion, prioridad, diagnostico, alta_medica "
			+ "FROM SesionLesion " + "WHERE id_lesion = ? " + "ORDER BY fecha_hora DESC";

	public List<InjuredPlayerViewDTO> getInjuredPlayers() {
		return dataBase.executeQueryPojo(InjuredPlayerViewDTO.class, FIND_JUGADORES_LESIONADOS);
	}

	public List<EquipoViewDTO> getAllEquipos() {
		return dataBase.executeQueryPojo(EquipoViewDTO.class, FIND_EQUIPOS);
	}

	public List<EmployeeViewDTO> getAllJugadores() {
		return dataBase.executeQueryPojo(EmployeeViewDTO.class, FIND_JUGADORES);
	}

	public List<EmployeeViewDTO> getJugadoresEquipo(int id_equipo) {
		return dataBase.executeQueryPojo(EmployeeViewDTO.class, FIND_JUGADORES_EQUIPO, id_equipo);
	}

	public List<InjuredPlayerViewDTO> getInjuredPlayersFromEquipo(int id_equipo) {
		return dataBase.executeQueryPojo(InjuredPlayerViewDTO.class, FIND_JUGADORES_LESIONADOS_EQUIPO, id_equipo);
	}

	public List<InjuredPlayerViewDTO> getInjuredPlayersById(int id_empleado) {
		return dataBase.executeQueryPojo(InjuredPlayerViewDTO.class, FIND_JUGADOR_LESIONADO, id_empleado);
	}

	public List<LesionDTO> getLesionesJugador(int id_empleado) {
		return dataBase.executeQueryPojo(LesionDTO.class, FIND_LESIONES_JUGADOR, id_empleado);
	}

	public Integer addLesion(int idJugador, EntrenamientoDTO entreno, Partido partido, String prioridad,
			String diagnostico) {
		Integer idEntrenamiento = (entreno == null) ? null : entreno.getId_entrenamiento();
	    Integer idPartido = (partido == null) ? null : partido.getIdPartido();

		return dataBase.executeInsertAndReturnId(INSERT_LESION,
	            idJugador,
	            diagnostico,
	            prioridad,
	            idEntrenamiento,
				idPartido).intValue();
	}

	public void darDeAlta(Integer id_lesion) {
		dataBase.executeUpdate(UPDATE_DAR_DE_ALTA, id_lesion);
	}

	public void addSesionLesion(int idLesion, String accion, String prioridad, String diagnostico,
			int altaMedica) {
		dataBase.executeUpdate(INSERT_SESION_LESION, idLesion, accion, 
				prioridad, diagnostico, altaMedica);
	}

	public void actualizarLesion(Integer id_lesion, String nuevaPrioridad, String nuevoDiagnostico) {
		dataBase.executeUpdate(UPDATE_LESION, nuevaPrioridad, nuevoDiagnostico, id_lesion);
	}

	public List<SesionLesionViewDTO> getHistorialLesion(int lesionId) {
		return dataBase.executeQueryPojo(SesionLesionViewDTO.class, FIND_HISTORIAL_LESION, lesionId);
	}
	
	public int countLesionesJugador(int idJugador) {
	    String sql = "SELECT COUNT(*) FROM Lesion WHERE id_jugador=?";

	    try (Connection cn = dataBase.getConnection();
	         PreparedStatement st = cn.prepareStatement(sql)) {

	        st.setInt(1, idJugador);
	        ResultSet rs = st.executeQuery();

	        if (rs.next())
	            return rs.getInt(1);

	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }

	    return 0;
	}


}
