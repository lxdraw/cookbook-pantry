package com.alexdrawbond.cbp.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.alexdrawbond.cbp.domain.Instruction;

public interface InstructionRepository extends CrudRepository<Instruction, Long> {
	public List<Instruction> findInstructionsByRecipeId(Long recipeId);
	
	public Instruction findInstructionByDbIdAndRecipeId(Long dbId, Long recipeId);
}
