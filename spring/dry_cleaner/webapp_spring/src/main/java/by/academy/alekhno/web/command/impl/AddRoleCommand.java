package by.academy.alekhno.web.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.academy.alekhno.exception.ServiceException;
import by.academy.alekhno.external.impl.UserDAOImpl;
import by.academy.alekhno.service.impl.UserServiceImpl;
import by.academy.alekhno.service.interf.UserService;
import by.academy.alekhno.vo.Role;
import by.academy.alekhno.vo.User;
import by.academy.alekhno.web.bundle.Bundle;
import by.academy.alekhno.web.command.Command;
import by.academy.alekhno.web.validation.Validation;
import by.academy.alekhno.web.validation.ValidationImpl;

public class AddRoleCommand implements Command {
	
	private static final Logger logger = Logger.getLogger(AddRoleCommand.class.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("Execute AddRoleCommand");
		
		String user_id = req
				.getParameter(Bundle.getResource("request.key.user.id"));
		String role_id = req.getParameter(Bundle
				.getResource("request.key.role.id"));
		
		Validation validation = new ValidationImpl();
		if (validation.isNumber(user_id) && validation.isNumber(role_id)){
			//correct
		} else {
			logger.error("Incorrect validation data.");
			req.setAttribute(Bundle.getResource("request.key.error.message"),
					Bundle.getResource("validation.error.message"));
			req.setAttribute(Bundle.getResource("request.key.return.page"),
					req.getRequestURI());
			return Bundle.getResource("to.page.error");
		}
		
		int userId = Integer.parseInt(user_id);
		int roleId = Integer.parseInt(role_id);
		User user = new User();
		user.setId(userId);
		Role role = new Role();
		role.setId(roleId);
		
		UserService userService = new UserServiceImpl();
		
		try {
			userService.setDaoUser(new UserDAOImpl());
			userService.addRoleToUser(user, role);
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			req.setAttribute(Bundle.getResource("request.key.error.message"),
					e.getMessage());
			return Bundle.getResource("to.page.error");
		}
		
		return Bundle.getResource("to.page.error");
	}

}
