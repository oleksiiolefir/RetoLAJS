package logger;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import file.TextFileManager;

public class Logger {

	private static final String LOG_DIRPATH = "files/log/";
	private static final String FECHA = "yyyy-MM-dd";
	private static final String FECHA_HORA = "yyyy-MM-dd HH:mm:ss";
	
	private static Logger logger;
	private String logFilepath;
	private int minLogLevel;

	private Logger() {
		logFilepath = LOG_DIRPATH + "log_" + getCurrentDate(FECHA) + ".log";
		minLogLevel = 0;
	}

	public static Logger getInstance() {
		if (logger == null)
			logger = new Logger();
		return logger;
	}

	public void setMinLogLevel(LogLevel logLevel) {
		minLogLevel = logLevel.ordinal();
	}

	private boolean checkMinLogLevel(int logLevel) {
		return (minLogLevel <= logLevel) ? true : false;
	}

	public void log(String logMsg, LogLevel logLevel, Class<?> cls, Exception ex) {
		if (checkMinLogLevel(logLevel.ordinal())) {
			StringBuilder sb = new StringBuilder();
			sb.append(getCurrentDate(FECHA_HORA) + " ");
			
			if (logLevel != null) sb.append("[" + logLevel + "] ");
			
			if (logMsg != null) sb.append("[" + logMsg + "] ");
			else sb.append("[ ] ");
			
			if (cls != null) sb.append("[Class: " + cls.getTypeName() + "] ");
			else sb.append("[ ] ");
			
			if (ex != null) {
				sb.append("[" + ex.toString() + "] ");
				if(ex.getCause()!=null)	
					sb.append(ex.getCause().toString());		
			}else sb.append("[ ] ");
			
			sb.append("\n");
			try {
				new TextFileManager().writeFile(logFilepath, true, sb.toString());
			} catch (IOException e) {
				System.out.println("Error al escribir log. " + e.getLocalizedMessage());
			}
		}
	}
	
	private String getCurrentDate(String format) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(cal.getTime());
	}

}