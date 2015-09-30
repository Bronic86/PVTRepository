package by.academy.alekhno.spring.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.academy.alekhno.database.dao.interf.ClotherPojoRepository;
import by.academy.alekhno.database.dao.interf.ModelPojoRepository;
import by.academy.alekhno.database.dao.interf.TypePojoRepository;
import by.academy.alekhno.spring.converter.ConverterPojoToVO;
import by.academy.alekhno.spring.converter.ConverterVOToPojo;
import by.academy.alekhno.spring.interf.ClotherService;
import by.academy.alekhno.spring.pojo.ClotherPojo;
import by.academy.alekhno.spring.pojo.ModelPojo;
import by.academy.alekhno.spring.pojo.TypePojo;
import by.academy.alekhno.vo.Clother;
import by.academy.alekhno.vo.Model;
import by.academy.alekhno.vo.Type;

@Service("clotherService")
@Transactional
public class ClotherServiceImpl implements ClotherService {

	@Autowired
	private ClotherPojoRepository clotherRepository;

	@Autowired
	private ModelPojoRepository modelRepository;

	@Autowired
	private TypePojoRepository typeRepository;

	@Override
	public Clother getClotherById(int clother_id) {
		ClotherPojo clotherPojo = clotherRepository.findOne(clother_id);
		return ConverterPojoToVO.getClother(clotherPojo);
	}

	@Override
	public Clother getClotherByModelId(int model_id) {
		ClotherPojo clotherPojo = clotherRepository.getByModelId(model_id);
		return ConverterPojoToVO.getClother(clotherPojo);
	}

	@Override
	public List<Type> getTypes() {
		List<TypePojo> typesPojo = typeRepository.findAll();
		List<Type> types = new ArrayList<Type>();
		for (TypePojo typePojo : typesPojo) {
			types.add(ConverterPojoToVO.getType(typePojo));
		}
		return types;
	}

	@Override
	public List<Model> getModelsByTypeId(int type_id) {
		List<ModelPojo> modelsPojo = modelRepository.getByTypeId(type_id);
		List<Model> models = new ArrayList<Model>();
		for (ModelPojo modelPojo : modelsPojo) {
			models.add(ConverterPojoToVO.getModel(modelPojo));
		}
		return models;
	}

	@Override
	public void addType(String name) {
		// Exist or not
		TypePojo typePojo = new TypePojo();
		typePojo.setName(name);
		typeRepository.saveAndFlush(typePojo);
	}

	@Override
	public void addModel(Model model) {
		// Exist or not
		ModelPojo modelPojo = ConverterVOToPojo.getModel(model);
		modelRepository.saveAndFlush(modelPojo);
	}

	@Override
	public void addClother(Clother clother) {
		// Exist or not
		ClotherPojo clotherPojo = ConverterVOToPojo.getClother(clother);
		clotherRepository.saveAndFlush(clotherPojo);
	}

	@Override
	public void updateType(Type type) {
		TypePojo persistType = typeRepository.getOne(type.getId());
		persistType.setName(type.getName());
		typeRepository.flush();
	}

	@Override
	public void updateModel(Model model) {
		ModelPojo persistModel = modelRepository.getOne(model.getId());
		persistModel.setName(model.getName());
		persistModel.setType(ConverterVOToPojo.getType(model.getType()));
		modelRepository.flush();
	}

	@Override
	public void updateClother(Clother clother) {
		ClotherPojo persistClother = clotherRepository.getOne(clother.getId());
		persistClother.setModel(ConverterVOToPojo.getModel(clother.getModel()));
		persistClother.setPrice(clother.getPrice());
		clotherRepository.flush();
	}

	@Override
	public void deleteType(int id) {
		// Model dependency exist
		typeRepository.delete(id);
	}

	@Override
	public void deleteModel(int id) {
		// Clother dependency exist
		modelRepository.delete(id);
	}

	@Override
	public void deleteClother(int id) {
		// Order dependency exist
		clotherRepository.delete(id);
	}

	@Override
	public List<Clother> getClothesByTypeId(int type_id) {
		List<ClotherPojo> clothesPojo = clotherRepository.getByTypeId(type_id);
		List<Clother> clothes = new ArrayList<Clother>();
		for (ClotherPojo clotherPojo : clothesPojo) {
			clothes.add(ConverterPojoToVO.getClother(clotherPojo));
		}
		return clothes;
	}

}
