package by.academy.alekhno.dao.interf;

import java.util.List;

import by.academy.alekhno.database.pojo.Order;

public interface CustomOrderDAO extends GenericDAO<Order> {

	List<Order> getOrdersByUserId(int id);
	
	List<Order> getOrdersByClotherId(int id);
}
