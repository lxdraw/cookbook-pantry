package com.alexdrawbond.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author alexdrawbond
 * @version 1.0
 * 
 * Persistence entity mapped to COOKBOOK.RECIPES, which contains recipe records
 */

@Entity
@Table(name="RECIPES", schema="COOKBOOK")
public class Recipe {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private long id;
	
	@Column
	private String name;
	
	@Column(name="CREATED_DATE")
	private Calendar createdDate;
	
	@Column(name="LAST_MADE_DATE")
	private Calendar lastMadeDate;
	
	@Column
	private String description;
	
	@Column
	private String notes;

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Calendar getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Calendar createdDate) {
		this.createdDate = createdDate;
	}

	public Calendar getLastMadeDate() {
		return lastMadeDate;
	}

	public void setLastMadeDate(Calendar lastMadeDate) {
		this.lastMadeDate = lastMadeDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return name;
	}
}