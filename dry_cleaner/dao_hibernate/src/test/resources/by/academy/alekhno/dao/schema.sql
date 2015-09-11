CREATE TABLE types
(
	id INT PRIMARY KEY NOT NULL DEFAULT 0,
	name VARCHAR(50)
);
CREATE TABLE models
(
	id INT PRIMARY KEY NOT NULL DEFAULT 0,
    name VARCHAR(50),
    type_id INT
);
CREATE TABLE clothes
(
    id INT PRIMARY KEY NOT NULL DEFAULT 0,
    model_id INT,
    price decimal
);
CREATE TABLE users
(
	id INT PRIMARY KEY NOT NULL DEFAULT 0,
	login VARCHAR(50),
	password VARCHAR(50),
	first_name VARCHAR(50),
	second_name VARCHAR (50),
	telephone BIGINT
);    
CREATE TABLE roles
(
	id INT PRIMARY KEY NOT NULL DEFAULT 0,
	name VARCHAR(50)
);    
CREATE TABLE user_roles
(
	id INT PRIMARY KEY NOT NULL DEFAULT 0,
	user_id INT,
	role_id INT
);
CREATE TABLE orders
(
	id INT PRIMARY KEY NOT NULL DEFAULT 0,
	user_id INT,
	clother_id INT,
	quantity INT,
    ordering_day DATETIME
);
CREATE TABLE states
(
	id INT PRIMARY KEY NOT NULL DEFAULT 0,
	state VARCHAR(50)
);
ALTER TABLE models ADD FOREIGN KEY (type_id) REFERENCES types (id);
ALTER TABLE clothes ADD FOREIGN KEY (model_id) REFERENCES models (id);
ALTER TABLE user_roles ADD FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE user_roles ADD FOREIGN KEY (role_id) REFERENCES roles (id);
ALTER TABLE orders ADD FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE orders ADD FOREIGN KEY (clother_id) REFERENCES clothes (id);
ALTER TABLE orders ADD FOREIGN KEY (state_id) REFERENCES states (id);