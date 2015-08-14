package by.academy.alekhno.service.impl;

import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

import org.apache.commons.codec.digest.DigestUtils;

import by.academy.alekhno.dao.interf.CustomOrderDao;
import by.academy.alekhno.dao.interf.CustomRole;
import by.academy.alekhno.dao.interf.CustomUserDao;
import by.academy.alekhno.dao.interf.CustomUserRoleDao;
import by.academy.alekhno.exception.DaoException;
import by.academy.alekhno.exception.ServiceException;
import by.academy.alekhno.vo.Order;
import by.academy.alekhno.vo.Role;
import by.academy.alekhno.vo.User;
import by.academy.alekhno.vo.UserRole;
import by.academy.alekhno.service.interf.UserService;

public class UserServiceImpl implements UserService {
	private CustomUserDao daoUser;
	private CustomOrderDao daoOrder;
	private CustomUserRoleDao daoUserRole;
	private CustomRole daoRole;
	private static Logger logger = Logger.getLogger(UserServiceImpl.class
			.getName());
	private Lock lock = new ReentrantLock();

	public UserServiceImpl() {
	}

	public User authorization(String login, String password)
			throws ServiceException, DaoException {
		// TODO Auto-generated method stub
		logger.info("Start authoruthation.");
		User user = new User();
		user.setLogin(login);
		password = modifyPassword(password);
		user.setPassword(password);
		User findingUser = null;
		try {
			lock.lock();
			findingUser = daoUser.getByLoginAndPassword(user);
		} finally {
			lock.unlock();
		}
		if (findingUser == null) {
			// logger.error("Didn't find user");
			throw new ServiceException("Login or password is incorrect.");
		}
		logger.info("End authoruthation.");
		return findingUser;
	}

	private String modifyPassword(String password) {
		// Override
		return DigestUtils.md5Hex(password);
	}

	public void registration(User user) throws ServiceException, DaoException {
		// TODO Auto-generated method stub
		logger.info("Start registration.");
		String login = user.getLogin();
		String password = modifyPassword(user.getPassword());
		user.setPassword(password);
		if (!loginExist(login)) {
			User newUser = null;
			try {
				lock.lock();
				daoUser.add(user);
				newUser = daoUser.getByLogin(login);
			} finally {
				lock.unlock();
			}
			UserRole userRole = new UserRole();
			userRole.setUser(newUser);
			Role role = new Role();
			role.setId(1);
			try {
				lock.lock();
				role = daoRole.getByID(role);
				userRole.setRole(role);
				daoUserRole.add(userRole);
			} finally {
				lock.unlock();
			}
		} else {
			// logger.error("User don't exist");
			throw new ServiceException("Login exist.");
		}
		logger.info("End registration.");
	}

	private boolean loginExist(String login) throws ServiceException,
			DaoException {
		User user = null;
		try {
			lock.lock();
			user = daoUser.getByLogin(login);
		} finally {
			lock.unlock();
		}
		return user != null;
	}

	public void addOrder(Order order) throws DaoException {
		// TODO Auto-generated method stub
		logger.info("Start addOrder.");
		try {
			lock.lock();
			daoOrder.add(order);
		} finally {
			lock.unlock();
		}
		logger.info("End addOrder.");
	}

	public void updateOrder(Order order) throws DaoException {
		// TODO Auto-generated method stub
		logger.info("Start updateOrder.");
		try {
			lock.lock();
			daoOrder.update(order);
		} finally {
			lock.unlock();
		}
		logger.info("End updateOrder.");
	}

	public void deleteOrderById(int id) throws DaoException {
		// TODO Auto-generated method stub
		logger.info("Start deleteOrderById.");
		Order order = new Order();
		order.setId(id);
		try {
			lock.lock();
			daoOrder.delete(order);
		} finally {
			lock.unlock();
		}
		logger.info("End deleteOrderById.");
	}

	public List<Order> getOrdersByUserId(int user_id) throws DaoException {
		// TODO Auto-generated method stub
		logger.info("Start getOrdersByUserId.");
		List<Order> orders = new ArrayList<Order>();
		try {
			lock.lock();
			orders = daoOrder.getOrdersByUserId(user_id);
		} finally {
			lock.unlock();
		}
		logger.info("End getOrdersByUserId.");
		return orders;
	}

	public List<Role> getRoleByUserId(int user_id) throws DaoException {
		// TODO Auto-generated method stub
		logger.info("Start getRoleByUserId.");
		List<Role> roles = new ArrayList<Role>();
		List<UserRole> userRoles = new ArrayList<UserRole>();
		try {
			lock.lock();
			userRoles = daoUserRole.getByIdUser(user_id);
		} finally {
			lock.unlock();
		}
		for (UserRole userRole : userRoles) {
			roles.add(userRole.getRole());
		}
		logger.info("End getRoleByUserId.");
		return roles;
	}

	public User getUserByLogin(String login) throws DaoException {
		// TODO Auto-generated method stub
		logger.info("Start getUserByLogin.");
		User user = null;
		try {
			lock.lock();
			user = daoUser.getByLogin(login);
		} finally {
			lock.unlock();
		}
		logger.info("End getUserByLogin.");
		return user;
	}

	public CustomUserDao getDaoUser() throws ServiceException {
		logger.info("Start getDaoUser.");
		if (daoUser == null) {
			// logger.error("DaoUser didn't set.");
			throw new ServiceException("Didn't set CustomRole");
		}
		logger.info("End getDaoUser.");
		return daoUser;
	}

	public void setDaoUser(CustomUserDao daoUser) {
		logger.info("Set daoUser.");
		this.daoUser = daoUser;
	}

	public CustomOrderDao getDaoOrder() throws ServiceException {
		logger.info("Start getDaoOrder.");
		if (daoOrder == null) {
			// logger.error("DaoOrder didn't set.");
			throw new ServiceException("Didn't set CustomRole");
		}
		logger.info("End getDaoOrder.");
		return daoOrder;
	}

	public void setDaoOrder(CustomOrderDao daoOrder) {
		logger.info("Set daoOrder.");
		this.daoOrder = daoOrder;
	}

	public CustomUserRoleDao getDaoUserRole() throws ServiceException {
		logger.info("Start getDaoUserRole.");
		if (daoUserRole == null) {
			// logger.error("DaoUserRole didn't set.");
			throw new ServiceException("Didn't set CustomRole");
		}
		logger.info("End getDaoUserRole.");
		return daoUserRole;
	}

	public void setDaoUserRole(CustomUserRoleDao daoUserRole) {
		logger.info("Set daoUserRole.");
		this.daoUserRole = daoUserRole;
	}

	public CustomRole getDaoRole() throws ServiceException {
		logger.info("Start getDaoRole.");
		if (daoRole == null) {
			// logger.error("DaoRole didn't set.");
			throw new ServiceException("Didn't set CustomRole");
		}
		logger.info("End getDaoRole.");
		return daoRole;
	}

	public void setDaoRole(CustomRole daoRole) {
		logger.info("Set daoRole.");
		this.daoRole = daoRole;
	}

}
