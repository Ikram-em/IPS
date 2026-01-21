package giis.demo.tkrun.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

public class FileLogger implements ILogger {

	private final File logFile;

	public FileLogger(String filePath) {
		File folder = new File("logs");
		if (!folder.exists()) {
			folder.mkdirs();
		}
		logFile = new File(folder, filePath); // logs/nombreFichero.log
	}

	@Override
	public void log(int sessionId, String action, String details) {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) {
			writer.write(ts + " | session=" + sessionId + " | " + action + " | " + details + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
