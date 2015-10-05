//package by.academy.alekhno;
//
//import static org.junit.Assert.assertEquals;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.logging.Logger;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.jmock.Expectations;
//import org.jmock.Mockery;
//import org.jmock.integration.junit4.JUnit4Mockery;
//import org.junit.Before;
//import org.junit.Ignore;
//import org.junit.Test;
//
//import by.academy.alekhno.database.util.HibernateUtil;
//import by.academy.alekhno.exception.DaoHibernateException;
//import by.academy.alekhno.exception.ServiceException;
//import by.academy.alekhno.external.ClotherDAO;
//import by.academy.alekhno.external.ModelDAO;
//import by.academy.alekhno.external.OrderDAO;
//import by.academy.alekhno.external.TypeDAO;
//import by.academy.alekhno.service.impl.ClotherServiceImpl;
//import by.academy.alekhno.service.interf.ClotherService;
//import by.academy.alekhno.vo.Clother;
//import by.academy.alekhno.vo.Model;
//import by.academy.alekhno.vo.Order;
//import by.academy.alekhno.vo.Type;
//
////@Ignore
//public class ClotherServiceTest {
//	private static SessionFactory sess;
//	private TypeDAO daoType;
//	private ModelDAO daoModel;
//	private ClotherDAO daoClother;
//	private OrderDAO daoOrder;
//	private ClotherService clotherServise = new ClotherServiceImpl();
//	private Mockery mockingContext = new JUnit4Mockery();
//	
//	private static final Logger logger = Logger
//			.getLogger(ClotherServiceTest.class.getName());
//
//	private final List<Type> types = new ArrayList<Type>();
//	private final List<Model> models = new ArrayList<Model>();
//	private final List<Clother> clothes = new ArrayList<Clother>();
//	private final List<Order> orders = new ArrayList<Order>();
//	private final Type type = new Type();
//	private final Type typeAdd = new Type();
//	private final Type typeDelete = new Type();
//	private final Model model = new Model();
//	private final Model modelAdd = new Model();
//	private final Model modelDelete = new Model();	
//	private final Clother clother = new Clother();
//	private final Clother clotherAdd = new Clother();
//	private final Clother clotherDelete = new Clother();
//	private final Order order = new Order();
//	private final double price = 15000.;
//	private final String name = "jeans";
//	private final int id = 1;
//	
//	private final SessionFactory sessionFactory = sess;
//	
//	static {
//			sess = HibernateUtil.getInstance().getSessionFactory();
//	}
//	
//	
//	@Before
//	public void setParamTest() {
//		daoType = mockingContext.mock(TypeDAO.class);
//		daoModel = mockingContext.mock(ModelDAO.class);
//		daoClother = mockingContext.mock(ClotherDAO.class);
//		daoOrder = mockingContext.mock(OrderDAO.class);
//		
//		type.setId(id);
//		type.setName(name);
//		
//		typeAdd.setName(name);
//		
//		typeDelete.setId(id);
//		
//		model.setId(id);
//		model.setType(type);
//		model.setName(name);
//		
//		modelAdd.setType(type);
//		modelAdd.setName(name);
//		
//		modelDelete.setId(id);
//		
//		clother.setId(id);
//		clother.setModel(model);
//		clother.setPrice(price);
//		
//		clotherAdd.setModel(model);
//		clotherAdd.setPrice(price);
//		
//		clotherDelete.setId(id);
//		
//		order.setId(id);
//		
//		types.add(type);
//		
//		models.add(model);
//		
//		clothes.add(clother);
//		
//		orders.add(order);
//	}
//	
//	
//	@Test
//	public void getTypes() throws ServiceException, DaoHibernateException  {
//		logger.info("Start test getTypes.");
//		mockingContext.checking(new Expectations() {
//			{
//				oneOf(daoType).setSessionFactory(sessionFactory);
//				oneOf(daoType).getAll();
//				will(returnValue(types));
//			}
//		});
//		
//		clotherServise.setDaoType(daoType);
//		List<Type> fTypes = clotherServise.getTypes();
//		assertEquals(types, fTypes);
//		logger.info("Test getTypes is finished.");
//	}
//	
//	@Test
//	public void getModelsByTypeId() throws ServiceException, DaoHibernateException  {
//		logger.info("Start test getModelsByTypeId.");
//		mockingContext.checking(new Expectations() {
//			{
//				oneOf(daoModel).setSessionFactory(sessionFactory);
//				oneOf(daoModel).getByTypeId(id);
//				will(returnValue(models));
//			}
//		});
//		
//		clotherServise.setDaoModel(daoModel);
//		List<Model> fModels = clotherServise.getModelsByTypeId(id);
//		assertEquals(models, fModels);
//		logger.info("Test getModelsByTypeId is finished.");
//	}
//	
//	@Test
//	public void getClotherByModelId() throws DaoHibernateException, ServiceException {
//		logger.info("Start test getClotherByModelId.");
//		mockingContext.checking(new Expectations() {
//			{
//				oneOf(daoClother).setSessionFactory(sessionFactory);
//				oneOf(daoClother).getByModelId(id);
//				will(returnValue(clother));
//			}
//		});
//		
//		clotherServise.setDaoClother(daoClother);
//		Clother clotherF = clotherServise.getClotherByModelId(id);
//		assertEquals(clother, clotherF);
//		logger.info("Test getClotherByModelId is finished.");
//	}
//	
//	@Test
//	public void addType() throws ServiceException, DaoHibernateException {
//		logger.info("Start test addType.");
//		mockingContext.checking(new Expectations() {
//			{
//				oneOf(daoType).setSessionFactory(sessionFactory);
//				oneOf(daoType).getByName(name);
//				will(returnValue(null));
//				oneOf(daoType).add(typeAdd);
//				will(returnValue(id));
//			}
//		});
//		
//		clotherServise.setDaoType(daoType);
//		clotherServise.addType(name);
//		logger.info("Test addType is finished.");
//	}
//	
//	
//	@Test(expected = ServiceException.class)
//	public void addTypeFalse() throws ServiceException, DaoHibernateException {
//		logger.info("Start test addTypeFalse.");
//		mockingContext.checking(new Expectations() {
//			{
//				oneOf(daoType).setSessionFactory(sessionFactory);
//				oneOf(daoType).getByName(name);
//				will(returnValue(type));
//			}
//		});
//		
//		clotherServise.setDaoType(daoType);
//		clotherServise.addType(name);
//		logger.info("Test addTypeFalse is finished.");
//	}
//	
//	@Test
//	public void addModel() throws ServiceException, DaoHibernateException  {
//		logger.info("Start test addModel.");
//		mockingContext.checking(new Expectations() {
//			{
//				oneOf(daoModel).setSessionFactory(sessionFactory);
//				oneOf(daoModel).getByName(name);
//				will(returnValue(null));
//				oneOf(daoModel).add(modelAdd);
//			}
//		});
//		
//		clotherServise.setDaoModel(daoModel);
//		clotherServise.addModel(modelAdd);
//		logger.info("Test addModel is finished.");
//	}
//	
//	
//	
//	@Test(expected = ServiceException.class)
//	public void addModelFalse() throws ServiceException, DaoHibernateException  {
//		logger.info("Start test deleteaddModelFalseByID.");
//		mockingContext.checking(new Expectations() {
//			{
//				oneOf(daoModel).setSessionFactory(sessionFactory);
//				oneOf(daoModel).getByName(name);
//				will(returnValue(model));
//			}
//		});
//		
//		clotherServise.setDaoModel(daoModel);
//		clotherServise.addModel(modelAdd);
//		logger.info("Test addModelFalse is finished.");
//	}
//	
//	@Test
//	public void addClother() throws ServiceException, DaoHibernateException  {
//		logger.info("Start test addClother.");
//		mockingContext.checking(new Expectations() {
//			{
//				oneOf(daoClother).setSessionFactory(sessionFactory);
//				oneOf(daoClother).getByModelId(id);
//				will(returnValue(null));
//				oneOf(daoClother).add(clotherAdd);
//			}
//		});
//		
//		clotherServise.setDaoClother(daoClother);
//		clotherServise.addClother(clotherAdd);
//		logger.info("Test addClother is finished.");
//	}
//	
//	
//	@Test(expected = ServiceException.class)
//	public void addClotherFalse() throws ServiceException, DaoHibernateException  {
//		logger.info("Start test addClotherFalse.");
//		mockingContext.checking(new Expectations() {
//			{
//				oneOf(daoClother).setSessionFactory(sessionFactory);
//				oneOf(daoClother).getByModelId(id);
//				will(returnValue(clother));
//			}
//		});
//		
//		clotherServise.setDaoClother(daoClother);
//		clotherServise.addClother(clotherAdd);
//		logger.info("Test addClotherFalse is finished.");
//	}
//	
//	
//	@Test
//	public void updateType() throws ServiceException, DaoHibernateException  {
//		logger.info("Start test updateType.");
//		mockingContext.checking(new Expectations() {
//			{
//				oneOf(daoType).setSessionFactory(sessionFactory);
//				oneOf(daoType).update(type);
//			}
//		});
//		
//		clotherServise.setDaoType(daoType);
//		clotherServise.updateType(type);
//		logger.info("Test updateType is finished.");
//	}
//	
//	@Test
//	public void updateModel() throws ServiceException, DaoHibernateException  {
//		logger.info("Start test updateModel.");
//		mockingContext.checking(new Expectations() {
//			{
//				oneOf(daoModel).setSessionFactory(sessionFactory);
//				oneOf(daoModel).update(model);
//			}
//		});
//		
//		clotherServise.setDaoModel(daoModel);
//		clotherServise.updateModel(model);
//		logger.info("Test updateModel is finished.");
//	}
//	
//	@Test
//	public void updateClother() throws ServiceException, DaoHibernateException  {
//		logger.info("Start test updateClother.");
//		mockingContext.checking(new Expectations() {
//			{
//				oneOf(daoClother).setSessionFactory(sessionFactory);
//				oneOf(daoClother).update(clother);
//			}
//		});
//		
//		clotherServise.setDaoClother(daoClother);
//		clotherServise.updateClother(clother);
//		logger.info("Test updateClother is finished.");
//	}
//	
//	@Test
//	public void deleteType() throws DaoHibernateException, ServiceException  {
//		logger.info("Start test deleteType.");
//		mockingContext.checking(new Expectations() {
//			{
//				oneOf(daoModel).setSessionFactory(sessionFactory);
//				oneOf(daoType).setSessionFactory(sessionFactory);
//				oneOf(daoModel).getByTypeId(id);
//				will(returnValue(new ArrayList<Model>()));
//				oneOf(daoType).delete(typeDelete);
//			}
//		});
//		
//		clotherServise.setDaoModel(daoModel);
//		clotherServise.setDaoType(daoType);
//		clotherServise.deleteType(id);
//		logger.info("Test deleteType is finished.");
//	}
//	
//	
//	@Test(expected = ServiceException.class)
//	public void deleteTypeFalse() throws ServiceException, DaoHibernateException  {
//		logger.info("Start test deleteTypeFalse.");
//		mockingContext.checking(new Expectations() {
//			{
//				oneOf(daoModel).setSessionFactory(sessionFactory);
//				oneOf(daoType).setSessionFactory(sessionFactory);
//				oneOf(daoModel).getByTypeId(id);
//				will(returnValue(models));
//			}
//		});
//		
//		clotherServise.setDaoModel(daoModel);
//		clotherServise.setDaoType(daoType);
//		clotherServise.deleteType(id);
//		logger.info("Test deleteTypeFalse is finished.");
//	}
//	
//	@Test
//	public void deleteModel() throws ServiceException, DaoHibernateException  {
//		logger.info("Start test deleteModel.");
//		mockingContext.checking(new Expectations() {
//			{
//				oneOf(daoModel).setSessionFactory(sessionFactory);
//				oneOf(daoClother).setSessionFactory(sessionFactory);
//				oneOf(daoClother).getByModelId(id);
//				will(returnValue(null));
//				oneOf(daoModel).delete(modelDelete);
//			}
//		});
//		
//		clotherServise.setDaoModel(daoModel);
//		clotherServise.setDaoClother(daoClother);
//		clotherServise.deleteModel(id);
//		logger.info("Test deleteModel is finished.");
//	}
//	
//	
//	@Test(expected = ServiceException.class)
//	public void deleteModelFalse() throws ServiceException, DaoHibernateException  {
//		logger.info("Start test deleteModelFalse.");
//		mockingContext.checking(new Expectations() {
//			{
//				oneOf(daoModel).setSessionFactory(sessionFactory);
//				oneOf(daoClother).setSessionFactory(sessionFactory);
//				oneOf(daoClother).getByModelId(id);
//				will(returnValue(new Clother()));
//			}
//		});
//		
//		clotherServise.setDaoModel(daoModel);
//		clotherServise.setDaoClother(daoClother);
//		clotherServise.deleteModel(id);
//		logger.info("Test deleteModelFalse is finished.");
//	}
//	
//	@Test
//	public void deleteClother() throws ServiceException, DaoHibernateException  {
//		logger.info("Start test deleteClother.");
//		mockingContext.checking(new Expectations() {
//			{
//				oneOf(daoOrder).setSessionFactory(sessionFactory);
//				oneOf(daoClother).setSessionFactory(sessionFactory);
//				oneOf(daoOrder).getOrdersByClotherId(id);
//				will(returnValue(new ArrayList<Order>()));
//				oneOf(daoClother).delete(clotherDelete);
//			}
//		});
//		
//		clotherServise.setDaoOrder(daoOrder);
//		clotherServise.setDaoClother(daoClother);
//		clotherServise.deleteClother(id);
//		logger.info("Test deleteClother is finished.");
//	}
//	
//	
//	@Test(expected = ServiceException.class)
//	public void deleteClotherFalse() throws ServiceException, DaoHibernateException  {
//		logger.info("Start test deleteClotherFalse.");
//		mockingContext.checking(new Expectations() {
//			{
//				oneOf(daoOrder).setSessionFactory(sessionFactory);
//				oneOf(daoClother).setSessionFactory(sessionFactory);
//				oneOf(daoOrder).getOrdersByClotherId(id);
//				will(returnValue(orders));
//			}
//		});
//		
//		clotherServise.setDaoOrder(daoOrder);
//		clotherServise.setDaoClother(daoClother);
//		clotherServise.deleteClother(id);
//		logger.info("Test deleteClotherFalse is finished.");
//	}
//	
//	@Test
//	public void getClotherById() throws ServiceException, DaoHibernateException  {
//		logger.info("Start test getClotherById.");
//		final Clother clotherS = new Clother();
//		clotherS.setId(id);
//		mockingContext.checking(new Expectations() {
//			{
//				oneOf(daoClother).setSessionFactory(sessionFactory);
//				oneOf(daoClother).getByID(clotherS);
//				will(returnValue(clother));
//			}
//		});
//		
//		clotherServise.setDaoClother(daoClother);
//		Clother clotherF = clotherServise.getClotherById(id);
//		assertEquals(clother, clotherF);
//		logger.info("Test getClotherById is finished.");
//	}
// }
