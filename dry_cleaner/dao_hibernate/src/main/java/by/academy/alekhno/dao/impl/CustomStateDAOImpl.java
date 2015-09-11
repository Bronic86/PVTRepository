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
import by.academy.alekhno.database.pojo.StatePojo;
import by.academy.alekhno.exception.DaoHibernateException;

public class CustomStateDAOImpl extends AbstractDAO<StatePojo> implements CustomStateDAO {
	private static final Logger logger = Logger.getLogger(CustomStateDAOImpl.class
			.getName());

	@Override
	protected Class getObjectClass() {
		return StatePojo.class;
	}

	@Override
	protected Serializable getId(StatePojo state) {
		return state.getId();
	}

	@Override
	public void setFields(StatePojo state, StatePojo updateState) {
		updateState.setFieldsByAnotherState(state);
	}

	@Override
	public StatePojo getByState(String state) throws DaoHibernateException {
		logger.info("Start getByState.");
		logger.debug("State - " + state);
		List<StatePojo> states = new ArrayList<StatePojo>();
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
			super.getTransaction().rollback();
			throw new DaoHibernateException(e);
		} finally {
			closeSession();
		}
		return states.isEmpty() ? null : states.get(0);
	}

}
