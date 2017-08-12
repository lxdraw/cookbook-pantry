package com.alexdrawbond.cbp.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.hateoas.ResourceSupport;

@Entity
public class Ingredient extends ResourceSupport {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private long dbId;
	
	@Column
	private String name;
	
	@Column
	private String amount;
	
	@Column(name="CREATED_DATE")
	private Calendar createdDate;
	
	@Column(name = "RECIPE_ID")
	private Long recipeId;

	public long getDbId() {
		return dbId;
	}

	public void setDbId(long dbId) {
		this.dbId = dbId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Long getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(Long recipeId) {
		this.recipeId = recipeId;
	}
	
	public Calendar getCreateDate() {
		return this.createdDate;
	}
	
	public void setCreatedDate(Calendar createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "Ingredient [name=" + name + ", amount=" + amount + "]";
	}
	
}
