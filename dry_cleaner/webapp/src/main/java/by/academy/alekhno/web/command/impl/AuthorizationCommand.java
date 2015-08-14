package by.academy.alekhno.web.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

public class AuthorizationCommand implements Command {

	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		String login = req.getParameter(Bundle.getResource("user.column.login"));
		String password = req.getParameter(Bundle.getResource("user.column.password"));
		
//		String correctLogin = Validation.correctLogin(login);
//		String correctPassword = Validation.correctPassword(password);
//		if (correctLogin.equals(KEY_CORRECT)) {
//			if (correctPassword.equals(KEY_CORRECT)) {
//				// Correct data.
//			} else {
//				req.setAttribute(KEY_ERROR_MESSAGE, correctPassword);
//				return Bundle.getPage("page.error.path");
//			}
//		} else {
//			req.setAttribute(KEY_ERROR_MESSAGE, correctLogin);
//			return Bundle.getPage("page.error.path");
//		}

		UserService userService = new UserServiceImpl();
		userService.setDaoUser(new UserImpl());
		userService.setDaoUserRole(new UserRoleImpl());
		try{
			User user = userService.authorization(login, password);
//			if (user != null) {
				List<Role> roles = userService.getRoleByUserId(user.getId());
				req.setAttribute("user", user);
				session.setAttribute(Bundle.getResource("session.key.user"), user);
				session.setAttribute(Bundle.getResource("session.key.roles"), roles);
				return Bundle.getResource("to.controller.command.navigation");
//			} else {
//				req.setAttribute(Bundle.getResource("session.key.error.message"),
//						"Login or password is incorrect.");
//				return Bundle.getResource("to.page.error");
//			}
		} catch(ServiceException e){
			req.setAttribute(Bundle.getResource("session.key.error.message"),
					e.getMessage());
			return Bundle.getResource("to.page.error");
		} catch (DaoException e) {
			req.setAttribute(Bundle.getResource("session.key.error.message"),
					e.getMessage());
			return Bundle.getResource("to.page.error");
		}
	}
}
