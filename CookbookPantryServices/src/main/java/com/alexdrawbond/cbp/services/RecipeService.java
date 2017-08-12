package com.alexdrawbond.cbp.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexdrawbond.cbp.domain.Recipe;
import com.alexdrawbond.cbp.repos.RecipeRepository;

@Service
public class RecipeService {
	@Autowired
	private RecipeRepository recipeRepo;
	
	public Recipe getRecipe(Long id) {
		List<Recipe> recipes = recipeRepo.findByDbId(id);
		if(recipes != null && recipes.size() > 0) {
			return recipes.get(0);
		} else {
			return null;
		}
	}
	
	public Recipe addRecipe(Recipe recipe) {
		recipe.setCreatedDate(LocalDateTime.now());
		recipeRepo.save(recipe);
		return recipe;
	}
	
	public Recipe updateRecipe(Long id, Recipe recipe) {
		Recipe temp = recipeRepo.findOne(id);
		recipe.setCreatedDate(temp.getCreatedDate());
		recipe.setDbId(id);
		recipeRepo.save(recipe);
		return recipe;
	}
	
	public void deleteRecipe(Long id) {
		recipeRepo.delete(id);
	}
}
