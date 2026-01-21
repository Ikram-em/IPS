package giis.demo.tkrun.logger;

import java.util.Arrays;
import java.util.List;

public class CompositeLogger implements ILogger {

	private final List<ILogger> loggers;

	public CompositeLogger(ILogger... loggers) {
		this.loggers = Arrays.asList(loggers);
	}

	@Override
	public void log(int sessionId, String action, String details) {
		for (ILogger logger : loggers) {
			logger.log(sessionId, action, details);
		}
	}

}
