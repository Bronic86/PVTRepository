package by.academy.alekhno.dao;

import java.util.List;

import by.academy.alekhno.vo.TypeClothes;

public interface TypeClothesDao {

	TypeClothes getTypeClothes (TypeClothes typeClothes);
	List<TypeClothes> getTypesClothes ();
	void addTypeClothes (TypeClothes typeClothes);
	void updateTypeClothes (TypeClothes typeClothes);
	void deleteTypeClothes (TypeClothes typeClothes);
	
}
