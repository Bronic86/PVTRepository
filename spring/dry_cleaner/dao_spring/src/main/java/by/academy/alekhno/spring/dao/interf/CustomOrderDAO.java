package by.academy.alekhno.spring.dao.interf;

import java.util.List;

import by.academy.alekhno.spring.pojo.OrderPojo;

public interface CustomOrderDAO extends GenericDAO<OrderPojo, Integer> {

	List<OrderPojo> getOrdersByUserId(int id);

	List<OrderPojo> getOrdersByClotherId(int id);

	List<OrderPojo> getOrdersByTypeId(int type_id);

	List<OrderPojo> getOrdersByStateId(int state_id);
}
