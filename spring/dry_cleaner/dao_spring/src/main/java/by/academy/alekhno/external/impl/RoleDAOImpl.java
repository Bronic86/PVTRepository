package by.academy.alekhno.external.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import by.academy.alekhno.dao.interf.CustomRoleDAO;
import by.academy.alekhno.database.converter.ConverterPojoToVO;
import by.academy.alekhno.database.converter.ConverterVOToPojo;
import by.academy.alekhno.database.pojo.RolePojo;
import by.academy.alekhno.exception.DaoHibernateException;
import by.academy.alekhno.external.RoleDAO;
import by.academy.alekhno.vo.Role;

public class RoleDAOImpl implements RoleDAO {
	private static final Logger logger = Logger
			.getLogger(RoleDAOImpl.class.getName());
	
	private CustomRoleDAO dao;
	private RolePojo roleP;
	private List<RolePojo> rolesP = new ArrayList<RolePojo>();
	
	public RoleDAOImpl() {
		dao = new by.academy.alekhno.dao.impl.CustomRoleDAOImpl();
	}
	

	@Override
	public void update(Role role) throws DaoHibernateException {
		logger.info("Start external update.");
		roleP = ConverterVOToPojo.getRole(role);
		dao.update(roleP);
	}

	@Override
	public List<Role> getAll() throws DaoHibernateException {
		logger.info("Start external getAll.");
		List<Role> rolesVO = new ArrayList<>();
		rolesP.addAll(dao.getAll());
		for (RolePojo roleP : rolesP) {
			rolesVO.add(ConverterPojoToVO.getRole(roleP));
		}
		return rolesVO;
	}

	@Override
	public void delete(Role role) throws DaoHibernateException {
		logger.info("Start external delete.");
		roleP = ConverterVOToPojo.getRole(role);
		dao.delete(roleP);
	}

	@Override
	public int add(Role role) throws DaoHibernateException {
		logger.info("Start external add.");
		roleP = ConverterVOToPojo.getRole(role);
		int id = dao.add(roleP);
		return id;
	}

	@Override
	public Role getByID(Role role) throws DaoHibernateException {
		logger.info("Start external getByID.");
		roleP = ConverterVOToPojo.getRole(role);
		roleP = dao.getByID(roleP);
		role = ConverterPojoToVO.getRole(roleP);
		return role;
	}

	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		dao.setSessionFactory(sessionFactory);
	}

	@Override
	public Role getByName(String name) throws DaoHibernateException {
		logger.info("Start external getByName.");
		roleP = dao.getByName(name);
		Role role = ConverterPojoToVO.getRole(roleP);
		return role;
	}

}
