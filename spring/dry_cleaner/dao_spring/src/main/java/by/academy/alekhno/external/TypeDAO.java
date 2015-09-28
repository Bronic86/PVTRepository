package by.academy.alekhno.external;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import by.academy.alekhno.exception.DaoHibernateException;
import by.academy.alekhno.vo.Type;

public interface TypeDAO {
	
	List<Type> getAll() throws DaoHibernateException ;

	void update(Type type) throws DaoHibernateException ;
	
	void delete(Type type) throws DaoHibernateException;
	
	int add(Type type) throws DaoHibernateException;
	
	Type getByID (Type type) throws DaoHibernateException;
	
	void setSessionFactory(SessionFactory sessionFactory);
	
	Type getByName(String name) throws DaoHibernateException;
	
}
