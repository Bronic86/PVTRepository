package by.academy.alekhno.external;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import by.academy.alekhno.exception.DaoHibernateException;
import by.academy.alekhno.vo.Order;

public interface OrderDAO {

void update(Order order) throws DaoHibernateException ;

	List<Order> getAll() throws DaoHibernateException ;
	
	void delete(Order order) throws DaoHibernateException;
	
	int add(Order order) throws DaoHibernateException;
	
	Order getByID (Order order) throws DaoHibernateException;
	
	void setSessionFactory(SessionFactory sessionFactory);
	
	List<Order> getOrdersByUserId(int id) throws DaoHibernateException;
	
	List<Order> getOrdersByClotherId(int id) throws DaoHibernateException;
	
	List<Order> getOrdersByTypeId(int id) throws DaoHibernateException;
	
	List<Order> getOrdersByStateId(int id) throws DaoHibernateException;
}
