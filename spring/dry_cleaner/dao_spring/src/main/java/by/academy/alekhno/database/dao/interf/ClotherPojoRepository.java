package by.academy.alekhno.database.dao.interf;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import by.academy.alekhno.spring.pojo.ClotherPojo;

@Repository("clotherPojoRepository")
public interface ClotherPojoRepository extends JpaRepository<ClotherPojo, Integer> {

	@Query("SELECT c FROM ClotherPojo c JOIN c.model m WHERE m.id = :model_id")
	ClotherPojo getByModelId(@Param("model_id") int model_id);

	@Query("SELECT c FROM ClotherPojo c JOIN c.model m JOIN m.type t WHERE t.id = :type_id")
	List<ClotherPojo> getByTypeId(@Param("type_id") int type_id);
}
