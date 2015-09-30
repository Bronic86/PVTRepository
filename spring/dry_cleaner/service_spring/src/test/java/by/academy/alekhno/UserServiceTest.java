package by.academy.alekhno;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import by.academy.alekhno.database.dao.interf.RolePojoRepository;
import by.academy.alekhno.database.dao.interf.UserPojoRepository;
import by.academy.alekhno.spring.impl.UserServiceImpl;
import by.academy.alekhno.spring.pojo.RolePojo;
import by.academy.alekhno.spring.pojo.UserPojo;
import by.academy.alekhno.vo.Role;
import by.academy.alekhno.vo.User;

//@Ignore
public class UserServiceTest {
	private static Logger logger = Logger.getLogger(UserServiceTest.class.getName());

	@InjectMocks
	UserServiceImpl userService;

	@Mock
	private UserPojoRepository userRepository;

	@Mock
	private RolePojoRepository roleRepository;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	private final String login = "boris";
	private final String password = "boris";
	private final String passwordCh = "4dbf44c6b1be736ee92ef90090452fc2";
	private RolePojo rolePojo = new RolePojo(1, "user");
	private Role role = new Role(1, "user");
	private final Set<Role> roles = new HashSet<Role>(Arrays.asList(role));
	private final Set<RolePojo> rolesPojo = new HashSet<RolePojo>(Arrays.asList(rolePojo));

	private User user = new User(1, login, null, "FirstName", "SecondName", 375L, roles);
	private UserPojo userPojo = new UserPojo(1, login, passwordCh, "FirstName", "SecondName", 375L,
			rolesPojo);

	@Test
	public void authorizeTrue() {
		logger.info("Start test authorizeTrue.");

		Mockito.when(userRepository.getByLoginAndPassword(login, passwordCh)).thenReturn(userPojo);

		User fUser = userService.authorization(login, password);
		assertEquals(fUser, user);
		logger.info("Test authorizeTrue is finished.");
	}

	@Test
	public void authorizeFalse() {
		logger.info("Start test authorizeFalse.");

		Mockito.when(userRepository.getByLoginAndPassword(login, passwordCh)).thenReturn(null);

		User fUser = userService.authorization(login, password);
		assertNull(fUser);
		logger.info("Test authorizeFalse is finished.");
	}

	@Ignore
	@Test
	public void registrationTrue() {
		logger.info("Start test registrationTrue.");
		final User userReg = new User(1, login, password, "FirstName", "SecondName", 375L,
				new HashSet<Role>());

		Mockito.when(userRepository.getByLogin(login)).thenReturn(null);
		Mockito.when(roleRepository.getByName("user")).thenReturn(rolePojo);
		Mockito.when(userRepository.saveAndFlush(userPojo)).thenReturn(userPojo);
		// Return null ???
		User fUser = userService.registration(userReg);
		assertEquals(fUser, user);
		logger.info("Test registrationTrue is finished.");
	}

	@Test
	public void registrationFalse() {
		logger.info("Start test registrationFalse.");

		Mockito.when(userRepository.getByLogin(login)).thenReturn(userPojo);

		User fUser = userService.registration(user);
		assertNull(fUser);
		logger.info("Test registrationFalse is finished.");
	}

	// @Test
	// public void addOrder() throws ServiceException, DaoHibernateException {
	// logger.info("Start test addOrder.");
	// final int idAdd = 1;
	// mockingContext.checking(new Expectations() {
	// {
	// oneOf(daoOrder).setSessionFactory(sessionFactory);
	// oneOf(daoOrder).add(order);
	// will(returnValue(idAdd));
	// }
	// });
	//
	// userService.setDaoOrder(daoOrder);
	// userService.addOrder(order);
	// logger.info("Test addOrder is finished.");
	// }
	//
	// @Test
	// public void updateOrder() throws ServiceException, DaoHibernateException
	// {
	// logger.info("Start test updateOrder.");
	// mockingContext.checking(new Expectations() {
	// {
	// oneOf(daoOrder).setSessionFactory(sessionFactory);
	// oneOf(daoOrder).update(order);
	// }
	// });
	//
	// userService.setDaoOrder(daoOrder);
	// userService.updateOrder(order);
	// logger.info("Test updateOrder is finished.");
	// }
	//
	// @Test
	// public void deleteOrderById() throws ServiceException,
	// DaoHibernateException {
	// logger.info("Start test deleteOrderById.");
	// final int idDelete = 1;
	// final Order orderDelete = new Order();
	// orderDelete.setId(idDelete);
	// mockingContext.checking(new Expectations() {
	// {
	// oneOf(daoOrder).setSessionFactory(sessionFactory);
	// oneOf(daoOrder).delete(orderDelete);
	// }
	// });
	//
	// userService.setDaoOrder(daoOrder);
	// userService.deleteOrderById(idDelete);
	// logger.info("Test deleteOrderById is finished.");
	// }
	//
	// @Test
	// public void getOrdersByUserId() throws ServiceException,
	// DaoHibernateException {
	// logger.info("Start test getOrdersByUserId.");
	// mockingContext.checking(new Expectations() {
	// {
	// oneOf(daoOrder).setSessionFactory(sessionFactory);
	// oneOf(daoOrder).getOrdersByUserId(id);
	// will(returnValue(orders));
	// }
	// });
	//
	// userService.setDaoOrder(daoOrder);
	// List<Order> fOrder = userService.getOrdersByUserId(id);
	// assertTrue(!fOrder.isEmpty());
	// logger.info("Test getOrdersByUserId is finished.");
	// }
	//
	// @Test
	// public void getOrdersByUserIdEmpty() throws ServiceException,
	// DaoHibernateException {
	// logger.info("Start test getOrdersByUserIdEmpty.");
	// mockingContext.checking(new Expectations() {
	// {
	// oneOf(daoOrder).setSessionFactory(sessionFactory);
	// oneOf(daoOrder).getOrdersByUserId(id);
	// will(returnValue(new ArrayList<Order>()));
	// }
	// });
	//
	// userService.setDaoOrder(daoOrder);
	// List<Order> fOrder = userService.getOrdersByUserId(id);
	// assertTrue(fOrder.isEmpty());
	// logger.info("Test getOrdersByUserIdEmpty is finished.");
	// }

	@Test
	public void getRoleByUserId() {
		logger.info("Start test deleteByID.");
		int id = 1;
		Mockito.when(userRepository.getOne(id)).thenReturn(userPojo);

		Set<Role> roles = userService.getRoleByUserId(id);
		assertTrue(roles.contains(role));
		logger.info("Test deleteByID is finished.");
	}

	// @Test
	// public void getUserByLogin() throws ServiceException,
	// DaoHibernateException {
	// logger.info("Start test deleteByID.");
	// mockingContext.checking(new Expectations() {
	// {
	// oneOf(daoUser).setSessionFactory(sessionFactory);
	// oneOf(daoUser).getByLogin(login);
	// will(returnValue(user));
	// }
	// });
	//
	// userService.setDaoUser(daoUser);
	// User fUser = userService.getUserByLogin(login);
	// assertEquals(fUser, user);
	// logger.info("Test deleteByID is finished.");
	// }

}
