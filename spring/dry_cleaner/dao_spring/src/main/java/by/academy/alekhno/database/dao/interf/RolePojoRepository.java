package by.academy.alekhno.database.dao.interf;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import by.academy.alekhno.spring.pojo.RolePojo;

@Repository("rolePojoRepository")
public interface RolePojoRepository extends JpaRepository<RolePojo, Integer> {

	@Query("SELECT r FROM RolePojo r  WHERE r.name like :name")
	RolePojo getByName(@Param("name") String name);

}
