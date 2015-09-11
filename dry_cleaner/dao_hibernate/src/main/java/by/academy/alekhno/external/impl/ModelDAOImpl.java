package by.academy.alekhno.external.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import by.academy.alekhno.dao.interf.CustomModelDAO;
import by.academy.alekhno.database.converter.ConverterPojoToVO;
import by.academy.alekhno.database.converter.ConverterVOToPojo;
import by.academy.alekhno.database.pojo.ModelPojo;
import by.academy.alekhno.exception.DaoHibernateException;
import by.academy.alekhno.external.ModelDAO;
import by.academy.alekhno.vo.Model;

public class ModelDAOImpl implements ModelDAO {
	private static final Logger logger = Logger
			.getLogger(ModelDAOImpl.class.getName());

	private CustomModelDAO dao;
	private ModelPojo modelP;
	private List<ModelPojo> modelsP = new ArrayList<ModelPojo>();
	
	public ModelDAOImpl() {
		dao = new by.academy.alekhno.dao.impl.CustomModelDAOImpl();
	}

	@Override
	public List<Model> getAll() throws DaoHibernateException {
		logger.info("Start external getAll.");
		List<Model> modelsVO = new ArrayList<>();
		modelsP.addAll(dao.getAll());
		for (ModelPojo modelP : modelsP) {
			modelsVO.add(ConverterPojoToVO.getModel(modelP));
		}
		return modelsVO;
	}

	@Override
	public void update(Model model) throws DaoHibernateException {
		logger.info("Start external update.");
		modelP = ConverterVOToPojo.getModel(model);
		dao.update(modelP);
	}

	@Override
	public void delete(Model model) throws DaoHibernateException {
		logger.info("Start external delete.");
		modelP = ConverterVOToPojo.getModel(model);
		dao.delete(modelP);
	}

	@Override
	public int add(Model model) throws DaoHibernateException {
		logger.info("Start external add.");
		modelP = ConverterVOToPojo.getModel(model);
		int id = dao.add(modelP);
		return id;
	}

	@Override
	public Model getByID(Model model) throws DaoHibernateException {
		logger.info("Start external getByID.");
		modelP = ConverterVOToPojo.getModel(model);
		modelP = dao.getByID(modelP);
		model = ConverterPojoToVO.getModel(modelP);
		return model;
	}

	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		dao.setSessionFactory(sessionFactory);
	}

	@Override
	public Model getByName(String name) throws DaoHibernateException {
		logger.info("Start external getByName.");
		modelP = dao.getByName(name);
		Model model = ConverterPojoToVO.getModel(modelP);
		return model;
	}

	@Override
	public List<Model> getByTypeId(int type_id) throws DaoHibernateException {
		logger.info("Start external getByTypeId.");
		List<Model> modelsVO = new ArrayList<>();
		modelsP.addAll(dao.getByTypeId(type_id));
		for (ModelPojo modelP : modelsP) {
			modelsVO.add(ConverterPojoToVO.getModel(modelP));
		}
		return modelsVO;
	}

}
