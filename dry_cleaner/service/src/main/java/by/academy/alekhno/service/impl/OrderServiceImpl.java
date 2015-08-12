package by.academy.alekhno.service.impl;

import java.util.List;

import by.academy.alekhno.dao.impl.OrderImpl;
import by.academy.alekhno.dao.interf.CustomOrderDao;
import by.academy.alekhno.exception.DaoException;
import by.academy.alekhno.service.interf.OrderService;
import by.academy.alekhno.vo.Order;

public class OrderServiceImpl implements OrderService {

	public int add(Order order) throws DaoException {
		CustomOrderDao daoOrder = new OrderImpl();
		daoOrder.add(order);
		List<Integer> idList = daoOrder.getIdByFields(order);
		return idList.get(0);
	}

	public void deleteByID(int id) throws DaoException {
		CustomOrderDao daoOrder = new OrderImpl();
		Order order = new Order();
		order.setId(id);
		daoOrder.delete(order);
	}

	public List<Order> getOrdersByUserId(int id) throws DaoException {
		CustomOrderDao daoOrder = new OrderImpl();
		return daoOrder.getOrdersByUserId(id);
	}

	public List<Order> getOrders() throws DaoException {
		CustomOrderDao daoOrder = new OrderImpl();
		return daoOrder.getAll();
	}

}
