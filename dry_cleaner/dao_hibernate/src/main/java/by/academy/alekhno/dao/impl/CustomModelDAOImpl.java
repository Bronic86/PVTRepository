package by.academy.alekhno.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import by.academy.alekhno.bundle.Bundle;
import by.academy.alekhno.dao.interf.AbstractDAO;
import by.academy.alekhno.dao.interf.CustomModelDAO;
import by.academy.alekhno.database.pojo.ModelPojo;
import by.academy.alekhno.exception.DaoHibernateException;

public class CustomModelDAOImpl extends AbstractDAO<ModelPojo> implements CustomModelDAO {
	private static final Logger logger = Logger.getLogger(CustomModelDAOImpl.class
			.getName());

	@Override
	protected Class getObjectClass() {
		return ModelPojo.class;
	}

	@Override
	protected Serializable getId(ModelPojo model) {
		return model.getId();
	}

	@Override
	public ModelPojo getByName(String name) throws DaoHibernateException {
		logger.info("Start getByName.");
		logger.debug("Name - " + name);
		List<ModelPojo> models = new ArrayList<ModelPojo>();
		try {
			super.startTransaction();
			String hql = Bundle.getQueryResource("model.get.by.name");
			Query query = super.getSession().createQuery(hql);
			query.setParameter("model_name", name);
			models.addAll(query.list());
			logger.debug("Models quantity - " + models.size());
			super.endTransaction();
		} catch (HibernateException e) {
			logger.debug("getByName method error.");
			super.getTransaction().rollback();
			throw new DaoHibernateException(e);
		} finally {
			closeSession();
		}
		return models.isEmpty() ? null : models.get(0);
	}

	@Override
	public List<ModelPojo> getByTypeId(int type_id) throws DaoHibernateException {
		logger.info("Start getByTypeId.");
		logger.debug("Id - " + type_id);
		List<ModelPojo> models = new ArrayList<ModelPojo>();
		try {
			super.startTransaction();
			String hql = Bundle.getQueryResource("model.get.by.type.id");
			Query query = super.getSession().createQuery(hql);
			query.setParameter("type_id", type_id);
			models.addAll(query.list());
			logger.debug("Models quantity - " + models.size());
			super.endTransaction();
		} catch (HibernateException e) {
			logger.debug("getByTypeId method error.");
			super.getTransaction().rollback();
			throw new DaoHibernateException(e);
		} finally {
			closeSession();
		}
		return models;
	}

	@Override
	public void setFields(ModelPojo model, ModelPojo updateModel) {
		updateModel.setFieldsByAnotherModel(model);
	}

}
