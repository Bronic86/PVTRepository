package by.academy.alekhno.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import by.academy.alekhno.bundle.Bundle;
import by.academy.alekhno.dao.interf.AbstractDAO;
import by.academy.alekhno.dao.interf.CustomClotherDAO;
import by.academy.alekhno.database.pojo.Clother;
import by.academy.alekhno.exception.DaoHibernateException;

public class ClotherDAOImpl extends AbstractDAO<Clother> implements
		CustomClotherDAO {
	private static final Logger logger = Logger.getLogger(ClotherDAOImpl.class
			.getName());

	@Override
	protected Class getObjectClass() {
		return Clother.class;
	}

	@Override
	protected Serializable getId(Clother clother) {
		return clother.getId();
	}

	@Override
	public Clother getByModelId(int model_id) throws DaoHibernateException {
		logger.info("Start getByModelId.");
		logger.debug("Model id - " + model_id);
		List<Clother> clothes = new ArrayList<Clother>();
		try {
			super.startTransaction();
			String hql = Bundle.getQueryResource("clother.get.by.model.id");
			Query query = super.getSession().createQuery(hql);
			query.setParameter("model_id", model_id);
			clothes.addAll(query.list());
			logger.debug("Clothes quantity - " + clothes.size());
			super.endTransaction();
		} catch (HibernateException e) {
			logger.debug("getByModelId method error.");
			logger.debug(e.getStackTrace());
			super.getTransaction().rollback();
			throw new DaoHibernateException(e.getCause());
		} finally {
			closeSession();
		}
		return clothes.isEmpty() ? null : clothes.get(0);
	}

	@Override
	protected void setFields(Clother clother, Clother updateClother) {
		updateClother.setFieldsByAnotherClother(clother);
	}

}
