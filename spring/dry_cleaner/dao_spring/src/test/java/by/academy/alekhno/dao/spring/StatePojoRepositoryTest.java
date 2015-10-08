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
import by.academy.alekhno.database.dao.interf.StatePojoRepository;
import by.academy.alekhno.spring.pojo.StatePojo;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppDAOJPAConfigTest.class })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@Transactional
@TransactionConfiguration(defaultRollback = true)
@DatabaseSetup("classpath:TestResource.xml")
public class StatePojoRepositoryTest {
	private static Logger logger = Logger.getLogger(UserPojoRepositoryTest.class.getName());

	@Autowired
	private StatePojoRepository statePojoRepository;

	@Before
	public void setUp() {
		logger.info("Before test");
	}

	@Test
	public void findStateByStateName() {
		logger.info("Start test findStateByStateName");
		StatePojo statePojo = statePojoRepository.getByState("processed");
		assertEquals(2, statePojo.getId());
		logger.info("Test is successfull");
	}

	@Test
	public void findStateByStateNameExcept() {
		logger.info("Start test findStateByStateNameExcept");
		StatePojo statePojo = statePojoRepository.getByState("except");
		assertNull(statePojo);
		logger.info("Test is successfull");
	}
}
