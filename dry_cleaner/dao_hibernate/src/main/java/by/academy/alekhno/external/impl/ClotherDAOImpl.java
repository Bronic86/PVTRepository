package by.academy.alekhno.external.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import by.academy.alekhno.dao.interf.CustomClotherDAO;
import by.academy.alekhno.database.converter.ConverterPojoToVO;
import by.academy.alekhno.database.converter.ConverterVOToPojo;
import by.academy.alekhno.external.ClotherDAO;
import by.academy.alekhno.vo.Clother;

public class ClotherDAOImpl implements ClotherDAO {

	private CustomClotherDAO dao = new by.academy.alekhno.dao.impl.ClotherDAOImpl();
	private by.academy.alekhno.database.pojo.Clother clotherP;
	private List<by.academy.alekhno.database.pojo.Clother> clothesP;

	
	@Override
	public List<Clother> getAll() {
		List<Clother> clothesVO = new ArrayList<>();
		clothesP = dao.getAll();
		for (by.academy.alekhno.database.pojo.Clother clotherP : clothesP) {
			clothesVO.add(ConverterPojoToVO.getClother(clotherP));
		}
		return clothesVO;
	}
	
	
	@Override
	public void update(Clother clother) {
		clotherP = ConverterVOToPojo.getClother(clother);
		dao.update(clotherP);
	}

	@Override
	public void delete(Clother clother) {
		clotherP = ConverterVOToPojo.getClother(clother);
		dao.delete(clotherP);
	}

	@Override
	public int add(Clother clother) {
		clotherP = ConverterVOToPojo.getClother(clother);
		int id = dao.add(clotherP);
		return id;
	}

	@Override
	public Clother getByID(Clother clother) {
		clotherP = ConverterVOToPojo.getClother(clother);
		clotherP = dao.getByID(clotherP);
		clother = ConverterPojoToVO.getClother(clotherP);
		return clother;
	}

	@Override
	public void setSession(Session session) {
		dao.setSession(session);
	}

	@Override
	public Clother getByModelId(int model_id) {
		clotherP = dao.getByModelId(model_id);
		Clother clother = ConverterPojoToVO.getClother(clotherP);
		return clother;
	}

}
