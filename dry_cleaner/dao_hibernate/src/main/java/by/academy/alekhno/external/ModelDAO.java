package by.academy.alekhno.external;

import java.util.List;

import org.hibernate.Session;


import by.academy.alekhno.vo.Model;


public interface ModelDAO {
	
	List<Model> getAll() ;

	void update(Model model) ;
	
	void delete(Model model);
	
	int add(Model model);
	
	Model getByID (Model model);
	
	void setSession(Session session);
	
	Model getByName(String name);
	
	List<Model> getByTypeId(int type_id);
}
