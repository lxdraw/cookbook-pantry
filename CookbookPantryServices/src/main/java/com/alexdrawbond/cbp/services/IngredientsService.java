package com.alexdrawbond.cbp.services;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexdrawbond.cbp.domain.Ingredient;
import com.alexdrawbond.cbp.repos.IngredientsRepository;

@Service
public class IngredientsService {
	@Autowired
	IngredientsRepository repo;
	
	public List<Ingredient> getIngredients(long recipeId) {
		return repo.findIngredientsByRecipeId(recipeId);
	}
	
	public Ingredient addIngredient(Ingredient ingredient, long recipeId) {
		ingredient.setCreatedDate(Calendar.getInstance());
		ingredient.setRecipeId(recipeId);
		repo.save(ingredient);
		return ingredient;
	}
	
	public Ingredient upateIngredient(long recipeId, long id, Ingredient ingredient) {
		Ingredient temp = repo.findIngredientByDbIdAndRecipeId(id, recipeId);
		ingredient.setCreatedDate(temp.getCreateDate());
		ingredient.setDbId(id);
		ingredient.setRecipeId(recipeId);
		repo.save(ingredient);
		return ingredient;
	}
	
	public void deleteIngredient(Long recipeId, Long id) {
		Ingredient ingredient = repo.findIngredientByDbIdAndRecipeId(id, recipeId);
		repo.delete(ingredient);
	}
}
