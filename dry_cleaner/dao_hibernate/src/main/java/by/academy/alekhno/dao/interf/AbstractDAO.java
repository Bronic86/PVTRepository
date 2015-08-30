package by.academy.alekhno.dao.interf;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;


public abstract class AbstractDAO<T> implements GenericDAO<T> {
	private Session session;
	private static final Logger logger = Logger.getLogger(AbstractDAO.class.getName());
	
	@Override
	public List<T> getAll() {
		logger.info("Start getAll for " + getObjectClass().getName());
		List<T> tList;
		startTransaction();
		tList = session.createCriteria(getObjectClass()).list();
		endTransaction();
		return tList;
	}

	abstract protected Class getObjectClass();

	@Override
	public void update(T t) {
		logger.info("Start update for " + getObjectClass().getName());
		logger.debug("Object - " + t);
		startTransaction();
		session.update(t);
		endTransaction();
	}

	@Override
	public void delete(T t) {
		logger.info("Start delete for " + getObjectClass().getName());
		logger.debug("Object - " + t);
		startTransaction();
		session.delete(t);
		endTransaction();
	}

	@Override
	public int add(T t) {
		logger.info("Start add for " + getObjectClass().getName());
		logger.debug("Object - " + t);
		startTransaction();
		int id = (int) session.save(t);
		endTransaction();
		return id;
	}

	@Override
	public T getByID(T t) {
		logger.info("Start getByID for " + getObjectClass().getName());
		logger.debug("Object - " + t);
		startTransaction();
		t = (T) session.get(getObjectClass(), getId(t));
		endTransaction();
		return t;
	}

	abstract protected Serializable getId(T t);

	@Override
	public void setSession(Session session) {
		logger.debug("Set session.");
		this.session = session;
	}

	@Override
	public Session getSession() {
		logger.debug("Get session.");
		return session;
	}
	
	protected void startTransaction() {
		session.getTransaction().begin();
		logger.debug("Start transaction.");
	}
	
	protected void endTransaction() {
		session.getTransaction().commit();
		logger.debug("Commit transaction.");
		session.flush();
		logger.debug("Flush session.");
	}
}
