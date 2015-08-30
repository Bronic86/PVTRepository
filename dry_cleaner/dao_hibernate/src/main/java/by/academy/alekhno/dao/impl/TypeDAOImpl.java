package by.academy.alekhno.dao.impl;

import java.io.Serializable;

import org.apache.log4j.Logger;

import by.academy.alekhno.dao.interf.AbstractDAO;
import by.academy.alekhno.dao.interf.CustomTypeDAO;
import by.academy.alekhno.database.pojo.Type;

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

}
