package by.academy.alekhno.web.filter;

import java.io.IOException;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.academy.alekhno.web.bundle.Bundle;
import by.academy.alekhno.web.command.CommandType;

public class UserFilter implements Filter {
	private static final Logger logger = Logger.getLogger(UserFilter.class
			.getName());

	private FilterConfig config;
	private HttpServletRequest req;
	HttpServletResponse resp;

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		logger.info("Start doFilter.");

		req = (HttpServletRequest) request;
		resp = (HttpServletResponse) response;

		String commandType = req
				.getParameter(Bundle.getResource("key.command"));
		if (commandType == null) {
			resp.sendRedirect(Bundle.getResource("redirect.to.error.page"));
		} else {
			commandType = commandType.toUpperCase();
			logger.info("Command - " + commandType);
			Set<String> roles = (Set<String>) req.getSession().getAttribute(
					Bundle.getResource("session.key.roles"));

			switch (CommandType.valueOf(commandType)) {
			case TO_AUTHORIZATION:
				chain.doFilter(req, resp);
				break;
			case AUTHORIZATION:
				chain.doFilter(req, resp);
				break;
			case TO_REGISTRATION:
				chain.doFilter(req, resp);
				break;
			case REGISTRATION:
				chain.doFilter(req, resp);
				break;
			case PRICE_LIST:
				if (roleNameExist("user", roles)) {
				}
				chain.doFilter(req, resp);
				break;
			case ORDERS:
				if (roleNameExist("user", roles)) {
					chain.doFilter(req, resp);
				} else {
					resp.sendRedirect(Bundle
							.getResource("redirect.to.start.page"));
				}
				break;
			case LOG_OUT:
				chain.doFilter(req, resp);
				break;
			case CHANGE_ORDER:
				if (roleNameExist("user", roles)) {
					chain.doFilter(req, resp);
				} else {
					resp.sendRedirect(Bundle
							.getResource("redirect.to.start.page"));
				}
				break;
			case ADD_ORDER:
				if (roleNameExist("user", roles)) {
					chain.doFilter(req, resp);
				} else {
					resp.sendRedirect(Bundle
							.getResource("redirect.to.start.page"));
				}
				break;
			case DELETE_ORDER:
				if (roleNameExist("user", roles)) {
					chain.doFilter(req, resp);
				} else {
					resp.sendRedirect(Bundle
							.getResource("redirect.to.start.page"));
				}
				break;
			case ADD_ROLE:
				if (roleNameExist("admin", roles)) {
					chain.doFilter(req, resp);
				} else {
					resp.sendRedirect(Bundle
							.getResource("redirect.to.start.page"));
				}
				break;
			case USER_INFORMATION:
				if (roleNameExist("user", roles) || roleNameExist("admin", roles)) {
					chain.doFilter(req, resp);
				} else {
					resp.sendRedirect(Bundle
							.getResource("redirect.to.start.page"));
				}
				break;
			case ALL_USERS:
				if (roleNameExist("admin", roles)) {
					chain.doFilter(req, resp);
				} else {
					resp.sendRedirect(Bundle
							.getResource("redirect.to.start.page"));
				}
				break;
			default:
				resp.sendRedirect(Bundle.getResource("redirect.to.start.page"));
				break;
			}
		}
	}

	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

	private boolean roleNameExist(String name, Set<String> names) {
		if (names == null) {
			return false;
		}
		return names.contains(name);
	}


}
