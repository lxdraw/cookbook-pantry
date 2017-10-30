create table RECIPES (
							ID int NOT NULL AUTO_INCREMENT,
							NAME varchar(100),
							CREATED_DATE datetime,
							LAST_MADE_DATE date,
							DESCRIPTION varchar(500),
							NOTES varchar(500),
							PRIMARY KEY(ID)
);

create table INGREDIENTS (
							ID int not NULL AUTO_INCREMENT,
							NAME varchar(100),
							AMOUNT varchar(5),
							CREATED_DATE datetime,
							RECIPE_ID int,
							PRIMARY KEY(ID)
				
);

create table INSTRUCTIONS (
							ID int NOT NULL AUTO_INCREMENT,
							INSTRUCTION varchar(1000),
							CREATED_DATE datetime,
							RECIPE_ID int,
							PRIMARY KEY(ID)
				
);

create table IMAGES(
							ID int NOT NULL AUTO_INCREMENT,
							CREATED_DATE datetime,
							IMAGE blob,
							RECIPE_ID int,
							PRIMARY KEY(ID)
);