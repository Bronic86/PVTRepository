package by.academy.alekhno.spring.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import by.academy.alekhno.bundle.Bundle;
import by.academy.alekhno.spring.dao.interf.AbstractDAO;
import by.academy.alekhno.spring.dao.interf.CustomTypeDAO;
import by.academy.alekhno.spring.pojo.TypePojo;

//@Repository
public class CustomTypeDAOImpl extends AbstractDAO<TypePojo, Integer> implements CustomTypeDAO {
	// private static final Logger logger =
	// Logger.getLogger(CustomTypeDAOImpl.class.getName());

	@Override
	protected Class getObjectClass() {
		return TypePojo.class;
	}

	@Override
	protected void setFields(TypePojo newType, TypePojo persistType) {
		persistType.setName(newType.getName());
	}

	@Override
	protected Integer getId(TypePojo type) {
		return type.getId();
	}

	/**
	 * Get Type by unique field "name"
	 */
	@Override
	public TypePojo getByName(String name) {
		List<TypePojo> types = new ArrayList<TypePojo>();
		String hql = Bundle.getQueryResource("type.get.by.name");
		Query query = super.getSession().createQuery(hql);
		query.setParameter("type_name", name);
		types.addAll(query.list());
		return types.isEmpty() ? null : types.get(0);
	}

}
