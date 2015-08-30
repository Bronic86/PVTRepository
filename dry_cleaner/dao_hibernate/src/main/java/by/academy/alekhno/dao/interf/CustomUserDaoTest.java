package by.academy.alekhno.dao.interf;

import java.util.Collection;
import java.util.List;

import org.hibernate.Session;

import by.academy.alekhno.database.pojo.Model;
import by.academy.alekhno.database.pojo.Role;
import by.academy.alekhno.database.pojo.Type;
import by.academy.alekhno.database.pojo.User;
import by.academy.alekhno.exception.DaoHibernateException;

public interface CustomUserDaoTest {

	User getByID(int id) throws DaoHibernateException;
	
	List<User> getAll() throws DaoHibernateException;
	
	List<Model> getByTypeId(int type_id) throws DaoHibernateException;
	
	List<Model> getAllModel() throws DaoHibernateException;
	
	List<Type> getAllType() throws DaoHibernateException;
	
	Collection<Role> getRoleByUserId(int user_id);
	
	void setSession(Session session);
	
	void closeSession();
}
