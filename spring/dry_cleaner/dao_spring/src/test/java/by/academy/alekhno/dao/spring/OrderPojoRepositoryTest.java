package by.academy.alekhno.dao.spring;

import static org.junit.Assert.assertEquals;

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
import by.academy.alekhno.database.dao.interf.OrderPojoRepository;
import by.academy.alekhno.spring.pojo.OrderPojo;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppDAOJPAConfigTest.class })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@Transactional
@TransactionConfiguration(defaultRollback = true)
@DatabaseSetup("classpath:TestResource.xml")
public class OrderPojoRepositoryTest {
	private static Logger logger = Logger.getLogger(UserPojoRepositoryTest.class.getName());

	@Autowired
	private OrderPojoRepository orderPojoRepository;

	@Before
	public void setUp() {
		logger.info("Before test");
	}

	@Test
	public void getOrdersByUserId() {
		logger.info("Start test getOrdersByUserId");
		List<OrderPojo> orders = orderPojoRepository.getOrdersByUserId(2);
		assertEquals(2, orders.size());
		logger.info("Test is successfull");
	}

	@Test
	public void getOrdersByUserIdEmpty() {
		logger.info("Start test getOrdersByUserIdEmpty");
		List<OrderPojo> orders = orderPojoRepository.getOrdersByUserId(0);
		assertEquals(0, orders.size());
		logger.info("Test is successfull");
	}

	@Test
	public void getOrdersByClotherId() {
		logger.info("Start test getOrdersByClotherId");
		List<OrderPojo> orders = orderPojoRepository.getOrdersByClotherId(3);
		assertEquals(2, orders.size());
		logger.info("Test is successfull");
	}

	@Test
	public void getOrdersByClotherIdEmpty() {
		logger.info("Start test getOrdersByClotherIdEmpty");
		List<OrderPojo> orders = orderPojoRepository.getOrdersByClotherId(0);
		assertEquals(0, orders.size());
		logger.info("Test is successfull");
	}

	@Test
	public void getOrdersByTypeId() {
		logger.info("Start test getOrdersByTypeId");
		List<OrderPojo> orders = orderPojoRepository.getOrdersByTypeId(2);
		assertEquals(3, orders.size());
		logger.info("Test is successfull");
	}

	@Test
	public void getOrdersByTypeIdEmpty() {
		logger.info("Start test getOrdersByTypeIdEmpty");
		List<OrderPojo> orders = orderPojoRepository.getOrdersByTypeId(0);
		assertEquals(0, orders.size());
		logger.info("Test is successfull");
	}

	@Test
	public void getOrdersByStateId() {
		logger.info("Start test getOrdersByStateId");
		List<OrderPojo> orders = orderPojoRepository.getOrdersByStateId(1);
		assertEquals(2, orders.size());
		logger.info("Test is successfull");
	}

	@Test
	public void getOrdersByStateIdEmpty() {
		logger.info("Start test getOrdersByStateIdEmpty");
		List<OrderPojo> orders = orderPojoRepository.getOrdersByStateId(0);
		assertEquals(0, orders.size());
		logger.info("Test is successfull");
	}
}
