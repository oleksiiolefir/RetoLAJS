package launcher;

import java.io.File;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import objetos.Alojamiento;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		DescargaWeb descarga = new DescargaWeb();
		File archivo = new File("alojTuristicos.xml");
		File archivo1 = new File("alojRural.xml");
		File archivo2 = new File("campings.xml");
		ArrayList<Alojamiento> alojT = new ArrayList<Alojamiento>();
		ArrayList<Alojamiento> alojR = new ArrayList<Alojamiento>();
		ArrayList<Alojamiento> camping = new ArrayList<Alojamiento>();
		descarga.hacerDescarga(archivo);
		descarga.hacerDescarga(archivo1);
		descarga.hacerDescarga(archivo2);

		alojT = descarga.leerTag(archivo, alojT);
		alojR = descarga.leerTag(archivo1, alojR);
		camping = descarga.leerTag(archivo2, camping);

		for (int i = 0; i < alojT.size() - 1; i++) {
			session.save(alojT.get(i));
		}
		for (int i = 0; i < alojR.size() - 1; i++) {
			session.save(alojR.get(i));
		}
		for (int i = 0; i < camping.size() - 1; i++) {
			session.save(camping.get(i));
		}

		tx.commit();
		session.close();
	}

}
