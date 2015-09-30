package by.academy.alekhno.test.dao;

import by.academy.alekhno.test.dao.annotation.Oracle;

@Oracle
public class OracleDao implements MyDao {

	@Override
	public void talk() {
		System.out.println("I am Oracle dao");

	}

}
