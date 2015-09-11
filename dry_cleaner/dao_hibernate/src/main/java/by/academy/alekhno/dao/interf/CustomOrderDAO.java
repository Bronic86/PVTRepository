package by.academy.alekhno.dao.interf;

import java.util.List;

import by.academy.alekhno.database.pojo.OrderPojo;
import by.academy.alekhno.exception.DaoHibernateException;

public interface CustomOrderDAO extends GenericDAO<OrderPojo> {

	List<OrderPojo> getOrdersByUserId(int id) throws DaoHibernateException;
	
	List<OrderPojo> getOrdersByClotherId(int id) throws DaoHibernateException;
 
	List<OrderPojo> getOrdersByTypeId(int type_id) throws DaoHibernateException;

	List<OrderPojo> getOrdersByStateId(int state_id) throws DaoHibernateException;
}
