package by.academy.alekhno.dao.interf;

import java.util.List;

import by.academy.alekhno.exception.DaoException;
import by.academy.alekhno.vo.Order;

public interface CustomOrderDao extends GenericDao<Order> {

	List<Order> getOrdersByUserId(int id) throws DaoException;

	List<Integer> getIdByFields(Order order) throws DaoException;

	List<Order> getOrdersByClotherId(int id) throws DaoException;
}
