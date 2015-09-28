package by.academy.alekhno.test.dao.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Retention(RetentionPolicy.RUNTIME)
@Autowired
@Repository
@Qualifier
public @interface Sql {

}
