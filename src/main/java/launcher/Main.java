package launcher;

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
			Usuario usuario = new Usuario(true, "admin", "admin", null, null, null, null);
			HibernateManager.insertData(usuario);
		} catch (Exception e) {
			Logger.getInstance().log("Error", LogLevel.FATAL, null, e);
		} finally {
			HibernateManager.disconnect();			
		}
		Logger.getInstance().log("Fin sesion", LogLevel.INFO, null, null);
	}
	
}
