package by.academy.alekhno.dao.impl;

import java.io.Serializable;

import org.apache.log4j.Logger;

import by.academy.alekhno.dao.interf.AbstractDAO;
import by.academy.alekhno.dao.interf.CustomUserDAO;
import by.academy.alekhno.database.pojo.User;

public class UserDAOImpl extends AbstractDAO<User> implements CustomUserDAO {
	private static final Logger logger = Logger.getLogger(UserDAOImpl.class.getName());
	
	@Override
	protected Class getObjectClass() {
		return User.class;
	}

	@Override
	protected Serializable getId(User user) {
		return user.getId();
	}

}
