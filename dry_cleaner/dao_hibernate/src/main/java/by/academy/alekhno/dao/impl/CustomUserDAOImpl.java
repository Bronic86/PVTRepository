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
import by.academy.alekhno.database.pojo.RolePojo;
import by.academy.alekhno.database.pojo.UserPojo;
import by.academy.alekhno.exception.DaoHibernateException;

public class CustomUserDAOImpl extends AbstractDAO<UserPojo> implements
		CustomUserDAO {
	private static final Logger logger = Logger
			.getLogger(CustomUserDAOImpl.class.getName());

	@Override
	protected Class getObjectClass() {
		return UserPojo.class;
	}

	@Override
	protected Serializable getId(UserPojo user) {
		return user.getId();
	}

	@Override
	public UserPojo getByLogin(String login) throws DaoHibernateException {
		logger.info("Start getByLogin.");
		logger.debug("Login - " + login);
		List<UserPojo> users = new ArrayList<>();
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
			super.getTransaction().rollback();
			throw new DaoHibernateException(e);
		} finally {
			closeSession();
		}
		return users.isEmpty() ? null : users.get(0);
	}

	@Override
	public UserPojo getByLoginAndPassword(UserPojo user)
			throws DaoHibernateException {
		logger.info("Start getByLoginAndPassword");
		logger.debug("Login - " + user.getLogin());
		List<UserPojo> users = new ArrayList<UserPojo>();
		try {
			super.startTransaction();
			String hql = Bundle
					.getQueryResource("user.get.by.login.and.password");
			Query query = super.getSession().createQuery(hql);
			query.setParameter("user_login", user.getLogin());
			query.setParameter("user_password", user.getPassword());
			users.addAll(query.list());
			logger.debug("Users quantity - " + users.size());
			super.endTransaction();
		} catch (HibernateException e) {
			logger.debug("getByLoginAndPassword method error.");
			super.getTransaction().rollback();
			throw new DaoHibernateException(e);
		} finally {
			closeSession();
		}
		return users.isEmpty() ? null : users.get(0);
	}

	@Override
	public void addRoleForUser(UserPojo user, RolePojo role)
			throws DaoHibernateException {
		logger.info("Start addRoleForUser");
		logger.debug("Login - " + user.getLogin());
		logger.debug("Role - " + role);
		try {
			super.startTransaction();
			user = (UserPojo) super.getSession().get(UserPojo.class,
					user.getId());
			Set<RolePojo> roles = user.getRoles();
			role = (RolePojo) super.getSession().get(RolePojo.class,
					role.getId());
			roles.add(role);
			user.setRoles(roles);
			super.getSession().update(user);
			super.endTransaction();
		} catch (HibernateException e) {
			logger.debug("addRoleForUser method error.");
			super.getTransaction().rollback();
			throw new DaoHibernateException(e);
		} finally {
			closeSession();
		}
	}

	@Override
	public void setFields(UserPojo user, UserPojo updateUser) {
		updateUser.setFieldsByAnotherUser(user);
	}

	@Override
	public Set<RolePojo> getRolesByUser(UserPojo user)
			throws DaoHibernateException {
		logger.info("Start getRolesByUser");
		logger.debug("Login - " + user.getLogin());
		Set<RolePojo> roles = new HashSet<RolePojo>();
		try {
			super.startTransaction();
			user = (UserPojo) super.getSession().get(UserPojo.class,
					user.getId());
			roles.addAll(user.getRoles());
			super.endTransaction();
		} catch (HibernateException e) {
			logger.debug("getRolesByUser method error.");
			super.getTransaction().rollback();
			throw new DaoHibernateException(e);
		} finally {
			closeSession();
		}
		return roles;
	}

}
