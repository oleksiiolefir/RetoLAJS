package util.hibernate;

import java.io.File;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import util.logger.LogLevel;
import util.logger.Logger;

public class SessionFactoryUtil {

	private static SessionFactory sessionFactory;
	private static Configuration configuration;
	
	private static boolean buildSessionFactory() {
		if (sessionFactory == null) {
			try {
				configuration = new Configuration().configure(new File("src/main/java/hibernate.cfg.xml"));		
				try {
					configuration.addAnnotatedClass(objetos.Usuario.class);
					configuration.addAnnotatedClass(objetos.Alojamiento.class);
					configuration.addAnnotatedClass(objetos.Reserva.class);
					StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
					serviceRegistryBuilder.applySettings(configuration.getProperties());
					ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
					sessionFactory = configuration.buildSessionFactory(serviceRegistry);
					return true;
				} catch (Exception e) {
					Logger.getInstance().log("Error en la configuración y/o mapeo de Hibernate", LogLevel.ERROR, null, e.getClass());
					return false;
				}
			} catch (Exception e) {
				Logger.getInstance().log("Error al acceder al archivo de configuración de Hibernate", LogLevel.ERROR, null, e.getClass());
				return false;
			}
		} return true;	
	}

	public static SessionFactory getSessionFactory() {
		buildSessionFactory();
		return sessionFactory;
	}

	/*
	public static void shutdown() {
		getSessionFactory().close();
	}*/

}
