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
import by.academy.alekhno.database.pojo.Type;
import by.academy.alekhno.database.pojo.User;
import by.academy.alekhno.exception.DaoHibernateException;

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
	public Type getByName(String name) throws DaoHibernateException {
		logger.info("Start getByName.");
		logger.debug("Name - " + name);
		List<Type>  types = new ArrayList<Type>();
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
			logger.debug(e.getStackTrace());
			super.getTransaction().rollback();
			throw new DaoHibernateException(e.getCause());
		} finally {
			closeSession();
		}
		return types.isEmpty() ? null : types.get(0);
	}

	@Override
	protected void setFields(Type type, Type updateType) {
		updateType.setFieldsByAnotherType(type);
	}

}
