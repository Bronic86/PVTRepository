package by.academy.alekhno.spring.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import by.academy.alekhno.bundle.Bundle;
import by.academy.alekhno.spring.dao.interf.AbstractDAO;
import by.academy.alekhno.spring.dao.interf.CustomRoleDAO;
import by.academy.alekhno.spring.pojo.RolePojo;

//@Repository
public class CustomRoleDAOImpl extends AbstractDAO<RolePojo, Integer> implements CustomRoleDAO {
	// private static final Logger logger =
	// Logger.getLogger(CustomRoleDAOImpl.class.getName());

	/**
	 * Get Role by unique field "name"
	 */
	@Override
	public RolePojo getByName(String name) {
		List<RolePojo> roles = new ArrayList<>();
		String hql = Bundle.getQueryResource("role.get.by.name");
		Query query = super.getSession().createQuery(hql);
		query.setParameter("name", name);
		roles.addAll(query.list());
		return roles.isEmpty() ? null : roles.get(0);
	}

	@Override
	protected Class getObjectClass() {
		return RolePojo.class;
	}

	@Override
	protected void setFields(RolePojo newRole, RolePojo persistRole) {
		persistRole.setName(newRole.getName());
		persistRole.setUsers(newRole.getUsers());
	}

	@Override
	protected Integer getId(RolePojo role) {
		return role.getId();
	}
}
