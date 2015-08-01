package by.academy.alekhno.dao;

import java.util.List;

import by.academy.alekhno.vo.Clothes;

public interface ClothesDao {

	Clothes getClothes (Clothes clothes);
	List<Clothes> getClotheses ();
	void addClothes (Clothes clothes);
	void updateClothes (Clothes clothes);
	void deleteClothes (Clothes clothes);
	
}
