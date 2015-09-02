package by.academy.alekhno.external;

import java.util.List;

import org.hibernate.Session;

import by.academy.alekhno.vo.Clother;

public interface ClotherDAO {
	
	List<Clother> getAll() ;

	void update(Clother clother) ;
	
	void delete(Clother clother);
	
	int add(Clother clother);
	
	Clother getByID (Clother clother);
	
	void setSession(Session session);
	
	Clother getByModelId(int model_id);
}
