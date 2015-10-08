package by.academy.alekhno.dao.spring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

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
import by.academy.alekhno.database.dao.interf.ClotherPojoRepository;
import by.academy.alekhno.spring.pojo.ClotherPojo;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppDAOJPAConfigTest.class })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@Transactional
@TransactionConfiguration(defaultRollback = true)
@DatabaseSetup("classpath:TestResource.xml")
public class ClotherPojoRepositoryTest {
	private static Logger logger = Logger.getLogger(UserPojoRepositoryTest.class.getName());

	@Autowired
	private ClotherPojoRepository clotherPojoRepository;

	@Before
	public void setUp() {
		logger.info("Before test");
	}

	@Test
	public void findClotherByModelId() {
		logger.info("Start test findClotherByModelId");
		ClotherPojo clotherPojo = clotherPojoRepository.getByModelId(2);
		assertEquals(2, clotherPojo.getId());
		logger.info("Test is successfull");
	}

	@Test
	public void findClotherByModelIdExcept() {
		logger.info("Start test findClotherByModelIdExcept");
		ClotherPojo clotherPojo = clotherPojoRepository.getByModelId(0);
		assertNull(clotherPojo);
		logger.info("Test is successfull");
	}

	@Test
	public void findClotherByTypeId() {
		logger.info("Start test findClotherByTypeId");
		List<ClotherPojo> clothes = clotherPojoRepository.getByTypeId(1);
		assertEquals(2, clothes.size());
		logger.info("Test is successfull");
	}

	@Test
	public void findClotherByTypeIdExcept() {
		logger.info("Start test findClotherByTypeIdExcept");
		List<ClotherPojo> clothes = clotherPojoRepository.getByTypeId(0);
		assertEquals(0, clothes.size());
		logger.info("Test is successfull");
	}
}
