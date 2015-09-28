package by.academy.alekhno.spring.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import by.academy.alekhno.bundle.Bundle;
import by.academy.alekhno.spring.dao.interf.AbstractDAO;
import by.academy.alekhno.spring.dao.interf.CustomOrderDAO;
import by.academy.alekhno.spring.pojo.OrderPojo;

//@Repository
public class CustomOrderDAOImpl extends AbstractDAO<OrderPojo, Integer> implements CustomOrderDAO {
	// private static final Logger logger =
	// Logger.getLogger(CustomOrderDAOImpl.class.getName());

	/**
	 * Get Orders by User field "id"
	 */
	@Override
	public List<OrderPojo> getOrdersByUserId(int user_id) {
		List<OrderPojo> orders = new ArrayList<OrderPojo>();
		String hql = Bundle.getQueryResource("order.get.all.by.user.id");
		Query query = super.getSession().createQuery(hql);
		query.setParameter("user_id", user_id);
		orders.addAll(query.list());
		return orders;
	}

	/**
	 * Get Orders by Clother field "id"
	 */
	@Override
	public List<OrderPojo> getOrdersByClotherId(int clother_id) {
		List<OrderPojo> orders = new ArrayList<OrderPojo>();
		String hql = Bundle.getQueryResource("order.get.all.by.clother.id");
		Query query = super.getSession().createQuery(hql);
		query.setParameter("clother_id", clother_id);
		orders.addAll(query.list());
		return orders;
	}

	/**
	 * Get Orders by Type field "id"
	 */
	@Override
	public List<OrderPojo> getOrdersByTypeId(int type_id) {
		List<OrderPojo> orders = new ArrayList<OrderPojo>();
		String hql = Bundle.getQueryResource("order.get.all.by.type.id");
		Query query = super.getSession().createQuery(hql);
		query.setParameter("type_id", type_id);
		orders.addAll(query.list());
		return orders;
	}

	/**
	 * Get Orders by State field "id"
	 */
	@Override
	public List<OrderPojo> getOrdersByStateId(int state_id) {
		List<OrderPojo> orders = new ArrayList<OrderPojo>();
		String hql = Bundle.getQueryResource("order.get.all.by.state.id");
		Query query = super.getSession().createQuery(hql);
		query.setParameter("state_id", state_id);
		orders.addAll(query.list());
		return orders;
	}

	@Override
	protected Class getObjectClass() {
		return OrderPojo.class;
	}

	@Override
	protected void setFields(OrderPojo newOrder, OrderPojo persistOrder) {
		persistOrder.setUser(newOrder.getUser());
		persistOrder.setClother(newOrder.getClother());
		persistOrder.setOrdering_day(newOrder.getOrdering_day());
		persistOrder.setQuantity(newOrder.getQuantity());
		persistOrder.setState(newOrder.getState());
	}

	@Override
	protected Integer getId(OrderPojo order) {
		return order.getId();
	}

}
