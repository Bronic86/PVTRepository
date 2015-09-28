package by.academy.alekhno.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.academy.alekhno.database.util.HibernateUtil;
import by.academy.alekhno.exception.DaoHibernateException;
import by.academy.alekhno.exception.ServiceException;
import by.academy.alekhno.external.OrderDAO;
import by.academy.alekhno.external.impl.OrderDAOImpl;
import by.academy.alekhno.service.interf.OrderService;
import by.academy.alekhno.vo.Order;

public class OrderServiceImpl implements OrderService {
	private OrderDAO daoOrder;
	private static Logger logger = Logger.getLogger(OrderServiceImpl.class
			.getName());

	public void deleteByID(int id) throws ServiceException  {
		logger.info("Start deleteByID.");
		Order order = new Order();
		order.setId(id);
		try {
			daoOrder.delete(order);
		} catch (DaoHibernateException e) {
			logger.error("Problem to daoOrder, method delete.");
			throw new ServiceException("deleteByID error", e);
		}
	}

	public List<Order> getOrdersByUserId(int id) throws ServiceException {
		logger.info("GetOrdersByUserId.");
		List<Order> orders = new ArrayList<Order>();
		try {
			orders = daoOrder.getOrdersByUserId(id);
		} catch (DaoHibernateException e) {
			logger.error("Problem to daoOrder, method getOrdersByUserId.");
			throw new ServiceException("getOrdersByUserId error", e);
		}
		return orders;
	}

	public List<Order> getOrders() throws ServiceException {
		logger.info("GetOrders.");
		List<Order> orders = new ArrayList<Order>();
		try {
			orders = daoOrder.getAll();
		} catch (DaoHibernateException e) {
			logger.error("Problem to daoOrder, method getAll.");
			throw new ServiceException("getOrders error", e);
		}
		return orders;
	}

	public void setDaoOrder(OrderDAO daoOrder) {
		logger.info("SetDaoOrder.");
		this.daoOrder = daoOrder;
		this.daoOrder.setSessionFactory(HibernateUtil.getInstance().getSessionFactory());
	}

	public List<Order> getOrdersByClotherId(int id) throws ServiceException {
		logger.info("getOrdersByClotherId.");
		List<Order> orders = new ArrayList<Order>();
		try {
			orders = daoOrder.getOrdersByClotherId(id);
		} catch (DaoHibernateException e) {
			logger.error("Problem to daoOrder, method getOrdersByClotherId.");
			throw new ServiceException("getOrdersByClotherId error", e);
		}
		return orders;
	}

}
