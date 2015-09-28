package by.academy.alekhno.web.command.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.academy.alekhno.web.bundle.Bundle;
import by.academy.alekhno.web.command.Command;

public class LogOutCommand implements Command {
	private static final Logger logger = Logger.getLogger(LogOutCommand.class.getName());

	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("Execute LogOutCommand");
		try {
			HttpSession session = req.getSession();
			session.removeAttribute(Bundle.getResource("session.key.user"));
//			session.removeAttribute(Bundle.getResource("session.key.user.role"));
			session.removeAttribute(Bundle.getResource("session.key.roles"));
			resp.sendRedirect(Bundle.getResource("redirect.to.start.page"));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			req.setAttribute(Bundle.getResource("request.key.error.message"),
					"Sorry. Problem with server.");
            return  Bundle.getResource("to.page.error");
		}
		return Bundle.getResource("key.redirect");
	}

}
