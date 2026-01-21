package giis.demo.tkrun.logger;

public class AuditService {

	private final ILogger logger;
	private final LoggerModel model;
	private int sessionId = -1;

	// Inyectamos tanto el logger como el model para permitir que startSession use
	// model
	public AuditService(ILogger logger, LoggerModel model) {
		this.logger = logger;
		this.model = model;
	}

	// Devuelve el sessionId creado en BD
	public int startSession(int userId) {
		this.sessionId = model.insertSession(userId);
		return this.sessionId;
	}

	public void log(String action, String details) {
		if (sessionId == -1) {
			System.err.println("AuditService: sessionId no inicializado. Ignorando log.");
			return;
		}
		logger.log(sessionId, action, details);
	}

	public void endSession() {
		if (sessionId != -1) {
			log("LOGOUT", "Cierre de sesión");
			sessionId = -1;
		}
	}

	public int getSessionId() {
		return sessionId;
	}

}
