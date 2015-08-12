package by.academy.alekhno;

import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Ignore;
import org.junit.Test;

import static org.mockito.Mockito.*;
import by.academy.alekhno.dao.interf.CustomUserDao;
import by.academy.alekhno.exception.DaoException;
import by.academy.alekhno.exception.ServiceException;
import by.academy.alekhno.service.impl.UserServiceImpl;
import by.academy.alekhno.service.interf.UserService;
import by.academy.alekhno.vo.User;


@Ignore
public class UserServiceTest {

	private final int id = 1;
	private final String login = "boris123";
	private final String password = "4dbf44c6b1be736ee92ef90090452fc2";
	private final String firstName = "Boris";
	private final String secondName = "Alekhno";
	private final long telephone = 375291234567l;

	private Mockery mockingContext = new JUnit4Mockery();
	private CustomUserDao daoUser;

	@Test
	public void authorize() throws DaoException, ServiceException {
		
		daoUser = mock(CustomUserDao.class);
		final User user1 = new User();
		user1.setId(id);
		user1.setLogin(login);
		user1.setPassword(password);
		user1.setFirstName(firstName);
		user1.setSecondName(secondName);
		user1.setTelephone(telephone);

		when(daoUser.getByLogin(login))
				.thenReturn(
						new User(id, login, password, firstName, secondName,
								telephone));

		// daoUser = mockingContext.mock(CustomUserDao.class);
		final User user = new User();
		user.setLogin(login);
		user.setPassword(login);
		System.out.println(user);

		// final User user1 = new User();
		// user1.setId(id);
		// user1.setLogin(login);
		// user1.setPassword(password);
		// user1.setFirstName(firstName);
		// user1.setSecondName(secondName);
		// user1.setTelephone(telephone);
		System.out.println(user1);
		final User userMock = user1;

		// mockingContext.checking(new Expectations() {
		// {
		// oneOf(daoUser).getByLogin(login);
		// will(returnValue(new User()));
		// }
		// });
		UserService userInterf = new UserServiceImpl();
		User userF = userInterf.getUserByLogin(login);
		 assertEquals(user1, userF);
		System.out.println(userF);
	}

	@Test
	public void authorizeEx() throws DaoException, ServiceException {
		final UserService userInterf = new UserServiceImpl();
		daoUser = mockingContext.mock(CustomUserDao.class);
		User user = new User();
		user.setLogin("boris");
		user.setPassword(password);
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoUser).getByLoginAndPassword(new User());
				will(returnValue(null));
			}
		});
		
		System.out.println(daoUser.getByLoginAndPassword(user));
		User fUser = userInterf.authorization(login, password);
		System.out.println(fUser);
		assertNull(fUser);
	}

}
