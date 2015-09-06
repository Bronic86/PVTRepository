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

	public ClotherServiceImpl() {
	}

	public List<Type> getTypes() throws ServiceException {
		logger.info("GetTypes.");
		List<Type> types = new ArrayList<Type>();
			try {
				types = daoType.getAll();
			} catch (DaoHibernateException e) {
				logger.error("Problem to daoType, method getAll.");
				throw new ServiceException("getTypes error", e.getStackTrace(), e.getCause());
			}
		return types;
	}

	public List<Model> getModelsByTypeId(int type_id) throws ServiceException {
		logger.info("GetModelsByTypeId.");
		List<Model> models = new ArrayList<Model>();
		try {
			models = daoModel.getByTypeId(type_id);
		} catch (DaoHibernateException e) {
			logger.error("Problem to daoModel, method getByTypeId.");
			throw new ServiceException("getModelsByTypeId error", e.getStackTrace(), e.getCause());
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
			throw new ServiceException("getClotherByModelId error", e.getStackTrace(), e.getCause());
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
					throw new ServiceException("addType error", e.getStackTrace(), e.getCause());
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
			throw new ServiceException("existName error", e.getStackTrace(), e.getCause());
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
				throw new ServiceException("addModel error", e.getStackTrace(), e.getCause());
			}
		} else {
			logger.error("Model exist");
//			throw new ServiceException("Type exist.");
		}
		logger.info("End addModel.");
	}

	private boolean existModel(String name) throws ServiceException {
		Model model;
		try {
			model = daoModel.getByName(name);
		} catch (DaoHibernateException e) {
			logger.error("Problem to daoModel, method getByName.");
			throw new ServiceException("existModel error", e.getStackTrace(), e.getCause());
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
				throw new ServiceException("addClother error", e.getStackTrace(), e.getCause());
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
			throw new ServiceException("existClother error", e.getStackTrace(), e.getCause());
		}
		return clother != null;
	}

	public void updateType(Type type) throws ServiceException {
		logger.info("UpdateType.");
		try {
			daoType.update(type);
		} catch (DaoHibernateException e) {
			logger.error("Problem to daoType, method update.");
			throw new ServiceException("updateType error", e.getStackTrace(), e.getCause());
		}
	}

	public void updateModel(Model model) throws ServiceException {
		logger.info("UpdateModel.");
		try {
			daoModel.update(model);
		} catch (DaoHibernateException e) {
			logger.error("Problem to daoModel, method update.");
			throw new ServiceException("updateModel error", e.getStackTrace(), e.getCause());
		}
	}

	public void updateClother(Clother clother) throws ServiceException {
		logger.info("UpdateClother.");
		try {
			daoClother.update(clother);
		} catch (DaoHibernateException e) {
			logger.error("Problem to daoClother, method update.");
			throw new ServiceException("updateClother error", e.getStackTrace(), e.getCause());
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
			throw new ServiceException("deleteType error", e.getStackTrace(), e.getCause());
		}
		if (models.isEmpty()) {
			try {
				daoType.delete(type);
			} catch (DaoHibernateException e) {
				logger.error("Problem to daoType, method delete.");
				throw new ServiceException("deleteType error", e.getStackTrace(), e.getCause());
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
			throw new ServiceException("deleteModel error", e.getStackTrace(), e.getCause());
		}
		if (clother == null) {
			try {
				daoModel.delete(model);
			} catch (DaoHibernateException e) {
				logger.error("Problem to daoModel, method delete.");
				throw new ServiceException("deleteModel error", e.getStackTrace(), e.getCause());
			}
		} else {
			logger.error("Clother exist. Didn't delete model.");
//			throw new ServiceException("Can't delete model.");
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
			throw new ServiceException("deleteClother error", e.getStackTrace(), e.getCause());
		}
		if (orders.isEmpty()) {
			try {
				daoClother.delete(clother);
			} catch (DaoHibernateException e) {
				logger.error("Problem to daoClother, method delete.");
				throw new ServiceException("deleteClother error", e.getStackTrace(), e.getCause());
			}
		} else {
			logger.error("Order exist. Didn't delete model.");
			throw new ServiceException("Can't delete clother.");
		}
		logger.info("End deleteClother.");
	}

//	public CustomTypeDao getDaoType() throws ServiceException {
//		logger.info("GetDaoType.");
//		if (daoType == null) {
//			logger.error("DaoType didn't set.");
//			throw new ServiceException("Didn't set daoType.");
//		}
//		return daoType;
//	}

	public void setDaoType(TypeDAO daoType) {
		logger.info("SetDaoType.");
		this.daoType = daoType;
		this.daoType.setSessionFactory(HibernateUtil.getInstance().getSessionFactory());
	}

//	public CustomModelDao getDaoModel() throws ServiceException {
//		logger.info("GetDaoModel.");
//		if (daoModel == null) {
//			logger.error("DaoModel didn't set.");
//			throw new ServiceException("Didn't set daoModel.");
//		}
//		return daoModel;
//	}

	public void setDaoModel(ModelDAO daoModel) {
		logger.info("SetDaoModel.");
		this.daoModel = daoModel;
		this.daoModel.setSessionFactory(HibernateUtil.getInstance().getSessionFactory());
	}

//	public CustomClotherDao getDaoClother() throws ServiceException {
//		logger.info("GetDaoClother.");
//		if (daoClother == null) {
//			logger.error("DaoClother didn't set.");
//			throw new ServiceException("Didn't set daoClother.");
//		}
//		return daoClother;
//	}

	public void setDaoClother(ClotherDAO daoClother) {
		logger.info("SetDaoClother.");
		this.daoClother = daoClother;
		this.daoClother.setSessionFactory(HibernateUtil.getInstance().getSessionFactory());
	}

//	public CustomOrderDao getDaoOrder() throws ServiceException {
//		logger.info("getDaoOrder.");
//		if (daoOrder == null) {
//			logger.error("getDaoOrder didn't set.");
//			throw new ServiceException("Didn't set daoClother.");
//		}
//		return daoOrder;
//	}

	public void setDaoOrder(OrderDAO daoOrder) {
		logger.info("setDaoOrder.");
		this.daoOrder = daoOrder;
		this.daoOrder.setSessionFactory(HibernateUtil.getInstance().getSessionFactory());
	}

	public Clother getClotherById(int clother_id) throws ServiceException {
		Clother clother = new Clother();
		clother.setId(clother_id);
		Clother fClother;
		try {
			fClother = daoClother.getByID(clother);
		} catch (DaoHibernateException e) {
			logger.error("Problem to daoClother, method getByID.");
			throw new ServiceException("getClotherById error", e.getStackTrace(), e.getCause());
		}
		return fClother;
	}
}
