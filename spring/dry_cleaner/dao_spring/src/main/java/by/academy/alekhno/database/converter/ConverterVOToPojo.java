package by.academy.alekhno.database.converter;

import org.apache.log4j.Logger;

import by.academy.alekhno.database.pojo.ClotherPojo;
import by.academy.alekhno.database.pojo.ModelPojo;
import by.academy.alekhno.database.pojo.OrderPojo;
import by.academy.alekhno.database.pojo.RolePojo;
import by.academy.alekhno.database.pojo.StatePojo;
import by.academy.alekhno.database.pojo.TypePojo;
import by.academy.alekhno.database.pojo.UserPojo;
import by.academy.alekhno.vo.Clother;
import by.academy.alekhno.vo.Model;
import by.academy.alekhno.vo.Order;
import by.academy.alekhno.vo.Role;
import by.academy.alekhno.vo.State;
import by.academy.alekhno.vo.Type;
import by.academy.alekhno.vo.User;

public class ConverterVOToPojo {
	private static final Logger logger = Logger.getLogger(ConverterVOToPojo.class
			.getName());

	public static UserPojo getUser(User user) {
		logger.info("Convert User to UserPojo.");
		if (user == null) {
			return null;
		}
		return new UserPojo(user.getId(), user.getLogin(), user.getPassword(),
				user.getFirstName(), user.getSecondName(), user.getTelephone());
	}

	public static RolePojo getRole(Role role) {
		logger.info("Convert Role to RolePojo.");
		if (role == null) {
			return null;
		}
		return new RolePojo(role.getId(), role.getName());
	}

	public static TypePojo getType(Type type) {
		logger.info("Convert Type to TypePojo.");
		if (type == null) {
			return null;
		}
		return new TypePojo(type.getId(), type.getName());
	}

	public static ModelPojo getModel(Model model) {
		logger.info("Convert Model to ModelPojo.");
		if (model == null) {
			return null;
		}
		return new ModelPojo(model.getId(), model.getName(),
				getType(model.getType()));
	}

	public static ClotherPojo getClother(Clother clother) {
		logger.info("Convert Clother to ClotherPojo.");
		if (clother == null) {
			return null;
		}
		return new ClotherPojo(clother.getId(), clother.getPrice(),
				getModel(clother.getModel()));
	}

	public static StatePojo getState(State state) {
		logger.info("Convert State to StatePojo.");
		if (state == null) {
			return null;
		}
		return new StatePojo(state.getId(), state.getState());
	}

	public static OrderPojo getOrder(Order order) {
		logger.info("Convert Order to OrderPojo.");
		if (order == null) {
			return null;
		}
		return new OrderPojo(order.getId(), order.getQuantity(),
				order.getOrdering_day(), getUser(order.getUser()),
				getClother(order.getClother()), getState(order.getState()));
	}
}
