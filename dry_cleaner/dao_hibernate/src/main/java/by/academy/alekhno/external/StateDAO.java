package by.academy.alekhno.external;

import java.util.List;

import org.hibernate.Session;

import by.academy.alekhno.vo.State;

public interface StateDAO {

	List<State> getAll() ;
	
	void update(State state) ;
	
	void delete(State state);
	
	int add(State state);
	
	State getByID (State state);
	
	void setSession(Session session);
	
	State getByStateName(String state);
	
}
