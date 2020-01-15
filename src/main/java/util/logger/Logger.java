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
	
	private Logger() {}
	
	public static Logger getInstance() {
		if(logger==null) {
			logger = new Logger();
			logFile = new File("files/log/log_"+ getNow(FECHA));
		}
		return logger;
	}
	
	public void log(String mensaje, LogLevel logLevel) {
		gestorFicheros.abrirFichero("files/log/log_"+ getNow(FECHA) +".log");
	}
	
	private static String getNow(String format) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(cal.getTime());
	}
}
