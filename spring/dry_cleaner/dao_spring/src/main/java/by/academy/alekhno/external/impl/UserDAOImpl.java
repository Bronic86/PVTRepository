package by.academy.alekhno.external.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import by.academy.alekhno.dao.impl.CustomUserDAOImpl;
import by.academy.alekhno.dao.interf.CustomUserDAO;
import by.academy.alekhno.database.converter.ConverterPojoToVO;
import by.academy.alekhno.database.converter.ConverterVOToPojo;
import by.academy.alekhno.database.pojo.RolePojo;
import by.academy.alekhno.database.pojo.UserPojo;
import by.academy.alekhno.exception.DaoHibernateException;
import by.academy.alekhno.external.UserDAO;
import by.academy.alekhno.vo.Role;
import by.academy.alekhno.vo.User;

public class UserDAOImpl implements UserDAO {
	private static final Logger logger = Logger
			.getLogger(UserDAOImpl.class.getName());

	private CustomUserDAO dao;
	private UserPojo userP;
	private RolePojo roleP;
	private List<UserPojo> usersP = new ArrayList<UserPojo>();

	public UserDAOImpl() {
		dao = new CustomUserDAOImpl();
	}
	
	@Override
	public List<User> getAll() throws DaoHibernateException {
		logger.info("Start external getAll.");
		List<User> usersVO = new ArrayList<>();
		usersP.addAll(dao.getAll());
		for (UserPojo userP : usersP) {
			usersVO.add(ConverterPojoToVO.getUser(userP));
		}
		return usersVO;
	}

	@Override
	public void update(User user) throws DaoHibernateException {
		logger.info("Start external update.");
		userP = ConverterVOToPojo.getUser(user);
		dao.update(userP);
	}

	@Override
	public void delete(User user) throws DaoHibernateException {
		logger.info("Start external delete.");
		userP = ConverterVOToPojo.getUser(user);
		dao.delete(userP);
	}

	@Override
	public int add(User user) throws DaoHibernateException {
		logger.info("Start external add.");
		userP = ConverterVOToPojo.getUser(user);
		int id = dao.add(userP);
		return id;
	}

	@Override
	public User getByID(User user) throws DaoHibernateException {
		logger.info("Start external getByID.");
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
		logger.info("Start external getByLogin.");
		userP = dao.getByLogin(login);
		User user = ConverterPojoToVO.getUser(userP);
		return user;
	}

	@Override
	public User getByLoginAndPassword(User user) throws DaoHibernateException {
		logger.info("Start external getByLoginAndPassword.");
		userP = ConverterVOToPojo.getUser(user);
		userP = dao.getByLoginAndPassword(userP);
		user = ConverterPojoToVO.getUser(userP);
		return user;
	}

	@Override
	public void addRoleForUser(User user, Role role)
			throws DaoHibernateException {
		logger.info("Start external addRoleForUser.");
		userP = ConverterVOToPojo.getUser(user);
		roleP = ConverterVOToPojo.getRole(role);
		dao.addRoleForUser(userP, roleP);
	}

	@Override
	public Set<Role> getRolesByUser(User user) throws DaoHibernateException {
		logger.info("Start external getRolesByUser.");
		userP = ConverterVOToPojo.getUser(user);
		Set<RolePojo> rolesP = new HashSet<RolePojo>();
		rolesP.addAll(dao.getRolesByUser(userP));
		Set<Role> rolesVO = new HashSet<>();
		for (RolePojo roleP : rolesP) {
			rolesVO.add(ConverterPojoToVO.getRole(roleP));
		}
		return rolesVO;
	}

}
