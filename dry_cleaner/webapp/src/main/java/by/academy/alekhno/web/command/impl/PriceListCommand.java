package by.academy.alekhno.web.command.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.academy.alekhno.dao.impl.ClotherImpl;
import by.academy.alekhno.dao.impl.ModelImpl;
import by.academy.alekhno.dao.impl.TypeImpl;
import by.academy.alekhno.exception.DaoException;
import by.academy.alekhno.service.impl.ClotherServiceImpl;
import by.academy.alekhno.service.interf.ClotherService;
import by.academy.alekhno.vo.Clother;
import by.academy.alekhno.vo.Model;
import by.academy.alekhno.vo.Type;
import by.academy.alekhno.web.bundle.Bundle;
import by.academy.alekhno.web.command.Command;

public class PriceListCommand implements Command {
	private static final Logger logger = Logger.getLogger(PriceListCommand.class.getName());

	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		logger.info("Execute PriceListCommand");
		String type_id_param = req.getParameter(Bundle.getResource("request.key.type.id"));

		ClotherService clotherService = new ClotherServiceImpl();
		clotherService.setDaoType(new TypeImpl());
		clotherService.setDaoModel(new ModelImpl());
		clotherService.setDaoClother(new ClotherImpl());

		List<Type> types;
		try {
			types = clotherService.getTypes();
		} catch (DaoException e) {
			logger.error(e.getMessage(), e);
			req.setAttribute(Bundle.getResource("request.key.error.message"),
					e.getMessage());
			return Bundle.getResource("to.page.error");
		}
		req.setAttribute(Bundle.getResource("request.key.types"), types);
		
		if (type_id_param != null) {
			int type_id = Integer.parseInt(type_id_param);
			List<Model> models;
			try {
				models = clotherService.getModelsByTypeId(type_id);
			} catch (DaoException e) {
				logger.error(e.getMessage(), e);
				req.setAttribute(
						Bundle.getResource("request.key.error.message"),
						e.getMessage());
				return Bundle.getResource("to.page.error");
			}
			req.setAttribute(Bundle.getResource("request.key.models"), models);
		
			List<Clother> clothes = new ArrayList<Clother>();
			try {
				clotherService.setDaoClother(new ClotherImpl());
				for (Model model : models){
				clothes.addAll(clotherService.getClothesByModelId(model.getId()));
				}
			} catch (DaoException e) {
				logger.error(e.getMessage(), e);
				req.setAttribute(
						Bundle.getResource("request.key.error.message"),
						e.getMessage());
				return Bundle.getResource("to.page.error");
			}
			req.setAttribute(Bundle.getResource("request.key.clothes"), clothes);
		}

		return Bundle.getResource("to.page.price.list");
	}

}
