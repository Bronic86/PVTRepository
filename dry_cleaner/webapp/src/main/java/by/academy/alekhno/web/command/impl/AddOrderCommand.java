package by.academy.alekhno.web.command.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.academy.alekhno.dao.impl.OrderImpl;
import by.academy.alekhno.exception.DaoException;
import by.academy.alekhno.exception.ServiceException;
import by.academy.alekhno.service.impl.UserServiceImpl;
import by.academy.alekhno.service.interf.UserService;
import by.academy.alekhno.vo.Clother;
import by.academy.alekhno.vo.Order;
import by.academy.alekhno.vo.User;
import by.academy.alekhno.web.bundle.Bundle;
import by.academy.alekhno.web.command.Command;

public class AddOrderCommand implements Command {
	private static final Logger logger = Logger.getLogger(AddOrderCommand.class.getName());

	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String clother_id_param = req.getParameter("clother_id");
		User user = (User) req.getSession().getAttribute("user");
		String quantity_param = req.getParameter("quantity");
		//Validation
		
		int clother_id = Integer.parseInt(clother_id_param);
		Clother clother = new Clother();
		clother.setId(clother_id);
		int quantity = Integer.parseInt(quantity_param);
		if (quantity < 0){
			req.setAttribute(
					Bundle.getResource("request.key.error.message"),
					"Enter orders quantity.");
			return Bundle.getResource("to.page.error");
		}
		Date ordering_day = new Date();
		Order order = new Order();
		order.setQuantity(quantity);
		order.setOrdering_day(ordering_day);
		order.setUser(user);
		order.setClother(clother);
		
		
		UserService userService = new UserServiceImpl();
		try {
			userService.setDaoOrder(new OrderImpl());
			userService.addOrder(order);
			
			req.removeAttribute("clother_id");
			req.removeAttribute("quantity");
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			req.setAttribute(
					Bundle.getResource("request.key.error.message"),
					e.getMessage());
			return Bundle.getResource("to.page.error");
		} catch (DaoException e) {
			logger.error(e.getMessage(), e);
			req.setAttribute(
					Bundle.getResource("request.key.error.message"),
					e.getMessage());
			return Bundle.getResource("to.page.error");
		}
		return "controller?command=orders";
	}

}
