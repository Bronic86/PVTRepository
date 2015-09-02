package by.academy.alekhno.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import by.academy.alekhno.bundle.Bundle;
import by.academy.alekhno.dao.interf.AbstractDAO;
import by.academy.alekhno.dao.interf.CustomOrderDAO;
import by.academy.alekhno.database.pojo.Order;
import by.academy.alekhno.database.pojo.User;

public class OrderDAOImpl extends AbstractDAO<Order> implements CustomOrderDAO {
	private static final Logger logger = Logger.getLogger(OrderDAOImpl.class.getName());

	@Override
	protected Class getObjectClass() {
		return Order.class;
	}

	@Override
	protected Serializable getId(Order order) {
		return order.getId();
	}

	@Override
	public List<Order> getOrdersByUserId(int id) {
		logger.info("Start getOrdersByUserId.");
		logger.debug("Id - " + id);
		super.startTransaction();
		String hql = Bundle.getQueryResource("order.get.all.by.user.id");
		Query query = super.getSession().createQuery(hql);
		query.setParameter("user_id", id);
		List<Order> orders = query.list();
		logger.debug("Orders quantity - " + orders.size());
		super.endTransaction();
		return orders;
	}


	@Override
	public List<Order> getOrdersByClotherId(int id) {
		logger.info("Start getOrdersByClotherId.");
		logger.debug("Id - " + id);
		super.startTransaction();
		String hql = Bundle.getQueryResource("order.get.all.by.clother.id");
		Query query = super.getSession().createQuery(hql);
		query.setParameter("clother_id", id);
		List<Order> orders = query.list();
		logger.debug("Orders quantity - " + orders.size());
		super.endTransaction();
		return orders;
	}

	@Override
	protected void setFields(Order order, Order updateOrder) {
		updateOrder.setFieldsByAnotherOrder(order);
	}
	
}
