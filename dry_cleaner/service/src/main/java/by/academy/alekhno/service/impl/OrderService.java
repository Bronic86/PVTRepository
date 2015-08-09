package by.academy.alekhno.service.impl;

import java.util.List;

import by.academy.alekhno.dao.impl.OrderImpl;
import by.academy.alekhno.dao.interf.CustomOrderDao;
import by.academy.alekhno.dao.interf.GenericDao;
import by.academy.alekhno.exception.DaoException;
import by.academy.alekhno.exception.ServiceException;
import by.academy.alekhno.vo.Order;

public class OrderService {

	public void add(Order order) throws ServiceException {
		GenericDao<Order> daoOrder = new OrderImpl();
		try {
			daoOrder.add(order);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("Add order error.\n" + e.getMessage());
		}
	}

	public void deleteByID(int id) throws ServiceException {
		GenericDao<Order> daoOrder = new OrderImpl();
		Order order = new Order();
		order.setId(id);
		try {
			daoOrder.delete(order);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("Delete order error.\n" + e.getMessage());
		}
	}

	public List<Order> getOrdersByUserId(int id) throws ServiceException {
		CustomOrderDao daoOrder = new OrderImpl();
		try {
			return daoOrder.getOrdersByUserId(id);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("GetOrdersByUserId  error.\n"
					+ e.getMessage());
		}
	}

	public List<Order> getOrders() throws ServiceException {
		GenericDao<Order> daoOrder = new OrderImpl();
		try {
			return daoOrder.getAll();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("GetOrders  error.\n" + e.getMessage());
		}
	}

}
