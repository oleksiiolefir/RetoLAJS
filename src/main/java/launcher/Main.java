package launcher;
import java.io.File;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import objetos.Administrador;
import objetos.Alojamiento;
import objetos.Reserva;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory(); 
        Session session = sessionFactory.openSession();
		
		Transaction tx= session.beginTransaction();
		
		DescargaWeb descarga = new DescargaWeb();
		File archivo = new File("landerArchivo.xml");
		ArrayList<Alojamiento> aloj = new ArrayList<Alojamiento>();
		descarga.hacerDescarga(archivo);
		aloj= descarga.leerTag(archivo, aloj);
		for (int i=0;i<aloj.size()-1;i++) {
			
			session.save(aloj.get(i));
		}
		tx.commit();
		session.close();
		
		
	}

}
