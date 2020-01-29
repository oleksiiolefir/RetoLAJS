package util.hibernate;

import java.io.File;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class SessionFactoryUtil {

	private static SessionFactory sessionFactory;
	private static Configuration configuration;

	private static void loadConfigFile(String filepath) {
		try {
			configuration = new Configuration().configure(new File(filepath));
			configuration.addAnnotatedClass(objetos.Usuario.class);
			configuration.addAnnotatedClass(objetos.Alojamiento.class);
			configuration.addAnnotatedClass(objetos.Reserva.class);
		} catch (HibernateException e) {
			throw new HibernateException("Error al cargar archivo de configuracion", e);
		}
	}

	private static void buildSessionFactory(String configFilePath) {
		if (sessionFactory == null) {
			try {
				loadConfigFile(configFilePath);
				StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
				serviceRegistryBuilder.applySettings(configuration.getProperties());
				ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			} catch (HibernateException e) {
				throw new HibernateException("Error de mapeo de Hibernate", e);
			}
		}
	}

	public static SessionFactory getSessionFactory(String configFilePath) throws HibernateException {
		try {			
			buildSessionFactory(configFilePath);
			return sessionFactory;
		} catch (HibernateException e) {
			throw new HibernateException("Error al crear SessionFactory", e);
		}
	}

	public static void shutdown() {
		if(sessionFactory!=null)
			sessionFactory.close(); 
	}
	 
}
