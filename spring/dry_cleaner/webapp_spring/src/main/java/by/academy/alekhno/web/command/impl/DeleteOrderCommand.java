package by.academy.alekhno.web.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.academy.alekhno.exception.ServiceException;
import by.academy.alekhno.external.impl.OrderDAOImpl;
import by.academy.alekhno.service.impl.UserServiceImpl;
import by.academy.alekhno.service.interf.UserService;
import by.academy.alekhno.web.command.Command;

public class DeleteOrderCommand implements Command {
	private static final Logger logger = Logger.getLogger(DeleteOrderCommand.class.getName());

	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("Execute DeleteOrderCommand");
		String order_id_param = req.getParameter("order_id");
		//Validation
		
		int order_id = Integer.parseInt(order_id_param);
		
		UserService userService = new UserServiceImpl();
		try {
			userService.setDaoOrder(new OrderDAOImpl());
			userService.deleteOrderById(order_id);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "controller?command=orders";
	}

}
