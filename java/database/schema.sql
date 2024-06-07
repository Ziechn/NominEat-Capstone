ROLLBACK;
BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, event, event_attendees, restaurant_event, restaurant,
restaurant_transactions, category, restaurant_category,restaurant_hours, transactions CASCADE;


CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE event (
   event_id SERIAL PRIMARY KEY,
   organizer_id INT NOT NULL,
   event_name VARCHAR (50) NOT NULL,
   zipcode VARCHAR (50) NOT NULL,
   event_link VARCHAR(300) NOT NULL UNIQUE,
   decision_date TIMESTAMP,
   FOREIGN KEY (organizer_id) REFERENCES users (user_id)
);

CREATE TABLE event_attendees (
	user_id INT NOT NULL,
	event_id INT NOT NULL,
	FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (event_id) REFERENCES event(event_id)
);

CREATE TABLE restaurant (
    restaurant_id SERIAL PRIMARY KEY,
    name VARCHAR (50) NOT NULL,
    phone VARCHAR (20),
    address1 VARCHAR (100),
    address2 VARCHAR (100),
    address3 VARCHAR (100),
    city VARCHAR (50),
    country VARCHAR (20),
    state VARCHAR (50),
    zipcode VARCHAR (20),
    image_url VARCHAR (100),
    menu_url VARCHAR (100),
    rating NUMERIC (3,2),
    latitude NUMERIC (10,6),
    longitude NUMERIC (10,6)
);

CREATE TABLE restaurant_event (
    event_id INT NOT NULL,
    restaurant_id INT NOT NULL,
    yes_votes INT DEFAULT 0,
    no_votes INT DEFAULT 0,
    FOREIGN KEY (event_id) REFERENCES event(event_id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant(restaurant_id)
);


CREATE TABLE transactions (
    transaction_id SERIAL PRIMARY KEY,
    transaction_name VARCHAR (100)
);

CREATE TABLE restaurant_transactions (
     restaurant_id INT NOT NULL,
     transaction_id INT NOT NULL,
     FOREIGN KEY (restaurant_id) REFERENCES restaurant (restaurant_id),
     FOREIGN KEY (transaction_id) REFERENCES transactions (transaction_id)
);

CREATE TABLE category (
     category_id SERIAL PRIMARY KEY,
     category_name VARCHAR (50) NOT NULL
);

CREATE TABLE restaurant_category (
     restaurant_id INT NOT NULL,
     category_id INT NOT NULL,
     FOREIGN KEY (restaurant_id) REFERENCES restaurant (restaurant_id),
     FOREIGN KEY (category_id) REFERENCES category (category_id)
);

CREATE TABLE restaurant_hours (
    hours_id SERIAL PRIMARY KEY,
    restaurant_id INT NOT NULL,
    day_id INT NOT NULL,
    day_name VARCHAR (20) NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (restaurant_id)
);

COMMIT TRANSACTION;
