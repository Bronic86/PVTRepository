package by.academy.alekhno.dao.impl;

import java.io.Serializable;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import by.academy.alekhno.bundle.Bundle;
import by.academy.alekhno.dao.interf.AbstractDAO;
import by.academy.alekhno.dao.interf.CustomRoleDAO;
import by.academy.alekhno.database.pojo.Role;

public class RoleDAOImpl extends AbstractDAO<Role> implements CustomRoleDAO {
	private static final Logger logger = Logger.getLogger(RoleDAOImpl.class.getName());

	@Override
	protected Class getObjectClass() {
		return Role.class;
	}

	@Override
	protected Serializable getId(Role role) {
		return role.getId();
	}

//	@Override
//	public Set<Role> getByUser(User user) {
//		logger.info("Start getByUser.");
//		logger.debug("Login - " + user.getLogin());
//		super.startTransaction();
//		String hql = Bundle.getQueryResource("role.get.by.user");
//		Query query = super.getSession().createQuery(hql);
//		query.setParameter("user_id", user.getId());
//		Set<Role> roles = (Set<Role>) query.list();
//		logger.debug("Roles quantity - " + roles.size());
//		super.endTransaction();
//		return roles;
//	}

	@Override
	protected void setFields(Role role, Role updateRole) {
		updateRole.setFieldsByAnotherRole(role);
	}

	@Override
	public Role getByName(String name) {
		logger.info("Start getByName");
		logger.debug("Name is \"" + name + "\".");
		super.startTransaction();
		String hql = Bundle.getQueryResource("role.get.by.name");
		Query query = super.getSession().createQuery(hql);
		query.setParameter("name", name);
		Set<Role> roles = (Set<Role>) query.list();
		logger.debug("Roles - " + roles.size());
		super.endTransaction();
		return null;
	}

}
