package util.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class GestorFicherosXML extends GestorFicheros {

	private Document document;
	private NodeList nodeList;

	public GestorFicherosXML(File fichero) {
		super(fichero);
	}
	
	public GestorFicherosXML(String ruta) {
		super(ruta);
	}
	
	public boolean parsearDocumento() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = null;
		try {
			documentBuilder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return false;
		}

		try {
			document = documentBuilder.parse(fichero);
		} catch (SAXException | IOException e) {
			e.printStackTrace();
			return false;
		}
		document.getDocumentElement().normalize();
		return true;
	}

	public void cargarNodeList(String tag) {
		nodeList = document.getElementsByTagName(tag);
	}

	public ArrayList<Element> getNodeList() {
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
		return element.getElementsByTagName(tag).item(index).getTextContent();
	}

}
