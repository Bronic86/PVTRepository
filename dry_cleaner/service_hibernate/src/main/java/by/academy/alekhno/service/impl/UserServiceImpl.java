package by.academy.alekhno.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import by.academy.alekhno.database.util.HibernateUtil;
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

	public UserServiceImpl() {
	}

	public User authorization(String login, String password) {
		logger.info("Start authoruthation.");
		User user = new User();
		user.setLogin(login);
		password = modifyPassword(password);
		user.setPassword(password);
		User findingUser = daoUser.getByLoginAndPassword(user);
		if (findingUser == null) {
			logger.error("Didn't find user");
			// throw new ServiceException("Login or password is incorrect.");
		}
		logger.info("End authoruthation.");
		return findingUser;
	}

	private String modifyPassword(String password) {
		// Override
		return DigestUtils.md5Hex(password);
	}

	public void registration(User user) {
		logger.info("Start registration.");
		String login = user.getLogin();
		String password = modifyPassword(user.getPassword());
		user.setPassword(password);
		if (!loginExist(login)) {
			int newId = daoUser.add(user);
			User newUser = new User();
			newUser.setId(newId);
			Role role = daoRole.getByName("user");
			logger.info(newUser);
			logger.info(role);
			daoUser.addRoleForUser(newUser, role);
		} else {
			logger.error("User don't exist");
			// throw new ServiceException("Login exist.");
		}
		logger.info("End registration.");
	}

	private boolean loginExist(String login) {
		User user = daoUser.getByLogin(login);
		return user != null;
	}

	public void addOrder(Order order) {
		logger.info("Start addOrder.");
		int id = daoOrder.add(order);
		logger.info("Order id = " + id);
	}

	public void updateOrder(Order order) {
		logger.info("Start updateOrder.");
		daoOrder.update(order);
	}

	public void deleteOrderById(int id) {
		logger.info("Start deleteOrderById.");
		Order order = new Order();
		order.setId(id);
		daoOrder.delete(order);
	}

	public List<Order> getOrdersByUserId(int user_id) {
		logger.info("Start getOrdersByUserId.");
		List<Order> orders = new ArrayList<Order>();
		orders = daoOrder.getOrdersByUserId(user_id);
		return orders;
	}

	public Set<Role> getRoleByUserId(int user_id) {
		logger.info("Start getRoleByUserId.");
		Set<Role> roles = new HashSet<Role>();
		User user = new User();
		user.setId(user_id);
		roles = daoUser.getRolesByUser(user);
		return roles;
	}

	public User getUserByLogin(String login) {
		logger.info("Start getUserByLogin.");
		User user = null;
		user = daoUser.getByLogin(login);
		return user;
	}

//	public CustomUserDao getDaoUser() throws ServiceException {
//		logger.info("Start getDaoUser.");
//		if (daoUser == null) {
//			logger.error("DaoUser didn't set.");
//			throw new ServiceException("Didn't set CustomRole");
//		}
//		logger.info("End getDaoUser.");
//		return daoUser;
//	}

	public void setDaoUser(UserDAO daoUser) {
		logger.info("Set daoUser.");
		this.daoUser = daoUser;
		this.daoUser.setSession(HibernateUtil.getInstance().getSession());
	}

//	public CustomOrderDao getDaoOrder() throws ServiceException {
//		logger.info("Start getDaoOrder.");
//		if (daoOrder == null) {
//			logger.error("DaoOrder didn't set.");
//			throw new ServiceException("Didn't set CustomRole");
//		}
//		logger.info("End getDaoOrder.");
//		return daoOrder;
//	}

	public void setDaoOrder(OrderDAO daoOrder) {
		logger.info("Set daoOrder.");
		this.daoOrder = daoOrder;
			this.daoOrder.setSession(HibernateUtil.getInstance().getSession());
	}

//	public CustomUserRoleDao getDaoUserRole() throws ServiceException {
//		logger.info("Start getDaoUserRole.");
//		if (daoUserRole == null) {
//			logger.error("DaoUserRole didn't set.");
//			throw new ServiceException("Didn't set CustomRole");
//		}
//		logger.info("End getDaoUserRole.");
//		return daoUserRole;
//	}

//	public void setDaoUserRole(CustomUserRoleDao daoUserRole)
//			throws ServiceException {
//		logger.info("Set daoUserRole.");
//		this.daoUserRole = daoUserRole;
//		try {
//			this.daoUserRole.setConnection(ConnectionPool.getInstance()
//					.getConnection());
//		} catch (DaoException e) {
//			logger.error("Problem with connection to database.", e);
//			throw new ServiceException("Sorry. Problem with server.");
//		}
//	}

//	public CustomRole getDaoRole() throws ServiceException {
//		logger.info("Start getDaoRole.");
//		if (daoRole == null) {
//			logger.error("DaoRole didn't set.");
//			throw new ServiceException("Didn't set CustomRole");
//		}
//		logger.info("End getDaoRole.");
//		return daoRole;
//	}

	public void setDaoRole(RoleDAO daoRole) {
		logger.info("Set daoRole.");
		this.daoRole = daoRole;
			this.daoRole.setSession(HibernateUtil.getInstance().getSession());
	}

}
