package by.academy.alekhno.dao;


import java.util.List;

public interface GenericDao<T> {

	List<T> getAll();
	void update(T t);
	void delete(T t);
	void add(T t);
	
}
