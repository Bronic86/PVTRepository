package by.academy.alekhno.database.util;



import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
	private static final Logger logger = Logger.getLogger(HibernateUtil.class.getName());

	private static volatile HibernateUtil util;
	private SessionFactory sessionFactory;
//	private final ThreadLocal sessions = new ThreadLocal();

	private HibernateUtil() {
		try {
			Configuration conf = new AnnotationConfiguration();
			sessionFactory = conf.configure()
					.buildSessionFactory();
		} catch (Throwable e) {
			logger.error("Initial SessionFactory creation failed.", e);
			throw new ExceptionInInitializerError(e);
//			Add my exception
		}
	}

	public static synchronized HibernateUtil getInstance() {

		if (util == null) {
			util = new HibernateUtil();
			logger.debug("Create instance");
		}
		logger.debug("Get instance");
		return util;
	}
//
//	public Session getSession() {
//		Session session = (Session) sessions.get();
//		if (session == null) {
//			session = sessionFactory.openSession();
//			sessions.set(session);
//		}
//		return session;
//	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
}
