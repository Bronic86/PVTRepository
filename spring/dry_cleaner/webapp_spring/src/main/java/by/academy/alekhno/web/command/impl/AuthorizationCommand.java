package by.academy.alekhno.web.command.impl;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.academy.alekhno.exception.ServiceException;
import by.academy.alekhno.external.impl.RoleDAOImpl;
import by.academy.alekhno.external.impl.UserDAOImpl;
import by.academy.alekhno.service.impl.UserServiceImpl;
import by.academy.alekhno.service.interf.UserService;
import by.academy.alekhno.vo.Role;
import by.academy.alekhno.vo.User;
import by.academy.alekhno.web.bundle.Bundle;
import by.academy.alekhno.web.command.Command;
import by.academy.alekhno.web.validation.Validation;
import by.academy.alekhno.web.validation.ValidationImpl;

public class AuthorizationCommand implements Command {
	private static final Logger logger = Logger.getLogger(AuthorizationCommand.class.getName());

	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("Execute AuthorizationCommand");
		String login = req
				.getParameter(Bundle.getResource("user.column.login"));
		String password = req.getParameter(Bundle
				.getResource("user.column.password"));
		Validation validation = new ValidationImpl();
		
		if (validation.isLogin(login) && validation.isPassword(password)) {
			// Correct data.
			logger.info("Correct validation data.");
		} else {
			logger.error("Incorrect validation data.");
			req.setAttribute(Bundle.getResource("request.key.error.message"),
					Bundle.getResource("validation.error.message"));
			req.setAttribute(Bundle.getResource("request.key.return.page"),
					req.getRequestURI());
			return Bundle.getResource("to.page.error");
		}

		UserService userService = new UserServiceImpl();
		try {
			userService.setDaoUser(new UserDAOImpl());
			User user = userService.authorization(login, password);
			HttpSession session = req.getSession();
			userService.setDaoUser(new UserDAOImpl());
			Set<Role> rolesList = userService.getRoleByUserId(user.getId());
			session.setAttribute(Bundle.getResource("session.key.user"), user);
			Set<String> roles = new HashSet<String>();
			for (Role role : rolesList){
				roles.add(role.getName());
			}
			session.setAttribute(Bundle.getResource("session.key.roles"),
					roles);
			return Bundle.getResource("to.start.page");
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			req.setAttribute(Bundle.getResource("request.key.error.message"),
					e.getMessage());
			return Bundle.getResource("to.page.error");
		} 
	}

}
