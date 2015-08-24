package by.academy.alekhno.database.util;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



public class HibernateUtil {
	private static final Logger logger = Logger.getLogger(HibernateUtil.class
			.getName());

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			return new Configuration().configure().buildSessionFactory();
		} catch (Throwable e) {
			logger.error("Initial SessionFactory creation failed.", e);
			throw new ExceptionInInitializerError(e);
			// throw new DaoHibernateException("HibernateUtil problem.", e);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
