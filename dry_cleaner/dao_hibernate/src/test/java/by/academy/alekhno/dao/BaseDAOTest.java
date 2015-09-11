package by.academy.alekhno.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.Before;
import org.unitils.UnitilsJUnit4;
import org.unitils.orm.hibernate.annotation.HibernateSessionFactory;

import by.academy.alekhno.dao.interf.CustomStateDAO;



@HibernateSessionFactory("hibernate1.cfg.xml")
public abstract class BaseDAOTest extends UnitilsJUnit4 {

	
	@HibernateSessionFactory
    private SessionFactory sessionFactory;
	
	private CustomStateDAO daoState;
	
	@Before
    public void initializeDao() {
		System.out.println("Start");
		CustomStateDAO daoState = getDaoUnderTest();
		daoState.setSessionFactory(new AnnotationConfiguration().configure().buildSessionFactory());
//		daoState.setSession(sessionFactory.getCurrentSession());
	}

    protected abstract CustomStateDAO getDaoUnderTest();
	
    public CustomStateDAO getDao(){
    	return daoState;
    }
}
