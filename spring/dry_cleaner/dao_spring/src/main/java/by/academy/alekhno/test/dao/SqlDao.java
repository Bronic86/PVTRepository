package by.academy.alekhno.test.dao;

import by.academy.alekhno.test.dao.annotation.Sql;

@Sql
public class SqlDao implements MyDao {

	@Override
	public void talk() {
		System.out.println("I am Sql dao");

	}

}
