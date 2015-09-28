package by.academy.alekhno.test.dao;

import javax.annotation.PostConstruct;

import by.academy.alekhno.test.dao.annotation.Oracle;

@org.springframework.stereotype.Service
public class Service implements MyService {

	// @Sql
	@Oracle
	private MyDao dao;

	@PostConstruct
	@Override
	public void whoAreYou() {
		System.out.println("I am service. And you?");
		dao.talk();
	}

}
