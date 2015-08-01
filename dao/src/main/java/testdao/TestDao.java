package testdao;

import by.academy.alekhno.dao.impl.UserDaoImpl;
import by.academy.alekhno.dao.interf.UserDao;
import by.academy.alekhno.vo.User;

public class TestDao {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setLogin("boris");
		UserDao userDao = new UserDaoImpl();
		System.out.println(userDao.getUser(user));
	}

}
