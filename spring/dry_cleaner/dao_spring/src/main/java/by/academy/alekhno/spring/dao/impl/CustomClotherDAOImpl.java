package by.academy.alekhno.spring.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import by.academy.alekhno.bundle.Bundle;
import by.academy.alekhno.spring.dao.interf.AbstractDAO;
import by.academy.alekhno.spring.dao.interf.CustomClotherDAO;
import by.academy.alekhno.spring.pojo.ClotherPojo;

//@Repository
public class CustomClotherDAOImpl extends AbstractDAO<ClotherPojo, Integer> implements
		CustomClotherDAO {
	// private static final Logger logger =
	// Logger.getLogger(CustomClotherDAOImpl.class.getName());

	@Override
	protected Class getObjectClass() {
		return ClotherPojo.class;
	}

	@Override
	protected void setFields(ClotherPojo newClother, ClotherPojo persistClother) {
		persistClother.setModel(newClother.getModel());
		persistClother.setPrice(newClother.getPrice());
	}

	@Override
	protected Integer getId(ClotherPojo clother) {
		return clother.getId();
	}

	/**
	 * Get Clother by unique Model field "id"
	 */
	@Override
	public ClotherPojo getByModelId(int model_id) {
		List<ClotherPojo> clothes = new ArrayList<ClotherPojo>();
		String hql = Bundle.getQueryResource("clother.get.by.model.id");
		Query query = super.getSession().createQuery(hql);
		query.setParameter("model_id", model_id);
		clothes.addAll(query.list());
		return clothes.isEmpty() ? null : clothes.get(0);
	}

	/**
	 * Get Clothes by Type field "id"
	 */
	@Override
	public List<ClotherPojo> getByTypeId(int type_id) {
		List<ClotherPojo> clothes = new ArrayList<ClotherPojo>();
		String hql = Bundle.getQueryResource("clother.get.by.type.id");
		Query query = super.getSession().createQuery(hql);
		query.setParameter("type_id", type_id);
		clothes.addAll(query.list());
		return clothes;
	}

}
