package by.academy.alekhno.spring.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import by.academy.alekhno.bundle.Bundle;
import by.academy.alekhno.spring.dao.interf.AbstractDAO;
import by.academy.alekhno.spring.dao.interf.CustomModelDAO;
import by.academy.alekhno.spring.pojo.ModelPojo;

//@Repository
public class CustomModelDAOImpl extends AbstractDAO<ModelPojo, Integer> implements CustomModelDAO {
	// private static final Logger logger =
	// Logger.getLogger(CustomModelDAOImpl.class.getName());

	/**
	 * Get Model by unique field "name"
	 */
	@Override
	public ModelPojo getByName(String name) {
		List<ModelPojo> models = new ArrayList<ModelPojo>();
		String hql = Bundle.getQueryResource("model.get.by.name");
		Query query = super.getSession().createQuery(hql);
		query.setParameter("model_name", name);
		models.addAll(query.list());
		return models.isEmpty() ? null : models.get(0);
	}

	/**
	 * Get Models by Type field "id"
	 */
	@Override
	public List<ModelPojo> getByTypeId(int type_id) {
		List<ModelPojo> models = new ArrayList<ModelPojo>();
		String hql = Bundle.getQueryResource("model.get.by.type.id");
		Query query = super.getSession().createQuery(hql);
		query.setParameter("type_id", type_id);
		models.addAll(query.list());
		return models;
	}

	@Override
	protected Class getObjectClass() {
		return ModelPojo.class;
	}

	@Override
	protected void setFields(ModelPojo newModel, ModelPojo persistModel) {
		persistModel.setName(newModel.getName());
		persistModel.setType(newModel.getType());
	}

	@Override
	protected Integer getId(ModelPojo model) {
		return model.getId();
	}

}
