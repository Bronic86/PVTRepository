package by.academy.alekhno.external.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import by.academy.alekhno.dao.interf.CustomStateDAO;
import by.academy.alekhno.database.converter.ConverterPojoToVO;
import by.academy.alekhno.database.converter.ConverterVOToPojo;
import by.academy.alekhno.external.StateDAO;
import by.academy.alekhno.vo.State;

public class StateDAOImpl implements StateDAO {
	
	private CustomStateDAO dao = new by.academy.alekhno.dao.impl.StateDAOImpl();
	private by.academy.alekhno.database.pojo.State stateP;
	private List<by.academy.alekhno.database.pojo.State> statesP;

	@Override
	public List<State> getAll() {
		List<State> statesVO = new ArrayList<>();
		statesP = dao.getAll();
		for (by.academy.alekhno.database.pojo.State stateP : statesP) {
			statesVO.add(ConverterPojoToVO.getState(stateP));
		}
		return statesVO;
	}

	@Override
	public void update(State state) {
		stateP = ConverterVOToPojo.getState(state);
		dao.update(stateP);
	}

	@Override
	public void delete(State state) {
		stateP = ConverterVOToPojo.getState(state);
		dao.delete(stateP);
	}

	@Override
	public int add(State state) {
		stateP = ConverterVOToPojo.getState(state);
		int id = dao.add(stateP);
		return id;
	}

	@Override
	public State getByID(State state) {
		stateP = ConverterVOToPojo.getState(state);
		stateP = dao.getByID(stateP);
		state = ConverterPojoToVO.getState(stateP);
		return state;
	}

	@Override
	public void setSession(Session session) {
		dao.setSession(session);
	}

	@Override
	public State getByStateName(String name) {
		stateP = dao.getByState(name);
		State state = ConverterPojoToVO.getState(stateP);
		return state;
	}

}
