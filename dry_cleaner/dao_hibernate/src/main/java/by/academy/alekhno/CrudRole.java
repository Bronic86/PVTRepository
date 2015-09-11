package by.academy.alekhno;

import by.academy.alekhno.dao.impl.CustomRoleDAOImpl;
import by.academy.alekhno.dao.interf.CustomRoleDAO;
import by.academy.alekhno.database.pojo.RolePojo;
import by.academy.alekhno.database.util.HibernateUtil;
import by.academy.alekhno.exception.DaoHibernateException;

public class CrudRole {

	public static void main(String[] args) throws DaoHibernateException {
		String name = "testing";
		String nameN = "testing new";
		
		
		CustomRoleDAO daoObj = new CustomRoleDAOImpl();
		daoObj.setSessionFactory(HibernateUtil.getInstance().getSessionFactory());
		
		RolePojo obj = new RolePojo();
		RolePojo objN = new RolePojo();
		RolePojo objU = new RolePojo();
		
		
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
		objN = new RolePojo();
		objN.setId(id);
		objN = daoObj.getByID(obj);
		System.out.println("Update  - " + objN);
		
		daoObj.setSessionFactory(HibernateUtil.getInstance().getSessionFactory());
		objU = new RolePojo();
		objU.setId(id);
		daoObj.delete(objU);
		
		daoObj.setSessionFactory(HibernateUtil.getInstance().getSessionFactory());
		objN = new RolePojo();
		objN.setId(id);
		objN = daoObj.getByID(obj);
		System.out.println("Delete  - " + objN);
		
		
		
	}

}
