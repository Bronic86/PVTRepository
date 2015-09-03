package by.academy.alekhno;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;

import by.academy.alekhno.database.util.HibernateUtil;
import by.academy.alekhno.external.OrderDAO;
import by.academy.alekhno.service.impl.OrderServiceImpl;
import by.academy.alekhno.service.interf.OrderService;
import by.academy.alekhno.vo.Clother;
import by.academy.alekhno.vo.Order;
import by.academy.alekhno.vo.User;

public class OrderServiceTest {
	private OrderDAO daoOrder;
	private OrderService orderService = new OrderServiceImpl();
	private Mockery mockingContext = new JUnit4Mockery();

	private static Logger logger = Logger.getLogger(OrderServiceTest.class
			.getName());

	private static Session sess;

	private final Order order = new Order();
	private final int id = 1;
	private final int quantity = 100;
	private final Date ordering_day = new Date();
	private final User user = new User();
	private final Clother clother = new Clother();
	private final List<Integer> idList = new ArrayList<Integer>();
	private final String login = "boris";
	private final List<Order> orders = new ArrayList<Order>();

	private final Session session = sess;

	static {
		sess = HibernateUtil.getInstance().getSession();
	}

	@Before
	public void setParamTest() {
		daoOrder = mockingContext.mock(OrderDAO.class);
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

	}

	@Test
	public void deleteByID() {
		logger.info("Start test deleteByID.");
		final int idDelete = 1;
		final Order orderDelete = new Order();
		orderDelete.setId(idDelete);
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoOrder).setSession(session);
				oneOf(daoOrder).delete(orderDelete);
			}
		});

		orderService.setDaoOrder(daoOrder);
		orderService.deleteByID(id);
		logger.info("Test deleteByID is finished.");
	}

	@Test
	public void getOrdersByUserId() {
		logger.info("Start test getOrdersByUserId.");
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoOrder).setSession(session);
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
	public void getOrders() {
		logger.info("Start test getOrders.");
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoOrder).setSession(session);
				oneOf(daoOrder).getAll();
				will(returnValue(orders));
			}
		});

		orderService.setDaoOrder(daoOrder);
		List<Order> fOrders = orderService.getOrders();
		assertEquals(fOrders, orders);
		logger.info("Test getOrders is finished.");
	}

	@Test
	public void getOrdersByClotherId() {
		logger.info("Start test getOrders.");
		final int clother_id = 1;
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoOrder).setSession(session);
				oneOf(daoOrder).getOrdersByClotherId(clother_id);
				will(returnValue(orders));
			}
		});

		orderService.setDaoOrder(daoOrder);
		List<Order> fOrders = orderService.getOrdersByClotherId(clother_id);
		assertEquals(fOrders, orders);
		logger.info("Test getOrders is finished.");
	}

}
