package by.academy.alekhno.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.dbunit.annotation.DataSet;

import by.academy.alekhno.dao.impl.StateDAOImpl;
import by.academy.alekhno.dao.interf.CustomStateDAO;
import by.academy.alekhno.database.pojo.State;
import by.academy.alekhno.exception.DaoHibernateException;

@Ignore

@DataSet
public class StateTest extends BaseDAOTest {
	private static final Logger logger = Logger.getLogger(StateTest.class
			.getName());

	private CustomStateDAO daoState;

	// @HibernateSessionFactory
	// private SessionFactory sessionFactory;

	@Before
//	@DataSet
	public void start(){
		super.initializeDao();
		daoState = super.getDao();
	}
	
	
	private final State state = new State();
	private String name = "Testing";
	private final int id = 1;

	// @DataSet
	// @HibernateSessionFactory("hibernate.cfg.xml")
	// @Before
	// public void setUp() throws SQLException {
	// logger.info("Before start");
	// daoState = new StateDAOImpl();
	// // Configuration conf = new AnnotationConfiguration();
	// // sessionFactory = conf.configure().buildSessionFactory();
	// System.out.println(sessionFactory);
	// // HibernateUtil.getInstance().setSessionFactory(sessionFactory);
	// Session session = sessionFactory.openSession();
	// System.out.println(session);
	// daoState.setSession(session);
	//
	// // state.setState(name);
	// state.setId(id);
	// logger.info("Before end");
	// }

	@Test
	public void getStateById() throws DaoHibernateException {
		logger.info("getStateById start");
		State stateGet = daoState.getByID(state);
		System.out.println(stateGet);
	}

	@Override
	protected CustomStateDAO getDaoUnderTest() {
		daoState = new StateDAOImpl();
		return daoState;
	}

	// public void addState(){
	// logger.info("getById start");
	// int idN = daoState.add(state);
	//
	// }
}
