package hibernate;

import java.io.File;
import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import launcher.Constants;

public class HibernateManager {

	private static SessionFactory sessionFactory;
	private static Configuration configuration;

	private static void loadConfigFile() {
		try {
			configuration = new Configuration().configure(new File(Constants.CONFIG_FILEPATH));
			configuration.addAnnotatedClass(entity.Usuario.class);
			configuration.addAnnotatedClass(entity.Alojamiento.class);
			configuration.addAnnotatedClass(entity.Reserva.class);
		} catch (HibernateException e) {
			throw new HibernateException("Error al cargar archivo de configuracion", e);
		}
	}

	private static void buildSessionFactory() throws HibernateException {
		if (sessionFactory == null) {
			loadConfigFile();
			StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
			serviceRegistryBuilder.applySettings(configuration.getProperties());
			ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
			try {
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			} catch (HibernateException e) {
				throw new HibernateException("Configuracion y/o mapping erroneo", e);
			}
		}
	}

	public static void connect() throws HibernateException {
		buildSessionFactory();
	}

	public static void disconnect() throws HibernateException {
		if (sessionFactory != null)
			try {
				sessionFactory.close();
			} catch (HibernateException e) {
				throw new HibernateException("Error al cerrar sessionFactory", e);
			}
	}

	public static void insertData(ArrayList<?> data) throws Exception {
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			for (Object object : data)
				session.saveOrUpdate(object);
			tx.commit();
			session.close();
		} catch (Exception e) {
			throw new Exception("Error al insertar datos en BD", e);
		}
	}
}
