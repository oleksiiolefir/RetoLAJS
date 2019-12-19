package launcher;
import java.io.File;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import objetos.Administrador;
import objetos.Alojamiento;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory(); 
		 
		Session session = sessionFactory.openSession();
		
		Transaction tx= session.beginTransaction();

		DescargaWeb descarga = new DescargaWeb();
		File archivo = new File("alojTuristicos.xml");
		File archivo1 = new File("alojRural.xml");
		File archivo2 = new File("campings.xml");
		ArrayList<Alojamiento> aloj = new ArrayList<Alojamiento>();
		descarga.hacerDescarga(archivo);
		descarga.hacerDescarga(archivo1);
		descarga.hacerDescarga(archivo2);
		descarga.leerTag(archivo, aloj);
		descarga.leerTag(archivo1, aloj);
		descarga.leerTag(archivo2, aloj);
		System.out.println(aloj.size());
	}

}
