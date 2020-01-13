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

import objetos.Alojamiento;

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
			System.out.println("Tag no encontrado");
			return " ";
		}
	}

	public ArrayList<Alojamiento> getAlojamientos(){
	
		// Parsear documento
		ArrayList<Alojamiento> result = new ArrayList<Alojamiento>();
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = factory.newDocumentBuilder();
			Document document = documentBuilder.parse(fichero);
			document.getDocumentElement().normalize();

			NodeList nodelist = document.getElementsByTagName("row");

			for(int i=0;i<nodelist.getLength();i++) {
				if(nodelist.item(i).getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) nodelist.item(i);
					Alojamiento alojamiento = new Alojamiento(
						getElementContent(element,"lodgingtype",0),
						getElementContent(element,"documentname",0),
						getElementContent(element,"turismdescription",1),
						getElementContent(element,"address",0),
						getElementContent(element, "municipality", 0),
						getElementContent(element,"territory",0),
						getElementContent(element,"phone",0),
						getElementContent(element,"tourismemail",0),
						getElementContent(element, "web", 0),
						getElementContent(element,"capacity",0),
						getElementContent(element,"latwgs84",0),
						getElementContent(element,"lonwgs84",0)
						);
					result.add(alojamiento);
				}
			}	
	
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
