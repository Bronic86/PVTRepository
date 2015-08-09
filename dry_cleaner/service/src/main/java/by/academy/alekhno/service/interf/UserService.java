package by.academy.alekhno.service.interf;

import java.util.List;

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
	
}
