package by.academy.alekhno.external;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import by.academy.alekhno.exception.DaoHibernateException;
import by.academy.alekhno.vo.Role;

public interface RoleDAO {

void update(Role role) throws DaoHibernateException ;

	List<Role> getAll() throws DaoHibernateException ;
	
	void delete(Role role) throws DaoHibernateException;
	
	int add(Role role) throws DaoHibernateException;
	
	Role getByID (Role role) throws DaoHibernateException;
	
	void setSessionFactory(SessionFactory sessionFactory);
	
	Role getByName(String name) throws DaoHibernateException;
}
