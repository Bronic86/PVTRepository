package by.academy.alekhno.spring.dao.interf;

import java.util.List;

public interface GenericDAO<T, Serializable> {

	List<T> getAll();

	void update(T t);

	int add(T t);

	void delete(Serializable id);

	T getByID(Serializable id);

}
