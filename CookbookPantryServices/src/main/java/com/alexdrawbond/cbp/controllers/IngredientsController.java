package com.alexdrawbond.cbp.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alexdrawbond.cbp.domain.Ingredient;
import com.alexdrawbond.cbp.services.IngredientsService;

@RestController
@RequestMapping("/cookbookpantry")
public class IngredientsController {
	@Autowired
	IngredientsService service;

	@RequestMapping(value = "/recipes/{recipeId}/ingredients", method = RequestMethod.GET)
	public ResponseEntity<List<Ingredient>> getIngredients(@PathVariable(required = true) long recipeId) {
		List<Ingredient> ingredients = service.getIngredients(recipeId);
		
		buildLinks(ingredients, recipeId);
		
		return new ResponseEntity<List<Ingredient>>(ingredients, HttpStatus.OK);
	}

	@RequestMapping(value = "/recipes/{recipeId}/ingredients", method = RequestMethod.POST)
	public ResponseEntity<Ingredient> addIngredients(@RequestBody Ingredient ingredient, @PathVariable long recipeId) {
		ingredient = service.addIngredient(ingredient, recipeId);
		
		buildLinks(Arrays.asList(ingredient), recipeId);
		
		return new ResponseEntity<Ingredient>(ingredient, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/recipes/{recipeId}/ingredients/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Ingredient> updateIngredient(@PathVariable(required = true) long recipeId,
			@PathVariable(required = true) long id, @RequestBody Ingredient ingredient) {
		ingredient = service.upateIngredient(recipeId, id, ingredient);
		
		buildLinks(Arrays.asList(ingredient), recipeId);
		
		return new ResponseEntity<Ingredient> (ingredient, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/recipes/{recipeId}/ingredients/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<HttpStatus> deleteIngredient(@PathVariable(required = true) Long recipeId, @PathVariable(required = true) Long id) {
		service.deleteIngredient(recipeId, id);
		return new ResponseEntity<HttpStatus> (HttpStatus.NO_CONTENT);
	}
	
	private void buildLinks(List<Ingredient> ingredients, Long recipeId) {
		for(Ingredient ingredient : ingredients) {
			ingredient.add(linkTo(methodOn(this.getClass()).getIngredients(recipeId)).withSelfRel());
			ingredient.add(linkTo(methodOn(RecipeController.class).getRecipe(recipeId)).withRel("recipe"));
		}
	}
}
