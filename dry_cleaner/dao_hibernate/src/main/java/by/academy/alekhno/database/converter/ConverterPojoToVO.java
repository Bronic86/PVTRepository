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

public class ConverterPojoToVO {
	private static final Logger logger = Logger.getLogger(ConverterPojoToVO.class
			.getName());

	public static User getUser(UserPojo user) {
		logger.info("Convert UserPojo to User VO.");
		if (user == null) {
			logger.debug("Return null");
			return null;
		}
		return new User(user.getId(), user.getLogin(), null,
				user.getFirstName(), user.getSecondName(), user.getTelephone());
	}

	public static Type getType(TypePojo type) {
		logger.info("Convert TypePojo to Type VO.");
		if (type == null) {
			logger.debug("Return null");
			return null;
		}
		return new Type(type.getId(), type.getName());
	}

	public static State getState(StatePojo state) {
		logger.info("Convert StatePojo to State VO.");
		if (state == null) {
			logger.debug("Return null");
			return null;
		}
		return new State(state.getId(), state.getState());
	}

	public static Role getRole(RolePojo role) {
		logger.info("Convert RolePojo to Role VO.");
		if (role == null) {
			logger.debug("Return null");
			return null;
		}
		return new Role(role.getId(), role.getName());
	}

	public static Model getModel(ModelPojo model) {
		logger.info("Convert ModelPojo to Model VO.");
		if (model == null) {
			logger.debug("Return null");
			return null;
		}
		return new Model(model.getId(), model.getName(),
				getType(model.getType()));
	}

	public static Clother getClother(ClotherPojo clother) {
		logger.info("Convert ClotherPojo to Clother VO.");
		if (clother == null) {
			logger.debug("Return null");
			return null;
		}
		return new Clother(clother.getId(), getModel(clother.getModel()),
				clother.getPrice());
	}

	public static Order getOrder(OrderPojo order) {
		logger.info("Convert OrderPojo to Order VO.");
		if (order == null) {
			logger.debug("Return null");
			return null;
		}
		return new Order(order.getId(), order.getQuantity(),
				order.getOrdering_day(), getUser(order.getUser()),
				getClother(order.getClother()), getState(order.getState()));
	}
}
