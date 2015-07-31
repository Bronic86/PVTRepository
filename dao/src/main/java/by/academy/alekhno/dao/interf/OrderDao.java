package by.academy.alekhno.dao;

import java.util.List;

import by.academy.alekhno.vo.Order;

public interface OrderDao {

	Order getOrder (Order order);
	List<Order> getAll ();
	void addOrder (Order order);
	void updateOrder (Order order);
	void deleteOrder (Order order);
	
}
