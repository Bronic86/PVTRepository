package by.academy.alekhno.database.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

public class EMFactory {
	private static final Logger logger = Logger.getLogger(EMFactory.class.getName());
	private EntityManagerFactory eMFactory;
	private static EMFactory instance;

	private EMFactory() {
		eMFactory = Persistence.createEntityManagerFactory("dry_cleaner");
	}
	
	public static synchronized EMFactory getInstance() {
		if (instance == null) {
			instance = new EMFactory();
			logger.debug("Create instance");
		}
		logger.debug("Get instance");
		return instance;
	}

	public EntityManagerFactory getEMFactory() {
		return eMFactory;
	}

//	public void seteMFactory(EntityManagerFactory eMFactory) {
//		this.eMFactory = eMFactory;
//	}
	
	
	
}
