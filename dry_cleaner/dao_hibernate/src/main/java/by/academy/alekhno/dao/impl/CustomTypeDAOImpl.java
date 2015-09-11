package by.academy.alekhno.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import by.academy.alekhno.bundle.Bundle;
import by.academy.alekhno.dao.interf.AbstractDAO;
import by.academy.alekhno.dao.interf.CustomTypeDAO;
import by.academy.alekhno.database.pojo.TypePojo;
import by.academy.alekhno.exception.DaoHibernateException;

public class CustomTypeDAOImpl extends AbstractDAO<TypePojo> implements CustomTypeDAO  {
	private static final Logger logger = Logger.getLogger(CustomTypeDAOImpl.class.getName());
	
	@Override
	protected Class getObjectClass() {
		return TypePojo.class;
	}

	@Override
	protected Serializable getId(TypePojo type) {
		return type.getId();
	}

	@Override
	public TypePojo getByName(String name) throws DaoHibernateException {
		logger.info("Start getByName.");
		logger.debug("Name - " + name);
		List<TypePojo>  types = new ArrayList<TypePojo>();
		try{
		super.startTransaction();
		String hql = Bundle.getQueryResource("type.get.by.name");
		Query query = super.getSession().createQuery(hql);
		query.setParameter("type_name", name);
		types.addAll(query.list());
		logger.debug("Types quantity - " + types.size());
		super.endTransaction();
		} catch (HibernateException e) {
			logger.debug("getByName method error.");
			super.getTransaction().rollback();
			throw new DaoHibernateException(e);
		} finally {
			closeSession();
		}
		return types.isEmpty() ? null : types.get(0);
	}

	@Override
	public void setFields(TypePojo type, TypePojo updateType) {
		updateType.setFieldsByAnotherType(type);
	}

}
