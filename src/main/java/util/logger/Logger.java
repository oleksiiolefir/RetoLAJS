package util.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Logger {

	private static Logger logger;
	private static File logFile;
	private static int minLevel;
	private static final String DIR_LOG = "files/log/";
	private static final String FECHA = "yyyy-MM-dd";
	// private static final String HORA = "HH:mm:ss";
	private static final String FECHA_HORA = "yyyy-MM-dd HH:mm:ss";

	private Logger() {
	}

	public static Logger getInstance() {
		if (logFile == null) {
			logFile = new File(DIR_LOG + "log_" + getCurrentDate(FECHA) + ".log");
			createLogFile();
		}
		if (logger == null) {
			logger = new Logger();
			minLevel = 0;
		}
		return logger;
	}

	public void setMinLevel(LogLevel logLevel) {
		Logger.minLevel = logLevel.ordinal();
	}

	private boolean checkLogLevel(int logLevel) {
		return (Logger.minLevel <= logLevel) ? true : false;
	}

	public void log(String mensaje, LogLevel logLevel, Class<?> clase, Class<?> exception) {
		if (checkLogLevel(logLevel.ordinal())) {
			StringBuilder sb = new StringBuilder();
			sb.append("[" + getCurrentDate(FECHA_HORA) + "]");
			if (logLevel != null)
				sb.append(logLevel);
			if (clase != null)
				sb.append("[Class: " + clase.getTypeName() + "]");
			if (exception != null)
				sb.append("[Exception: " + exception.getSimpleName() + "]");
			if (mensaje != null)
				sb.append(" " + mensaje);

			try {
				writeToFile(sb.toString());
			} catch (IOException e) {
				System.out.println("Error al escribir log");
			}
		}
	}

	private static String getCurrentDate(String format) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(cal.getTime());
	}

	private static void writeToFile(String text) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true));
		writer.write(text);
		writer.newLine();
		writer.close();
	}

	private static void createLogFile() {
		try {
			if (!logFile.exists())
				if (!logFile.getParentFile().exists())
					if (logFile.getParentFile().mkdirs())
						logFile.createNewFile();
		} catch (IOException e) {
			System.out.println("Error al crear archivo log");
		}

	}
}