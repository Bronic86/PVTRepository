package by.academy.alekhno.dao.impl;

import java.io.Serializable;

import org.apache.log4j.Logger;

import by.academy.alekhno.dao.interf.AbstractDAO;
import by.academy.alekhno.dao.interf.CustomStateDAO;
import by.academy.alekhno.database.pojo.State;

public class StateDAOImpl extends AbstractDAO<State> implements CustomStateDAO {
	private static final Logger logger = Logger.getLogger(StateDAOImpl.class.getName());

	@Override
	protected Class getObjectClass() {
		return State.class;
	}

	@Override
	protected Serializable getId(State state) {
		return state.getId();
	}

}
