package by.academy.alekhno.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import by.academy.alekhno.database.util.HibernateUtil;
import by.academy.alekhno.exception.DaoHibernateException;
import by.academy.alekhno.exception.ServiceException;
import by.academy.alekhno.external.OrderDAO;
import by.academy.alekhno.external.RoleDAO;
import by.academy.alekhno.external.UserDAO;
import by.academy.alekhno.vo.Order;
import by.academy.alekhno.vo.Role;
import by.academy.alekhno.vo.User;
import by.academy.alekhno.service.interf.UserService;

public class UserServiceImpl implements UserService {
	private UserDAO daoUser;
	private OrderDAO daoOrder;
	private RoleDAO daoRole;
	private static Logger logger = Logger.getLogger(UserServiceImpl.class
			.getName());

	public User authorization(String login, String password)
			throws ServiceException {
		logger.info("Start authoruthation.");
		User user = new User();
		user.setLogin(login);
		password = modifyPassword(password);
		user.setPassword(password);
		User findingUser = null;
		try {
			findingUser = daoUser.getByLoginAndPassword(user);
		} catch (DaoHibernateException e) {
			logger.error("Problem to daoUser, method getByLoginAndPassword.");
			throw new ServiceException("authorization error", e);
		}
		if (findingUser == null) {
			logger.error("Didn't find user");
			throw new ServiceException("authorization error");
		}
		logger.info("End authoruthation.");
		return findingUser;
	}

	private String modifyPassword(String password) {
		// Override
		return DigestUtils.md5Hex(password);
	}

	public void registration(User user) throws ServiceException {
		logger.info("Start registration.");
		String login = user.getLogin();
		String password = modifyPassword(user.getPassword());
		user.setPassword(password);
		if (!loginExist(login)) {
			int newId;
			try {
				newId = daoUser.add(user);
			} catch (DaoHibernateException e) {
				logger.error("Problem to daoUser, method add.");
				throw new ServiceException("registration error", e);
			}
			User newUser = new User();
			newUser.setId(newId);
			Role role = null;
			try {
				role = daoRole.getByName("user");
			} catch (DaoHibernateException e) {
				logger.error("Problem to daoRole, method getByName.");
				throw new ServiceException("registration error", e);
			}
			logger.info(newUser);
			logger.info(role);
			try {
				daoUser.addRoleForUser(newUser, role);
			} catch (DaoHibernateException e) {
				logger.error("Problem to daoUser, method addRoleForUser.");
				throw new ServiceException("registration error", e);
			}
		} else {
			logger.error("User don't exist");
			throw new ServiceException("Login exist.");
		}
		logger.info("End registration.");
	}

	private boolean loginExist(String login) throws ServiceException {
		User user;
		try {
			user = daoUser.getByLogin(login);
		} catch (DaoHibernateException e) {
			logger.error("Problem to daoUser, method getByLogin.");
			throw new ServiceException("loginExist error", e);
		}
		return user != null;
	}

	public void addOrder(Order order) throws ServiceException {
		logger.info("Start addOrder.");
		int id;
		try {
			id = daoOrder.add(order);
		} catch (DaoHibernateException e) {
			logger.error("Problem to daoOrder, method add.");
			throw new ServiceException("addOrder error", e);
		}
		logger.info("Order id = " + id);
	}

	public void updateOrder(Order order) throws ServiceException {
		logger.info("Start updateOrder.");
		try {
			daoOrder.update(order);
		} catch (DaoHibernateException e) {
			logger.error("Problem to daoOrder, method update.");
			throw new ServiceException("updateOrder error", e);
		}
	}

	public void deleteOrderById(int id) throws ServiceException {
		logger.info("Start deleteOrderById.");
		Order order = new Order();
		order.setId(id);
		try {
			daoOrder.delete(order);
		} catch (DaoHibernateException e) {
			logger.error("Problem to daoOrder, method delete.");
			throw new ServiceException("deleteOrderById error", e);
		}
	}

	public List<Order> getOrdersByUserId(int user_id) throws ServiceException {
		logger.info("Start getOrdersByUserId.");
		List<Order> orders = new ArrayList<Order>();
		try {
			orders = daoOrder.getOrdersByUserId(user_id);
		} catch (DaoHibernateException e) {
			logger.error("Problem to daoOrder, method getOrdersByUserId.");
			throw new ServiceException("getOrdersByUserId error", e);
		}
		return orders;
	}

	public Set<Role> getRoleByUserId(int user_id) throws ServiceException {
		logger.info("Start getRoleByUserId.");
		Set<Role> roles = new HashSet<Role>();
		User user = new User();
		user.setId(user_id);
		try {
			roles = daoUser.getRolesByUser(user);
		} catch (DaoHibernateException e) {
			logger.error("Problem to daoUser, method getRolesByUser.");
			throw new ServiceException("getRoleByUserId error", e);
		}
		return roles;
	}

	public User getUserByLogin(String login) throws ServiceException {
		logger.info("Start getUserByLogin.");
		User user = null;
		try {
			user = daoUser.getByLogin(login);
		} catch (DaoHibernateException e) {
			logger.error("Problem to daoUser, method getByLogin.");
			throw new ServiceException("getUserByLogin error", e);
		}
		return user;
	}

	public void setDaoUser(UserDAO daoUser) {
		logger.info("Set daoUser.");
		this.daoUser = daoUser;
		this.daoUser.setSessionFactory(HibernateUtil.getInstance()
				.getSessionFactory());
	}

	public void setDaoOrder(OrderDAO daoOrder) {
		logger.info("Set daoOrder.");
		this.daoOrder = daoOrder;
		this.daoOrder.setSessionFactory(HibernateUtil.getInstance()
				.getSessionFactory());
	}

	public void setDaoRole(RoleDAO daoRole) {
		logger.info("Set daoRole.");
		this.daoRole = daoRole;
		this.daoRole.setSessionFactory(HibernateUtil.getInstance()
				.getSessionFactory());
	}

	@Override
	public void addRoleToUser(User user, Role role) throws ServiceException {
		logger.info("Start addRoleToUser.");
		try {
			daoUser.addRoleForUser(user, role);
		} catch (DaoHibernateException e) {
			logger.error("Problem to daoUser, method addRoleToUser.");
			throw new ServiceException("addRoleToUser error", e);
		}
	}

	@Override
	public Set<User> getAll() throws ServiceException {
		logger.info("Start getAll.");
		Set<User> users = new HashSet<User>();
		try {
			users.addAll(daoUser.getAll());
		} catch (DaoHibernateException e) {
			logger.error("Problem to daoUser, method getAll.");
			throw new ServiceException("getAll error", e);
		}
		return users;
	}

}
