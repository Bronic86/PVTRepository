package by.academy.alekhno.spring.dao.interf;

import by.academy.alekhno.spring.pojo.RolePojo;

public interface CustomRoleDAO extends GenericDAO<RolePojo, Integer> {

	RolePojo getByName(String name);
}
