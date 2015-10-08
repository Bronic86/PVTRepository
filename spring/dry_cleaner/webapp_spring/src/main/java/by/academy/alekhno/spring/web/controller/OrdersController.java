package by.academy.alekhno.spring.web.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import by.academy.alekhno.spring.interf.ClotherService;
import by.academy.alekhno.spring.interf.OrderService;
import by.academy.alekhno.spring.interf.UserService;
import by.academy.alekhno.vo.Clother;
import by.academy.alekhno.vo.Order;

@Controller
public class OrdersController {
	private static Logger logger = Logger.getLogger(OrdersController.class.getName());

	@Autowired
	private ClotherService clotherService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/price_list/order/{clother_id}", method = RequestMethod.GET)
	public ModelAndView toAddOrder(Map<String, ?> model, @PathVariable int clother_id) {
		ModelAndView modelAndView = new ModelAndView();
		Clother clother = clotherService.getClotherById(clother_id);
		modelAndView.addObject("add_clother", clother);
		modelAndView.addObject("addOrder", new Order());
		modelAndView.addAllObjects(model);
		modelAndView.setViewName("add_order");
		logger.info(clother);
		return modelAndView;
	}

	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public String toOrders(Model model) {
		List<Order> orders = orderService.getOrdersByUserName(getLogin());
		model.addAttribute("user_orders", orders);
		return "orders";
	}

	// @RequestMapping(value = "/price_list/order/add", method =
	// RequestMethod.POST)
	// public String AddOrder(Model model, @ModelAttribute("addOrder") Order
	// order) {
	// logger.info(order);
	// order.setUser(userService.getUserByLogin(getLogin()));
	// order.setOrdering_day(new Date());
	// orderService.addOrder(order);
	// logger.info(order);
	// return "redirect:/orders";
	// }

	@RequestMapping(value = "/price_list/order/add", method = RequestMethod.POST)
	public String AddOrder(Model model, HttpServletRequest req) {
		String quantity_param = req.getParameter("quantity");
		String clother_id_param = req.getParameter("clother_id");
		int quantity = Integer.parseInt(quantity_param);
		int clother_id = Integer.parseInt(clother_id_param);
		// add validation

		Order order = new Order();
		order.setUser(userService.getUserByLogin(getLogin()));
		order.setOrdering_day(new Date());
		order.setQuantity(quantity);
		Clother clother = new Clother();
		clother.setId(clother_id);
		order.setClother(clother);
		orderService.addOrder(order);
		return "redirect:/orders";
	}

	private String getLogin() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		String login = userDetail.getUsername();
		return login;
	}
}
