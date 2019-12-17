
import java.io.File;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.hql.internal.ast.util.SessionFactoryHelper;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
public class SessionFactoryUtil {
	
	 private static SessionFactory sessionFactory = buildSessionFactory();
	 
	   private static SessionFactory buildSessionFactory()
	   {
	      try
	      {
	         if (sessionFactory == null)
	         {
	            Configuration configuration = new Configuration().configure(new File("/hibernate.cfg.xml"));
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
	
	
	
	
	
	
	
  
  
}
