package by.academy.alekhno;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import by.academy.alekhno.database.util.HibernateUtil;
import by.academy.alekhno.exception.ServiceException;
import by.academy.alekhno.external.ClotherDAO;
import by.academy.alekhno.external.ModelDAO;
import by.academy.alekhno.external.OrderDAO;
import by.academy.alekhno.external.TypeDAO;
import by.academy.alekhno.service.impl.ClotherServiceImpl;
import by.academy.alekhno.service.interf.ClotherService;
import by.academy.alekhno.vo.Clother;
import by.academy.alekhno.vo.Model;
import by.academy.alekhno.vo.Order;
import by.academy.alekhno.vo.Type;

public class ClotherServiceTest {
	private static Session sess;
	private TypeDAO daoType;
	private ModelDAO daoModel;
	private ClotherDAO daoClother;
	private OrderDAO daoOrder;
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
	
	private final Session session = sess;
	
	static {
			sess = HibernateUtil.getInstance().getSession();
	}
	
	
	@Before
	public void setParamTest() {
		daoType = mockingContext.mock(TypeDAO.class);
		daoModel = mockingContext.mock(ModelDAO.class);
		daoClother = mockingContext.mock(ClotherDAO.class);
		daoOrder = mockingContext.mock(OrderDAO.class);
		
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
	public void getTypes()  {
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoType).setSession(session);
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
	public void getModelsByTypeId()  {
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoModel).setSession(session);
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
	public void getClothesByModelId() {
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoClother).setSession(session);
				oneOf(daoClother).getByModelId(id);
				will(returnValue(clothes));
			}
		});
		
		clotherServise.setDaoClother(daoClother);
//		List<Clother> fClothes = clotherServise.getClothesByModelId(id);
//		assertEquals(clothes, fClothes);
		logger.info("Test getClothesByModelId is finished.");
	}
	
	@Test
	public void addType() {
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoType).setSession(session);
				oneOf(daoType).getByName(name);
				will(returnValue(null));
				oneOf(daoType).add(typeAdd);
			}
		});
		
		clotherServise.setDaoType(daoType);
		clotherServise.addType(name);
		logger.info("Test addType is finished.");
	}
	
	@Ignore
	@Test(expected = ServiceException.class)
	public void addTypeEx() {
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoType).setSession(session);
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
	public void addModel()  {
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoModel).setSession(session);
//				oneOf(daoModel).getIdByFields(modelAdd);
				will(returnValue(0));
				oneOf(daoModel).add(modelAdd);
			}
		});
		
		clotherServise.setDaoModel(daoModel);
		clotherServise.addModel(modelAdd);
		logger.info("Test addModel is finished.");
	}
	
	@Ignore
	@Test(expected = ServiceException.class)
	public void addModelEx()  {
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoModel).setSession(session);
//				oneOf(daoModel).getIdByFields(modelAdd);
				will(returnValue(id));
				oneOf(daoModel).add(modelAdd);
			}
		});
		
		clotherServise.setDaoModel(daoModel);
		clotherServise.addModel(modelAdd);
		logger.info("Test addModelEx is finished.");
	}
	
	@Test
	public void addClother()  {
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoClother).setSession(session);
//				oneOf(daoClother).getIdByFields(clotherAdd);
				will(returnValue(0));
				oneOf(daoClother).add(clotherAdd);
			}
		});
		
		clotherServise.setDaoClother(daoClother);
		clotherServise.addClother(clotherAdd);
		logger.info("Test addClother is finished.");
	}
	
	@Ignore
	@Test(expected = ServiceException.class)
	public void addClotherEx()  {
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoClother).setSession(session);
//				oneOf(daoClother).getIdByFields(clotherAdd);
				will(returnValue(id));
				oneOf(daoClother).add(clotherAdd);
			}
		});
		
		clotherServise.setDaoClother(daoClother);
		clotherServise.addClother(clotherAdd);
		logger.info("Test addClotherEx is finished.");
	}
	
	
	@Test
	public void updateType()  {
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoType).setSession(session);
				oneOf(daoType).update(type);
			}
		});
		
		clotherServise.setDaoType(daoType);
		clotherServise.updateType(type);
		logger.info("Test updateType is finished.");
	}
	
	@Test
	public void updateModel()  {
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoModel).setSession(session);
				oneOf(daoModel).update(model);
			}
		});
		
		clotherServise.setDaoModel(daoModel);
		clotherServise.updateModel(model);
		logger.info("Test updateModel is finished.");
	}
	
	@Test
	public void updateClother()  {
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoClother).setSession(session);
				oneOf(daoClother).update(clother);
			}
		});
		
		clotherServise.setDaoClother(daoClother);
		clotherServise.updateClother(clother);
		logger.info("Test updateClother is finished.");
	}
	
	@Test
	public void deleteType()  {
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoModel).setSession(session);
				oneOf(daoType).setSession(session);
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
	
	@Ignore
	@Test(expected = ServiceException.class)
	public void deleteTypeEx()  {
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoModel).setSession(session);
				oneOf(daoType).setSession(session);
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
	public void deleteModel()  {
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoModel).setSession(session);
				oneOf(daoClother).setSession(session);
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
	
	@Ignore
	@Test(expected = ServiceException.class)
	public void deleteModelEx()  {
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoModel).setSession(session);
				oneOf(daoClother).setSession(session);
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
	public void deleteClother()  {
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoOrder).setSession(session);
				oneOf(daoClother).setSession(session);
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
	
	@Ignore
	@Test(expected = ServiceException.class)
	public void deleteClotherEx()  {
		mockingContext.checking(new Expectations() {
			{
				oneOf(daoOrder).setSession(session);
				oneOf(daoClother).setSession(session);
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
