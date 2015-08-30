package by.academy.alekhno;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;

import by.academy.alekhno.dao.connection.ConnectionPool;
import by.academy.alekhno.dao.interf.CustomClotherDao;
import by.academy.alekhno.dao.interf.CustomModelDao;
import by.academy.alekhno.dao.interf.CustomOrderDao;
import by.academy.alekhno.dao.interf.CustomTypeDao;
import by.academy.alekhno.exception.DaoException;
import by.academy.alekhno.exception.ServiceException;
import by.academy.alekhno.service.impl.ClotherServiceImpl;
import by.academy.alekhno.service.interf.ClotherService;
import by.academy.alekhno.vo.Clother;
import by.academy.alekhno.vo.Model;
import by.academy.alekhno.vo.Order;
import by.academy.alekhno.vo.Type;

public class ClotherServiceTest {
	private static Connection conn;
	private CustomTypeDao daoType;
	private CustomModelDao daoModel;
	private CustomClotherDao daoClother;
	private CustomOrderDao daoOrder;
	private ClotherService clotherServise = new ClotherServiceImpl();
	private Mockery mockingContext = new JUnit4Mockery();
	
	private static final Logger logger = Logger
			.getLogger(ClotherServiceTest.class.getName());

	private final List<Type> types = new ArrayList<Type>();
	private final List<Model> models = new ArrayList<Model>();
	private final List<Clother> clothes = new ArrayList<Clother>();
	private final List<Order> orders = new ArrayList<Order>();
	private final Type type = new Type();
	private final Type typeAdd = new Type();
	private final Type typeDelete = new Type();
	private final Model model = new Model();
	private final Model modelAdd = new Model();
	private final Model modelDelete = new Model();	
	private final Clother clother = new Clother();
	private final Clother clotherAdd = new Clother();
	private final Clother clotherDelete = new Clother();
	private final Order order = new Order();
	private final double price = 15000.;
	private final String name = "jeans";
	private final int id = 1;
	
	private final Connection connection = conn;
	
	static {
		try {
			conn = ConnectionPool.getInstance().getConnection();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Before
	public void setParamTest() {
		daoType = mockingContext.mock(CustomTypeDao.class);
		daoModel = mockingContext.mock(CustomModelDao.class);
		daoClother = mockingContext.mock(CustomClotherDao.class);
		daoOrder = mockingContext.mock(CustomOrderDao.class);
		
		type.setId(id);
		type.setName(name);
		
		typeAdd.setName(name);
		
		typeDelete.setId(id);
		
		model.setId(id);
		model.setType(type);
		model.setName(name);
		
		modelAdd.setType(type);
		modelAdd.setName(name);
		
		modelDelete.setId(id);
		
		clother.setId(id);
		clother.setModel(model);
		clother.setPrice(price);
		
		clotherAdd.setModel(model);
		clotherAdd.setPrice(price);
		
		clotherDelete.setId(id);
		
		order.setId(id);
		
		types.add(type);
		
		models.add(model);
		
		clothes.add(clother);
		
		orders.add(order);
	}
	
	
	@Test
	public void getTypes() throws DaoException, ServiceException  {
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoType).setConnection(connection);
				oneOf(daoType).getAll();
				will(returnValue(types));
			}
		});
		
		clotherServise.setDaoType(daoType);
		List<Type> fTypes = clotherServise.getTypes();
		assertEquals(types, fTypes);
		logger.info("Test getTypes is finished.");
	}
	
	@Test
	public void getModelsByTypeId() throws DaoException, ServiceException  {
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoModel).setConnection(connection);
				oneOf(daoModel).getByTypeId(id);
				will(returnValue(models));
			}
		});
		
		clotherServise.setDaoModel(daoModel);
		List<Model> fModels = clotherServise.getModelsByTypeId(id);
		assertEquals(models, fModels);
		logger.info("Test getModelsByTypeId is finished.");
	}
	
	@Test
	public void getClothesByModelId() throws DaoException, ServiceException  {
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoClother).setConnection(connection);
				oneOf(daoClother).getByModelId(id);
				will(returnValue(clothes));
			}
		});
		
		clotherServise.setDaoClother(daoClother);
		List<Clother> fClothes = clotherServise.getClothesByModelId(id);
		assertEquals(clothes, fClothes);
		logger.info("Test getClothesByModelId is finished.");
	}
	
	@Test
	public void addType() throws DaoException, ServiceException  {
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoType).setConnection(connection);
				oneOf(daoType).getByName(name);
				will(returnValue(null));
				oneOf(daoType).add(typeAdd);
			}
		});
		
		clotherServise.setDaoType(daoType);
		clotherServise.addType(name);
		logger.info("Test addType is finished.");
	}
	
	@Test(expected = ServiceException.class)
	public void addTypeEx() throws DaoException, ServiceException  {
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoType).setConnection(connection);
				oneOf(daoType).getByName(name);
				will(returnValue(type));
				oneOf(daoType).add(typeAdd);
			}
		});
		
		clotherServise.setDaoType(daoType);
		clotherServise.addType(name);
		logger.info("Test addTypeEx is finished.");
	}
	
	@Test
	public void addModel() throws DaoException, ServiceException  {
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoModel).setConnection(connection);
				oneOf(daoModel).getIdByFields(modelAdd);
				will(returnValue(0));
				oneOf(daoModel).add(modelAdd);
			}
		});
		
		clotherServise.setDaoModel(daoModel);
		clotherServise.addModel(modelAdd);
		logger.info("Test addModel is finished.");
	}
	
	@Test(expected = ServiceException.class)
	public void addModelEx() throws DaoException, ServiceException  {
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoModel).setConnection(connection);
				oneOf(daoModel).getIdByFields(modelAdd);
				will(returnValue(id));
				oneOf(daoModel).add(modelAdd);
			}
		});
		
		clotherServise.setDaoModel(daoModel);
		clotherServise.addModel(modelAdd);
		logger.info("Test addModelEx is finished.");
	}
	
	@Test
	public void addClother() throws DaoException, ServiceException  {
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoClother).setConnection(connection);
				oneOf(daoClother).getIdByFields(clotherAdd);
				will(returnValue(0));
				oneOf(daoClother).add(clotherAdd);
			}
		});
		
		clotherServise.setDaoClother(daoClother);
		clotherServise.addClother(clotherAdd);
		logger.info("Test addClother is finished.");
	}
	
	@Test(expected = ServiceException.class)
	public void addClotherEx() throws DaoException, ServiceException  {
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoClother).setConnection(connection);
				oneOf(daoClother).getIdByFields(clotherAdd);
				will(returnValue(id));
				oneOf(daoClother).add(clotherAdd);
			}
		});
		
		clotherServise.setDaoClother(daoClother);
		clotherServise.addClother(clotherAdd);
		logger.info("Test addClotherEx is finished.");
	}
	
	
	@Test
	public void updateType() throws DaoException, ServiceException  {
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoType).setConnection(connection);
				oneOf(daoType).update(type);
			}
		});
		
		clotherServise.setDaoType(daoType);
		clotherServise.updateType(type);
		logger.info("Test updateType is finished.");
	}
	
	@Test
	public void updateModel() throws DaoException, ServiceException  {
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoModel).setConnection(connection);
				oneOf(daoModel).update(model);
			}
		});
		
		clotherServise.setDaoModel(daoModel);
		clotherServise.updateModel(model);
		logger.info("Test updateModel is finished.");
	}
	
	@Test
	public void updateClother() throws DaoException, ServiceException  {
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoClother).setConnection(connection);
				oneOf(daoClother).update(clother);
			}
		});
		
		clotherServise.setDaoClother(daoClother);
		clotherServise.updateClother(clother);
		logger.info("Test updateClother is finished.");
	}
	
	@Test
	public void deleteType() throws DaoException, ServiceException  {
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoModel).setConnection(connection);
				oneOf(daoType).setConnection(connection);
				oneOf(daoModel).getByTypeId(id);
				will(returnValue(new ArrayList<Model>()));
				oneOf(daoType).delete(typeDelete);
			}
		});
		
		clotherServise.setDaoModel(daoModel);
		clotherServise.setDaoType(daoType);
		clotherServise.deleteType(id);
		logger.info("Test deleteType is finished.");
	}
	
	@Test(expected = ServiceException.class)
	public void deleteTypeEx() throws DaoException, ServiceException  {
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoModel).setConnection(connection);
				oneOf(daoType).setConnection(connection);
				oneOf(daoModel).getByTypeId(id);
				will(returnValue(models));
				oneOf(daoType).delete(typeDelete);
			}
		});
		
		clotherServise.setDaoModel(daoModel);
		clotherServise.setDaoType(daoType);
		clotherServise.deleteType(id);
		logger.info("Test deleteTypeEx is finished.");
	}
	
	@Test
	public void deleteModel() throws DaoException, ServiceException  {
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoModel).setConnection(connection);
				oneOf(daoClother).setConnection(connection);
				oneOf(daoClother).getByModelId(id);
				will(returnValue(new ArrayList<Clother>()));
				oneOf(daoModel).delete(modelDelete);
			}
		});
		
		clotherServise.setDaoModel(daoModel);
		clotherServise.setDaoClother(daoClother);
		clotherServise.deleteModel(id);
		logger.info("Test deleteModel is finished.");
	}
	
	@Test(expected = ServiceException.class)
	public void deleteModelEx() throws DaoException, ServiceException  {
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoModel).setConnection(connection);
				oneOf(daoClother).setConnection(connection);
				oneOf(daoClother).getByModelId(id);
				will(returnValue(clothes));
				oneOf(daoModel).delete(modelDelete);
			}
		});
		
		clotherServise.setDaoModel(daoModel);
		clotherServise.setDaoClother(daoClother);
		clotherServise.deleteModel(id);
		logger.info("Test deleteModelEx is finished.");
	}
	
	@Test
	public void deleteClother() throws DaoException, ServiceException  {
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoOrder).setConnection(connection);
				oneOf(daoClother).setConnection(connection);
				oneOf(daoOrder).getOrdersByClotherId(id);
				will(returnValue(new ArrayList<Order>()));
				oneOf(daoClother).delete(clotherDelete);
			}
		});
		
		clotherServise.setDaoOrder(daoOrder);
		clotherServise.setDaoClother(daoClother);
		clotherServise.deleteClother(id);
		logger.info("Test deleteClother is finished.");
	}
	
	@Test(expected = ServiceException.class)
	public void deleteClotherEx() throws DaoException, ServiceException  {
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoOrder).setConnection(connection);
				oneOf(daoClother).setConnection(connection);
				oneOf(daoOrder).getOrdersByClotherId(id);
				will(returnValue(orders));
				oneOf(daoClother).delete(clotherDelete);
			}
		});
		
		clotherServise.setDaoOrder(daoOrder);
		clotherServise.setDaoClother(daoClother);
		clotherServise.deleteClother(id);
		logger.info("Test deleteClother is finished.");
	}
}
