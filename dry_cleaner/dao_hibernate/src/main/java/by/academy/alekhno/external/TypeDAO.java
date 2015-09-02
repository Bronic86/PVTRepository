package by.academy.alekhno.external;

import java.util.List;

import org.hibernate.Session;

import by.academy.alekhno.vo.Type;

public interface TypeDAO {
	
	List<Type> getAll() ;

	void update(Type type) ;
	
	void delete(Type type);
	
	int add(Type type);
	
	Type getByID (Type type);
	
	void setSession(Session session);
	
	Type getByName(String name);
	
}
