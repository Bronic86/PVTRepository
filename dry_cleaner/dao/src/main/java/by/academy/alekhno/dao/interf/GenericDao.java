package by.academy.alekhno.dao.interf;


import java.util.List;

import by.academy.alekhno.exception.SqlException;

public interface GenericDao<T> {

	List<T> getAll() throws SqlException;
	
	void update(T t) throws SqlException;
	
	void delete(T t) throws SqlException;
	
	void add(T t) throws SqlException;
	
	T getByID (T t) throws SqlException;
	
}
