package by.academy.alekhno.external;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import by.academy.alekhno.exception.DaoHibernateException;
import by.academy.alekhno.vo.Role;
import by.academy.alekhno.vo.User;

public interface UserDAO {

	List<User> getAll() throws DaoHibernateException ;
	
	void update(User user) throws DaoHibernateException ;
	
	void delete(User user) throws DaoHibernateException;
	
	int add(User user) throws DaoHibernateException;
	
	User getByID (User user) throws DaoHibernateException;
	
	void setSessionFactory(SessionFactory sessionFactory);
	
	User getByLogin(String login) throws DaoHibernateException;
	
	User getByLoginAndPassword(User user) throws DaoHibernateException;
	
	void addRoleForUser(User user, Role role) throws DaoHibernateException;
	
	Set<Role> getRolesByUser(User user) throws DaoHibernateException;
}
