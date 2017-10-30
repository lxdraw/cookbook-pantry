package com.alexdrawbond.cbp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
@EntityScan(basePackageClasses = {CookbookPantryApp.class, Jsr310JpaConverters.class})
public class CookbookPantryApp {
	public static void main(String[] args) {
		SpringApplication.run(CookbookPantryApp.class, args);
	}
	
}
