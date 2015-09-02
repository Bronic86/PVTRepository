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
			logger.info("Create instance");
		}
		logger.info("Get instance");
		return instance;
	}

	public EntityManagerFactory getEMFactory() {
		logger.info(eMFactory);
		return eMFactory;
	}

//	public void seteMFactory(EntityManagerFactory eMFactory) {
//		this.eMFactory = eMFactory;
//	}
	
	
	
}
