
import java.io.File;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.hql.internal.ast.util.SessionFactoryHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class SessionFactoryUtil {
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
    
  
}
