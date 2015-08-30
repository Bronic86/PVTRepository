package by.academy.alekhno.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

import by.academy.alekhno.dao.connection.ConnectionPool;
import by.academy.alekhno.dao.interf.CustomClotherDao;
import by.academy.alekhno.dao.interf.CustomModelDao;
import by.academy.alekhno.dao.interf.CustomOrderDao;
import by.academy.alekhno.dao.interf.CustomTypeDao;
import by.academy.alekhno.exception.DaoException;
import by.academy.alekhno.exception.ServiceException;
import by.academy.alekhno.service.interf.ClotherService;
import by.academy.alekhno.vo.Clother;
import by.academy.alekhno.vo.Model;
import by.academy.alekhno.vo.Order;
import by.academy.alekhno.vo.Type;

public class ClotherServiceImpl implements ClotherService {
	private CustomTypeDao daoType;
	private CustomModelDao daoModel;
	private CustomClotherDao daoClother;
	private CustomOrderDao daoOrder;
	private static final Logger logger = Logger
			.getLogger(ClotherServiceImpl.class.getName());
	private static Lock lock = new ReentrantLock();

	public ClotherServiceImpl() {
	}

	public List<Type> getTypes() throws DaoException {
		logger.info("GetTypes.");
		List<Type> types = new ArrayList<Type>();
		try {
			lock.lock();
			types = daoType.getAll();
		} finally {
			lock.unlock();
		}
		return types;
	}

	public List<Model> getModelsByTypeId(int type_id) throws DaoException {
		logger.info("GetModelsByTypeId.");
		List<Model> models = new ArrayList<Model>();
		try {
			lock.lock();
			models = daoModel.getByTypeId(type_id);
		} finally {
			lock.unlock();
		}
		return models;
	}

	public List<Clother> getClothesByModelId(int model_id) throws DaoException {
		logger.info("GetClothesByModelId.");
		List<Clother> clothes = new ArrayList<Clother>();
		try {
			lock.lock();
			clothes = daoClother.getByModelId(model_id);
		} finally {
			lock.unlock();
		}
		return clothes;
	}

	public void addType(String name) throws DaoException, ServiceException {
		logger.info("Start addType.");
		Type type = new Type();
		type.setName(name);
		if (!existName(name)) {
			try {
				lock.lock();
				daoType.add(type);
			} finally {
				lock.unlock();
			}
		} else {
			logger.error("Type exist");
			throw new ServiceException("Type exist.");
		}
		logger.info("End addType.");
	}

	private boolean existName(String name) throws DaoException {
		Type type = null;
		try {
			lock.lock();
			type = daoType.getByName(name);
		} finally {
			lock.unlock();
		}
		return type != null;
	}

	public void addModel(Model model) throws DaoException, ServiceException {
		logger.info("Start addModel.");
		if (!existModel(model)) {
			try {
				lock.lock();
				daoModel.add(model);
			} finally {
				lock.unlock();
			}
		} else {
			logger.error("Model exist");
			throw new ServiceException("Type exist.");
		}
		logger.info("End addModel.");
	}

	private boolean existModel(Model model) throws DaoException {
		int id = 0;
		try {
			lock.lock();
			id = daoModel.getIdByFields(model);
		} finally {
			lock.unlock();
		}
		return id != 0;
	}

	public void addClother(Clother clother) throws ServiceException,
			DaoException {
		logger.info("Start addClother.");
		if (!existClother(clother)) {
			try {
				lock.lock();
				daoClother.add(clother);
			} finally {
				lock.unlock();
			}
		} else {
			logger.error("Clother exist");
			throw new ServiceException("Clother exist.");
		}
		logger.info("End addClother.");
	}

	private boolean existClother(Clother clother) throws DaoException {
		int id = 0;
		try {
			lock.lock();
			id = daoClother.getIdByFields(clother);
		} finally {
			lock.unlock();
		}
		return id != 0;
	}

	public void updateType(Type type) throws DaoException {
		logger.info("UpdateType.");
		try {
			lock.lock();
			daoType.update(type);
		} finally {
			lock.unlock();
		}
	}

	public void updateModel(Model model) throws DaoException {
		logger.info("UpdateModel.");
		try {
			lock.lock();
			daoModel.update(model);
		} finally {
			lock.unlock();
		}
	}

	public void updateClother(Clother clother) throws DaoException {
		logger.info("UpdateClother.");
		try {
			lock.lock();
			daoClother.update(clother);
		} finally {
			lock.unlock();
		}
	}

	public void deleteType(int type_id) throws DaoException, ServiceException {
		logger.info("Start deleteType.");
		Type type = new Type();
		type.setId(type_id);
		List<Model> models = new ArrayList<Model>();
		try {
			lock.lock();
			models = daoModel.getByTypeId(type_id);
		} finally {
			lock.unlock();
		}
		if (models.isEmpty()) {
			try {
				lock.lock();
				daoType.delete(type);
			} finally {
				lock.unlock();
			}
		} else {
			logger.error("Models exist. Didn't delete type.");
			throw new ServiceException("Can't delete type.");
		}
		logger.info("End deleteType.");
	}

	public void deleteModel(int model_id) throws DaoException, ServiceException {
		logger.info("Start deleteModel.");
		Model model = new Model();
		model.setId(model_id);
		List<Clother> clothes = new ArrayList<Clother>();
		try {
			lock.lock();
			clothes = daoClother.getByModelId(model_id);
		} finally {
			lock.unlock();
		}
		if (clothes.isEmpty()) {
			try {
				lock.lock();
				daoModel.delete(model);
			} finally {
				lock.unlock();
			}
		} else {
			logger.error("Clother exist. Didn't delete model.");
			throw new ServiceException("Can't delete model.");
		}
		logger.info("End deleteModel.");
	}

	public void deleteClother(int clother_id) throws DaoException,
			ServiceException {
		logger.info("Start deleteClother.");
		Clother clother = new Clother();
		clother.setId(clother_id);
		List<Order> orders = new ArrayList<Order>();
		try {
			lock.lock();
			orders = daoOrder.getOrdersByClotherId(clother_id);
		} finally {
			lock.unlock();
		}
		if (orders.isEmpty()) {
			try {
				lock.lock();
				daoClother.delete(clother);
			} finally {
				lock.unlock();
			}
		} else {
			logger.error("Order exist. Didn't delete model.");
			throw new ServiceException("Can't delete clother.");
		}
		logger.info("End deleteClother.");
	}

	public CustomTypeDao getDaoType() throws ServiceException {
		logger.info("GetDaoType.");
		if (daoType == null) {
			logger.error("DaoType didn't set.");
			throw new ServiceException("Didn't set daoType.");
		}
		return daoType;
	}

	public void setDaoType(CustomTypeDao daoType) throws ServiceException {
		logger.info("SetDaoType.");
		this.daoType = daoType;
		try {
			this.daoType.setConnection(ConnectionPool.getInstance()
					.getConnection());
		} catch (DaoException e) {
			logger.error("Problem with connection to database.", e);
			throw new ServiceException("Sorry. Problem with server.");
		}
	}

	public CustomModelDao getDaoModel() throws ServiceException {
		logger.info("GetDaoModel.");
		if (daoModel == null) {
			logger.error("DaoModel didn't set.");
			throw new ServiceException("Didn't set daoModel.");
		}
		return daoModel;
	}

	public void setDaoModel(CustomModelDao daoModel) throws ServiceException {
		logger.info("SetDaoModel.");
		this.daoModel = daoModel;
		try {
			this.daoModel.setConnection(ConnectionPool.getInstance()
					.getConnection());
		} catch (DaoException e) {
			logger.error("Problem with connection to database.", e);
			throw new ServiceException("Sorry. Problem with server.");
		}
	}

	public CustomClotherDao getDaoClother() throws ServiceException {
		logger.info("GetDaoClother.");
		if (daoClother == null) {
			logger.error("DaoClother didn't set.");
			throw new ServiceException("Didn't set daoClother.");
		}
		return daoClother;
	}

	public void setDaoClother(CustomClotherDao daoClother)
			throws ServiceException {
		logger.info("SetDaoClother.");
		this.daoClother = daoClother;
		try {
			this.daoClother.setConnection(ConnectionPool.getInstance()
					.getConnection());
		} catch (DaoException e) {
			logger.error("Problem with connection to database.", e);
			throw new ServiceException("Sorry. Problem with server.");
		}
	}

	public CustomOrderDao getDaoOrder() throws ServiceException {
		logger.info("getDaoOrder.");
		if (daoOrder == null) {
			logger.error("getDaoOrder didn't set.");
			throw new ServiceException("Didn't set daoClother.");
		}
		return daoOrder;
	}

	public void setDaoOrder(CustomOrderDao daoOrder) throws ServiceException {
		logger.info("setDaoOrder.");
		this.daoOrder = daoOrder;
		try {
			this.daoOrder.setConnection(ConnectionPool.getInstance()
					.getConnection());
		} catch (DaoException e) {
			logger.error("Problem with connection to database.", e);
			throw new ServiceException("Sorry. Problem with server.");
		}
	}

	public Clother getClotherById(int clother_id) throws ServiceException {
		Clother clother = new Clother();
		clother.setId(clother_id);
		Clother fClother;
		try {
			fClother = daoClother.getByID(clother);
		} catch (DaoException e) {
			logger.error("Problem with database. Methode getClotherById.");
			throw new ServiceException("Problem with database");
		}
		return fClother;
	}
}
