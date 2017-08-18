package com.alexdrawbond.cbp.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alexdrawbond.cbp.domain.Recipe;
import com.alexdrawbond.cbp.services.RecipeService;

@RestController
@RequestMapping("/cookbookpantry")
public class RecipeController {
	@Autowired
	RecipeService service;
	
	//TODO: Add search functionality instead of returning all recipes
	@RequestMapping(value = "/recipes", method = RequestMethod.GET)
	public ResponseEntity<List<Recipe>> getRecipes() {
		List<Recipe> recipes = service.getRecipes();
		HttpStatus status;
		
		if(recipes != null) {
			for(Recipe recipe : recipes) {
				buildLinks(recipe, recipe.getDbId());
			}
			status = HttpStatus.OK;
		} else {
			status = HttpStatus.NOT_FOUND;
		}

		
		return new ResponseEntity<List<Recipe>> (recipes, status);
	}
	
	@RequestMapping(value = "/recipes/{id}", method = RequestMethod.GET)
	public ResponseEntity<Recipe> getRecipe(@PathVariable Long id) {
		Recipe recipe = service.getRecipe(id);
		HttpStatus status;
		
		if(recipe != null) {
			buildLinks(recipe, id);
			status = HttpStatus.OK;
		} else {
			status = HttpStatus.NOT_FOUND;
		}
		
		return new ResponseEntity<Recipe> (recipe, status);
	}
	
	@RequestMapping(value = "/recipes", method = RequestMethod.POST) 
	public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) {
		recipe = service.addRecipe(recipe);
		
		buildLinks(recipe, recipe.getDbId());
		
		return new ResponseEntity<Recipe> (recipe, HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value = "/recipes/{id}", method = RequestMethod.PUT) 
	public ResponseEntity<Recipe> updateRecipe(@PathVariable Long id, @RequestBody Recipe recipe) {
		recipe = service.updateRecipe(id, recipe);
		
		buildLinks(recipe, id);
		
		return new ResponseEntity<Recipe> (recipe, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/recipes/{id}", method = RequestMethod.DELETE) 
	public ResponseEntity<HttpStatus> deleteRecipe(@PathVariable(required = false) Long id) {
		service.deleteRecipe(id);
		return new ResponseEntity<HttpStatus> (HttpStatus.NO_CONTENT);
	}
	
	private void buildLinks(Recipe recipe, Long id) {
		recipe.add(linkTo(methodOn(RecipeController.class).getRecipe(id)).withSelfRel());
		recipe.add(linkTo(methodOn(IngredientsController.class).getIngredients(id)).withRel("ingredients"));
		recipe.add(linkTo(methodOn(InstructionController.class).getInstructionsForRecipe(id)).withRel("instructions"));
	}
	
}

