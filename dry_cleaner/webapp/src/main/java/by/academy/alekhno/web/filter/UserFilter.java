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

import by.academy.alekhno.vo.Role;
import by.academy.alekhno.web.bundle.Bundle;
import by.academy.alekhno.web.command.CommandType;

public class UserFilter implements Filter {
	private FilterConfig config;
	private HttpServletRequest req;
	HttpServletResponse resp;

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		req = (HttpServletRequest) request;
		resp = (HttpServletResponse) response;
		
		String commandType = req.getParameter(Bundle.getResource("key.command")).toUpperCase();
		Set<Role> roles = (Set<Role>) req.getSession().getAttribute(
				Bundle.getResource("session.key.user.roles"));
		
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
			//Add role user or guest
			chain.doFilter(req, resp);
			break;
		case ORDERS:
			if (roleNameExist("user", roles)){
				setUserRole("user");
				chain.doFilter(req, resp);
			} else {
				resp.sendRedirect(Bundle.getResource("redirect.to.start.page"));
			}
			break;
		case LOG_OUT:
			chain.doFilter(req, resp);
			break;
		case CHANGE_ORDER:
			if (roleNameExist("user", roles)){
				setUserRole("user");
				chain.doFilter(req, resp);
			} else {
				resp.sendRedirect(Bundle.getResource("redirect.to.start.page"));
			}
			break;
		case ADD_ORDER:
			if (roleNameExist("user", roles)){
				setUserRole("user");
				chain.doFilter(req, resp);
			} else {
				resp.sendRedirect(Bundle.getResource("redirect.to.start.page"));
			}
			break;
		default:
			resp.sendRedirect(Bundle.getResource("redirect.to.start.page"));
			break;
		}
		
	}

	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}
	
	
	private boolean roleNameExist (String name, Set<Role> roles){
		for (Role role : roles) {
			String roleName = role.getName();
			if (name.equals(roleName)) {
				return true;
			}
		}
		return false;
	}
	
	private void setUserRole (String roleName) {
		req.setAttribute(Bundle.getResource("session.key.user.role"), roleName);
	}

}
