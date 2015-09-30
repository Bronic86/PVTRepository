package by.academy.alekhno.database.dao.interf;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import by.academy.alekhno.spring.pojo.TypePojo;

@Repository("typePojoRepository")
public interface TypePojoRepository extends JpaRepository<TypePojo, Integer> {

	@Query("SELECT t FROM TypePojo t WHERE t.name like :type_name")
	TypePojo getByName(@Param("type_name") String name);
}
