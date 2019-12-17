import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory(); 
		 
		Session session = sessionFactory.openSession();
		
		Transaction tx= session.beginTransaction();
		

	}

}
