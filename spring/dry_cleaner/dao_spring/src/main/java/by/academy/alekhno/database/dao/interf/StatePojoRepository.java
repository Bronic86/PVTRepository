package by.academy.alekhno.database.dao.interf;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import by.academy.alekhno.spring.pojo.StatePojo;

@Repository("statePojoRepository")
public interface StatePojoRepository extends JpaRepository<StatePojo, Integer> {

	@Query("SELECT s FROM StatePojo s WHERE s.state like :state_state")
	StatePojo getByState(@Param("state_state") String state);
}
