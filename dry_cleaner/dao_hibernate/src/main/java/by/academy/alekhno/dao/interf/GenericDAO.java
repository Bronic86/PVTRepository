package by.academy.alekhno.dao.interf;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import by.academy.alekhno.exception.DaoHibernateException;


public interface GenericDAO<T> {
	
	List<T> getAll() throws DaoHibernateException ;
	
	void update(T t) throws DaoHibernateException;
	
	void delete(T t) throws DaoHibernateException;
	
	int add(T t) throws DaoHibernateException;
	
	T getByID (T t) throws DaoHibernateException;
	
	void setSessionFactory (SessionFactory sessionFactory);
	
}
