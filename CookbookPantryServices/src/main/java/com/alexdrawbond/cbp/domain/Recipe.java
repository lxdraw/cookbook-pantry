package com.alexdrawbond.cbp.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @author alexdrawbond
 * @version 1.0
 * 
 * Persistence entity mapped to COOKBOOK.RECIPES, which contains recipe records
 */

@Entity
public class Recipe extends ResourceSupport {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private long dbId;
	
	@Column
	private String name;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
	@Column(name="CREATED_DATE")
	private LocalDateTime createdDate;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	@Column(name="LAST_MADE_DATE")
	private LocalDate lastMadeDate;
	
	@Column
	private String description;
	
	@Column
	private String notes;
	
	public long getDbId() {
		return dbId;
	}
	
	public void setDbId(long id) {
		this.dbId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDate getLastMadeDate() {
		return lastMadeDate;
	}

	public void setLastMadeDate(LocalDate lastMadeDate) {
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