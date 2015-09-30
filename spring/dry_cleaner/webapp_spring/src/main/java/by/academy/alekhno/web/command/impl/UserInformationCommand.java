package by.academy.alekhno.web.command.impl;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.academy.alekhno.exception.ServiceException;
import by.academy.alekhno.external.impl.UserDAOImpl;
import by.academy.alekhno.service.impl.UserServiceImpl;
import by.academy.alekhno.service.interf.UserService;
import by.academy.alekhno.vo.Role;
import by.academy.alekhno.vo.User;
import by.academy.alekhno.web.auxiliary.UserRoles;
import by.academy.alekhno.web.bundle.Bundle;
import by.academy.alekhno.web.command.Command;
import by.academy.alekhno.web.validation.Validation;
import by.academy.alekhno.web.validation.ValidationImpl;

public class UserInformationCommand implements Command{
	private static final Logger logger = Logger.getLogger(UserInformationCommand.class.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("Execute UserInformationCommand");
		String login = req
				.getParameter(Bundle.getResource("user.column.login"));
		
		Validation validation = new ValidationImpl();
		
		if (validation.isLogin(login)) {
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
		
		HttpSession session = req.getSession();
		UserService userService = new UserServiceImpl();
		try {
			userService.setDaoUser(new UserDAOImpl());
			User user = userService.getUserByLogin(login);
			userService.setDaoUser(new UserDAOImpl());
			Set<Role> roles = userService.getRoleByUserId(user.getId());
			UserRoles userRoles = new UserRoles();
			userRoles.setUser(user);
			userRoles.setRoles(roles);
			session.setAttribute(Bundle.getResource("session.key.users.roles"), userRoles);
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			req.setAttribute(Bundle.getResource("request.key.error.message"),
					e.getMessage());
			return Bundle.getResource("to.page.error");
		} 
		return Bundle.getResource("to.user.information");
	}

}
