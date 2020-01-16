package util.file;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import util.logger.LogLevel;
import util.logger.Logger;

public class GestorFicherosXML extends GestorFicheros {

	private Document document;
	private NodeList nodeList;
	
	public boolean parsearDocumento(String pathFrom) {
		if(openFile(pathFrom)) {
			DocumentBuilderFactory factory = null;
			try {
				factory = DocumentBuilderFactory.newInstance();
			} catch (FactoryConfigurationError e) {
				Logger.getInstance().log("El servicio de configuración del documento no está disponible", LogLevel.ERROR, getClass(), e.getClass());
				return false;
			}
			
			DocumentBuilder documentBuilder = null;
			try {
				documentBuilder = factory.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				Logger.getInstance().log("Error al crear documento con la configuración proporcionada", LogLevel.ERROR, getClass(), e.getClass());
				return false;
			}

			try {
				document = documentBuilder.parse(file);
			} catch (SAXException | IOException e) {
				Logger.getInstance().log("Error al parsear el fichero", LogLevel.ERROR, getClass(), e.getClass());
				return false;
			}
			document.getDocumentElement().normalize();
			return true;
		}
		return false;
	}

	public void cargarNodeList(String tag) {
		nodeList = document.getElementsByTagName(tag);
	}

	public ArrayList<Element> getNodeElementList() {
		ArrayList<Element> list = new ArrayList<Element>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				list.add(element);
			}
		}
		return list;
	}
	
	public String getElementContent(Element element, String tag, int index) {
		try {
			return element.getElementsByTagName(tag).item(index).getTextContent();
		} catch (Exception e) {
			Logger.getInstance().log("Tag " + tag + " no encontrado", LogLevel.WARNING, getClass(), e.getClass());
			return "";
		}
	}

}
