package com.alexdrawbond.cbp.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.alexdrawbond.cbp.domain.Ingredient;

public interface IngredientsRepository extends CrudRepository<Ingredient, Long> {
	public List<Ingredient> findIngredientsByRecipeId(long recipeId);
	
	public Ingredient findIngredientByDbIdAndRecipeId(Long dbId, Long recipeId);
}
