package by.academy.alekhno.spring.interf;

import java.util.List;

import by.academy.alekhno.vo.Order;

public interface OrderService {

	void deleteByID(int id);

	void addOrder(Order order);

	void updateOrder(Order order);

	List<Order> getOrdersByUserId(int id);

	List<Order> getOrders();

	List<Order> getOrdersByClotherId(int id);

}
