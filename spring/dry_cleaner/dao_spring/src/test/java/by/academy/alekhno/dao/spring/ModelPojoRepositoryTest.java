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

import by.academy.alekhno.database.config.AppDAOJPAConfig;
import by.academy.alekhno.database.dao.interf.ModelPojoRepository;
import by.academy.alekhno.spring.pojo.ModelPojo;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppDAOJPAConfig.class })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@Transactional
@TransactionConfiguration(defaultRollback = true)
@DatabaseSetup("classpath:TestResource.xml")
public class ModelPojoRepositoryTest {
	private static Logger logger = Logger.getLogger(UserPojoRepositoryTest.class.getName());

	@Autowired
	private ModelPojoRepository modelPojoRepository;

	@Before
	public void setUp() {
		logger.info("Before test");
	}

	@Test
	public void getModelByName() {
		logger.info("Start test getModelByName");
		ModelPojo modelPojo = modelPojoRepository.getByName("heavy jeans");
		assertEquals(2, modelPojo.getId());
		logger.info("Test is successfull");
	}

	@Test
	public void getByModelNameExcept() {
		logger.info("Start test getByModelNameExcept");
		ModelPojo modelPojo = modelPojoRepository.getByName("except");
		assertNull(modelPojo);
		logger.info("Test is successfull");
	}

	@Test
	public void getModelByTypeId() {
		logger.info("Start test getModelByTypeId");
		List<ModelPojo> models = modelPojoRepository.getByTypeId(1);
		assertEquals(2, models.size());
		logger.info("Test is successfull");
	}

	@Test
	public void getByModelTypeIdExcept() {
		logger.info("Start test getByModelTypeIdExcept");
		List<ModelPojo> models = modelPojoRepository.getByTypeId(0);
		assertEquals(0, models.size());
		logger.info("Test is successfull");
	}
}
