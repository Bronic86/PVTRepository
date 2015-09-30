package by.academy.alekhno.spring.dao.interf;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDAO<T, Serializable> implements GenericDAO<T, Serializable> {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<T> getAll() {
		List<T> tList = new ArrayList<T>();
		tList.addAll(getSession().createCriteria(getObjectClass()).list());
		return tList;
	}

	protected abstract Class getObjectClass();

	@Override
	public void update(T t) {
		T tP = (T) getSession().get(getObjectClass(), getId(t));
		setFields(t, tP);
		getSession().update(tP);
	}

	protected abstract void setFields(T newObject, T persistObject);

	protected abstract Integer getId(T t);

	@Override
	public void delete(Serializable id) {
		T t = (T) getSession().get(getObjectClass(), (java.io.Serializable) id);
		getSession().delete(t);
	}

	@Override
	public int add(T t) {
		return (int) getSession().save(t);
	}

	@Override
	public T getByID(Serializable id) {
		return (T) getSession().get(getObjectClass(), (java.io.Serializable) id);
	}
}
