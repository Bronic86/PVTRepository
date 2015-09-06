package by.academy.alekhno.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import by.academy.alekhno.bundle.Bundle;
import by.academy.alekhno.dao.interf.AbstractDAO;
import by.academy.alekhno.dao.interf.CustomStateDAO;
import by.academy.alekhno.database.pojo.State;
import by.academy.alekhno.database.pojo.Type;
import by.academy.alekhno.exception.DaoHibernateException;

public class StateDAOImpl extends AbstractDAO<State> implements CustomStateDAO {
	private static final Logger logger = Logger.getLogger(StateDAOImpl.class
			.getName());

	@Override
	protected Class getObjectClass() {
		return State.class;
	}

	@Override
	protected Serializable getId(State state) {
		return state.getId();
	}

	@Override
	protected void setFields(State state, State updateState) {
		updateState.setFieldsByAnotherState(state);
	}

	@Override
	public State getByState(String state) throws DaoHibernateException {
		logger.info("Start getByState.");
		logger.debug("State - " + state);
		List<State> states = new ArrayList<State>();
		try {
			super.startTransaction();
			String hql = Bundle.getQueryResource("state.get.by.state");
			Query query = super.getSession().createQuery(hql);
			query.setParameter("state_state", state);
			states.addAll(query.list());
			logger.debug("States quantity - " + states.size());
			super.endTransaction();
		} catch (HibernateException e) {
			logger.debug("getByName method error.");
			logger.debug(e.getStackTrace());
			super.getTransaction().rollback();
			throw new DaoHibernateException(e.getCause());
		} finally {
			closeSession();
		}
		return states.isEmpty() ? null : states.get(0);
	}

}
