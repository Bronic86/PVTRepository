package by.academy.alekhno;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import by.academy.alekhno.dao.interf.CustomUserDao;
import by.academy.alekhno.exception.DaoException;
import by.academy.alekhno.exception.ServiceException;
import by.academy.alekhno.service.impl.UserServiceImpl;
import by.academy.alekhno.service.interf.UserService;
import by.academy.alekhno.vo.User;


@Ignore
public class UserServiceTest{

//	public UserServiceTest(String testName) {
//		userGet = new User();
//		userGet.setLogin(login1);
//		userGet.setPassword(password1);
//		// TODO Auto-generated constructor stub
//	}
	
	private final int id = 1;
	private final String login1 = "boris";
	private final String password1 = "4dbf44c6b1be736ee92ef90090452fc2";
	private final String firstName = "Boris";
	private final String secondName = "Alekhno";
	private final long telephone = 375291234567l;
	private final User userGet = userChange;
	private static User userChange;
	static{
		userChange = new User();
		userChange.setLogin("boris");
		userChange.setPassword("4dbf44c6b1be736ee92ef90090452fc2");
	}
	
	
	private UserService userInterf;
	private CustomUserDao daoUser;
	private CustomUserDao daoUserRole;
	private Mockery mockingContext = new Mockery();
//	private String login;
//	private String password;
	private User user = new User();
//	private User user1;

	@Before
	public void doBeforeTest() throws DaoException, ServiceException {
		userInterf = new UserServiceImpl();
		daoUser = mockingContext.mock(CustomUserDao.class);
		user.setLogin(login1);
		user.setPassword(login1);
//		user1 = null;
		
	}

	@Test
	public void authorize() throws DaoException, ServiceException {
		mockingContext.checking(new Expectations() {
			{
				
				final User user2 = userGet; 
				one(daoUser).getByLoginAndPassword(user2);
				will(returnValue(user2));
			}
		});
		
		System.out.println(userInterf.authorization(login1, login1));
	}
	
	@Test(expected = ServiceException.class)
	public void authorizeEx() throws DaoException, ServiceException {
		mockingContext.checking(new Expectations() {
			{
				final User user2 = userGet;
				final User user1 = null;
				one(daoUser).getByLoginAndPassword(user2);
				will(returnValue(user1));
			}
		});
		userInterf.authorization(user.getLogin(), user.getPassword());
	}
	
	

}
