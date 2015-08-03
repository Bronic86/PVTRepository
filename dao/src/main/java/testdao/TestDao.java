package testdao;

import by.academy.alekhno.dao.impl.ClotherImpl;
import by.academy.alekhno.dao.impl.RoleImpl;
import by.academy.alekhno.dao.impl.UserDaoImpl;
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
		System.out.println(userDao.getUser(user));
		GenericDao<Clother> genericDao = new ClotherImpl();
		Clother clother = new Clother();
		clother.setId(1);
		clother.setPrice(55000);
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
	}

}
