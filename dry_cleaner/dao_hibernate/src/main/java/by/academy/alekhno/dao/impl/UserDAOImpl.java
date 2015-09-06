package by.academy.alekhno.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import by.academy.alekhno.bundle.Bundle;
import by.academy.alekhno.dao.interf.AbstractDAO;
import by.academy.alekhno.dao.interf.CustomUserDAO;
import by.academy.alekhno.database.pojo.Role;
import by.academy.alekhno.database.pojo.User;
import by.academy.alekhno.exception.DaoHibernateException;

public class UserDAOImpl extends AbstractDAO<User> implements CustomUserDAO {
	private static final Logger logger = Logger.getLogger(UserDAOImpl.class
			.getName());

	@Override
	protected Class getObjectClass() {
		return User.class;
	}

	@Override
	protected Serializable getId(User user) {
		return user.getId();
	}

	@Override
	public User getByLogin(String login) throws DaoHibernateException {
		logger.info("Start getByLogin.");
		logger.debug("Login - " + login);
		List<User> users = new ArrayList<>();
		try {
			super.startTransaction();
			String hql = Bundle.getQueryResource("user.get.by.login");
			Query query = super.getSession().createQuery(hql);
			query.setParameter("user_login", login);
			users.addAll(query.list());
			logger.debug("Users quantity - " + users.size());
			super.endTransaction();
		} catch (HibernateException e) {
			logger.debug("getByLogin method error.");
			logger.debug(e.getStackTrace());
			super.getTransaction().rollback();
			throw new DaoHibernateException(e.getCause());
		} finally {
			closeSession();
		}
		return users.isEmpty() ? null : users.get(0);
	}

	@Override
	public User getByLoginAndPassword(User user) throws DaoHibernateException {
		logger.info("Start getByLoginAndPassword");
		logger.debug("Login - " + user.getLogin());
		List<User> users = new ArrayList<User>();
		try {
			super.startTransaction();
			String hql = Bundle
					.getQueryResource("user.get.by.login.and.password");
			Query query = super.getSession().createQuery(hql);
			query.setParameter("user_login", user.getLogin());
			query.setParameter("user_password", user.getPassword());
			users = query.list();
			logger.debug("Users quantity - " + users.size());
			super.endTransaction();
		} catch (HibernateException e) {
			logger.debug("getByLoginAndPassword method error.");
			logger.debug(e.getStackTrace());
			super.getTransaction().rollback();
			throw new DaoHibernateException(e.getCause());
		} finally {
			closeSession();
		}
		return users.isEmpty() ? null : users.get(0);
	}

	@Override
	public void addRoleForUser(User user, Role role) throws DaoHibernateException {
		logger.info("Start addRoleForUser");
		logger.debug("Login - " + user.getLogin());
		logger.debug("Role - " + role);
		try{
		super.startTransaction();
		user = (User) super.getSession().get(User.class, user.getId());
		Set<Role> roles = user.getRoles();
		role = (Role) super.getSession().get(Role.class, role.getId());
		roles.add(role);
		user.setRoles(roles);
		super.getSession().update(user);
		super.endTransaction();
		} catch (HibernateException e) {
			logger.debug("addRoleForUser method error.");
			logger.debug(e.getStackTrace());
			super.getTransaction().rollback();
			throw new DaoHibernateException(e.getCause());
		} finally {
			closeSession();
		}
	}

	@Override
	protected void setFields(User user, User updateUser) {
		updateUser.setFieldsByAnotherUser(user);
	}

	@Override
	public Set<Role> getRolesByUser(User user) throws DaoHibernateException {
		logger.info("Start getRolesByUser");
		logger.debug("Login - " + user.getLogin());
		Set<Role> roles = new HashSet<Role>();
		try{
		super.startTransaction();
		user = (User) super.getSession().get(User.class, user.getId());
		roles.addAll(user.getRoles());
		super.endTransaction();
		} catch (HibernateException e) {
			logger.debug("getRolesByUser method error.");
			logger.debug(e.getStackTrace());
			super.getTransaction().rollback();
			throw new DaoHibernateException(e.getCause());
		} finally {
			closeSession();
		}
		return roles;
	}

}
