package launcher;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import entity.Alojamiento;
import file.XmlFileManager;
import logger.LogLevel;
import logger.Logger;
import security.Checksum;

public class UrlReader {

	private XmlFileManager xmlFileManager;

	public UrlReader() {
		xmlFileManager = new XmlFileManager();
	} 

	public ArrayList<Alojamiento> getAlojamientos(String url, String filepath) {
		if(cacheUrl(url, filepath)) {
			try {
				xmlFileManager.downloadFile(filepath, new URL(url));
				return getXmlData(filepath, "row");
			}catch (IOException e) {
				Logger.getInstance().log(e.getLocalizedMessage(), LogLevel.WARNING, getClass(), e);
				return null;
			}
		} return null;
	}

	private boolean cacheUrl(String url, String filepath) {
		String urlChecksum = "";
		try {
			urlChecksum = Checksum.getMD5Checksum(new URL(url));
		} catch (IOException e) {
			Logger.getInstance().log("No se pudo crear checksum", LogLevel.WARNING, getClass(), e);
			return false;
		}
		try {
			String fileChecksum = Checksum.getMD5Checksum(filepath);
			return compareChecksums(urlChecksum, fileChecksum);
		} catch (IOException e) {
			Logger.getInstance().log("No se ha encontrado fichero para cachear. Se descargará el fichero", LogLevel.INFO, getClass(), e);
			return true;
		}
	}
	
	private boolean compareChecksums(String aChecksum, String anotherChecksum) {
		if (aChecksum.equalsIgnoreCase(anotherChecksum)) {
			Logger.getInstance().log("Checksums iguales. No se descargará el archivo", LogLevel.INFO, getClass(), null);
			return false;
		} else {
			Logger.getInstance().log("Checksums diferentes. Se descargará el archivo", LogLevel.INFO, getClass(), null);
			return true;
		}
	}

	private ArrayList<Alojamiento> getXmlData(String filepath, String tag) {
		try {
			ArrayList<Element> elementos = xmlFileManager.getNodeListElements(filepath, tag);
			ArrayList<Alojamiento> data = new ArrayList<Alojamiento>();
			for (Element element : elementos)
				data.add(getAlojamiento(element));
			return data;
		} catch (SAXException | IOException | ParserConfigurationException e) {
			Logger.getInstance().log("Error al leer fichero xml", LogLevel.ERROR, getClass(), e);
			return null;
		}
	}

	private Alojamiento getAlojamiento(Element element) {
		Alojamiento alojamiento = new Alojamiento(xmlFileManager.getElementTextContent(element, "lodgingtype", 0),
				xmlFileManager.getElementTextContent(element, "documentname", 0),
				xmlFileManager.getElementTextContent(element, "turismdescription", 1),
				xmlFileManager.getElementTextContent(element, "address", 0),
				xmlFileManager.getElementTextContent(element, "municipality", 0),
				xmlFileManager.getElementTextContent(element, "territory", 0),
				xmlFileManager.getElementTextContent(element, "phone", 0),
				xmlFileManager.getElementTextContent(element, "tourismemail", 0),
				xmlFileManager.getElementTextContent(element, "web", 0),
				xmlFileManager.getElementTextContent(element, "capacity", 0),
				xmlFileManager.getElementTextContent(element, "latwgs84", 0),
				xmlFileManager.getElementTextContent(element, "lonwgs84", 0));
		return alojamiento;
	}

}