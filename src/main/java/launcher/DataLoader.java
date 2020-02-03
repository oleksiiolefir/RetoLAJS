package launcher;

import java.io.IOException;
import java.util.ArrayList;

import entity.Alojamiento;
import file.JsonFileManager;
import file.TextFileManager;
import hibernate.HibernateManager;
import logger.LogLevel;
import logger.Logger;

public class DataLoader {
	
	public DataLoader(String urlsFilepath) throws Exception {
		loadData(loadUrlsFile(urlsFilepath));
	}
	
	private void loadData(ArrayList<String> urls) throws Exception {
		for(String url : urls) {
			String xmlFilepath = makeFilepath(Constants.XML_DIRPATH, url, ".xml");
			ArrayList<Alojamiento> alojamientos = new UrlReader().getAlojamientos(url, xmlFilepath);
			
			if(alojamientos!=null) {
				String jsonFilepath = makeFilepath(Constants.JSON_DIRPATH , url , ".json");
				makeJsonFile(jsonFilepath, alojamientos);
				HibernateManager.insertData(alojamientos);
			}
		}
	} 
	
	private String makeFilepath(String dirpath, String url, String extension) {
		String[] urlSplit = url.split("/");
		return dirpath + urlSplit[5] + extension;
	}
	
	private ArrayList<String> loadUrlsFile(String filepath) throws IOException {
		try {
			return new TextFileManager().getFileLines(filepath);
		} catch (IOException e) {
			throw new IOException("Error al cargar fichero principal", e);
		}
	}
	
	private void makeJsonFile(String filepath, ArrayList<?> data) {
		try {
			new JsonFileManager().writeFile(filepath, data);
		} catch (IOException e) {
			Logger.getInstance().log("Error al crear fichero json", LogLevel.ERROR, null, e);
		}
	}
}
