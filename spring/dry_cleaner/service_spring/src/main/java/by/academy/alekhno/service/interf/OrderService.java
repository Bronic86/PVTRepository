package by.academy.alekhno.service.interf;

import java.util.List;

import by.academy.alekhno.exception.ServiceException;
import by.academy.alekhno.external.OrderDAO;
import by.academy.alekhno.vo.Order;

public interface OrderService {
	
	void deleteByID(int id) throws ServiceException;
	
	List<Order> getOrdersByUserId(int id) throws ServiceException;
	
	List<Order> getOrders() throws ServiceException;
	
	List<Order> getOrdersByClotherId(int id) throws ServiceException;
	
	void setDaoOrder(OrderDAO daoOrder);
}
