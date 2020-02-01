package launcher;

import java.io.IOException;
import java.util.ArrayList;

import entity.Alojamiento;
import file.JsonFileManager;
import file.TextFileManager;
import hibernate.HibernateManager;
import logger.LogLevel;
import logger.Logger;

public class Main {

	public static ArrayList<String> urls;
	 
	public static void main(String[] args) {
		Logger.getInstance().log("Inicio sesion", LogLevel.DEBUG, null, null);
		Logger.getInstance().setMinLogLevel(LogLevel.INFO);
		
		try {
			HibernateManager.connect();
			urls = loadUrlsFile(Constants.URLS_FILEPATH);
			for(String url : urls) {
				ArrayList<Alojamiento> data = getUrlData(url);
				if(data!=null) {
					makeJsonFile(url, data);
					HibernateManager.insertData(data);
				}
			}
			// crear usuario
			HibernateManager.disconnect();
		} catch (Exception e) {
			Logger.getInstance().log("Error en main", LogLevel.FATAL, null, e);
		}
		Logger.getInstance().log("Fin sesion", LogLevel.INFO, null, null);
	}
	
	private static String makeFilepath(String dirpath, String url, String extension) {
		String[] urlSplit = url.split("/");
		return dirpath + urlSplit[5] + extension;
	}
	
	private static ArrayList<String> loadUrlsFile(String filepath) throws IOException {
		try {
			return new TextFileManager().getFileLines(filepath);
		} catch (IOException e) {
			throw new IOException("Error al cargar fichero principal", e);
		}
	}
	
	private static ArrayList<Alojamiento> getUrlData(String url) {
		String xmlFilepath = makeFilepath(Constants.XML_DIRPATH, url, ".xml");
		return new DataGetter().getData(url, xmlFilepath);
	}
	
	private static void makeJsonFile(String url, ArrayList<?> data) {
		String jsonFilepath = makeFilepath(Constants.JSON_DIRPATH , url , ".json");
		try {
			new JsonFileManager().writeFile(jsonFilepath, data);
		} catch (IOException e) {
			Logger.getInstance().log("Error al crear fichero json", LogLevel.ERROR, null, e);
		}
	}
}
