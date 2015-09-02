package by.academy.alekhno.database.converter;

import by.academy.alekhno.database.pojo.Clother;
import by.academy.alekhno.database.pojo.Model;
import by.academy.alekhno.database.pojo.Order;
import by.academy.alekhno.database.pojo.Role;
import by.academy.alekhno.database.pojo.State;
import by.academy.alekhno.database.pojo.Type;
import by.academy.alekhno.database.pojo.User;

public class ConverterVOToPojo {

	public static User getUser(by.academy.alekhno.vo.User user) {
		if (user == null) {
			return null;
		}
		return new User(user.getId(), user.getLogin(), user.getPassword(),
				user.getFirstName(), user.getSecondName(), user.getTelephone());
	}

	public static Role getRole(by.academy.alekhno.vo.Role role) {
		if (role == null) {
			return null;
		}
		return new Role(role.getId(), role.getName());
	}

	public static Type getType(by.academy.alekhno.vo.Type type) {
		if (type == null) {
			return null;
		}
		return new Type(type.getId(), type.getName());
	}

	public static Model getModel(by.academy.alekhno.vo.Model model) {
		if (model == null) {
			return null;
		}
		return new Model(model.getId(), model.getName(),
				getType(model.getType()));
	}

	public static Clother getClother(by.academy.alekhno.vo.Clother clother) {
		if (clother == null) {
			return null;
		}
		return new Clother(clother.getId(), clother.getPrice(),
				getModel(clother.getModel()));
	}

	public static State getState(by.academy.alekhno.vo.State state) {
		if (state == null) {
			return null;
		}
		return new State(state.getId(), state.getState());
	}

	public static Order getOrder(by.academy.alekhno.vo.Order order) {
		if (order == null) {
			return null;
		}
		return new Order(order.getId(), order.getQuantity(),
				order.getOrdering_day(), getUser(order.getUser()),
				getClother(order.getClother()), getState(order.getState()));
	}
}
