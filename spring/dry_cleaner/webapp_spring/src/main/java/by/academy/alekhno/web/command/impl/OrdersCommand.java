package by.academy.alekhno.web.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.academy.alekhno.exception.ServiceException;
import by.academy.alekhno.external.impl.OrderDAOImpl;
import by.academy.alekhno.service.impl.OrderServiceImpl;
import by.academy.alekhno.service.interf.OrderService;
import by.academy.alekhno.vo.Order;
import by.academy.alekhno.vo.User;
import by.academy.alekhno.web.bundle.Bundle;
import by.academy.alekhno.web.command.Command;

public class OrdersCommand implements Command {
	private static final Logger logger = Logger
			.getLogger(OrdersCommand.class.getName());

	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		User user_param = (User) req.getSession().getAttribute("user");
		int user_id = user_param.getId();
		List<Order> orders = null;
		OrderService orderService = new OrderServiceImpl();
		try {
			orderService.setDaoOrder(new OrderDAOImpl());
			orders = orderService.getOrdersByUserId(user_id);
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			req.setAttribute(
					Bundle.getResource("request.key.error.message"),
					e.getMessage());
			return Bundle.getResource("to.page.error");
		} 
		if (!orders.isEmpty()) {
			req.setAttribute("user_orders", orders);
		}
		return "WEB-INF/pages/orders.jsp";
	}

}
