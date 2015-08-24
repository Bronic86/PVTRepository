package by.academy.alekhno.service.interf;

import java.util.List;

import by.academy.alekhno.dao.interf.CustomOrderDao;
import by.academy.alekhno.dao.interf.CustomRole;
import by.academy.alekhno.dao.interf.CustomUserDao;
import by.academy.alekhno.dao.interf.CustomUserRoleDao;
import by.academy.alekhno.exception.DaoException;
import by.academy.alekhno.exception.ServiceException;
import by.academy.alekhno.vo.Order;
import by.academy.alekhno.vo.Role;
import by.academy.alekhno.vo.User;

public interface UserService {

	User authorization(String login, String password) throws DaoException, ServiceException;
	
	void registration(User user) throws ServiceException, DaoException;
	
	void addOrder(Order order) throws DaoException;
	
	void updateOrder(Order order) throws DaoException;
	
	void deleteOrderById(int id) throws DaoException;
	
	List<Order> getOrdersByUserId(int user_id) throws DaoException;
	
	List<Role> getRoleByUserId(int user_id) throws DaoException;
	
	User getUserByLogin(String login) throws DaoException;
	
	CustomUserDao getDaoUser() throws ServiceException;
	
	void setDaoUser(CustomUserDao daoUser) throws ServiceException;
	
	CustomOrderDao getDaoOrder() throws ServiceException;
	
	void setDaoOrder(CustomOrderDao daoOrder) throws ServiceException;
	
	CustomUserRoleDao getDaoUserRole() throws ServiceException;
	
	void setDaoUserRole(CustomUserRoleDao daoUserRole) throws ServiceException;
	
	CustomRole getDaoRole() throws ServiceException;
	
	void setDaoRole(CustomRole daoRole) throws ServiceException;
}
