package com.alexdrawbond.cbp.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.alexdrawbond.cbp.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
	public List<Recipe> findByDbId(Long dbId);
}
