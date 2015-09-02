package by.academy.alekhno.external.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import by.academy.alekhno.dao.interf.CustomRoleDAO;
import by.academy.alekhno.database.converter.ConverterPojoToVO;
import by.academy.alekhno.database.converter.ConverterVOToPojo;
import by.academy.alekhno.external.RoleDAO;
import by.academy.alekhno.vo.Role;

public class RoleDAOImpl implements RoleDAO {
	
	
	private CustomRoleDAO dao = new by.academy.alekhno.dao.impl.RoleDAOImpl();
	private by.academy.alekhno.database.pojo.Role roleP;
	private List<by.academy.alekhno.database.pojo.Role> rolesP;
	

	@Override
	public void update(Role role) {
		roleP = ConverterVOToPojo.getRole(role);
		dao.update(roleP);
	}

	@Override
	public List<Role> getAll() {
		List<Role> rolesVO = new ArrayList<>();
		rolesP = dao.getAll();
		for (by.academy.alekhno.database.pojo.Role roleP : rolesP) {
			rolesVO.add(ConverterPojoToVO.getRole(roleP));
		}
		return rolesVO;
	}

	@Override
	public void delete(Role role) {
		roleP = ConverterVOToPojo.getRole(role);
		dao.delete(roleP);
	}

	@Override
	public int add(Role role) {
		roleP = ConverterVOToPojo.getRole(role);
		int id = dao.add(roleP);
		return id;
	}

	@Override
	public Role getByID(Role role) {
		roleP = ConverterVOToPojo.getRole(role);
		roleP = dao.getByID(roleP);
		role = ConverterPojoToVO.getRole(roleP);
		return role;
	}

	@Override
	public void setSession(Session session) {
		dao.setSession(session);
	}

}
