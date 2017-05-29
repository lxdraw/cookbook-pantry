package com.alexdrawbond.services;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.alexdrawbond.domain.Recipe;

@Transactional
public class RecipesService {
	@PersistenceContext(unitName = "PANTRYDB")
	EntityManager em;
	
	public List<Recipe> getAllRecipes () {
		List<Recipe> recipes = null;

		try {
			TypedQuery<Recipe> typedQuery = em.createQuery("select r from Recipe r", Recipe.class);

			recipes = typedQuery.getResultList();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return recipes;
	}
	
	public void addRecipe(Recipe recipe) {
		recipe.setCreatedDate(Calendar.getInstance());
		em.persist(recipe);
	}
	
	public void updateRecipe(Recipe recipe) {
		em.merge(recipe);
	}
	
	public void deleteRecipe(long recipeId) {
		Recipe r = new Recipe();
		r.setId(recipeId);
		r = em.merge(r);
		em.remove(r);
	}
	
}
