package by.academy.alekhno.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.academy.alekhno.database.util.HibernateUtil;
import by.academy.alekhno.exception.DaoHibernateException;
import by.academy.alekhno.exception.ServiceException;
import by.academy.alekhno.external.ClotherDAO;
import by.academy.alekhno.external.ModelDAO;
import by.academy.alekhno.external.OrderDAO;
import by.academy.alekhno.external.TypeDAO;
import by.academy.alekhno.service.interf.ClotherService;
import by.academy.alekhno.vo.Clother;
import by.academy.alekhno.vo.Model;
import by.academy.alekhno.vo.Order;
import by.academy.alekhno.vo.Type;

public class ClotherServiceImpl implements ClotherService {
	private TypeDAO daoType;
	private ModelDAO daoModel;
	private ClotherDAO daoClother;
	private OrderDAO daoOrder;
	private static final Logger logger = Logger
			.getLogger(ClotherServiceImpl.class.getName());

	public List<Type> getTypes() throws ServiceException {
		logger.info("GetTypes.");
		List<Type> types = new ArrayList<Type>();
		logger.info("types " + types);
		try {
			logger.info("Dao " + daoType);
			types.addAll(daoType.getAll());
		} catch (DaoHibernateException e) {
			logger.error("Problem to daoType, method getAll.");
			throw new ServiceException("getTypes error", e);
		}
		return types;
	}

	public List<Model> getModelsByTypeId(int type_id) throws ServiceException {
		logger.info("GetModelsByTypeId.");
		List<Model> models = new ArrayList<Model>();
		try {
			models.addAll(daoModel.getByTypeId(type_id));
		} catch (DaoHibernateException e) {
			logger.error("Problem to daoModel, method getByTypeId.");
			throw new ServiceException("getModelsByTypeId error", e);
		}
		return models;
	}

	public Clother getClotherByModelId(int model_id) throws ServiceException {
		logger.info("GetByModelId.");
		Clother clother;
		try {
			clother = daoClother.getByModelId(model_id);
		} catch (DaoHibernateException e) {
			logger.error("Problem to daoClother, method getByModelId.");
			throw new ServiceException("getClotherByModelId error", e);
		}
		return clother;
	}

	public void addType(String name) throws ServiceException {
		logger.info("Start addType.");
		Type type = new Type();
		type.setName(name);
		if (!existName(name)) {
			try {
				daoType.add(type);
			} catch (DaoHibernateException e) {
				logger.error("Problem to daoType, method add.");
				throw new ServiceException("addType error", e);
			}
		} else {
			logger.error("Type exist");
			throw new ServiceException("Type exist.");
		}
		logger.info("End addType.");
	}

	private boolean existName(String name) throws ServiceException {
		Type type;
		try {
			type = daoType.getByName(name);
		} catch (DaoHibernateException e) {
			logger.error("Problem to daoType, method getByName.");
			throw new ServiceException("existName error", e);
		}
		return type != null;
	}

	public void addModel(Model model) throws ServiceException {
		logger.info("Start addModel.");
		if (!existModel(model.getName())) {
			try {
				daoModel.add(model);
			} catch (DaoHibernateException e) {
				logger.error("Problem to daoModel, method add.");
				throw new ServiceException("addModel error", e);
			}
		} else {
			logger.error("Model exist");
			throw new ServiceException("Model exist.");
		}
		logger.info("End addModel.");
	}

	private boolean existModel(String name) throws ServiceException {
		Model model;
		try {
			model = daoModel.getByName(name);
		} catch (DaoHibernateException e) {
			logger.error("Problem to daoModel, method getByName.");
			throw new ServiceException("existModel error", e);
		}
		return model != null;
	}

	public void addClother(Clother clother) throws ServiceException {
		logger.info("Start addClother.");
		if (!existClother(clother.getModel())) {
			try {
				daoClother.add(clother);
			} catch (DaoHibernateException e) {
				logger.error("Problem to daoClother, method add.");
				throw new ServiceException("addClother error", e);
			}
		} else {
			logger.error("Clother exist");
			throw new ServiceException("Clother exist.");
		}
		logger.info("End addClother.");
	}

	private boolean existClother(Model model) throws ServiceException {
		Clother clother;
		try {
			clother = daoClother.getByModelId(model.getId());
		} catch (DaoHibernateException e) {
			logger.error("Problem to daoClother, method getByModelId.");
			throw new ServiceException("existClother error", e);
		}
		return clother != null;
	}

	public void updateType(Type type) throws ServiceException {
		logger.info("UpdateType.");
		try {
			daoType.update(type);
		} catch (DaoHibernateException e) {
			logger.error("Problem to daoType, method update.");
			throw new ServiceException("updateType error", e);
		}
	}

	public void updateModel(Model model) throws ServiceException {
		logger.info("UpdateModel.");
		try {
			daoModel.update(model);
		} catch (DaoHibernateException e) {
			logger.error("Problem to daoModel, method update.");
			throw new ServiceException("updateModel error", e);
		}
	}

	public void updateClother(Clother clother) throws ServiceException {
		logger.info("UpdateClother.");
		try {
			daoClother.update(clother);
		} catch (DaoHibernateException e) {
			logger.error("Problem to daoClother, method update.");
			throw new ServiceException("updateClother error", e);
		}
	}

	public void deleteType(int type_id) throws ServiceException {
		logger.info("Start deleteType.");
		Type type = new Type();
		type.setId(type_id);
		List<Model> models;
		try {
			models = daoModel.getByTypeId(type_id);
		} catch (DaoHibernateException e) {
			logger.error("Problem to daoModel, method getByTypeId.");
			throw new ServiceException("deleteType error", e);
		}
		if (models.isEmpty()) {
			try {
				daoType.delete(type);
			} catch (DaoHibernateException e) {
				logger.error("Problem to daoType, method delete.");
				throw new ServiceException("deleteType error", e);
			}
		} else {
			logger.error("Models exist. Didn't delete type.");
			throw new ServiceException("Can't delete type.");
		}
		logger.info("End deleteType.");
	}

	public void deleteModel(int model_id) throws ServiceException {
		logger.info("Start deleteModel.");
		Model model = new Model();
		model.setId(model_id);
		Clother clother;
		try {
			clother = daoClother.getByModelId(model_id);
		} catch (DaoHibernateException e) {
			logger.error("Problem to daoClother, method getByModelId.");
			throw new ServiceException("deleteModel error", e);
		}
		if (clother == null) {
			try {
				daoModel.delete(model);
			} catch (DaoHibernateException e) {
				logger.error("Problem to daoModel, method delete.");
				throw new ServiceException("deleteModel error", e);
			}
		} else {
			logger.error("Clother exist. Didn't delete model.");
			throw new ServiceException("Can't delete model.");
		}
		logger.info("End deleteModel.");
	}

	public void deleteClother(int clother_id) throws ServiceException {
		logger.info("Start deleteClother.");
		Clother clother = new Clother();
		clother.setId(clother_id);
		List<Order> orders = new ArrayList<Order>();
		try {
			orders = daoOrder.getOrdersByClotherId(clother_id);
		} catch (DaoHibernateException e) {
			logger.error("Problem to daoOrder, method getOrdersByClotherId.");
			throw new ServiceException("deleteClother error", e);
		}
		if (orders.isEmpty()) {
			try {
				daoClother.delete(clother);
			} catch (DaoHibernateException e) {
				logger.error("Problem to daoClother, method delete.");
				throw new ServiceException("deleteClother error", e);
			}
		} else {
			logger.error("Order exist. Didn't delete model.");
			throw new ServiceException("Can't delete clother.");
		}
		logger.info("End deleteClother.");
	}

	public void setDaoType(TypeDAO daoType) {
		logger.info("SetDaoType.");
		this.daoType = daoType;
		this.daoType.setSessionFactory(HibernateUtil.getInstance()
				.getSessionFactory());
	}

	public void setDaoModel(ModelDAO daoModel) {
		logger.info("SetDaoModel.");
		this.daoModel = daoModel;
		this.daoModel.setSessionFactory(HibernateUtil.getInstance()
				.getSessionFactory());
	}

	public void setDaoClother(ClotherDAO daoClother) {
		logger.info("SetDaoClother.");
		this.daoClother = daoClother;
		this.daoClother.setSessionFactory(HibernateUtil.getInstance()
				.getSessionFactory());
	}

	public void setDaoOrder(OrderDAO daoOrder) {
		logger.info("setDaoOrder.");
		this.daoOrder = daoOrder;
		this.daoOrder.setSessionFactory(HibernateUtil.getInstance()
				.getSessionFactory());
	}

	public Clother getClotherById(int clother_id) throws ServiceException {
		logger.info("Start getClotherById.");
		Clother clother = new Clother();
		clother.setId(clother_id);
		Clother fClother;
		try {
			fClother = daoClother.getByID(clother);
		} catch (DaoHibernateException e) {
			logger.error("Problem to daoClother, method getByID.");
			throw new ServiceException("getClotherById error", e);
		}
		return fClother;
	}

	@Override
	public List<Clother> getClothesByTypeId(int type_id) throws ServiceException {
		logger.info("Start getClothesByTypeId.");
		List<Clother> clothes = new ArrayList<Clother>();
		try {
			clothes.addAll(daoClother.getByTypeId(type_id));
		} catch (DaoHibernateException e) {
			logger.error("Problem to daoClother, method getByTypeId.");
			throw new ServiceException("getClothesByTypeId error", e);
		}
		return clothes;
	}
}
