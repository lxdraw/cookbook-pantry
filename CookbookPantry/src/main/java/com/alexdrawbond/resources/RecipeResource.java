package com.alexdrawbond.resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.alexdrawbond.domain.Recipe;
import com.alexdrawbond.services.RecipesService;

/**
 * 
 * @author alexdrawbond
 * @version 1.0
 * Resource class for performing GET, POST, UPDATE and DELETE operations on recipes
 * 
 */

@Path("/recipes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RecipeResource {
	@Inject
	RecipesService recipesService;
	
	@GET
	public List<Recipe> getRecipes() {
		return recipesService.getAllRecipes();
	}
	
	@POST
	public void addRecipe(Recipe recipe) {
		recipesService.addRecipe(recipe);
	}
	
	@PUT
	public Response updatedRecipe(Recipe recipe) {
		if(recipe.getId() <= 0) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		recipesService.updateRecipe(recipe);
		return Response.status(Status.ACCEPTED).build();
	}
	
	@DELETE
	@Path("/{recipeId}")
	public Response deleteRecipe(@PathParam("recipeId") long recipeId) {
		if(recipeId <= 0) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		recipesService.deleteRecipe(recipeId);
		return Response.status(Status.OK).build();
	}
}
