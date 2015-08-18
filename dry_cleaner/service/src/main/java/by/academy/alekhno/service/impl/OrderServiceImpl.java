package by.academy.alekhno.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

import by.academy.alekhno.dao.interf.CustomOrderDao;
import by.academy.alekhno.exception.DaoException;
import by.academy.alekhno.exception.ServiceException;
import by.academy.alekhno.service.interf.OrderService;
import by.academy.alekhno.vo.Order;

public class OrderServiceImpl implements OrderService {
	private CustomOrderDao daoOrder;
	private static Logger logger = Logger.getLogger(OrderServiceImpl.class.getName());
	private Lock lock = new ReentrantLock();

	public OrderServiceImpl() {
	}

	public int add(Order order) throws DaoException {
		logger.info("Start add.");
		List<Integer> idList = new ArrayList<Integer>();
		try {
			lock.lock();
		daoOrder.add(order);
		idList = daoOrder.getIdByFields(order);
		} finally {
			lock.unlock();
		}
		logger.info("End add.");
		return idList.get(0);
	}

	public void deleteByID(int id) throws DaoException {
		logger.info("Start deleteByID.");
		Order order = new Order();
		order.setId(id);
		try {
			lock.lock();
		daoOrder.delete(order);
		} finally {
			lock.unlock();
		}
		logger.info("End deleteByID.");
	}

	public List<Order> getOrdersByUserId(int id) throws DaoException {
		logger.info("GetOrdersByUserId.");
		List<Order> orders = new ArrayList<Order>();
		try {
			lock.lock();
			orders = daoOrder.getOrdersByUserId(id);
		} finally {
			lock.unlock();			
		}
		return orders;
	}

	public List<Order> getOrders() throws DaoException {
		logger.info("GetOrders.");
		List<Order> orders = new ArrayList<Order>();
		try {
			lock.lock();
			orders = daoOrder.getAll();
		} finally {
			lock.unlock();			
		}
		return orders;
	}

	public CustomOrderDao getDaoOrder() throws ServiceException {
		logger.info("GetDaoOrder.");
		if (daoOrder == null) {
			logger.error("daoOrder didn't set.");
			throw new ServiceException("Didn't set daoOrder.");
		}
		return daoOrder;
	}

	public void setDaoOrder(CustomOrderDao daoOrder) {
		logger.info("SetDaoOrder.");
		this.daoOrder = daoOrder;
	}

	public List<Order> getOrdersByClotherId(int id) throws DaoException {
		logger.info("getOrdersByClotherId.");
		List<Order> orders = new ArrayList<Order>();
		try {
			lock.lock();
			orders = daoOrder.getOrdersByClotherId(id);
		} finally {
			lock.unlock();			
		}
		return orders;
	}

	
}
