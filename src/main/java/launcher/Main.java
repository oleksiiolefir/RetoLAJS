package launcher;

import java.util.Date;

import entity.Usuario;
import hibernate.HibernateManager;
import logger.LogLevel;
import logger.Logger;

public class Main {
	 
	public static void main(String[] args) {
		Logger.getInstance().log("Inicio sesion", LogLevel.DEBUG, null, null);
		Logger.getInstance().setMinLogLevel(LogLevel.INFO);
		
		try {
			HibernateManager.connect();
			new DataLoader(Constants.URLS_FILEPATH);
			Usuario usuario = new Usuario(1, true, "admin", "admin", "", "", "", new Date());
			HibernateManager.insertData(usuario);
		} catch (Exception e) {
			Logger.getInstance().log("Error en main", LogLevel.FATAL, null, e);
		} finally {
			HibernateManager.disconnect();			
		}
		Logger.getInstance().log("Fin sesion", LogLevel.INFO, null, null);
	}
	
}
