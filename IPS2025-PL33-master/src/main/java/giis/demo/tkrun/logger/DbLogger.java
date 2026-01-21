package giis.demo.tkrun.logger;

public class DbLogger implements ILogger {

	private final LoggerModel model;

	public DbLogger(LoggerModel model) {
		this.model = model;
	}

	@Override
	public void log(int sessionId, String action, String details) {
		model.insertLog(sessionId, action, details);
	}

}
