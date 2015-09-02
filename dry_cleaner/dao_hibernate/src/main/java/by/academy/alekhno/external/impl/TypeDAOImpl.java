package by.academy.alekhno.external.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import by.academy.alekhno.dao.interf.CustomTypeDAO;
import by.academy.alekhno.database.converter.ConverterPojoToVO;
import by.academy.alekhno.database.converter.ConverterVOToPojo;
import by.academy.alekhno.external.TypeDAO;
import by.academy.alekhno.vo.Type;

public class TypeDAOImpl implements TypeDAO {
	
	private CustomTypeDAO dao = new by.academy.alekhno.dao.impl.TypeDAOImpl();
	private by.academy.alekhno.database.pojo.Type typeP;
	private List<by.academy.alekhno.database.pojo.Type> typesP;

	@Override
	public List<Type> getAll() {
		List<Type> typesVO = new ArrayList<>();
		typesP = dao.getAll();
		for (by.academy.alekhno.database.pojo.Type typeP : typesP) {
			typesVO.add(ConverterPojoToVO.getType(typeP));
		}
		return typesVO;
	}

	@Override
	public void update(Type type) {
		typeP = ConverterVOToPojo.getType(type);
		dao.update(typeP);
	}

	@Override
	public void delete(Type type) {
		typeP = ConverterVOToPojo.getType(type);
		dao.delete(typeP);
	}

	@Override
	public int add(Type type) {
		typeP = ConverterVOToPojo.getType(type);
		int id = dao.add(typeP);
		return id;
	}

	@Override
	public Type getByID(Type type) {
		typeP = ConverterVOToPojo.getType(type);
		typeP = dao.getByID(typeP);
		type = ConverterPojoToVO.getType(typeP);
		return type;
	}

	@Override
	public void setSession(Session session) {
		dao.setSession(session);
	}

	@Override
	public Type getByName(String name) {
		typeP = dao.getByName(name);
		Type type = ConverterPojoToVO.getType(typeP);
		return type;
	}

}
