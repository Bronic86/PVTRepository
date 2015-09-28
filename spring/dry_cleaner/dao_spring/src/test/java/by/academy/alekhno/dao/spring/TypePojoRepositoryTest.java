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
import by.academy.alekhno.database.dao.interf.TypePojoRepository;
import by.academy.alekhno.spring.pojo.TypePojo;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppDAOJPAConfig.class })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@Transactional
@TransactionConfiguration(defaultRollback = true)
@DatabaseSetup("classpath:TestResource.xml")
public class TypePojoRepositoryTest {
	private static Logger logger = Logger.getLogger(TypePojoRepositoryTest.class.getName());

	@Autowired
	private TypePojoRepository typePojoRepository;

	@Before
	public void setUp() {
		logger.info("Before test");
	}

	@Test
	public void findTypeByName() {
		logger.info("Start test findTypeByName");
		TypePojo typePojo = typePojoRepository.getByName("shirt");
		assertEquals(2, typePojo.getId());
		logger.info("Test is successfull");
	}

	@Test
	public void findTypeByNameExcept() {
		logger.info("Start test findTypeByNameExcept");
		TypePojo typePojo = typePojoRepository.getByName("except");
		assertNull(typePojo);
		logger.info("Test is successfull");
	}
}
