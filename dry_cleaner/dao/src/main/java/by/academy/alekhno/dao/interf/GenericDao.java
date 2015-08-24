package by.academy.alekhno.dao.interf;


import java.sql.Connection;
import java.util.List;



import by.academy.alekhno.exception.DaoException;

public interface GenericDao<T> {

	List<T> getAll() throws DaoException;
	
	void update(T t) throws DaoException;
	
	void delete(T t) throws DaoException;
	
	void add(T t) throws DaoException;
	
	T getByID (T t) throws DaoException;
	
	void setConnection(Connection connection);
	
	Connection getConnection();
}
