package by.academy.alekhno.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.academy.alekhno.database.util.HibernateUtil;
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

	public List<Type> getTypes() {
		logger.info("GetTypes.");
		List<Type> types = new ArrayList<Type>();
			types = daoType.getAll();
		return types;
	}

	public List<Model> getModelsByTypeId(int type_id) {
		logger.info("GetModelsByTypeId.");
		List<Model> models = new ArrayList<Model>();
		models = daoModel.getByTypeId(type_id);
		return models;
	}

	public Clother getClotherByModelId(int model_id) {
		logger.info("GetByModelId.");
		Clother clother = daoClother.getByModelId(model_id);
		return clother;
	}

	public void addType(String name) {
		logger.info("Start addType.");
		Type type = new Type();
		type.setName(name);
		if (!existName(name)) {
				daoType.add(type);
		} else {
			logger.error("Type exist");
//			throw new ServiceException("Type exist.");
		}
		logger.info("End addType.");
	}

	private boolean existName(String name) {
		Type type = daoType.getByName(name);
		return type != null;
	}

	public void addModel(Model model) {
		logger.info("Start addModel.");
		if (!existModel(model.getName())) {
			daoModel.add(model);
		} else {
			logger.error("Model exist");
//			throw new ServiceException("Type exist.");
		}
		logger.info("End addModel.");
	}

	private boolean existModel(String name) {
		Model model = daoModel.getByName(name);
		return model != null;
	}

	public void addClother(Clother clother) {
		logger.info("Start addClother.");
		if (!existClother(clother.getModel())) {
			daoClother.add(clother);
		} else {
			logger.error("Clother exist");
//			throw new ServiceException("Clother exist.");
		}
		logger.info("End addClother.");
	}

	private boolean existClother(Model model) {
		Clother clother = daoClother.getByModelId(model.getId());
		return clother != null;
	}

	public void updateType(Type type) {
		logger.info("UpdateType.");
		daoType.update(type);
	}

	public void updateModel(Model model) {
		logger.info("UpdateModel.");
		daoModel.update(model);
	}

	public void updateClother(Clother clother) {
		logger.info("UpdateClother.");
		daoClother.update(clother);
	}

	public void deleteType(int type_id) {
		logger.info("Start deleteType.");
		Type type = new Type();
		type.setId(type_id);
		List<Model> models = daoModel.getByTypeId(type_id);
		if (models.isEmpty()) {
			daoType.delete(type);
		} else {
			logger.error("Models exist. Didn't delete type.");
//			throw new ServiceException("Can't delete type.");
		}
		logger.info("End deleteType.");
	}

	public void deleteModel(int model_id) {
		logger.info("Start deleteModel.");
		Model model = new Model();
		model.setId(model_id);
		Clother clother = daoClother.getByModelId(model_id);
		if (clother == null) {
			daoModel.delete(model);
		} else {
			logger.error("Clother exist. Didn't delete model.");
//			throw new ServiceException("Can't delete model.");
		}
		logger.info("End deleteModel.");
	}

	public void deleteClother(int clother_id) {
		logger.info("Start deleteClother.");
		Clother clother = new Clother();
		clother.setId(clother_id);
		List<Order> orders = new ArrayList<Order>();
		orders = daoOrder.getOrdersByClotherId(clother_id);
		if (orders.isEmpty()) {
			daoClother.delete(clother);
		} else {
			logger.error("Order exist. Didn't delete model.");
//			throw new ServiceException("Can't delete clother.");
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
		this.daoType.setSession(HibernateUtil.getInstance().getSession());
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
		this.daoModel.setSession(HibernateUtil.getInstance().getSession());
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
		this.daoClother.setSession(HibernateUtil.getInstance().getSession());
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
		this.daoOrder.setSession(HibernateUtil.getInstance().getSession());;
	}

	public Clother getClotherById(int clother_id) {
		Clother clother = new Clother();
		clother.setId(clother_id);
		Clother fClother;
		fClother = daoClother.getByID(clother);
		return fClother;
	}
}
