package by.academy.alekhno.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;



import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.dbunit.annotation.DataSet;

import by.academy.alekhno.dao.impl.CustomStateDAOImpl;
import by.academy.alekhno.dao.interf.CustomStateDAO;
import by.academy.alekhno.database.pojo.ClotherPojo;
import by.academy.alekhno.database.pojo.ModelPojo;
import by.academy.alekhno.database.pojo.OrderPojo;
import by.academy.alekhno.database.pojo.RolePojo;
import by.academy.alekhno.database.pojo.StatePojo;
import by.academy.alekhno.database.pojo.TypePojo;
import by.academy.alekhno.database.pojo.UserPojo;
import by.academy.alekhno.exception.DaoHibernateException;

@Ignore

@DataSet
public class StateTest extends UnitilsJUnit4 {
	private static final Logger logger = Logger.getLogger(StateTest.class
			.getName());
	
	private UserPojo user;
	private RolePojo role;
	private StatePojo state;
	private ModelPojo model;
	private TypePojo type;
	private ClotherPojo clother;
	private OrderPojo order;


	private CustomStateDAO daoState;
	
//	@HibernateSessionFactory
	private SessionFactory sessionFactory;
	

	
	@Before
	@DataSet
	public void setUp() {
		logger.info("setUp start");
		sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		daoState = new CustomStateDAOImpl();
		daoState.setSessionFactory(sessionFactory);
		logger.info("setSessionFactory");
		user = new UserPojo();
		role = new RolePojo();
		state= new StatePojo();
		type = new TypePojo();
		model = new ModelPojo();
		clother = new ClotherPojo();
		order = new OrderPojo();
	}
	

	@Test
	public void getStateById() throws DaoHibernateException {
		logger.info("getStateById start");
		int id = 1;
		state.setId(id);
		String name = "create";
		StatePojo stateGet = daoState.getByID(state);
		System.out.println(stateGet);
		assertEquals(stateGet.getState(), name);
	}
	
	@Test
	public void getAll() throws DaoHibernateException {
		logger.info("getAll start");
		List<StatePojo> states = new ArrayList<StatePojo>();
		states.addAll(daoState.getAll());
		assertEquals(states.size(), 2);
		logger.info("getAll end successfull.");
	}
	
	@Test
	public void delete() throws DaoHibernateException {
		logger.info("delete start");
		int id = 2;
		state.setId(id);
		daoState.delete(state);
		logger.info("delete end successfull.");
	}
	
	@Test
	public void add() throws DaoHibernateException {
		logger.info("add start");
		state.setId(3);
		String stateName = "test";
		state.setState(stateName);
		int idN = daoState.add(state);
		assertEquals(idN, 3);
		logger.info("add end successfull.");
	}

}
