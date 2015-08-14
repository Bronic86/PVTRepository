package by.academy.alekhno;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import by.academy.alekhno.dao.interf.CustomOrderDao;
import by.academy.alekhno.dao.interf.CustomRole;
import by.academy.alekhno.dao.interf.CustomUserDao;
import by.academy.alekhno.dao.interf.CustomUserRoleDao;
import by.academy.alekhno.exception.DaoException;
import by.academy.alekhno.exception.ServiceException;
import by.academy.alekhno.service.impl.UserServiceImpl;
import by.academy.alekhno.service.interf.UserService;
import by.academy.alekhno.vo.Clother;
import by.academy.alekhno.vo.Order;
import by.academy.alekhno.vo.Role;
import by.academy.alekhno.vo.User;
import by.academy.alekhno.vo.UserRole;


public class UserServiceTest {

	private final int id = 1;
	private final String login = "boris123";
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
	
	

	private Mockery mockingContext = new JUnit4Mockery();
	
	private CustomUserDao daoUser;
	private CustomOrderDao daoOrder;
	private CustomUserRoleDao daoUserRole;
	private CustomRole daoRole;
	private UserService userService = new UserServiceImpl();
	private Clother clother = new Clother();
	
	@Before
	public void setParamTest(){
		user.setId(id);
		user.setLogin(login);
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setSecondName(secondName);
		user.setTelephone(telephone);
		
		user1.setLogin(login);
		user1.setPassword(password);
				
		daoUser = mockingContext.mock(CustomUserDao.class);
		daoOrder = mockingContext.mock(CustomOrderDao.class);
		daoUserRole = mockingContext.mock(CustomUserRoleDao.class);
		daoRole = mockingContext.mock(CustomRole.class);
		
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
	}
		
	@Test
	public void authorize() throws DaoException, ServiceException {
		
			
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoUser).getByLoginAndPassword(user1);
				will(returnValue(user));
			}
		});
		
		
		userService.setDaoUser(daoUser);
		User fUser = userService.authorization(login, "boris");
		assertEquals(fUser, user);
	}

	@Test(expected = ServiceException.class)
	public void authorizeEx() throws DaoException, ServiceException {
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoUser).getByLoginAndPassword(user1);
				will(returnValue(null));
			}
		});
		userService.setDaoUser(daoUser);
		User fUser = userService.authorization(login, "boris");
		assertTrue(user.equals(fUser));
	}
	
//	@Ignore
	@Test
	public void registration() throws DaoException, ServiceException{
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoUser).getByLogin(login);
				will(returnValue(null));
				oneOf(daoUser).add(user);
				oneOf(daoUser).getByLogin(login);
				will(returnValue(user));
				oneOf(daoRole).getByID(role);
				will(returnValue(role));
				oneOf(daoUserRole).add(userRole);
			}
		});
		userService.setDaoUser(daoUser);
		userService.setDaoRole(daoRole);
		userService.setDaoUserRole(daoUserRole);
		userService.registration(user);
	}
	
	@Test
	public void addOrder() throws DaoException{
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoOrder).add(order);
			}});
		
		userService.setDaoOrder(daoOrder);
		userService.addOrder(order);
	}
	
	
	@Test
	public void getOrdersByUserId() throws DaoException{
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoOrder).getOrdersByUserId(id);
				will(returnValue(orders));
			}});
		
		userService.setDaoOrder(daoOrder);
		List<Order> fOrder = userService.getOrdersByUserId(id);
		assertTrue(!fOrder.isEmpty());
	}
	
	@Test
	public void getOrdersByUserIdEmpty() throws DaoException{
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoOrder).getOrdersByUserId(id);
				will(returnValue(new ArrayList<Order>()));
			}});
		
		userService.setDaoOrder(daoOrder);
		List<Order> fOrder = userService.getOrdersByUserId(id);
		assertTrue(fOrder.isEmpty());
	}
	
	@Test
	public void getRoleByUserId() throws DaoException{
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoUserRole).getByIdUser(id);
				will(returnValue(userRoles));
				
			}});
		
		userService.setDaoUserRole(daoUserRole);
		List<Role> roles = userService.getRoleByUserId(id);
		assertTrue(!roles.isEmpty());
	}
	
	@Test
	public void getUserByLogin() throws DaoException{
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoUser).getByLogin(login);
				will(returnValue(user));
			}
		});
		userService.setDaoUser(daoUser);
		User fUser = userService.getUserByLogin(login);
		assertEquals(fUser, user);
	}
	

}
