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
import by.academy.alekhno.dao.interf.CustomRoleDAO;
import by.academy.alekhno.database.pojo.Role;
import by.academy.alekhno.exception.DaoHibernateException;

public class RoleDAOImpl extends AbstractDAO<Role> implements CustomRoleDAO {
	private static final Logger logger = Logger.getLogger(RoleDAOImpl.class
			.getName());

	@Override
	protected Class getObjectClass() {
		return Role.class;
	}

	@Override
	protected Serializable getId(Role role) {
		return role.getId();
	}

	@Override
	protected void setFields(Role role, Role updateRole) {
		updateRole.setFieldsByAnotherRole(role);
	}

	@Override
	public Role getByName(String name) throws DaoHibernateException {
		logger.info("Start getByName");
		logger.debug("Name is \"" + name + "\".");
		List<Role> roles = new ArrayList<>();
		try {
			super.startTransaction();
			String hql = Bundle.getQueryResource("role.get.by.name");
			Query query = super.getSession().createQuery(hql);
			query.setParameter("name", name);
			roles.addAll(query.list());
			logger.debug("Roles - " + roles.size());
			super.endTransaction();
		} catch (HibernateException e) {
			logger.debug("getByName method error.");
			logger.debug(e.getStackTrace());
			super.getTransaction().rollback();
			throw new DaoHibernateException(e.getCause());
		} finally {
			closeSession();
		}
		return roles.isEmpty() ? null : roles.get(0);
	}

}
