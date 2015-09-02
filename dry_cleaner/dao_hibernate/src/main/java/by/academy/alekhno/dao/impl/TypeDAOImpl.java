package by.academy.alekhno.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import by.academy.alekhno.bundle.Bundle;
import by.academy.alekhno.dao.interf.AbstractDAO;
import by.academy.alekhno.dao.interf.CustomTypeDAO;
import by.academy.alekhno.database.pojo.Type;
import by.academy.alekhno.database.pojo.User;

public class TypeDAOImpl extends AbstractDAO<Type> implements CustomTypeDAO  {
	private static final Logger logger = Logger.getLogger(TypeDAOImpl.class.getName());
	
	@Override
	protected Class getObjectClass() {
		return Type.class;
	}

	@Override
	protected Serializable getId(Type type) {
		return type.getId();
	}

	@Override
	public Type getByName(String name) {
		logger.info("Start getByName.");
		logger.debug("Name - " + name);
		super.startTransaction();
		String hql = Bundle.getQueryResource("type.get.by.name");
		Query query = super.getSession().createQuery(hql);
		query.setParameter("type_name", name);
		List<Type> types = query.list();
		logger.debug("Types quantity - " + types.size());
		super.endTransaction();
		return types.get(0);
	}

	@Override
	protected void setFields(Type type, Type updateType) {
		updateType.setFieldsByAnotherType(type);
	}

}
