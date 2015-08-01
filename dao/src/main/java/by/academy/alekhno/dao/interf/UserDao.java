package by.academy.alekhno.dao;

import java.util.List;

import by.academy.alekhno.vo.User;

public interface UserDao {

	User getUser (User user);
	List<User> getUsers ();
	void addUser (User user);
	void updateUser (User user);
	void deleteUser (User user);
	
}
