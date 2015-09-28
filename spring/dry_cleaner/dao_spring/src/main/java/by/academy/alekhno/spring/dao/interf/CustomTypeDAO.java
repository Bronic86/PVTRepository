package by.academy.alekhno.spring.dao.interf;

import by.academy.alekhno.spring.pojo.TypePojo;

public interface CustomTypeDAO extends GenericDAO<TypePojo, Integer> {

	TypePojo getByName(String name);
}
