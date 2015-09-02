package by.academy.alekhno.external;

import java.util.List;

import org.hibernate.Session;

import by.academy.alekhno.vo.Order;

public interface OrderDAO {

void update(Order order) ;

	List<Order> getAll() ;
	
	void delete(Order order);
	
	int add(Order order);
	
	Order getByID (Order order);
	
	void setSession(Session session);
	
	List<Order> getOrdersByUserId(int id);
	
	List<Order> getOrdersByClotherId(int id);
}
