package by.academy.alekhno.service.interf;

import java.util.List;

import by.academy.alekhno.exception.ServiceException;
import by.academy.alekhno.external.OrderDAO;
import by.academy.alekhno.external.RoleDAO;
import by.academy.alekhno.external.UserDAO;
import by.academy.alekhno.vo.Order;
import by.academy.alekhno.vo.Role;
import by.academy.alekhno.vo.User;

public interface UserService {

	User authorization(String login, String password);
	
	void registration(User user);
	
	void addOrder(Order order);
	
	void updateOrder(Order order);
	
	void deleteOrderById(int id);
	
	List<Order> getOrdersByUserId(int user_id);
	
	List<Role> getRoleByUserId(int user_id);
	
	User getUserByLogin(String login);
	
	
	void setDaoUser(UserDAO daoUser);
	
	void setDaoOrder(OrderDAO daoOrder);
	
	void setDaoRole(RoleDAO daoRole);
}
