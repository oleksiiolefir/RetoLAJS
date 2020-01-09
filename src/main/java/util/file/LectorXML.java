package util.file;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LectorXML extends LectorFicheros {
	
	private Document document;
	private NodeList nodeList;
	
	public LectorXML() {
		super();
	}
	
	public boolean writeFile() {
		String cadena;
		try {
			while((cadena = bReader.readLine())!=null) {
				try {
					bWriter.write(cadena);
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				bReader.close();
				bWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean parseDocument() {
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
        //System.out.println(fichero.getAbsolutePath());
        document.getDocumentElement().normalize();
        return true;
	}
	
	public void loadNodeList(String tag) {	
        nodeList = document.getElementsByTagName(tag);
	}
}
