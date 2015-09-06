package by.academy.alekhno.dao.interf;

import java.util.List;

import by.academy.alekhno.database.pojo.Order;
import by.academy.alekhno.exception.DaoHibernateException;

public interface CustomOrderDAO extends GenericDAO<Order> {

	List<Order> getOrdersByUserId(int id) throws DaoHibernateException;
	
	List<Order> getOrdersByClotherId(int id) throws DaoHibernateException;
 
	List<Order> getOrdersByTypeId(int type_id) throws DaoHibernateException;

	List<Order> getOrdersByStateId(int state_id) throws DaoHibernateException;
}
