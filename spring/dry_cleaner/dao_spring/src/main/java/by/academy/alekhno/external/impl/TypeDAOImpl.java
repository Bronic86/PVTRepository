package by.academy.alekhno.external.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import by.academy.alekhno.dao.interf.CustomTypeDAO;
import by.academy.alekhno.database.converter.ConverterPojoToVO;
import by.academy.alekhno.database.converter.ConverterVOToPojo;
import by.academy.alekhno.database.pojo.TypePojo;
import by.academy.alekhno.exception.DaoHibernateException;
import by.academy.alekhno.external.TypeDAO;
import by.academy.alekhno.vo.Type;

public class TypeDAOImpl implements TypeDAO {
	private static final Logger logger = Logger
			.getLogger(TypeDAOImpl.class.getName());
	
	private CustomTypeDAO dao;
	private TypePojo typeP;
	private List<TypePojo> typesP = new ArrayList<TypePojo>();
	
	public TypeDAOImpl() {
		dao = new by.academy.alekhno.dao.impl.CustomTypeDAOImpl();
	}

	@Override
	public List<Type> getAll() throws DaoHibernateException {
		logger.info("Start external getAll.");
		List<Type> typesVO = new ArrayList<>();
		typesP.addAll(dao.getAll());
		for (TypePojo typeP : typesP) {
			typesVO.add(ConverterPojoToVO.getType(typeP));
		}
		return typesVO;
	}

	@Override
	public void update(Type type) throws DaoHibernateException {
		logger.info("Start external update.");
		typeP = ConverterVOToPojo.getType(type);
		dao.update(typeP);
	}

	@Override
	public void delete(Type type) throws DaoHibernateException {
		logger.info("Start external delete.");
		typeP = ConverterVOToPojo.getType(type);
		dao.delete(typeP);
	}

	@Override
	public int add(Type type) throws DaoHibernateException {
		logger.info("Start external add.");
		typeP = ConverterVOToPojo.getType(type);
		int id = dao.add(typeP);
		return id;
	}

	@Override
	public Type getByID(Type type) throws DaoHibernateException {
		logger.info("Start external getByID.");
		typeP = ConverterVOToPojo.getType(type);
		typeP = dao.getByID(typeP);
		type = ConverterPojoToVO.getType(typeP);
		return type;
	}

	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		dao.setSessionFactory(sessionFactory);
	}

	@Override
	public Type getByName(String name) throws DaoHibernateException {
		logger.info("Start external getByName.");
		typeP = dao.getByName(name);
		Type type = ConverterPojoToVO.getType(typeP);
		return type;
	}

}
