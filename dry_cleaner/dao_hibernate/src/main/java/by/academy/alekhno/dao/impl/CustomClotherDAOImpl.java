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
import by.academy.alekhno.database.pojo.ClotherPojo;
import by.academy.alekhno.exception.DaoHibernateException;

public class CustomClotherDAOImpl extends AbstractDAO<ClotherPojo> implements
		CustomClotherDAO {
	private static final Logger logger = Logger.getLogger(CustomClotherDAOImpl.class
			.getName());

	@Override
	protected Class getObjectClass() {
		return ClotherPojo.class;
	}

	@Override
	protected Serializable getId(ClotherPojo clother) {
		return clother.getId();
	}

	@Override
	public ClotherPojo getByModelId(int model_id) throws DaoHibernateException {
		logger.info("Start getByModelId.");
		logger.debug("Model id - " + model_id);
		List<ClotherPojo> clothes = new ArrayList<ClotherPojo>();
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
			super.getTransaction().rollback();
			throw new DaoHibernateException(e);
		} finally {
			closeSession();
		}
		return clothes.isEmpty() ? null : clothes.get(0);
	}

	@Override
	public void setFields(ClotherPojo clother, ClotherPojo updateClother) {
		updateClother.setFieldsByAnotherClother(clother);
	}

	@Override
	public List<ClotherPojo> getByTypeId(int type_id)
			throws DaoHibernateException {
		logger.info("Start getByModelId.");
		logger.debug("Type id - " + type_id);
		List<ClotherPojo> clothes = new ArrayList<ClotherPojo>();
		try {
			super.startTransaction();
			String hql = Bundle.getQueryResource("clother.get.by.type.id");
			Query query = super.getSession().createQuery(hql);
			query.setParameter("type_id", type_id);
			clothes.addAll(query.list());
			logger.debug("Clothes quantity - " + clothes.size());
			super.endTransaction();
		} catch (HibernateException e) {
			logger.debug("getByTypeId method error.");
			super.getTransaction().rollback();
			throw new DaoHibernateException(e);
		} finally {
			closeSession();
		}
		return clothes;
	}

}
