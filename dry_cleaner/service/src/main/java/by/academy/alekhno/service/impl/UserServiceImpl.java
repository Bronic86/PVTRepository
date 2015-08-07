package by.academy.alekhno.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import by.academy.alekhno.dao.impl.OrderImpl;
import by.academy.alekhno.dao.impl.UserImpl;
import by.academy.alekhno.dao.impl.UserRoleImpl;
import by.academy.alekhno.dao.interf.CustomOrderDao;
import by.academy.alekhno.dao.interf.CustomUserDao;
import by.academy.alekhno.dao.interf.CustomUserRoleDao;
import by.academy.alekhno.dao.interf.GenericDao;
import by.academy.alekhno.exception.DaoException;
import by.academy.alekhno.exception.ServiceException;
import by.academy.alekhno.vo.Order;
import by.academy.alekhno.vo.Role;
import by.academy.alekhno.vo.User;
import by.academy.alekhno.vo.UserRole;
import by.academy.alekhno.service.interf.UserService;

public class UserServiceImpl implements UserService {

	public void authorization(String login, String password) throws ServiceException, DaoException {
		// TODO Auto-generated method stub
		CustomUserDao daoUser = new UserImpl();
		User user = new User();
		user.setLogin(login);
		password = modifyPassword(password);
		user.setPassword(password);
		User findingUser = daoUser.getByLoginAndPassword(user);
		if(findingUser == null){
			throw new ServiceException("Authorithation error.");
		}
	}
	
	private String modifyPassword(String password) {
		// Override
		return DigestUtils.md5Hex(password);
	}

	public void registration(User user) throws ServiceException, DaoException {
		// TODO Auto-generated method stub
		GenericDao<User> daoUser = new UserImpl();
		String login = user.getLogin();
			if (!loginExist(login)) {
				daoUser.add(user);
			} else {
				throw new ServiceException("Login exist.");
			}
	}
	
	private boolean loginExist(String login) throws ServiceException, DaoException {
		CustomUserDao daoUser = new UserImpl();
		User user = daoUser.getByLogin(login);
		return user.getLogin() != null;
	}

	public void addOrder(Order order) throws DaoException {
		// TODO Auto-generated method stub
		GenericDao<Order> daoOrder = new OrderImpl();
		daoOrder.add(order);
	}

	public void updateOrder(Order order) throws DaoException {
		// TODO Auto-generated method stub
		GenericDao<Order> daoOrder = new OrderImpl();
		daoOrder.update(order);
	}

	public void deleteOrderById(int id) throws DaoException {
		// TODO Auto-generated method stub
		GenericDao<Order> daoOrder = new OrderImpl();
		Order order = new Order();
		order.setId(id);
		daoOrder.delete(order);
	}

	public List<Order> getOrdersByUserId(int user_id) throws DaoException {
		// TODO Auto-generated method stub
		List<Order> orders = null;
		CustomOrderDao daoOrder = new OrderImpl();
		orders = daoOrder.getOrdersByUserId(user_id);
		return orders;
	}

	public List<Role> getRoleByUserId(int user_id) throws DaoException {
		// TODO Auto-generated method stub
		List<Role> roles = new ArrayList<Role>();
		CustomUserRoleDao daoUserRole = new UserRoleImpl();
			List<UserRole> userRoles = daoUserRole.getByIdUser(user_id);
			for(UserRole userRole : userRoles){
				roles.add(userRole.getRole());
			}	
		return roles;
	}

}
