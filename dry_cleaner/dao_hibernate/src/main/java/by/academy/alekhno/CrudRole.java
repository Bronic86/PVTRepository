package by.academy.alekhno;

import by.academy.alekhno.dao.impl.RoleDAOImpl;
import by.academy.alekhno.dao.interf.CustomRoleDAO;
import by.academy.alekhno.database.pojo.Role;
import by.academy.alekhno.database.util.HibernateUtil;

public class CrudRole {

	public static void main(String[] args) {
		String name = "testing";
		String nameN = "testing new";
		
		
		CustomRoleDAO daoObj = new RoleDAOImpl();
		daoObj.setSession(HibernateUtil.getInstance().getSession());
		
		Role obj = new Role();
		Role objN = new Role();
		Role objU = new Role();
		
//		name = "admin";
		
		obj.setName(name);
		int id = daoObj.add(obj);
		
		objN.setId(id);
		objN = daoObj.getByID(obj);
		System.out.println("Add - " + objN);
		
		objU.setId(id);
		objU.setName(nameN);
		daoObj.update(objU);
		
		objN = new Role();
		objN.setId(id);
		objN = daoObj.getByID(obj);
		System.out.println("Update  - " + objN);
		
		objU = new Role();
		objU.setId(id);
		daoObj.delete(objU);
		
		objN = new Role();
		objN.setId(id);
		objN = daoObj.getByID(obj);
		System.out.println("Delete  - " + objN);
		
		
		
	}

}
