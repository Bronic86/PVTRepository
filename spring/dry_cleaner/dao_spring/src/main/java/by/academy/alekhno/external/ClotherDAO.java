package by.academy.alekhno.external;

import java.util.Collection;
import java.util.List;

import org.hibernate.SessionFactory;

import by.academy.alekhno.exception.DaoHibernateException;
import by.academy.alekhno.vo.Clother;

public interface ClotherDAO {
	
	List<Clother> getAll() throws DaoHibernateException ;

	void update(Clother clother) throws DaoHibernateException;
	
	void delete(Clother clother) throws DaoHibernateException;
	
	int add(Clother clother) throws DaoHibernateException;
	
	Clother getByID (Clother clother) throws DaoHibernateException;
	
	void setSessionFactory(SessionFactory sessionFactory);
	
	Clother getByModelId(int model_id) throws DaoHibernateException;

	List<Clother> getByTypeId(int type_id) throws DaoHibernateException;
}
