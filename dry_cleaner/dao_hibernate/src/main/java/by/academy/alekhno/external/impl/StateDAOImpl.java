package by.academy.alekhno.external.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import by.academy.alekhno.dao.interf.CustomStateDAO;
import by.academy.alekhno.database.converter.ConverterPojoToVO;
import by.academy.alekhno.database.converter.ConverterVOToPojo;
import by.academy.alekhno.database.pojo.StatePojo;
import by.academy.alekhno.exception.DaoHibernateException;
import by.academy.alekhno.external.StateDAO;
import by.academy.alekhno.vo.State;

public class StateDAOImpl implements StateDAO {
	private static final Logger logger = Logger
			.getLogger(StateDAOImpl.class.getName());
	
	private CustomStateDAO dao;
	private StatePojo stateP;
	private List<StatePojo> statesP = new ArrayList<StatePojo>();
	
	public StateDAOImpl() {
		dao = new by.academy.alekhno.dao.impl.CustomStateDAOImpl();
	}

	@Override
	public List<State> getAll() throws DaoHibernateException {
		logger.info("Start external getAll.");
		List<State> statesVO = new ArrayList<>();
		statesP.addAll(dao.getAll());
		for (StatePojo stateP : statesP) {
			statesVO.add(ConverterPojoToVO.getState(stateP));
		}
		return statesVO;
	}

	@Override
	public void update(State state) throws DaoHibernateException {
		logger.info("Start external update.");
		stateP = ConverterVOToPojo.getState(state);
		dao.update(stateP);
	}

	@Override
	public void delete(State state) throws DaoHibernateException {
		logger.info("Start external delete.");
		stateP = ConverterVOToPojo.getState(state);
		dao.delete(stateP);
	}

	@Override
	public int add(State state) throws DaoHibernateException {
		logger.info("Start external add.");
		stateP = ConverterVOToPojo.getState(state);
		int id = dao.add(stateP);
		return id;
	}

	@Override
	public State getByID(State state) throws DaoHibernateException {
		logger.info("Start external getByID.");
		stateP = ConverterVOToPojo.getState(state);
		stateP = dao.getByID(stateP);
		state = ConverterPojoToVO.getState(stateP);
		return state;
	}

	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		dao.setSessionFactory(sessionFactory);
	}

	@Override
	public State getByStateName(String name) throws DaoHibernateException {
		logger.info("Start external getByStateName.");
		stateP = dao.getByState(name);
		State state = ConverterPojoToVO.getState(stateP);
		return state;
	}

}
