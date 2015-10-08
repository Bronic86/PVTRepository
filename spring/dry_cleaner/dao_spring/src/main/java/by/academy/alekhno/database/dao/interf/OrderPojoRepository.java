package by.academy.alekhno.database.dao.interf;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import by.academy.alekhno.spring.pojo.OrderPojo;

@Repository("orderPojoRepository")
public interface OrderPojoRepository extends JpaRepository<OrderPojo, Integer> {

	@Query("SELECT o FROM OrderPojo o JOIN o.user u WHERE u.id = :user_id")
	List<OrderPojo> getOrdersByUserId(@Param("user_id") int id);

	@Query("SELECT o FROM OrderPojo o JOIN o.clother c WHERE c.id = :clother_id")
	List<OrderPojo> getOrdersByClotherId(@Param("clother_id") int id);

	@Query("SELECT o FROM OrderPojo o JOIN o.clother c JOIN c.model m JOIN m.type t WHERE t.id = :type_id")
	List<OrderPojo> getOrdersByTypeId(@Param("type_id") int type_id);

	@Query("SELECT o FROM OrderPojo o JOIN o.state s WHERE s.id = :state_id")
	List<OrderPojo> getOrdersByStateId(@Param("state_id") int state_id);

	@Query("SELECT o FROM OrderPojo o JOIN o.user u WHERE u.login = :login")
	List<OrderPojo> getOrdersByUserLogin(@Param("login") String login);
}
