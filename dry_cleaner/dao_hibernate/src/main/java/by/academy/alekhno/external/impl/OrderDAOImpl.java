package by.academy.alekhno.external.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import by.academy.alekhno.dao.interf.CustomOrderDAO;
import by.academy.alekhno.database.converter.ConverterPojoToVO;
import by.academy.alekhno.database.converter.ConverterVOToPojo;
import by.academy.alekhno.external.OrderDAO;
import by.academy.alekhno.vo.Order;

public class OrderDAOImpl implements OrderDAO {
	
	private CustomOrderDAO dao = new by.academy.alekhno.dao.impl.OrderDAOImpl();
	private by.academy.alekhno.database.pojo.Order orderP;
	private List<by.academy.alekhno.database.pojo.Order> ordersP;
	

	@Override
	public void update(Order order) {
		orderP = ConverterVOToPojo.getOrder(order);
		dao.update(orderP);
	}

	@Override
	public List<Order> getAll() {
		List<Order> ordersVO = new ArrayList<>();
		ordersP = dao.getAll();
		for (by.academy.alekhno.database.pojo.Order orderP : ordersP) {
			ordersVO.add(ConverterPojoToVO.getOrder(orderP));
		}
		return ordersVO;
	}

	@Override
	public void delete(Order order) {
		orderP = ConverterVOToPojo.getOrder(order);
		dao.delete(orderP);
	}

	@Override
	public int add(Order order) {
		orderP = ConverterVOToPojo.getOrder(order);
		int id = dao.add(orderP);
		return id;
	}

	@Override
	public Order getByID(Order order) {
		orderP = ConverterVOToPojo.getOrder(order);
		orderP = dao.getByID(orderP);
		order = ConverterPojoToVO.getOrder(orderP);
		return order;
	}

	@Override
	public void setSession(Session session) {
		dao.setSession(session);
	}

	@Override
	public List<Order> getOrdersByUserId(int user_id) {
		List<Order> ordersVO = new ArrayList<>();
		ordersP = dao.getOrdersByUserId(user_id);
		for (by.academy.alekhno.database.pojo.Order orderP : ordersP) {
			ordersVO.add(ConverterPojoToVO.getOrder(orderP));
		}
		return ordersVO;
	}

	@Override
	public List<Order> getOrdersByClotherId(int clother_id) {
		List<Order> ordersVO = new ArrayList<>();
		ordersP = dao.getOrdersByClotherId(clother_id);
		for (by.academy.alekhno.database.pojo.Order orderP : ordersP) {
			ordersVO.add(ConverterPojoToVO.getOrder(orderP));
		}
		return ordersVO;
	}

}
