package by.academy.alekhno.external;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import by.academy.alekhno.exception.DaoHibernateException;
import by.academy.alekhno.vo.State;

public interface StateDAO {

	List<State> getAll() throws DaoHibernateException ;
	
	void update(State state) throws DaoHibernateException ;
	
	void delete(State state) throws DaoHibernateException;
	
	int add(State state) throws DaoHibernateException;
	
	State getByID (State state) throws DaoHibernateException;
	
	void setSessionFactory(SessionFactory sessionFactory);
	
	State getByStateName(String state) throws DaoHibernateException;
	
}
