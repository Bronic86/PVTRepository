package by.academy.alekhno.service.interf;

import java.util.List;

import by.academy.alekhno.exception.DaoException;
import by.academy.alekhno.exception.ServiceException;
import by.academy.alekhno.vo.Order;

public interface OrderService {

	int add(Order order) throws ServiceException, DaoException;
	
	void deleteByID(int id) throws DaoException;
	
	List<Order> getOrdersByUserId(int id) throws DaoException;
	
	List<Order> getOrders() throws DaoException;
}
