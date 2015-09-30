package by.academy.alekhno.database.dao.interf;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import by.academy.alekhno.spring.pojo.ModelPojo;

@Repository("modelPojoRepository")
public interface ModelPojoRepository extends JpaRepository<ModelPojo, Integer> {

	@Query("SELECT m FROM ModelPojo m WHERE m.name like :model_name")
	ModelPojo getByName(@Param("model_name") String name);

	@Query("SELECT m FROM ModelPojo m JOIN m.type t WHERE t.id = :type_id")
	List<ModelPojo> getByTypeId(@Param("type_id") int type_id);
}
