package by.academy.alekhno.external.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import by.academy.alekhno.dao.interf.CustomOrderDAO;
import by.academy.alekhno.database.converter.ConverterPojoToVO;
import by.academy.alekhno.database.converter.ConverterVOToPojo;
import by.academy.alekhno.database.pojo.OrderPojo;
import by.academy.alekhno.exception.DaoHibernateException;
import by.academy.alekhno.external.OrderDAO;
import by.academy.alekhno.vo.Order;

public class OrderDAOImpl implements OrderDAO {
	private static final Logger logger = Logger
			.getLogger(OrderDAOImpl.class.getName());
	
	private CustomOrderDAO dao;
	private OrderPojo orderP;
	private List<OrderPojo> ordersP = new ArrayList<OrderPojo>();
	
	public OrderDAOImpl() {
		dao = new by.academy.alekhno.dao.impl.CustomOrderDAOImpl();
	}
	

	@Override
	public void update(Order order) throws DaoHibernateException {
		logger.info("Start external update.");
		orderP = ConverterVOToPojo.getOrder(order);
		dao.update(orderP);
	}

	@Override
	public List<Order> getAll() throws DaoHibernateException {
		logger.info("Start external getAll.");
		List<Order> ordersVO = new ArrayList<>();
		ordersP.addAll(dao.getAll());
		for (OrderPojo orderP : ordersP) {
			ordersVO.add(ConverterPojoToVO.getOrder(orderP));
		}
		return ordersVO;
	}

	@Override
	public void delete(Order order) throws DaoHibernateException {
		logger.info("Start external delete.");
		orderP = ConverterVOToPojo.getOrder(order);
		dao.delete(orderP);
	}

	@Override
	public int add(Order order) throws DaoHibernateException {
		logger.info("Start external add.");
		orderP = ConverterVOToPojo.getOrder(order);
		int id = dao.add(orderP);
		return id;
	}

	@Override
	public Order getByID(Order order) throws DaoHibernateException {
		logger.info("Start external getByID.");
		orderP = ConverterVOToPojo.getOrder(order);
		orderP = dao.getByID(orderP);
		order = ConverterPojoToVO.getOrder(orderP);
		return order;
	}

	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		logger.info("Start external getAll.");
		dao.setSessionFactory(sessionFactory);
	}

	@Override
	public List<Order> getOrdersByUserId(int user_id) throws DaoHibernateException {
		logger.info("Start external getOrdersByUserId.");
		List<Order> ordersVO = new ArrayList<>();
		ordersP.addAll(dao.getOrdersByUserId(user_id));
		for (OrderPojo orderP : ordersP) {
			ordersVO.add(ConverterPojoToVO.getOrder(orderP));
		}
		return ordersVO;
	}

	@Override
	public List<Order> getOrdersByClotherId(int clother_id) throws DaoHibernateException {
		logger.info("Start external getOrdersByClotherId.");
		List<Order> ordersVO = new ArrayList<>();
		ordersP.addAll(dao.getOrdersByClotherId(clother_id));
		for (OrderPojo orderP : ordersP) {
			ordersVO.add(ConverterPojoToVO.getOrder(orderP));
		}
		return ordersVO;
	}

	@Override
	public List<Order> getOrdersByTypeId(int type_id) throws DaoHibernateException {
		logger.info("Start external getOrdersByTypeId.");
		List<Order> ordersVO = new ArrayList<>();
		ordersP.addAll(dao.getOrdersByTypeId(type_id));
		for (OrderPojo orderP : ordersP) {
			ordersVO.add(ConverterPojoToVO.getOrder(orderP));
		}
		return ordersVO;
	}

	@Override
	public List<Order> getOrdersByStateId(int state_id) throws DaoHibernateException {
		logger.info("Start external getOrdersByStateId.");
		List<Order> ordersVO = new ArrayList<>();
		ordersP.addAll(dao.getOrdersByStateId(state_id));
		for (OrderPojo orderP : ordersP) {
			ordersVO.add(ConverterPojoToVO.getOrder(orderP));
		}
		return ordersVO;
	}

}
