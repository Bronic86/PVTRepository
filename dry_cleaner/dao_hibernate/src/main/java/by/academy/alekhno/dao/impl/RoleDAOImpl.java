package by.academy.alekhno.dao.impl;

import java.io.Serializable;

import org.apache.log4j.Logger;

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

}
