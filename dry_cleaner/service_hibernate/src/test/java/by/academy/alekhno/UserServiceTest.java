package by.academy.alekhno;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;

import by.academy.alekhno.database.util.HibernateUtil;
import by.academy.alekhno.exception.DaoHibernateException;
import by.academy.alekhno.exception.ServiceException;
import by.academy.alekhno.external.OrderDAO;
import by.academy.alekhno.external.RoleDAO;
import by.academy.alekhno.external.UserDAO;
import by.academy.alekhno.service.impl.UserServiceImpl;
import by.academy.alekhno.service.interf.UserService;
import by.academy.alekhno.vo.Clother;
import by.academy.alekhno.vo.Order;
import by.academy.alekhno.vo.Role;
import by.academy.alekhno.vo.User;
import by.academy.alekhno.vo.UserRole;

public class UserServiceTest {
	private static Logger logger = Logger.getLogger(UserServiceTest.class
			.getName());

	private static SessionFactory sess;
	private final int id = 1;
	private final String login = "boris123";
//	private final String passwordBefore = "boris";
	private final String password = "4dbf44c6b1be736ee92ef90090452fc2";
	private final String firstName = "Boris";
	private final String secondName = "Alekhno";
	private final long telephone = 375291234567l;
	private final User user = new User();
	private final User user1 = new User();
	private final Role role = new Role();
	private final List<Role> roles = new ArrayList<Role>();
	private final UserRole userRole = new UserRole();
	private final Order order = new Order();
	private final List<Order> orders = new ArrayList<Order>();
	private final List<UserRole> userRoles = new ArrayList<UserRole>();
	private final Set<Role> roleSet = new HashSet<>();

	private final SessionFactory sessionFactory = sess;

	static {
		sess = HibernateUtil.getInstance().getSessionFactory();
	}

	private Mockery mockingContext = new JUnit4Mockery();

	private UserDAO daoUser;
	private OrderDAO daoOrder;
	private RoleDAO daoRole;
	private UserService userService = new UserServiceImpl();
	private Clother clother = new Clother();

	@Before
	public void setParamTest() {
		user.setId(id);
		user.setLogin(login);
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setSecondName(secondName);
		user.setTelephone(telephone);

		user1.setLogin(login);
		user1.setPassword(password);

		daoUser = mockingContext.mock(UserDAO.class);
		daoOrder = mockingContext.mock(OrderDAO.class);
		daoRole = mockingContext.mock(RoleDAO.class);

		role.setId(1);

		roles.add(role);

		userRole.setRole(role);
		userRole.setUser(user);

		userRoles.add(userRole);

		clother.setId(1);

		order.setId(1);
		order.setUser(user);
		order.setQuantity(1);
		order.setOrdering_day(new Date());
		order.setClother(clother);

		orders.add(order);

		roleSet.add(role);
	}

	@Test
	public void authorizeTrue() throws ServiceException, DaoHibernateException {
		logger.info("Start test authorizeTrue.");
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoUser).setSessionFactory(sessionFactory);
				oneOf(daoUser).getByLoginAndPassword(user1);
				will(returnValue(user));
			}
		});

		userService.setDaoUser(daoUser);
		User fUser = userService.authorization(login, "boris");
		assertEquals(fUser, user);
		logger.info("Test authorizeTrue is finished.");
	}

	@Test(expected = ServiceException.class)
	public void authorizeFalse() throws ServiceException, DaoHibernateException {
		logger.info("Start test authorizeFalse.");
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoUser).setSessionFactory(sessionFactory);
				oneOf(daoUser).getByLoginAndPassword(user1);
				will(returnValue(null));
			}
		});
		userService.setDaoUser(daoUser);
		User fUser = userService.authorization(login, "boris");
		assertNull(fUser);
		logger.info("Test authorizeFalse is finished.");
	}

	@Test
	public void registrationTrue() throws ServiceException, DaoHibernateException {
		logger.info("Start test registrationTrue.");
		final int idAdd = 1;
		final String name = "user";
		final User userAdd = new User();
		userAdd.setId(idAdd);
		final Role roleAdd = new Role();
		roleAdd.setId(idAdd);
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoUser).setSessionFactory(sessionFactory);
				oneOf(daoRole).setSessionFactory(sessionFactory);
				oneOf(daoUser).getByLogin(login);
				will(returnValue(null));
				oneOf(daoUser).add(user1);
				will(returnValue(idAdd));
				oneOf(daoRole).getByName(name);
				will(returnValue(roleAdd));
				oneOf(daoUser).addRoleForUser(userAdd, roleAdd);
			}
		});
		userService.setDaoUser(daoUser);
		userService.setDaoRole(daoRole);
		userService.registration(user1);
		logger.info("Test registrationTrue is finished.");
	}

	@Test(expected = ServiceException.class)
	public void registrationFalse() throws ServiceException, DaoHibernateException {
		logger.info("Start test registrationFalse.");
		final int idAdd = 1;
		final String name = "user";
		final User userAdd = new User();
		user.setId(idAdd);
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoUser).setSessionFactory(sessionFactory);
				oneOf(daoRole).setSessionFactory(sessionFactory);
				oneOf(daoUser).getByLogin(login);
				will(returnValue(user));
				oneOf(daoUser).add(user);
				will(returnValue(idAdd));
				oneOf(daoRole).getByName(name);
				will(returnValue(role));
				oneOf(daoUser).addRoleForUser(userAdd, role);
			}
		});
		userService.setDaoUser(daoUser);
		userService.setDaoRole(daoRole);
		userService.registration(user);
		logger.info("Test registrationFalse is finished.");
	}

	@Test
	public void addOrder() throws ServiceException, DaoHibernateException {
		logger.info("Start test addOrder.");
		final int idAdd = 1;
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoOrder).setSessionFactory(sessionFactory);
				oneOf(daoOrder).add(order);
				will(returnValue(idAdd));
			}
		});

		userService.setDaoOrder(daoOrder);
		userService.addOrder(order);
		logger.info("Test addOrder is finished.");
	}

	@Test
	public void updateOrder() throws ServiceException, DaoHibernateException {
		logger.info("Start test updateOrder.");
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoOrder).setSessionFactory(sessionFactory);
				oneOf(daoOrder).update(order);
			}
		});

		userService.setDaoOrder(daoOrder);
		userService.updateOrder(order);
		logger.info("Test updateOrder is finished.");
	}

	@Test
	public void deleteOrderById() throws ServiceException, DaoHibernateException {
		logger.info("Start test deleteOrderById.");
		final int idDelete = 1;
		final Order orderDelete = new Order();
		orderDelete.setId(idDelete);
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoOrder).setSessionFactory(sessionFactory);
				oneOf(daoOrder).delete(orderDelete);
			}
		});

		userService.setDaoOrder(daoOrder);
		userService.deleteOrderById(idDelete);
		logger.info("Test deleteOrderById is finished.");
	}

	@Test
	public void getOrdersByUserId() throws ServiceException, DaoHibernateException {
		logger.info("Start test getOrdersByUserId.");
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoOrder).setSessionFactory(sessionFactory);
				oneOf(daoOrder).getOrdersByUserId(id);
				will(returnValue(orders));
			}
		});

		userService.setDaoOrder(daoOrder);
		List<Order> fOrder = userService.getOrdersByUserId(id);
		assertTrue(!fOrder.isEmpty());
		logger.info("Test getOrdersByUserId is finished.");
	}

	@Test
	public void getOrdersByUserIdEmpty() throws ServiceException, DaoHibernateException {
		logger.info("Start test getOrdersByUserIdEmpty.");
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoOrder).setSessionFactory(sessionFactory);
				oneOf(daoOrder).getOrdersByUserId(id);
				will(returnValue(new ArrayList<Order>()));
			}
		});

		userService.setDaoOrder(daoOrder);
		List<Order> fOrder = userService.getOrdersByUserId(id);
		assertTrue(fOrder.isEmpty());
		logger.info("Test getOrdersByUserIdEmpty is finished.");
	}

	@Test
	public void getRoleByUserId() throws ServiceException, DaoHibernateException {
		logger.info("Start test deleteByID.");
		final int idUser = 1;
		final User userM = new User();
		userM.setId(idUser);
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoUser).setSessionFactory(sessionFactory);
				oneOf(daoUser).getRolesByUser(userM);
				will(returnValue(roleSet));

			}
		});

		userService.setDaoUser(daoUser);
		Set<Role> roles = userService.getRoleByUserId(idUser);
		assertTrue(!roles.isEmpty());
		logger.info("Test deleteByID is finished.");
	}

	@Test
	public void getUserByLogin() throws ServiceException, DaoHibernateException {
		logger.info("Start test deleteByID.");
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoUser).setSessionFactory(sessionFactory);
				oneOf(daoUser).getByLogin(login);
				will(returnValue(user));
			}
		});

		userService.setDaoUser(daoUser);
		User fUser = userService.getUserByLogin(login);
		assertEquals(fUser, user);
		logger.info("Test deleteByID is finished.");
	}

}
