package by.academy.alekhno.dao.impl;

import java.io.Serializable;

import org.apache.log4j.Logger;

import by.academy.alekhno.dao.interf.AbstractDAO;
import by.academy.alekhno.dao.interf.CustomClotherDAO;
import by.academy.alekhno.database.pojo.Clother;

public class ClotherDAOImpl extends AbstractDAO<Clother> implements CustomClotherDAO {
	private static final Logger logger = Logger.getLogger(ClotherDAOImpl.class.getName());

	@Override
	protected Class getObjectClass() {
		return Clother.class;
	}

	@Override
	protected Serializable getId(Clother clother) {
		return clother.getId();
	}
	
}
