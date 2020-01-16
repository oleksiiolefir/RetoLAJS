package util.logger;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import util.file.GestorFicheros;

public class Logger {

	private static Logger logger;
	private static String path;
	private static final String FECHA = "yyyy-MM-dd";
	private static final String HORA = "HH:mm:ss";
	private static final String FECHA_HORA = "yyyy-MM-dd HH:mm:ss";
	
	private Logger() {}
	
	public static Logger getInstance() {
		path = "files/log/log_" + getCurrentDate(FECHA) + ".log";
		if(logger==null) 
			logger = new Logger();
		return logger;
	}
	
	public boolean log(String mensaje, LogLevel logLevel, Class<?> clase, Class<?> exception) {
		StringBuilder sb = new StringBuilder();
		sb.append("[" + getCurrentDate(FECHA_HORA) + "]");
		if(logLevel!=null) sb.append(logLevel);
		if(clase!=null) sb.append("[Class: " + clase.getTypeName() + "]");
		if(exception!=null) sb.append("[Exception: " + exception.getSimpleName()+ "]");
		if(mensaje!=null) sb.append(" " + mensaje);
		
		if (new GestorFicheros().writeFile(path, true, sb.toString()))
			return true;
		else
			return false;
	}
	
	private static String getCurrentDate(String format) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(cal.getTime());
	}
}
