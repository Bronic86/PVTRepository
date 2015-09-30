package by.academy.alekhno.spring.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import by.academy.alekhno.bundle.Bundle;
import by.academy.alekhno.spring.dao.interf.AbstractDAO;
import by.academy.alekhno.spring.dao.interf.CustomStateDAO;
import by.academy.alekhno.spring.pojo.StatePojo;

//@Repository
public class CustomStateDAOImpl extends AbstractDAO<StatePojo, Integer> implements CustomStateDAO {
	// private static final Logger logger =
	// Logger.getLogger(CustomStateDAOImpl.class.getName());

	/**
	 * Get State by unique field "state"
	 */
	@Override
	public StatePojo getByState(String state) {
		List<StatePojo> states = new ArrayList<StatePojo>();
		String hql = Bundle.getQueryResource("state.get.by.state");
		Query query = super.getSession().createQuery(hql);
		query.setParameter("state_state", state);
		states.addAll(query.list());
		return states.isEmpty() ? null : states.get(0);
	}

	@Override
	protected Class getObjectClass() {
		return StatePojo.class;
	}

	@Override
	protected void setFields(StatePojo newState, StatePojo persistState) {
		persistState.setState(newState.getState());
	}

	@Override
	protected Integer getId(StatePojo state) {
		return state.getId();
	}

}
