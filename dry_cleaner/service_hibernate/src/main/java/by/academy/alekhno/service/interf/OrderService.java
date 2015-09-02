package by.academy.alekhno.service.interf;

import java.util.List;

import by.academy.alekhno.exception.ServiceException;
import by.academy.alekhno.external.OrderDAO;
import by.academy.alekhno.vo.Order;

public interface OrderService {

	int add(Order order);
	
	void deleteByID(int id);
	
	List<Order> getOrdersByUserId(int id);
	
	List<Order> getOrders();
	
	List<Order> getOrdersByClotherId(int id);
	
	void setDaoOrder(OrderDAO daoOrder);
}
