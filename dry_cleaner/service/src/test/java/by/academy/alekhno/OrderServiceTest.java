package by.academy.alekhno;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;

import by.academy.alekhno.dao.interf.CustomOrderDao;
import by.academy.alekhno.exception.DaoException;
import by.academy.alekhno.service.impl.OrderServiceImpl;
import by.academy.alekhno.service.interf.OrderService;
import by.academy.alekhno.vo.Clother;
import by.academy.alekhno.vo.Order;
import by.academy.alekhno.vo.User;

public class OrderServiceTest {
	private CustomOrderDao daoOrder;
	private OrderService orderService = new OrderServiceImpl();
	private Mockery mockingContext = new JUnit4Mockery();

	private static Logger logger = Logger.getLogger(OrderServiceTest.class
			.getName());

	private final Order order = new Order();
	private final int id = 1;
	private final int quantity = 100;
	private final Date ordering_day = new Date();
	private final User user = new User();
	private final Clother clother = new Clother();
	private final List<Integer> idList = new ArrayList<Integer>();
	private final String login = "boris";
	private final Order orderDelete = new Order();
	private final List<Order> orders = new ArrayList<Order>();

	@Before
	public void setParamTest() {
		daoOrder = mockingContext.mock(CustomOrderDao.class);
		order.setId(id);
		clother.setId(id);
		order.setClother(clother);
		order.setOrdering_day(ordering_day);
		order.setQuantity(quantity);
		user.setId(id);
		user.setLogin(login);
		order.setUser(user);

		idList.add(id);

		orders.add(order);

		orderDelete.setId(id);
	}

	@Test
	public void add() throws DaoException {
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoOrder).add(order);
				oneOf(daoOrder).getIdByFields(order);
				will(returnValue(idList));
			}
		});
		orderService.setDaoOrder(daoOrder);
		int fId = orderService.add(order);
		assertEquals(id, fId);
		logger.info("Test add is finished.");
	}

	@Test
	public void deleteByID() throws DaoException {
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoOrder).delete(orderDelete);
			}
		});

		orderService.setDaoOrder(daoOrder);
		orderService.deleteByID(id);
		logger.info("Test deleteByID is finished.");
	}

	@Test
	public void getOrdersByUserId() throws DaoException {
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoOrder).getOrdersByUserId(id);
				will(returnValue(orders));
			}
		});

		orderService.setDaoOrder(daoOrder);
		List<Order> fOrders = orderService.getOrdersByUserId(id);
		assertEquals(fOrders, orders);
		logger.info("Test getOrdersByUserId is finished.");
	}

	@Test
	public void getOrders() throws DaoException {
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoOrder).getAll();
				will(returnValue(orders));
			}
		});

		orderService.setDaoOrder(daoOrder);
		List<Order> fOrders = orderService.getOrders();
		assertEquals(fOrders, orders);
		logger.info("Test getOrders is finished.");
	}

}
