package launcher;
import java.io.File;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class SessionFactoryUtil {

	
	 private static SessionFactory sessionFactory = buildSessionFactory();
	 
	   private static SessionFactory buildSessionFactory()
	   {
	      try
	      {
	         if (sessionFactory == null)
	         {
	            Configuration configuration = new Configuration().configure(new File("src/main/java/hibernate.cfg.xml"));
	            configuration.addAnnotatedClass(objetos.Usuario.class);
	            configuration.addAnnotatedClass(objetos.Alojamiento.class);
	            configuration.addAnnotatedClass(objetos.Reserva.class);
	            StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
	            serviceRegistryBuilder.applySettings(configuration.getProperties());
	            ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
	            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	         }
	         return sessionFactory;
	      } catch (Throwable ex)
	      {
	         System.err.println("Initial SessionFactory creation failed." + ex);
	         throw new ExceptionInInitializerError(ex);
	      }
	   }
	 
	   public static SessionFactory getSessionFactory()
	   {
	      return sessionFactory;
	   }
	 
	   public static void shutdown()
	   {
	      getSessionFactory().close();
	   }
	
	
  /*
=======
	static Configuration configuration;
	 private static SessionFactory sessionFactory;
	 private static ServiceRegistry serviceRegistry;
	 static {
        try {
            // Build a SessionFactory object from session-factory config
            // defined in the hibernate.cfg.xml file. In this file we
            // register the JDBC connection information, connection pool,
            // the hibernate dialect that we used and the mapping to our
            // hbm.xml file for each pojo (plain old java object).
        	 configuration = new Configuration();
    	    configuration.configure(new File("hibernate.cfg.xml"));
    	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()). buildServiceRegistry();
           
        } catch (Throwable e) {
            System.err.println("Error in creating SessionFactory object."
                    + e.getMessage());
            throw new ExceptionInInitializerError(e);
        }
    }
	
	public static SessionFactory getSessionFactory() {
		
	    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		
		return sessionFactory;
	}

    public static void main(String[] args) {
    	
    	SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
    	Session session = sesion.openSession();
    	Transaction tx= session.beginTransaction();
    	session.close();
    	
    	
       
    }
    
>>>>>>> c80d6d726c7011fda2896246fd98504120f1fbc9*/
  
}
