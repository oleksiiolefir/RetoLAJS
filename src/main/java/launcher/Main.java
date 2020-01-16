package launcher;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.w3c.dom.Element;

import objetos.Alojamiento;
import util.file.Checksum;
import util.file.GestorFicheros;
import util.file.GestorFicherosJSON;
import util.file.GestorFicherosXML;
import util.hibernate.SessionFactoryUtil;

public class Main {
	
	static ArrayList<Alojamiento> alojamientos = new ArrayList<Alojamiento>();
	
	public static void main(String[] args) {
		new DataGetter().start();
		cargarAlojamientos(urls, checksums);
		generarJson();
		insertarDatosBD();		
	}
	
	public static void cargarAlojamientos(ArrayList<String> urls, ArrayList<String> checksums) {
		
		GestorFicherosXML[] ficheros = new GestorFicherosXML[3];
		ficheros[0] = new GestorFicherosXML(new File("files/xml/albergues.xml"));
		ficheros[1] = new GestorFicherosXML(new File("files/xml/alojRurales.xml"));
		ficheros[2] = new GestorFicherosXML(new File("files/xml/campings.xml"));
		
		Iterator<String> iteratorUrls = urls.iterator();
		Iterator<String> iteratorCheckSums = checksums.iterator();
		for(GestorFicherosXML fichero:ficheros) {
			try {
				String url = iteratorUrls.next();
				String checksum = iteratorCheckSums.next();	
				try {
					String newChecksum = null;
					if((newChecksum = compararCheckSum(url,checksum)) != null) {
						fichero.downloadFile(new URL(url));
						System.out.println("Fichero descargado");
						
					}
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				
			} catch (NoSuchElementException e) {
				System.out.println("No hay mas urls para cargar.");
			}
			
			if(fichero.parsearDocumento())
				fichero.cargarNodeList("row");
			else
				System.out.println("Error al parsear documento");
			
			ArrayList<Element> elementos = fichero.getNodeElementList();
			for(Element element:elementos) {
				Alojamiento alojamiento = new Alojamiento(
						fichero.getElementContent(element, "lodgingtype", 0),
						fichero.getElementContent(element,"documentname",0),
						fichero.getElementContent(element,"turismdescription",1),
						fichero.getElementContent(element,"address",0),
						fichero.getElementContent(element, "municipality", 0),
						fichero.getElementContent(element,"territory",0),
						fichero.getElementContent(element,"phone",0),
						fichero.getElementContent(element,"tourismemail",0),
						fichero.getElementContent(element, "web", 0),
						fichero.getElementContent(element,"capacity",0),
						fichero.getElementContent(element,"latwgs84",0),
						fichero.getElementContent(element,"lonwgs84",0)
						);
				alojamientos.add(alojamiento);
			}	
		}
	}

	private static String compararCheckSum(String url, String checksum) {
		Checksum checker = new Checksum();
		String urlMD5 = null;
		try {
			urlMD5 = checker.getMD5Checksum(new URL(url).openStream());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		if(checksum.equalsIgnoreCase(urlMD5))
			return null;
		else
			return urlMD5;
	}

	public static void generarJson() {
		GestorFicherosJSON[] ficheros = new GestorFicherosJSON[3];
		ficheros[0] = new GestorFicherosJSON(new File("files/json/albergues.json"));
		ficheros[1] = new GestorFicherosJSON(new File("files/json/alojRurales.json"));
		ficheros[2] = new GestorFicherosJSON(new File("files/json/campings.json"));
		for(GestorFicherosJSON fichero:ficheros) {
			fichero.escribirFichero(alojamientos);
		}
	}

	public static void insertarDatosBD() {
		SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		for (Alojamiento alojamiento : alojamientos)
			session.save(alojamiento);
		tx.commit();
		session.close();
		sessionFactory.close();
	}
}
