package launcher;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import util.hibernate.SessionFactoryUtil;
import util.logger.LogLevel;
import util.logger.Logger;

public class DataSetter {

	private SessionFactory sessionFactory;
	private Session session;

	public DataSetter() {}

	public boolean connect(String configFilepath) {
		try {			
			sessionFactory = SessionFactoryUtil.getSessionFactory(configFilepath);
			return true;
		} catch (HibernateException e) {
			Logger.getInstance().log("Error al conectar con la BD", LogLevel.ERROR, getClass(), e);
			return false;
		}
	}

	public void setData(ArrayList<?> data) {
		session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		for (Object object : data)
			session.save(object);
		tx.commit();
		session.close();
		sessionFactory.close();
	}

}
