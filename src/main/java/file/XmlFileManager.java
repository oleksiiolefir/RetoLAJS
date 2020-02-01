package file;

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

public class XmlFileManager extends FileManager {

	private Document document;
	private NodeList nodeList;
	
	private void parseFile() throws SAXException, IOException, ParserConfigurationException {		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder	documentBuilder = factory.newDocumentBuilder();
			document = documentBuilder.parse(file);
		} catch (SAXException e) {
			throw new SAXException("Error al parsear archivo " + file.getPath(), e);
		} catch (IOException e) {
			throw new IOException("Error al leer archivo " + file.getPath(), e);
		} catch (ParserConfigurationException e) {
			throw new ParserConfigurationException("Error al crear documentBuilder");
		}
		document.getDocumentElement().normalize();
	}

	private void loadNodeList(String tag) {	
		nodeList = document.getElementsByTagName(tag);
	}

	public ArrayList<Element> getNodeListElements(String filepath, String tag) throws IOException, SAXException, ParserConfigurationException {
		openFile(filepath);	
		parseFile();
		loadNodeList(tag);
		
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
	
	public String getElementTextContent(Element element, String tag, int index) {
		try {
			return element.getElementsByTagName(tag).item(index).getTextContent();
		} catch (Exception e) {
			return "";
		}
	}

}
