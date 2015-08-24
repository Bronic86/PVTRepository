package by.academy.alekhno.web.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.academy.alekhno.dao.impl.ClotherImpl;
import by.academy.alekhno.dao.impl.OrderImpl;
import by.academy.alekhno.exception.DaoException;
import by.academy.alekhno.exception.ServiceException;
import by.academy.alekhno.service.impl.ClotherServiceImpl;
import by.academy.alekhno.service.impl.OrderServiceImpl;
import by.academy.alekhno.service.interf.ClotherService;
import by.academy.alekhno.service.interf.OrderService;
import by.academy.alekhno.vo.Clother;
import by.academy.alekhno.vo.Order;
import by.academy.alekhno.vo.User;
import by.academy.alekhno.web.bundle.Bundle;
import by.academy.alekhno.web.command.Command;
import by.academy.alekhno.web.validation.Validation;
import by.academy.alekhno.web.validation.ValidationImpl;

public class ChangeOrderCommand implements Command {
	private static final Logger logger = Logger
			.getLogger(ChangeOrderCommand.class.getName());

	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("Execute ChangeOrderCommand");
		String clother_id_param = req.getParameter("clother_id");
		Validation validation = new ValidationImpl();
		if (clother_id_param == null) {
			req.setAttribute(
					Bundle.getResource("request.key.error.message"),
					"There are no clother_id param.");
			return Bundle.getResource("to.page.error");
		} else {
			if (validation.isNumber(clother_id_param)) {
				int clother_id = Integer.parseInt(clother_id_param);
				req.removeAttribute("clother_id");
				ClotherService clotherService = new ClotherServiceImpl();
				try {
					clotherService.setDaoClother(new ClotherImpl());
					Clother clother = clotherService.getClotherById(clother_id);
					req.setAttribute("add_clother", clother);
				} catch (ServiceException e) {
					logger.error(e.getMessage(), e);
					req.setAttribute(
							Bundle.getResource("request.key.error.message"),
							e.getMessage());
					return Bundle.getResource("to.page.error");
				}
			} else {
				logger.error("Incorrect validation data.");
				req.setAttribute(
						Bundle.getResource("request.key.error.message"),
						Bundle.getResource("validation.error.message"));
				return Bundle.getResource("to.page.error");
			}
		}

		return "WEB-INF/pages/orders.jsp";
	}

}
