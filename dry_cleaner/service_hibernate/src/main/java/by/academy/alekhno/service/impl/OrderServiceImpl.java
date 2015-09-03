package by.academy.alekhno.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.academy.alekhno.database.util.HibernateUtil;
import by.academy.alekhno.external.OrderDAO;
import by.academy.alekhno.service.interf.OrderService;
import by.academy.alekhno.vo.Order;

public class OrderServiceImpl implements OrderService {
	private OrderDAO daoOrder;
	private static Logger logger = Logger.getLogger(OrderServiceImpl.class
			.getName());

	public OrderServiceImpl() {
	}

	public void deleteByID(int id)  {
		logger.info("Start deleteByID.");
		Order order = new Order();
		order.setId(id);
		daoOrder.delete(order);
	}

	public List<Order> getOrdersByUserId(int id) {
		logger.info("GetOrdersByUserId.");
		List<Order> orders = new ArrayList<Order>();
		orders = daoOrder.getOrdersByUserId(id);
		return orders;
	}

	public List<Order> getOrders() {
		logger.info("GetOrders.");
		List<Order> orders = new ArrayList<Order>();
		orders = daoOrder.getAll();
		return orders;
	}

//	public CustomOrderDao getDaoOrder() throws ServiceException {
//		logger.info("GetDaoOrder.");
//		if (daoOrder == null) {
//			logger.error("daoOrder didn't set.");
//			throw new ServiceException("Didn't set daoOrder.");
//		}
//		return daoOrder;
//	}

	public void setDaoOrder(OrderDAO daoOrder) {
		logger.info("SetDaoOrder.");
		this.daoOrder = daoOrder;
		this.daoOrder.setSession(HibernateUtil.getInstance().getSession());
	}

	public List<Order> getOrdersByClotherId(int id) {
		logger.info("getOrdersByClotherId.");
		List<Order> orders = new ArrayList<Order>();
		orders = daoOrder.getOrdersByClotherId(id);
		return orders;
	}

}
