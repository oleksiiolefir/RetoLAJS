package launcher;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.w3c.dom.Element;

import objetos.Alojamiento;
import util.file.Checksum;
import util.file.GestorFicherosXML;
import util.logger.LogLevel;
import util.logger.Logger;

public class DataGetter {
	
	private static final String PATH_URLS = "files/urls.txt";
	private static final String PATH_CHECKSUMS = "files/checksums.txt";
	private static final String DIR_XML = "files/xml/";

	private GestorFicherosXML fileManager;
	private ArrayList<String> urls;
	private ArrayList<String> checksums;
	private ArrayList<Alojamiento> alojamientos;
	
	public DataGetter() {
		fileManager = new GestorFicherosXML();
		alojamientos = new ArrayList<Alojamiento>();
	}
	
	public ArrayList<Alojamiento> getData() {
		loadData();
		return alojamientos;
	}
	
	private void loadData() {
		loadUrls();
		readUrls();
		refreshChecksumFile();
	}
	
	private void loadUrls() {
		try {
			urls = fileManager.readFile(PATH_URLS);
			checksums = fileManager.readFile(PATH_CHECKSUMS);
		} catch (IOException e) {
			Logger.getInstance().log("Error al leer ficheros de carga de datos", LogLevel.ERROR, getClass(), e.getClass());
		}
	}

	private void readUrls() {
		for (int i = 0; i < urls.size(); i++) {
			String[] urlSplit = urls.get(i).split("/");
			String filepath = DIR_XML + urlSplit[5] + ".xml";
			String newChecksum = getUrlMD5(urls.get(i));
			String oldChecksum = (checksums.size() -1 < i) ? "" : checksums.get(i);
					
			if(!cacheUrl(newChecksum, oldChecksum)) {
				downloadUrl(urls.get(i), filepath);
				refreshChecksum(newChecksum, i);
				readXml(filepath);
			}		
		}
	}

	private String getUrlMD5(String url) {
		try {
			String checksum = new Checksum().getMD5Checksum(new URL(url));
			return checksum;
		} catch (MalformedURLException e) {
			Logger.getInstance().log("URL mal formada.", LogLevel.ERROR, getClass(), e.getClass());
			return null;
		}
	}
	
	private boolean cacheUrl(String newChecksum, String oldChecksum) {
		if(newChecksum.equalsIgnoreCase(oldChecksum)) {
			Logger.getInstance().log("Checksums iguales. No se descargará el archivo", LogLevel.INFO, getClass(), null);
			return true;
		} else {
			Logger.getInstance().log("Checksums diferentes. Se descargará el archivo", LogLevel.INFO, getClass(), null);
			return false;
		}
	}
	
	private void downloadUrl(String url, String filepath) {
		try {
			fileManager.downloadFile(new URL(url), filepath);
		} catch (IOException e) {
			Logger.getInstance().log("Error al descargar archivo", LogLevel.ERROR, getClass(), e.getClass());
		}
	}
	
	private void refreshChecksum(String newChecksum, int index) {
		if(index < checksums.size()) 		
			checksums.remove(index);
		checksums.add(index, newChecksum);
	}
	
	private void refreshChecksumFile() {
		try {
			fileManager.writeFile(PATH_CHECKSUMS, false, checksums);
		} catch (IOException e) {
			Logger.getInstance().log("Error al escribir fichero", LogLevel.ERROR, getClass(), e.getClass());
		}
	}
	
	private void readXml(String filepath) {
		try {
			if(fileManager.parsearDocumento(filepath)) {
				fileManager.cargarNodeList("row");
				ArrayList<Element> elementos = fileManager.getNodeElementList();
				for(Element element:elementos) {
					addAlojamiento(element);
				}		
			}
		} catch (IOException e) {
			Logger.getInstance().log("Error al abrir fichero xml", LogLevel.ERROR, getClass(), e.getClass());
		}
	}
	
	private void addAlojamiento(Element element) {
		Alojamiento alojamiento = new Alojamiento(
				fileManager.getElementContent(element, "lodgingtype", 0),
				fileManager.getElementContent(element,"documentname",0),
				fileManager.getElementContent(element,"turismdescription",1),
				fileManager.getElementContent(element,"address",0),
				fileManager.getElementContent(element, "municipality", 0),
				fileManager.getElementContent(element,"territory",0),
				fileManager.getElementContent(element,"phone",0),
				fileManager.getElementContent(element,"tourismemail",0),
				fileManager.getElementContent(element, "web", 0),
				fileManager.getElementContent(element,"capacity",0),
				fileManager.getElementContent(element,"latwgs84",0),
				fileManager.getElementContent(element,"lonwgs84",0)
				);
		alojamientos.add(alojamiento);
	}	
}