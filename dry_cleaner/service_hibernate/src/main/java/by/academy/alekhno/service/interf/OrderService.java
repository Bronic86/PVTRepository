package by.academy.alekhno.service.interf;

import java.util.List;

import by.academy.alekhno.external.OrderDAO;
import by.academy.alekhno.vo.Order;

public interface OrderService {
	
	void deleteByID(int id);
	
	List<Order> getOrdersByUserId(int id);
	
	List<Order> getOrders();
	
	List<Order> getOrdersByClotherId(int id);
	
	void setDaoOrder(OrderDAO daoOrder);
}
