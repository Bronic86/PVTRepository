package by.academy.alekhno.dao.impl;

import java.io.Serializable;

import org.apache.log4j.Logger;

import by.academy.alekhno.dao.interf.AbstractDAO;
import by.academy.alekhno.dao.interf.CustomOrderDAO;
import by.academy.alekhno.database.pojo.Order;

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
	
}
