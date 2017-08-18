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

import com.alexdrawbond.cbp.domain.Instruction;
import com.alexdrawbond.cbp.services.InstructionService;

@RestController
@RequestMapping("/cookbookpantry")
public class InstructionController {
	@Autowired
	InstructionService service;

	@RequestMapping(value = "/recipes/{recipeId}/instructions/", method = RequestMethod.GET)
	public ResponseEntity<List<Instruction>> getInstructionsForRecipe(@PathVariable(required = true) Long recipeId) {
		List<Instruction> instructions = service.getInstructions(recipeId);
		HttpStatus status;
		
		if(instructions != null) {
			buildLinks(instructions, recipeId);
			status = HttpStatus.OK;
		} else {
			status = HttpStatus.NOT_FOUND;
		}

		return new ResponseEntity<List<Instruction>>(instructions, status);
	}

	@RequestMapping(value = "/recipes/{recipeId}/instructions", method = RequestMethod.POST)
	public ResponseEntity<Instruction> addInstructionToRecipe(@PathVariable(required = true) Long recipeId,
			@RequestBody Instruction instruction) {
		instruction = service.addInstructionToRecipe(recipeId, instruction);

		buildLinks(Arrays.asList(instruction), recipeId);

		return new ResponseEntity<Instruction>(instruction, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/recipes/{recipeId}/instructions/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Instruction> updateInstruction(@PathVariable(required = true) Long recipeId,
			@PathVariable(required = true) Long id, @RequestBody Instruction instruction) {
		instruction = service.updateInstruction(recipeId, id, instruction);

		buildLinks(Arrays.asList(instruction), recipeId);

		return new ResponseEntity<Instruction>(instruction, HttpStatus.OK);
	}

	@RequestMapping(value = "/recipes/{recipeId}/instructions/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<HttpStatus> deleteInstructionFromRecipe(@PathVariable(required = true) Long recipeId,
			@PathVariable(required = true) Long id) {
		service.deleteInstructionFromRecipe(recipeId, id);
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}

	private void buildLinks(List<Instruction> instructions, Long recipeId) {
		for (Instruction instruction : instructions) {
			instruction
					.add(linkTo(methodOn(this.getClass()).getInstructionsForRecipe(recipeId)).withSelfRel());
			instruction.add(linkTo(methodOn(RecipeController.class).getRecipe(recipeId)).withRel("recipe"));
		}
	}
}
