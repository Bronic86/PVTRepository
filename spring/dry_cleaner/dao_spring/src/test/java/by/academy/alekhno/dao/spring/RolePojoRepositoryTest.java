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

import by.academy.alekhno.dao.spring.config.AppDAOJPAConfigTest;
import by.academy.alekhno.database.dao.interf.RolePojoRepository;
import by.academy.alekhno.spring.pojo.RolePojo;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppDAOJPAConfigTest.class })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@Transactional
@TransactionConfiguration(defaultRollback = true)
@DatabaseSetup("classpath:TestResource.xml")
public class RolePojoRepositoryTest {
	private static Logger logger = Logger.getLogger(UserPojoRepositoryTest.class.getName());

	@Autowired
	private RolePojoRepository rolePojoRepository;

	@Before
	public void setUp() {
		logger.info("Before test");
	}

	@Test
	public void findRoleByName() {
		logger.info("Start test findRoleByName");
		RolePojo rolePojo = rolePojoRepository.getByName("admin");
		assertEquals(2, rolePojo.getId());
		logger.info("Test is successfull");
	}

	@Test
	public void findRoleByNameExcept() {
		logger.info("Start test findRoleByNameExcept");
		RolePojo rolePojo = rolePojoRepository.getByName("except");
		assertNull(rolePojo);
		logger.info("Test is successfull");
	}
}
