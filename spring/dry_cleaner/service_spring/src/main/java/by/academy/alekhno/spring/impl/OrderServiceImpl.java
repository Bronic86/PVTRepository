package by.academy.alekhno.spring.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.academy.alekhno.database.dao.interf.OrderPojoRepository;
import by.academy.alekhno.database.dao.interf.StatePojoRepository;
import by.academy.alekhno.spring.converter.ConverterPojoToVO;
import by.academy.alekhno.spring.converter.ConverterVOToPojo;
import by.academy.alekhno.spring.interf.OrderService;
import by.academy.alekhno.spring.pojo.OrderPojo;
import by.academy.alekhno.spring.pojo.StatePojo;
import by.academy.alekhno.vo.Order;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderPojoRepository orderRepository;

	@Autowired
	private StatePojoRepository stateRepository;

	@Override
	public void deleteByID(int id) {
		orderRepository.delete(id);
	}

	@Override
	public void addOrder(Order order) {
		OrderPojo orderPojo = ConverterVOToPojo.getOrder(order);
		StatePojo statePojo = stateRepository.getByState("create");
		orderPojo.setState(statePojo);
		orderRepository.saveAndFlush(orderPojo);
	}

	@Override
	public void updateOrder(Order order) {
		OrderPojo orderPojo = ConverterVOToPojo.getOrder(order);
		OrderPojo persistOrder = orderRepository.findOne(orderPojo.getId());
		persistOrder.setUser(orderPojo.getUser());
		persistOrder.setClother(orderPojo.getClother());
		persistOrder.setOrdering_day(orderPojo.getOrdering_day());
		persistOrder.setQuantity(orderPojo.getQuantity());
		persistOrder.setState(orderPojo.getState());
	}

	@Override
	public List<Order> getOrdersByUserId(int user_id) {
		List<OrderPojo> ordersPojo = orderRepository.getOrdersByUserId(user_id);
		List<Order> orders = new ArrayList<Order>();
		for (OrderPojo orderPojo : ordersPojo) {
			orders.add(ConverterPojoToVO.getOrder(orderPojo));
		}
		return orders;
	}

	@Override
	public List<Order> getOrders() {
		List<OrderPojo> ordersPojo = orderRepository.findAll();
		List<Order> orders = new ArrayList<Order>();
		for (OrderPojo orderPojo : ordersPojo) {
			orders.add(ConverterPojoToVO.getOrder(orderPojo));
		}
		return orders;
	}

	@Override
	public List<Order> getOrdersByClotherId(int clother_id) {
		List<OrderPojo> ordersPojo = orderRepository.getOrdersByClotherId(clother_id);
		List<Order> orders = new ArrayList<Order>();
		for (OrderPojo orderPojo : ordersPojo) {
			orders.add(ConverterPojoToVO.getOrder(orderPojo));
		}
		return orders;
	}

	@Override
	public List<Order> getOrdersByUserName(String login) {
		List<OrderPojo> ordersPojo = orderRepository.getOrdersByUserLogin(login);
		List<Order> orders = new ArrayList<Order>();
		for (OrderPojo orderPojo : ordersPojo) {
			orders.add(ConverterPojoToVO.getOrder(orderPojo));
		}
		return orders;
	}

}
