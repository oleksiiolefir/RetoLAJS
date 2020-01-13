package launcher;

import java.io.File;
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
import util.file.GestorFicheros;
import util.file.GestorFicherosJSON;
import util.file.GestorFicherosXML;

public class Main {
	
	static ArrayList<Alojamiento> alojamientos = new ArrayList<Alojamiento>();
	
	public static void main(String[] args) {
		cargarURLs();
		generarJson();
		insertarDatosBD();		
	}

	public static void cargarURLs() {
		GestorFicheros ficheroUrls = new GestorFicheros("files/urlsAlojamientos.txt");
		ArrayList<String> urls = ficheroUrls.leerFichero();
		cargarAlojamientos(urls);
	}
	
	public static void cargarAlojamientos(ArrayList<String> urls) {
		GestorFicherosXML[] ficheros = new GestorFicherosXML[3];
		ficheros[0] = new GestorFicherosXML(new File("files/xml/albergues.xml"));
		ficheros[1] = new GestorFicherosXML(new File("files/xml/alojRurales.xml"));
		ficheros[2] = new GestorFicherosXML(new File("files/xml/campings.xml"));
		
		Iterator<String> iterator = urls.iterator();
		for(GestorFicherosXML fichero:ficheros) {
			try {
				fichero.descargarFichero(new URL(iterator.next()));
				System.out.println("Fichero descargado");
			} catch (MalformedURLException | NoSuchElementException e) {
				e.printStackTrace();
			}
			fichero.parsearDocumento();
			//alojamientos.addAll(fichero.getAlojamientos());
			
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
	}
}
