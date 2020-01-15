package util.logger;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import util.file.GestorFicheros;

public class Logger {

	private static Logger logger;
	private static GestorFicheros gestorFicheros;
	private static File logFile;
	private static final String FECHA = "yyyy-MM-dd";
	private static final String HORA = "HH:mm:ss";
	private static final String FECHA_HORA = "yyyy-MM-dd HH:mm:ss";
	
	private Logger() {}
	
	public static Logger getInstance() {
		if(logger==null) {
			logger = new Logger();
			logFile = new File("files/log/log_"+ getCurrentDate(FECHA));
		}
		return logger;
	}
	
	public void log(String mensaje, LogLevel logLevel, Class clase) {
		//gestorFicheros.abrirFichero("files/log/log_"+ getCurrentDate(FECHA) +".log");
		StringBuilder sb = new StringBuilder();
		sb.append(getCurrentDate(FECHA_HORA));
		sb.append(" - ");
		sb.append(clase.getTypeName());
		sb.append("[");
		sb.append(logLevel);
		sb.append("] - ");
		sb.append(mensaje);
		//lector.escribirFichero(sb.toString());
	}
	
	public void log(String mensaje, LogLevel logLevel, Class<?> clase, Class<?> exception) {
		//gestorFicheros.abrirFichero("files/log/log_"+ getCurrentDate(FECHA) +".log");
		StringBuilder sb = new StringBuilder();
		sb.append(getCurrentDate(FECHA_HORA));
		sb.append(" - ");
		sb.append(clase.getTypeName());
		sb.append("[");
		sb.append(logLevel);
		sb.append("] - ");
		sb.append(mensaje);
		//lector.escribirFichero(sb.toString());
	}
	
	private static String getCurrentDate(String format) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(cal.getTime());
	}
}
