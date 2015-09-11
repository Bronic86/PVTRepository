package by.academy.alekhno.external.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import by.academy.alekhno.dao.impl.CustomClotherDAOImpl;
import by.academy.alekhno.dao.interf.CustomClotherDAO;
import by.academy.alekhno.database.converter.ConverterPojoToVO;
import by.academy.alekhno.database.converter.ConverterVOToPojo;
import by.academy.alekhno.database.pojo.ClotherPojo;
import by.academy.alekhno.exception.DaoHibernateException;
import by.academy.alekhno.external.ClotherDAO;
import by.academy.alekhno.vo.Clother;

public class ClotherDAOImpl implements ClotherDAO {
	private static final Logger logger = Logger
			.getLogger(ClotherDAOImpl.class.getName());

	private CustomClotherDAO dao;
	private ClotherPojo clotherP;
	private List<ClotherPojo> clothesP = new ArrayList<ClotherPojo>();

	public ClotherDAOImpl() {
		dao = new CustomClotherDAOImpl();
	}
	
	@Override
	public List<Clother> getAll() throws DaoHibernateException {
		logger.info("Start external getAll.");
		List<Clother> clothesVO = new ArrayList<>();
		clothesP.addAll(dao.getAll());
		for (ClotherPojo clotherP : clothesP) {
			clothesVO.add(ConverterPojoToVO.getClother(clotherP));
		}
		return clothesVO;
	}
	
	
	@Override
	public void update(Clother clother) throws DaoHibernateException {
		logger.info("Start external update.");
		clotherP = ConverterVOToPojo.getClother(clother);
		dao.update(clotherP);
	}

	@Override
	public void delete(Clother clother) throws DaoHibernateException {
		logger.info("Start external delete.");
		clotherP = ConverterVOToPojo.getClother(clother);
		dao.delete(clotherP);
	}

	@Override
	public int add(Clother clother) throws DaoHibernateException {
		logger.info("Start external add.");
		clotherP = ConverterVOToPojo.getClother(clother);
		int id = dao.add(clotherP);
		return id;
	}

	@Override
	public Clother getByID(Clother clother) throws DaoHibernateException {
		logger.info("Start external getByID.");
		clotherP = ConverterVOToPojo.getClother(clother);
		clotherP = dao.getByID(clotherP);
		clother = ConverterPojoToVO.getClother(clotherP);
		return clother;
	}

	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		dao.setSessionFactory(sessionFactory);
	}

	@Override
	public Clother getByModelId(int model_id) throws DaoHibernateException {
		logger.info("Start external getByModelId.");
		clotherP = dao.getByModelId(model_id);
		Clother clother = ConverterPojoToVO.getClother(clotherP);
		return clother;
	}

	@Override
	public List<Clother> getByTypeId(int type_id) throws DaoHibernateException {
		logger.info("Start external getByTypeId.");
		List<Clother> clothesVO = new ArrayList<>();
		clothesP.addAll(dao.getByTypeId(type_id));
		for (ClotherPojo clotherP : clothesP) {
			clothesVO.add(ConverterPojoToVO.getClother(clotherP));
		}
		return clothesVO;
	}

}
