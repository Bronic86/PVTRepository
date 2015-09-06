package by.academy.alekhno.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import by.academy.alekhno.bundle.Bundle;
import by.academy.alekhno.dao.interf.AbstractDAO;
import by.academy.alekhno.dao.interf.CustomOrderDAO;
import by.academy.alekhno.database.pojo.Order;
import by.academy.alekhno.database.pojo.User;
import by.academy.alekhno.exception.DaoHibernateException;

public class OrderDAOImpl extends AbstractDAO<Order> implements CustomOrderDAO {
	private static final Logger logger = Logger.getLogger(OrderDAOImpl.class
			.getName());

	@Override
	protected Class getObjectClass() {
		return Order.class;
	}

	@Override
	protected Serializable getId(Order order) {
		return order.getId();
	}

	@Override
	public List<Order> getOrdersByUserId(int id) throws DaoHibernateException {
		logger.info("Start getOrdersByUserId.");
		logger.debug("Id - " + id);
		List<Order> orders = new ArrayList<Order>();
		try {
			super.startTransaction();
			String hql = Bundle.getQueryResource("order.get.all.by.user.id");
			Query query = super.getSession().createQuery(hql);
			query.setParameter("user_id", id);
			orders.addAll(query.list());
			logger.debug("Orders quantity - " + orders.size());
			super.endTransaction();
		} catch (HibernateException e) {
			logger.debug("getOrdersByUserId method error.");
			logger.debug(e.getStackTrace());
			super.getTransaction().rollback();
			throw new DaoHibernateException(e.getCause());
		} finally {
			closeSession();
		}
		return orders;
	}

	@Override
	public List<Order> getOrdersByClotherId(int id)
			throws DaoHibernateException {
		logger.info("Start getOrdersByClotherId.");
		logger.debug("Id - " + id);
		List<Order> orders = new ArrayList<Order>();
		try {
			super.startTransaction();
			String hql = Bundle.getQueryResource("order.get.all.by.clother.id");
			Query query = super.getSession().createQuery(hql);
			query.setParameter("clother_id", id);
			orders.addAll(query.list());
			logger.debug("Orders quantity - " + orders.size());
			super.endTransaction();
		} catch (HibernateException e) {
			logger.debug("getOrdersByClotherId method error.");
			logger.debug(e.getStackTrace());
			super.getTransaction().rollback();
			throw new DaoHibernateException(e.getCause());
		} finally {
			closeSession();
		}
		return orders;
	}

	@Override
	protected void setFields(Order order, Order updateOrder) {
		updateOrder.setFieldsByAnotherOrder(order);
	}

	@Override
	public List<Order> getOrdersByTypeId(int type_id)
			throws DaoHibernateException {
		logger.info("Start getOrdersByTypeId.");
		logger.debug("type_id - " + type_id);
		List<Order> orders = new ArrayList<Order>();
		try {
			super.startTransaction();
			String hql = Bundle.getQueryResource("order.get.all.by.type.id");
			Query query = super.getSession().createQuery(hql);
			query.setParameter("type_id", type_id);
			orders = query.list();
			logger.debug("Orders quantity - " + orders.size());
			super.endTransaction();
		} catch (HibernateException e) {
			logger.debug("getOrdersByTypeId method error.");
			logger.debug(e.getStackTrace());
			super.getTransaction().rollback();
			throw new DaoHibernateException(e.getCause());
		} finally {
			closeSession();
		}
		return orders;
	}

	@Override
	public List<Order> getOrdersByStateId(int state_id)
			throws DaoHibernateException {
		logger.info("Start getOrdersByStateId.");
		logger.debug("state_id - " + state_id);
		List<Order> orders = new ArrayList<Order>();
		try {
			super.startTransaction();
			String hql = Bundle.getQueryResource("order.get.all.by.state.id");
			Query query = super.getSession().createQuery(hql);
			query.setParameter("state_id", state_id);
			orders = query.list();
			logger.debug("Orders quantity - " + orders.size());
			super.endTransaction();
		} catch (HibernateException e) {
			logger.debug("getOrdersByStateId method error.");
			logger.debug(e.getStackTrace());
			super.getTransaction().rollback();
			throw new DaoHibernateException(e.getCause());
		} finally {
			closeSession();
		}
		return orders;
	}

}
