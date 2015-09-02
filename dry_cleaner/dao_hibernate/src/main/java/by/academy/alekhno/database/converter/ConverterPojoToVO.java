package by.academy.alekhno.database.converter;

import by.academy.alekhno.vo.Clother;
import by.academy.alekhno.vo.Model;
import by.academy.alekhno.vo.Order;
import by.academy.alekhno.vo.Role;
import by.academy.alekhno.vo.State;
import by.academy.alekhno.vo.Type;
import by.academy.alekhno.vo.User;

public class ConverterPojoToVO {

	public static User getUser(by.academy.alekhno.database.pojo.User user){
		return new User(user.getId(), user.getLogin(), null, user.getFirstName(), user.getSecondName(), 
				user.getTelephone());
	}
	
	public static Type getType(by.academy.alekhno.database.pojo.Type type){
		return new Type(type.getId(), type.getName());
	}
	
	public static State getState(by.academy.alekhno.database.pojo.State state){
		return new State(state.getId(), state.getState());
	}
	
	public static Role getRole(by.academy.alekhno.database.pojo.Role role){
		return new Role(role.getId(), role.getName());
	}
	
	public static Model getModel(by.academy.alekhno.database.pojo.Model model){
		return new Model(model.getId(), model.getName(), getType(model.getType()));
	}
	
	public static Clother getClother(by.academy.alekhno.database.pojo.Clother clother){
		return new Clother(clother.getId(), getModel(clother.getModel()), clother.getPrice());
	}
	
	public static Order getOrder(by.academy.alekhno.database.pojo.Order order){
		return new Order(order.getId(), order.getQuantity(), order.getOrdering_day(), getUser(order.getUser()),
				 getClother(order.getClother()), getState(order.getState()));
	}
}
