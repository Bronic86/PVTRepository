package by.academy.alekhno.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import by.academy.alekhno.bundle.Bundle;
import by.academy.alekhno.dao.interf.AbstractDAO;
import by.academy.alekhno.dao.interf.CustomModelDAO;
import by.academy.alekhno.database.pojo.Model;
import by.academy.alekhno.database.pojo.Type;

public class ModelDAOImpl extends AbstractDAO<Model> implements CustomModelDAO {
	private static final Logger logger = Logger.getLogger(ModelDAOImpl.class.getName());

	@Override
	protected Class getObjectClass() {
		return Model.class;
	}

	@Override
	protected Serializable getId(Model model) {
		return model.getId();
	}

	@Override
	public Model getByName(String name) {
		logger.info("Start getByName.");
		logger.debug("Name - " + name);
		super.startTransaction();
		String hql = Bundle.getQueryResource("model.get.by.name");
		Query query = super.getSession().createQuery(hql);
		query.setParameter("model_name", name);
		List<Model> models = query.list();
		logger.debug("Models quantity - " + models.size());
		super.endTransaction();
		return models.get(0);
	}

	@Override
	public List<Model> getByTypeId(int type_id) {
		logger.info("Start getByTypeId.");
		logger.debug("Id - " + type_id);
		super.startTransaction();
		String hql = Bundle.getQueryResource("model.get.by.type.id");
		Query query = super.getSession().createQuery(hql);
		query.setParameter("type_id", type_id);
		List<Model> models = query.list();
		logger.debug("Models quantity - " + models.size());
		super.endTransaction();
		return models;
	}

	@Override
	protected void setFields(Model model, Model updateModel) {
		updateModel.setFieldsByAnotherModel(model);
	}

}
