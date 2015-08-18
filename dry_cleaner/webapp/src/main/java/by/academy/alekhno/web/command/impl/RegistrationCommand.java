package by.academy.alekhno.web.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.academy.alekhno.dao.impl.RoleImpl;
import by.academy.alekhno.dao.impl.UserImpl;
import by.academy.alekhno.dao.impl.UserRoleImpl;
import by.academy.alekhno.exception.DaoException;
import by.academy.alekhno.exception.ServiceException;
import by.academy.alekhno.service.impl.UserServiceImpl;
import by.academy.alekhno.service.interf.UserService;
import by.academy.alekhno.vo.User;
import by.academy.alekhno.web.bundle.Bundle;
import by.academy.alekhno.web.command.Command;
import by.academy.alekhno.web.validation.Validation;
import by.academy.alekhno.web.validation.ValidationImpl;

public class RegistrationCommand implements Command {
	private static final Logger logger = Logger.getLogger(RegistrationCommand.class.getName());

	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("Execute RegistrationCommand");
		String login = req.getParameter(Bundle.getResource("user.column.login"));
		String password = req.getParameter(Bundle.getResource("user.column.password"));
		String firstName = req.getParameter(Bundle.getResource("user.column.first.name"));
		String secondName = req.getParameter(Bundle.getResource("user.column.second.name"));
		String telephone = req.getParameter(Bundle.getResource("user.column.telephone"));
		Validation validation = new ValidationImpl();
		if (validation.isLogin(login) 
				&& validation.isPassword(password)
				&& validation.isFirstName(firstName)
				&& validation.isSecondName(secondName)
				&& validation.isTelephone(telephone)) {
			// Correct data
			logger.info("Correct validation data.");
		} else {
			logger.error("Incorrect validation data.");
			req.setAttribute(Bundle.getResource("request.key.error.message"),
					Bundle.getResource("validation.error.message"));
			return Bundle.getResource("to.page.error");
		}

		User user = new User();
		user.setLogin(login);
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setSecondName(secondName);
		user.setTelephone(Long.parseLong(telephone));

		UserService userService = new UserServiceImpl();
		userService.setDaoUser(new UserImpl());
		userService.setDaoRole(new RoleImpl());
		userService.setDaoUserRole(new UserRoleImpl());

		try {
			userService.registration(user);
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

		return Bundle.getResource("to.start.page");
	}

}
