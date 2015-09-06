package by.academy.alekhno.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.dbunit.annotation.DataSet;
import org.junit.Before;

import by.academy.alekhno.dao.impl.UserDAOImpl;
import by.academy.alekhno.dao.interf.CustomUserDAO;
import by.academy.alekhno.database.pojo.Clother;
import by.academy.alekhno.database.pojo.Model;
import by.academy.alekhno.database.pojo.Order;
import by.academy.alekhno.database.pojo.Role;
import by.academy.alekhno.database.pojo.State;
import by.academy.alekhno.database.pojo.Type;
import by.academy.alekhno.database.pojo.User;
import by.academy.alekhno.exception.DaoHibernateException;


//@Ignore
@DataSet
//@HibernateSessionFactory("hibernate.cfg.xml")
public class UserImplTest extends UnitilsJUnit4 {
	private static final Logger logger = Logger.getLogger(UserImplTest.class.getName());
	private User user;
	private Role role;
	private State state;
	private Model model;
	private Type type;
	private Clother clother;
	private Order order;
//	private int id = 2;


	private CustomUserDAO userDao;
	
//	@HibernateSessionFactory
	private SessionFactory sessionFactory;
	

	
	@Before
	@DataSet
	public void setUp() {
		logger.info("setUp start");
		sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		userDao = new UserDAOImpl();
		userDao.setSessionFactory(sessionFactory);
		logger.info("setSessionFactory");
		user = new User();
		role = new Role();
		state= new State();
		type = new Type();
		model = new Model();
		clother = new Clother();
		order = new Order();
	}
	
	

	@Test
	public void getById() throws DaoHibernateException {
		logger.info("getById start");
		String login = "test@mail.ru";
		int id = 2;
		User user = new User();
		user.setId(id);
		User fUser = userDao.getByID(user);
		assertEquals(fUser.getLogin(), login);
		logger.info("getById end successfull.");
	}
	
	@Test
	public void getByLogin() throws DaoHibernateException {
		logger.info("getByLogin start");
		int id = 1;
		String login = "boris@mail.ru";
		User fUser = userDao.getByLogin(login);
		assertEquals(fUser.getId(), id);
		logger.info("getByLogin end successfull.");
	}
	
	@Test
	public void getByLoginAndPassword() throws DaoHibernateException {
		logger.info("getByLoginAndPassword start");
		String password = "123";
		String login = "boris@mail.ru";
		user.setLogin(login);
		user.setPassword(password);
		int id = 1;
		User fUser = userDao.getByLoginAndPassword(user);
		assertEquals(fUser.getId(), id);
		logger.info("getByLoginAndPassword end successfull.");
	}
	
	@Ignore
	@Test
	public void addRoleForUser() throws DaoHibernateException {
		logger.info("addRoleForUser start");
		int id = 2;
		user.setId(id);
		role.setId(id);
		userDao.addRoleForUser(user, role);
		logger.info("addRoleForUser end successfull.");
	}

	@Test
	public void getRolesByUser() throws DaoHibernateException {
		logger.info("getRolesByUser start");
		int id = 1;
		user.setId(id);
		Set<Role> fRoles = userDao.getRolesByUser(user);
		assertEquals(fRoles.size(), 2);
		role.setId(1);
		role.setName("user");
		assertTrue(fRoles.contains(role));
		role.setId(2);
		role.setName("admin");
		assertTrue(fRoles.contains(role));
		logger.info("getRolesByUser end successfull.");
	}
	
	@Test
	public void getAll() throws DaoHibernateException {
		logger.info("getAll start");
		Set<User> users = new HashSet<User>();
		users.addAll(userDao.getAll());
		System.out.println(users.size());
		assertEquals(users.size(), 2);
		logger.info("getAll end successfull.");
	}
	
	@Ignore
	@Test
	public void update() throws DaoHibernateException {
		logger.info("update start");
		user.setId(1);
		user = userDao.getByID(user);
		User userB = new User();
		userDao.setSessionFactory(new AnnotationConfiguration().configure().buildSessionFactory());
		userB.setId(user.getId());
		userB.setLogin(user.getLogin());
		userB.setPassword(user.getPassword());
		userB.setFirstName(user.getFirstName());
		userB.setSecondName(user.getSecondName());
		userB.setTelephone(12345L);
		userDao.update(userB);
		userDao.setSessionFactory(new AnnotationConfiguration().configure().buildSessionFactory());
		User userA = userDao.getByID(user);
		assertEquals(userA.getTelephone(), userB.getTelephone());
		logger.info("update end successfull.");
	}
}
