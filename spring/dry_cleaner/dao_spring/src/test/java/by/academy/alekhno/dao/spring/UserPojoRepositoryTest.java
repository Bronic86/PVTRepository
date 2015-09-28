package by.academy.alekhno.dao.spring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import by.academy.alekhno.database.config.AppDAOJPAConfig;
import by.academy.alekhno.database.dao.interf.UserPojoRepository;
import by.academy.alekhno.spring.pojo.UserPojo;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppDAOJPAConfig.class })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@Transactional
@TransactionConfiguration(defaultRollback = true)
@DatabaseSetup("classpath:TestResource.xml")
public class UserPojoRepositoryTest {
	private static Logger logger = Logger.getLogger(UserPojoRepositoryTest.class.getName());

	@Autowired
	private UserPojoRepository userPojoRepository;

	@Before
	public void setUp() {
		logger.info("Before test");
	}

	@Test
	public void findUserByLogin() {
		logger.info("Start test findUserByLogin");
		UserPojo userPojo = userPojoRepository.getByLogin("test@mail.ru");
		assertEquals(2, userPojo.getId());
		logger.info("Test is successfull");
	}

	@Test
	public void findUserByLoginExcept() {
		logger.info("Start test findUserByLoginExcept");
		UserPojo userPojo = userPojoRepository.getByLogin("except");
		assertNull(userPojo);
		logger.info("Test is successfull");
	}

	@Test
	public void findUserByLoginAndPassword() {
		logger.info("Start test findUserByLoginAndPassword");
		UserPojo userPojo = userPojoRepository.getByLoginAndPassword("test@mail.ru", "321");
		assertEquals(2, userPojo.getId());
		logger.info("Test is successfull");
	}

	@Test
	public void findUserByLoginAndPasswordExceptByLogin() {
		logger.info("Start test findUserByLoginAndPasswordExceptByLogin");
		UserPojo userPojo = userPojoRepository.getByLoginAndPassword("except", "321");
		assertNull(userPojo);
		logger.info("Test is successfull");
	}

	@Test
	public void findUserByLoginAndPasswordExceptByPassword() {
		logger.info("Start test findUserByLoginAndPasswordExceptByPassword");
		UserPojo userPojo = userPojoRepository.getByLoginAndPassword("test@mail.ru", "error");
		assertNull(userPojo);
		logger.info("Test is successfull");
	}
}
