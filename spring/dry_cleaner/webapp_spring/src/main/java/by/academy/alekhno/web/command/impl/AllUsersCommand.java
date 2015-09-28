package by.academy.alekhno.web.command.impl;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

public class AllUsersCommand implements Command {
	private static final Logger logger = Logger
			.getLogger(UserInformationCommand.class.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("Execute UserInformationCommand");

		UserService userService = new UserServiceImpl();

		try {
			userService.setDaoUser(new UserDAOImpl());
			Set<User> users = userService.getAll();
			Set<UserRoles> usersRoles = new HashSet<>();
			for (User user : users) {
				UserRoles userRoles = new UserRoles();
				userRoles.setUser(user);
				userService.setDaoUser(new UserDAOImpl());
				Set<Role> roles = userService.getRoleByUserId(user.getId());
				userRoles.setRoles(roles);

			}
			req.setAttribute(Bundle.getResource("session.key.users.roles"),
					usersRoles);
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			req.setAttribute(Bundle.getResource("request.key.error.message"),
					e.getMessage());
		}
		//Not work. Error to usersRoles
		return Bundle.getResource("to.users.list");
	}

}
