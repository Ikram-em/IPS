package giis.demo.tkrun.logger;

import java.util.List;

import giis.demo.tkrun.logger.view.SesionLogDTO;
import giis.demo.tkrun.logger.view.UserLogDTO;
import giis.demo.util.Database;

public class LoggerModel {

	private Database dataBase = new Database();

	private final String INSERT_USER_SESSION = "INSERT INTO User_session (user_id) VALUES (?)";
	private final String INSERT_USER_LOG = "INSERT INTO User_log (session_id, accion, detalles) VALUES (?,?,?)";

	/*****************************************************************************************************************/

	private final String FIND_ALL_SESSIONS = "SELECT us.session_id, us.user_id, us.fecha_inicio, u.username "
			+ "FROM User_session us " + "JOIN User u ON us.user_id = u.id_user " + "ORDER BY us.fecha_inicio DESC";

	private final String FIND_ALL_SESSIONS_BY_USER = "SELECT us.session_id, us.user_id, us.fecha_inicio, u.username "
			+ "FROM User_session us " + "JOIN User u ON us.user_id = u.id_user "
			+ "ORDER BY u.username ASC, us.fecha_inicio DESC";

	private final String FIND_ALL_LOG_SESSION = "SELECT ul.log_id, ul.session_id, ul.accion, ul.detalles, ul.fecha_hora, us.user_id "
			+ "FROM User_log ul " + "JOIN User_session us ON ul.session_id = us.session_id "
			+ "WHERE ul.session_id = ? " + "ORDER BY ul.fecha_hora";

	public int insertSession(int userId) {
		Number id = dataBase.executeInsertAndReturnId(INSERT_USER_SESSION, userId);
		return id != null ? id.intValue() : -1;
	}

	public void insertLog(int sessionId, String action, String details) {
		dataBase.executeUpdate(INSERT_USER_LOG, sessionId, action, details);
	}

	public List<SesionLogDTO> getAllSessions() {
		return dataBase.executeQueryPojo(SesionLogDTO.class, FIND_ALL_SESSIONS);
	}

	public List<SesionLogDTO> getAllSessionsByUser() {
		return dataBase.executeQueryPojo(SesionLogDTO.class, FIND_ALL_SESSIONS_BY_USER);
	}

	public List<UserLogDTO> getLogsSesion(int session_id) {
		return dataBase.executeQueryPojo(UserLogDTO.class, FIND_ALL_LOG_SESSION, session_id);
	}

}
