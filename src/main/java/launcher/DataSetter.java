package launcher;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import util.hibernate.SessionFactoryUtil;

public class DataSetter {
	
	public DataSetter(ArrayList<?> list) {
		SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		for (Object object : list)
			session.save(object);
		tx.commit();
		session.close();
		sessionFactory.close();
	}
	
}
