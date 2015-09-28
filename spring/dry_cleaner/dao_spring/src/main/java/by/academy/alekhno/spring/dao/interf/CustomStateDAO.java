package by.academy.alekhno.spring.dao.interf;

import by.academy.alekhno.spring.pojo.StatePojo;

public interface CustomStateDAO extends GenericDAO<StatePojo, Integer> {

	StatePojo getByState(String state);

}
