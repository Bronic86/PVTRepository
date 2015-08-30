package testdao;

import by.academy.alekhno.dao.impl.UserImpl;
import by.academy.alekhno.dao.interf.CustomUserDao;
import by.academy.alekhno.exception.DaoException;
import by.academy.alekhno.vo.User;



public class TestDao {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setLogin("Person");
		user.setPassword("123");
		user.setFirstName("Name");
		user.setSecondName("Surname");
		user.setTelephone(375291234567l);
		System.out.println("1 " + user);
		
		CustomUserDao genericU = new UserImpl();
		try {
			genericU.add(user);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		try {
			System.out.println("after add " + new UserImpl().getByLogin(user.getLogin()));
		} catch (DaoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		User user1 = new User();
		
		user1.setLogin(user.getLogin());
		user1.setPassword(user.getPassword());
		System.out.println("user1 " + user1);
		CustomUserDao uDao = new UserImpl();
		try {
			user = uDao.getByLoginAndPassword(user1);
		} catch (DaoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		user.setFirstName("ChangePerson");
		user.setLogin("newLogin");
		System.out.println("user " + user);
		
		try {
			genericU.update(user);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		
		CustomUserDao daoU = new UserImpl();
		User user2 = new User();
		try {
			user2 = daoU.getByLoginAndPassword(user);
			System.out.println("after update " + user2);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		try {
			genericU.delete(user2);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			user2 = daoU.getByLoginAndPassword(user2);
			System.out.println("after delete " + user2);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

}
