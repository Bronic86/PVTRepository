package by.academy.alekhno.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import by.academy.alekhno.bundle.Bundle;
import by.academy.alekhno.dao.interf.AbstractDAO;
import by.academy.alekhno.dao.interf.CustomRoleDAO;
import by.academy.alekhno.database.pojo.RolePojo;
import by.academy.alekhno.exception.DaoHibernateException;

public class CustomRoleDAOImpl extends AbstractDAO<RolePojo> implements CustomRoleDAO {
	private static final Logger logger = Logger.getLogger(CustomRoleDAOImpl.class
			.getName());

	@Override
	protected Class getObjectClass() {
		return RolePojo.class;
	}

	@Override
	protected Serializable getId(RolePojo role) {
		return role.getId();
	}

	@Override
	public void setFields(RolePojo role, RolePojo updateRole) {
		updateRole.setFieldsByAnotherRole(role);
	}

	@Override
	public RolePojo getByName(String name) throws DaoHibernateException {
		logger.info("Start getByName");
		logger.debug("Name is \"" + name + "\".");
		List<RolePojo> roles = new ArrayList<>();
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
			super.getTransaction().rollback();
			throw new DaoHibernateException(e);
		} finally {
			closeSession();
		}
		return roles.isEmpty() ? null : roles.get(0);
	}

}
