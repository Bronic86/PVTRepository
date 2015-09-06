package by.academy.alekhno.external.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import by.academy.alekhno.dao.interf.CustomUserDAO;
import by.academy.alekhno.database.converter.ConverterPojoToVO;
import by.academy.alekhno.database.converter.ConverterVOToPojo;
import by.academy.alekhno.exception.DaoHibernateException;
import by.academy.alekhno.external.UserDAO;
import by.academy.alekhno.vo.Role;
import by.academy.alekhno.vo.User;

public class UserDAOImpl implements UserDAO {
	
	
	private CustomUserDAO dao = new by.academy.alekhno.dao.impl.UserDAOImpl();
	private by.academy.alekhno.database.pojo.User userP;
	private by.academy.alekhno.database.pojo.Role roleP;
	private List<by.academy.alekhno.database.pojo.User> usersP;

	@Override
	public List<User> getAll() throws DaoHibernateException {
		List<User> usersVO = new ArrayList<>();
		usersP = dao.getAll();
		for(by.academy.alekhno.database.pojo.User userP : usersP){
			usersVO.add(ConverterPojoToVO.getUser(userP));
		}
		return usersVO;
	}

	@Override
	public void update(User user) throws DaoHibernateException {
		userP = ConverterVOToPojo.getUser(user);
		dao.update(userP);		
	}

	@Override
	public void delete(User user) throws DaoHibernateException {
		userP = ConverterVOToPojo.getUser(user);
		dao.delete(userP);
	}

	@Override
	public int add(User user) throws DaoHibernateException {
		userP = ConverterVOToPojo.getUser(user);
		int id = dao.add(userP);
		return id;
	}

	@Override
	public User getByID(User user) throws DaoHibernateException {
		userP = ConverterVOToPojo.getUser(user);
		userP = dao.getByID(userP);
		user = ConverterPojoToVO.getUser(userP);
		return user;
	}

	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		dao.setSessionFactory(sessionFactory);
	}

	@Override
	public User getByLogin(String login) throws DaoHibernateException {
		userP = dao.getByLogin(login);
		User user = ConverterPojoToVO.getUser(userP);
		return user;
	}

	@Override
	public User getByLoginAndPassword(User user) throws DaoHibernateException {
		userP = ConverterVOToPojo.getUser(user);
		userP = dao.getByLoginAndPassword(userP);
		user = ConverterPojoToVO.getUser(userP);
		return user;
	}

	@Override
	public void addRoleForUser(User user, Role role) throws DaoHibernateException {
		userP = ConverterVOToPojo.getUser(user);
		roleP = ConverterVOToPojo.getRole(role);
		dao.addRoleForUser(userP, roleP);
	}

	@Override
	public Set<Role> getRolesByUser(User user) throws DaoHibernateException {
		userP = ConverterVOToPojo.getUser(user);
		Set<by.academy.alekhno.database.pojo.Role> rolesP = dao.getRolesByUser(userP);
		Set<Role> rolesVO = new HashSet<>();
		for(by.academy.alekhno.database.pojo.Role roleP : rolesP){
			rolesVO.add(ConverterPojoToVO.getRole(roleP));
		}
		return rolesVO;
	}

}
