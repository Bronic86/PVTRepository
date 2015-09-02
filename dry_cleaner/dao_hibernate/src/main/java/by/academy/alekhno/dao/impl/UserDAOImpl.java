package by.academy.alekhno.dao.impl;

import java.io.Serializable;




import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import by.academy.alekhno.bundle.Bundle;
import by.academy.alekhno.dao.interf.AbstractDAO;
import by.academy.alekhno.dao.interf.CustomUserDAO;
import by.academy.alekhno.database.pojo.Role;
import by.academy.alekhno.database.pojo.User;

public class UserDAOImpl extends AbstractDAO<User> implements CustomUserDAO {
	private static final Logger logger = Logger.getLogger(UserDAOImpl.class.getName());
	

	@Override
	protected Class getObjectClass() {
		return User.class;
	}

	@Override
	protected Serializable getId(User user) {
		return user.getId();
	}

	@Override
	public User getByLogin(String login) {
		logger.info("Start getByLogin.");
		logger.debug("Login - " + login);
		super.startTransaction();
		String hql = Bundle.getQueryResource("user.get.by.login");
		Query query = super.getSession().createQuery(hql);
		query.setParameter("user_login", login);
		List<User> users = query.list();
		logger.debug("Users quantity - " + users.size());
		super.endTransaction();
		return users.get(0);
	}

	@Override
	public User getByLoginAndPassword(User user) {
		logger.info("Start getByLoginAndPassword");
		logger.debug("Login - " + user.getLogin());
		super.startTransaction();
		String hql = Bundle.getQueryResource("user.get.by.login.and.password");
		Query query = super.getSession().createQuery(hql);
		query.setParameter("user_login", user.getLogin());
		query.setParameter("user_password", user.getPassword());
		List<User> users = query.list();
		logger.debug("Users quantity - " + users.size());
		super.endTransaction();
		return users.get(0);
	}

	@Override
	public void addRoleForUser(User user, Role role) {
		logger.info("Start addRoleForUser");
		logger.debug("Login - " + user.getLogin());
		logger.debug("Role - " + role);
		super.startTransaction();
		Set<Role> roles = user.getRoles();
		roles.add(role);
//		logger.info(roles);
		user.setRoles(roles);
		super.getSession().update(user);
		super.endTransaction();
	}

	@Override
	protected void setFields(User user, User updateUser) {
		updateUser.setFieldsByAnotherUser(user);
	}

}
