package by.academy.alekhno.dao.interf;

import by.academy.alekhno.database.pojo.Type;

public interface CustomTypeDAO extends GenericDAO<Type> {

	Type getByName(String name);
}
