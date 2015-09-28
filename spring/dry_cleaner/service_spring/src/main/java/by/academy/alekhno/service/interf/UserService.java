package by.academy.alekhno.service.interf;

import java.util.List;
import java.util.Set;

import by.academy.alekhno.exception.ServiceException;
import by.academy.alekhno.external.OrderDAO;
import by.academy.alekhno.external.RoleDAO;
import by.academy.alekhno.external.UserDAO;
import by.academy.alekhno.vo.Order;
import by.academy.alekhno.vo.Role;
import by.academy.alekhno.vo.User;

public interface UserService {

	User authorization(String login, String password) throws ServiceException;

	void registration(User user) throws ServiceException;

	void addOrder(Order order) throws ServiceException;

	void updateOrder(Order order) throws ServiceException;

	void deleteOrderById(int id) throws ServiceException;

	List<Order> getOrdersByUserId(int user_id) throws ServiceException;

	Set<Role> getRoleByUserId(int user_id) throws ServiceException;

	User getUserByLogin(String login) throws ServiceException;

	void setDaoUser(UserDAO daoUser);

	void setDaoOrder(OrderDAO daoOrder);

	void setDaoRole(RoleDAO daoRole);

	void addRoleToUser(User user, Role role) throws ServiceException;

	Set<User> getAll() throws ServiceException;
}
