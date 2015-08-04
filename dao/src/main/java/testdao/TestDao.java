package testdao;

import by.academy.alekhno.dao.impl.ClotherImpl;
import by.academy.alekhno.dao.impl.RoleImpl;
import by.academy.alekhno.dao.impl.UserDaoImpl;
<<<<<<< HEAD
=======
import by.academy.alekhno.dao.impl.UserImpl;
>>>>>>> dao
import by.academy.alekhno.dao.interf.GenericDao;
import by.academy.alekhno.dao.interf.UserDao;
import by.academy.alekhno.exception.SqlException;
import by.academy.alekhno.vo.Clother;
import by.academy.alekhno.vo.Model;
import by.academy.alekhno.vo.User;

public class TestDao {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Start method");
		User user = new User();
		user.setLogin("boris");
		UserDao userDao = new UserDaoImpl();
<<<<<<< HEAD
		System.out.println(userDao.getUser(user));
		GenericDao<Clother> genericDao = new ClotherImpl();
		Clother clother = new Clother();
		clother.setId(1);
		clother.setPrice(55000);
=======
		User userDB = userDao.getUser(user);
		System.out.println(userDB);
		
		GenericDao<Clother> genericDao = new ClotherImpl();
		Clother clother = new Clother();
		clother.setId(1);
		try {
			System.out.println(genericDao.getByID(clother));
		} catch (SqlException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		clother.setPrice(60000);
>>>>>>> dao
		Model model = new Model();
		model.setId(1);
		clother.setModel(model);
		
		try {
			genericDao.update(clother);
		} catch (SqlException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			clother = genericDao.getByID(clother);
			System.out.println(clother.getPrice());
		} catch (SqlException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
<<<<<<< HEAD
=======
		
		GenericDao<User> genericDaoU = new UserImpl();
		try {
			System.out.println(genericDaoU.getByID(userDB));
		} catch (SqlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
>>>>>>> dao
	}

}
