package by.academy.alekhno;

import by.academy.alekhno.dao.impl.RoleDAOImpl;
import by.academy.alekhno.dao.interf.CustomRoleDAO;
import by.academy.alekhno.database.pojo.Role;
import by.academy.alekhno.database.util.HibernateUtil;
import by.academy.alekhno.exception.DaoHibernateException;

public class CrudRole {

	public static void main(String[] args) throws DaoHibernateException {
		String name = "testing";
		String nameN = "testing new";
		
		
		CustomRoleDAO daoObj = new RoleDAOImpl();
		daoObj.setSessionFactory(HibernateUtil.getInstance().getSessionFactory());
		
		Role obj = new Role();
		Role objN = new Role();
		Role objU = new Role();
		
//		name = "admin";
		
		obj.setName(name);
		int id = daoObj.add(obj);
		
		
		daoObj.setSessionFactory(HibernateUtil.getInstance().getSessionFactory());
		objN.setId(id);
		objN = daoObj.getByID(obj);
		System.out.println("Add - " + objN);
		
		daoObj.setSessionFactory(HibernateUtil.getInstance().getSessionFactory());
		objU.setId(id);
		objU.setName(nameN);
		daoObj.update(objU);
		
		daoObj.setSessionFactory(HibernateUtil.getInstance().getSessionFactory());
		objN = new Role();
		objN.setId(id);
		objN = daoObj.getByID(obj);
		System.out.println("Update  - " + objN);
		
		daoObj.setSessionFactory(HibernateUtil.getInstance().getSessionFactory());
		objU = new Role();
		objU.setId(id);
		daoObj.delete(objU);
		
		daoObj.setSessionFactory(HibernateUtil.getInstance().getSessionFactory());
		objN = new Role();
		objN.setId(id);
		objN = daoObj.getByID(obj);
		System.out.println("Delete  - " + objN);
		
		
		
	}

}
