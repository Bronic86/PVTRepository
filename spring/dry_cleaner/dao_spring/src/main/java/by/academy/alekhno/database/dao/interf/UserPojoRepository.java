package by.academy.alekhno.database.dao.interf;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import by.academy.alekhno.spring.pojo.UserPojo;

@Repository("userPojoRepository")
public interface UserPojoRepository extends JpaRepository<UserPojo, Integer> {

	@Query("SELECT u FROM UserPojo u WHERE u.login like :login")
	UserPojo getByLogin(@Param("login") String login);

	@Query("SELECT u FROM UserPojo u WHERE u.login like :login AND u.password like :password")
	UserPojo getByLoginAndPassword(@Param("login") String login, @Param("password") String password);

}
