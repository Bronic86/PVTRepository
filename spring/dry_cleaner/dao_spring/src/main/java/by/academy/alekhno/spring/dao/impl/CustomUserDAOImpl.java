package by.academy.alekhno.spring.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;

import by.academy.alekhno.bundle.Bundle;
import by.academy.alekhno.spring.dao.interf.AbstractDAO;
import by.academy.alekhno.spring.dao.interf.CustomUserDAO;
import by.academy.alekhno.spring.pojo.RolePojo;
import by.academy.alekhno.spring.pojo.UserPojo;

//@Repository
public class CustomUserDAOImpl extends AbstractDAO<UserPojo, Integer> implements CustomUserDAO {
	// private static final Logger logger =
	// Logger.getLogger(CustomUserDaoImpl.class.getName());

	/**
	 * Get User by unique field "login"
	 */
	@Override
	public UserPojo getByLogin(String login) {
		List<UserPojo> users = new ArrayList<UserPojo>();
		String hql = Bundle.getQueryResource("user.get.by.login");
		Query query = super.getSession().createQuery(hql);
		query.setParameter("user_login", login);
		users.addAll(query.list());
		return users.get(0);
	}

	/**
	 * Get unique User by unique field "login" and "password"
	 */
	@Override
	public UserPojo getByLoginAndPassword(String login, String password) {
		List<UserPojo> users = new ArrayList<UserPojo>();
		String hql = Bundle.getQueryResource("user.get.by.login.and.password");
		Query query = super.getSession().createQuery(hql);
		query.setParameter("user_login", login);
		query.setParameter("user_password", password);
		users.addAll(query.list());
		return users.get(0);
	}

	/**
	 * Add Role for User (by id)
	 */
	@Override
	public void addRoleForUser(UserPojo user, RolePojo role) {
		user = (UserPojo) super.getSession().get(UserPojo.class, user.getId());
		Set<RolePojo> roles = user.getRoles();
		role = (RolePojo) super.getSession().get(RolePojo.class, role.getId());
		roles.add(role);
		user.setRoles(roles);
	}

	/**
	 * Get Roles by User (id)
	 */
	@Override
	public Set<RolePojo> getRolesByUser(UserPojo user) {
		user = (UserPojo) super.getSession().get(UserPojo.class, user.getId());
		Set<RolePojo> roles = user.getRoles();
		return roles;
	}

	@Override
	protected Class getObjectClass() {
		return UserPojo.class;
	}

	@Override
	protected void setFields(UserPojo newUser, UserPojo persistUser) {
		persistUser.setLogin(newUser.getLogin());
		persistUser.setPassword(newUser.getPassword());
		persistUser.setFirstName(newUser.getFirstName());
		persistUser.setSecondName(newUser.getSecondName());
		persistUser.setTelephone(newUser.getTelephone());
	}

	@Override
	protected Integer getId(UserPojo user) {
		return user.getId();
	}

}
