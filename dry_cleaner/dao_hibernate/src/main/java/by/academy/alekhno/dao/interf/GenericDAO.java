package by.academy.alekhno.dao.interf;

import java.util.List;

import org.hibernate.Session;


public interface GenericDAO<T> {
	
	List<T> getAll() ;
	
	void update(T t) ;
	
	void delete(T t);
	
	int add(T t);
	
	T getByID (T t);
	
	void setSession(Session session);
	
	Session getSession();
}
