package by.academy.alekhno.dao.interf;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import by.academy.alekhno.exception.DaoHibernateException;

public abstract class AbstractDAO<T> implements GenericDAO<T> {
	private Transaction transaction;
	private Session session;
	private SessionFactory sessionFactory;
	private static final Logger logger = Logger.getLogger(AbstractDAO.class
			.getName());

	@Override
	public List<T> getAll() throws DaoHibernateException {
		logger.info("Start getAll for " + getObjectClass().getName());
		List<T> tList;
		try {
			startTransaction();
			tList = session.createCriteria(getObjectClass()).list();
			endTransaction();
		} catch (HibernateException e) {
			logger.debug("getAll method error.");
			logger.debug(e.getStackTrace());
			transaction.rollback();
			throw new DaoHibernateException(e.getCause());
		} finally {
			closeSession();
		}
		return tList;
	}

	abstract protected Class getObjectClass();

	@Override
	public void update(T t) throws DaoHibernateException {
		logger.info("Start update for " + getObjectClass().getName());
		logger.debug("Object - " + t);
		try {
			startTransaction();
			T updateT = t;
//			(T) session.get(getObjectClass(), getId(t));
//			session.flush();
//			setFields(t, updateT);
			session.update(updateT);
			endTransaction();
		} catch (HibernateException e) {
			logger.debug("update method error.");
			logger.debug(e.getStackTrace());
			transaction.rollback();
			throw new DaoHibernateException(e.getCause());
		} finally {
			closeSession();
		}
	}

	abstract protected void setFields(T t, T updateT);

	@Override
	public void delete(T t) throws DaoHibernateException {
		logger.info("Start delete for " + getObjectClass().getName());
		logger.debug("Object - " + t);
		try {
			startTransaction();
			t = (T) session.get(getObjectClass(), getId(t));
			session.delete(t);
			endTransaction();
		} catch (HibernateException e) {
			logger.debug("delete method error.");
			logger.debug(e.getStackTrace());
			transaction.rollback();
			throw new DaoHibernateException(e.getCause());
		} finally {
			closeSession();
		}
	}

	@Override
	public int add(T t) throws DaoHibernateException {
		logger.info("Start add for " + getObjectClass().getName());
		logger.debug("Object - " + t);
		int id = 0;
		try {
			startTransaction();
			id = (int) session.save(t);
			endTransaction();
		} catch (HibernateException e) {
			logger.debug("add method error.");
			logger.debug(e.getStackTrace());
			transaction.rollback();
			throw new DaoHibernateException(e.getCause());
		} finally {
			closeSession();
		}
		return id;
	}

	@Override
	public T getByID(T t) throws DaoHibernateException {
		logger.info("Start getByID for " + getObjectClass().getName());
		logger.debug("Object - " + t);
		try {
			startTransaction();
			t = (T) session.get(getObjectClass(), getId(t));
			endTransaction();
		} catch (HibernateException e) {
			logger.debug("getByID method error.");
			logger.debug(e.getStackTrace());
			transaction.rollback();
			throw new DaoHibernateException(e.getCause());
		} finally {
			closeSession();
		}
		return t;
	}

	abstract protected Serializable getId(T t);

	protected void startTransaction() {
		logger.debug("Start transaction.");
		transaction.begin();
	}

	protected void endTransaction() {
		logger.debug("Commit transaction.");
		session.getTransaction().commit();
		logger.debug("Flush session.");
		session.flush();
	}

	protected void closeSession() {
		session.close();
	}

	protected Session getSession() {
		return session;
	}
	
	protected Transaction getTransaction() {
		return transaction;
	}

	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		logger.debug("Set sessionFactory.");
		this.sessionFactory = sessionFactory;
		logger.debug("Open session.");
		session = sessionFactory.openSession();
		logger.debug("Create transaction.");
		transaction = session.getTransaction();
	}
}
