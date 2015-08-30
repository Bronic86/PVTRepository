package by.academy.alekhno.dao.impl;

import java.io.Serializable;

import org.apache.log4j.Logger;

import by.academy.alekhno.dao.interf.AbstractDAO;
import by.academy.alekhno.dao.interf.CustomModelDAO;
import by.academy.alekhno.database.pojo.Model;

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

}
