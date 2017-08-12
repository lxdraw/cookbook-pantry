package com.alexdrawbond.cbp.services;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexdrawbond.cbp.domain.Instruction;
import com.alexdrawbond.cbp.repos.InstructionRepository;

@Service
public class InstructionService {
	@Autowired
	InstructionRepository repo;
	
	public List<Instruction> getInstructions(Long recipeId) {
		return repo.findInstructionsByRecipeId(recipeId);
	}
	
	public Instruction addInstructionToRecipe(Long recipeId, Instruction instruction) {
		instruction.setRecipeId(recipeId);
		instruction.setCreatedDate(Calendar.getInstance());
		
		repo.save(instruction);
		
		return instruction;
	}
	
	public Instruction updateInstruction(Long recipeId, Long id, Instruction instruction) {
		Instruction temp = repo.findInstructionByDbIdAndRecipeId(id, recipeId);
		instruction.setCreatedDate(temp.getCreatedDate());
		instruction.setDbId(id);
		instruction.setRecipeId(recipeId);
		
		repo.save(instruction);
		
		return instruction;
	}
	
	public void deleteInstructionFromRecipe(Long recipeId, Long id) {
		Instruction instruction = repo.findInstructionByDbIdAndRecipeId(id, recipeId);
		repo.delete(instruction);
	}
}
