package by.academy.alekhno.external;

import java.util.List;

import org.hibernate.Session;


import org.hibernate.SessionFactory;

import by.academy.alekhno.exception.DaoHibernateException;
import by.academy.alekhno.vo.Model;


public interface ModelDAO {
	
	List<Model> getAll() throws DaoHibernateException ;

	void update(Model model) throws DaoHibernateException ;
	
	void delete(Model model) throws DaoHibernateException;
	
	int add(Model model) throws DaoHibernateException;
	
	Model getByID (Model model) throws DaoHibernateException;
	
	void setSessionFactory(SessionFactory sessionFactory);
	
	Model getByName(String name) throws DaoHibernateException;
	
	List<Model> getByTypeId(int type_id) throws DaoHibernateException;
}
