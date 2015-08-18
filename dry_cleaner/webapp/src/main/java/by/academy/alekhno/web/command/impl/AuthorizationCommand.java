package by.academy.alekhno.web.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.academy.alekhno.dao.impl.UserImpl;
import by.academy.alekhno.dao.impl.UserRoleImpl;
import by.academy.alekhno.exception.DaoException;
import by.academy.alekhno.exception.ServiceException;
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
			return Bundle.getResource("to.page.error");
		}

		UserService userService = new UserServiceImpl();
		userService.setDaoUser(new UserImpl());
		userService.setDaoUserRole(new UserRoleImpl());
		try {
			User user = userService.authorization(login, password);
			user.setPassword(null);
			HttpSession session = req.getSession();
			List<Role> roles = userService.getRoleByUserId(user.getId());
			session.setAttribute(Bundle.getResource("session.key.user"), user);
			session.setAttribute(Bundle.getResource("session.key.user.role"),
					getMaxRole(roles));
			return Bundle.getResource("to.start.page");
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			req.setAttribute(Bundle.getResource("request.key.error.message"),
					e.getMessage());
			return Bundle.getResource("to.page.error");
		} catch (DaoException e) {
			logger.error(e.getMessage(), e);
			req.setAttribute(Bundle.getResource("request.key.error.message"),
					e.getMessage());
			return Bundle.getResource("to.page.error");
		}
	}

	private Role getMaxRole(List<Role> roles) {
		// TODO Auto-generated method stub
		for (Role role : roles) {
			if (role.getId() == 2) {
				return role;
			}
		}
		for (Role role : roles) {
			if (role.getId() == 1) {
				return role;
			}
		}
		return null;
	}
}
