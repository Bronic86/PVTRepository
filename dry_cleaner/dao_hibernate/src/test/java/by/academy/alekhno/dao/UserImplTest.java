package by.academy.alekhno.dao;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.Ignore;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.orm.hibernate.annotation.HibernateSessionFactory;
import org.junit.Before;

import by.academy.alekhno.dao.impl.CustomUserDAOImpl;
import by.academy.alekhno.dao.interf.CustomUserDAO;
import by.academy.alekhno.database.pojo.ClotherPojo;
import by.academy.alekhno.database.pojo.ModelPojo;
import by.academy.alekhno.database.pojo.OrderPojo;
import by.academy.alekhno.database.pojo.RolePojo;
import by.academy.alekhno.database.pojo.StatePojo;
import by.academy.alekhno.database.pojo.TypePojo;
import by.academy.alekhno.database.pojo.UserPojo;
import by.academy.alekhno.exception.DaoHibernateException;


@Ignore
@DataSet
//@HibernateSessionFactory("hibernate.cfg.xml")
public class UserImplTest extends UnitilsJUnit4 {
	private static final Logger logger = Logger.getLogger(UserImplTest.class.getName());
	private UserPojo user;
	private RolePojo role;
	private StatePojo state;
	private ModelPojo model;
	private TypePojo type;
	private ClotherPojo clother;
	private OrderPojo order;
//	private int id = 2;


	private CustomUserDAO userDao;
	
	@HibernateSessionFactory
	private SessionFactory sessionFactory;
	

	
	@Before
	@DataSet
	public void setUp() {
		logger.info("setUp start");
		sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		userDao = new CustomUserDAOImpl();
		userDao.setSessionFactory(sessionFactory);
		logger.info("setSessionFactory");
		user = new UserPojo();
		role = new RolePojo();
		state= new StatePojo();
		type = new TypePojo();
		model = new ModelPojo();
		clother = new ClotherPojo();
		order = new OrderPojo();
	}
	
	

	@Test
	public void getById() throws DaoHibernateException {
		logger.info("getById start");
		String login = "test@mail.ru";
		int id = 2;
		UserPojo user = new UserPojo();
		user.setId(id);
		UserPojo fUser = userDao.getByID(user);
		assertEquals(fUser.getLogin(), login);
		logger.info("getById end successfull.");
	}
	
	@Test
	public void getByLogin() throws DaoHibernateException {
		logger.info("getByLogin start");
		int id = 1;
		String login = "boris@mail.ru";
		UserPojo fUser = userDao.getByLogin(login);
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
		UserPojo fUser = userDao.getByLoginAndPassword(user);
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
		Set<RolePojo> fRoles = userDao.getRolesByUser(user);
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
		Set<UserPojo> users = new HashSet<UserPojo>();
		users.addAll(userDao.getAll());
		System.out.println(users.size());
		assertEquals(users.size(), 2);
		logger.info("getAll end successfull.");
	}
	
	@Ignore
	@Test
	public void update() throws DaoHibernateException {
		logger.info("update start");
//		user.setId(1);
//		user = userDao.getByID(user);
		UserPojo userB = new UserPojo();
//		userDao.setSessionFactory(sessionFactory);
//		userB.setId(user.getId());
//		userB.setLogin(user.getLogin());
//		userB.setPassword(user.getPassword());
//		userB.setFirstName(user.getFirstName());
//		userB.setSecondName(user.getSecondName());
		userB.setTelephone(12345L);
		userB.setId(1);
		userB.setLogin("boris@mail.ru");
		userB.setPassword("123");
		userB.setFirstName("Boris");
		userB.setSecondName("Alekhno");
		System.out.println("UserB = " + userB);
		userDao.update(userB);
//		userDao.setSessionFactory(sessionFactory);
//		UserPojo userA = userDao.getByID(user);
//		assertEquals(userA.getTelephone(), userB.getTelephone());
		logger.info("update end successfull.");
	}
	
	@Ignore
	@Test
	public void delete() throws DaoHibernateException {
		logger.info("delete start");
		int id = 2;
		user.setId(id);
		userDao.delete(user);
		logger.info("delete end successfull.");
	}
	
	@Ignore
	@Test
	public void add() throws DaoHibernateException {
		logger.info("add start");
		user.setId(3);
		user.setLogin("B");
		user.setPassword("P");
		user.setFirstName("FN");
		user.setSecondName("SN");
		user.setTelephone(1L);
		int idN = userDao.add(user);
		assertEquals(idN, 3);
		logger.info("add end successfull.");
	}
}
