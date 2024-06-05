BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS event;
DROP TABLE IF EXISTS event_attendees;
DROP TABLE IF EXISTS restaurant_event;

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
   location VARCHAR (50) NOT NULL,
   event_link VARCHAR (200) NOT NULL UNIQUE,
   decision_date TIMESTAMP,
   FOREIGN KEY (organizer_id) REFERENCES users (user_id)
);

CREATE TABLE event_attendees (
	user_id INT NOT NULL,
	event_id INT NOT NULL,
	PRIMARY KEY (user_id, event_id),
	FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (event_id) REFERENCES event(event_id)
);

CREATE TABLE restaurant_event (
    event_id INT NOT NULL,
    restaurant_id VARCHAR (100) NOT NULL,
    yes_votes INT DEFAULT 0,
    no_votes INT DEFAULT 0,
    PRIMARY KEY (event_id, restaurant_id),
    FOREIGN KEY (event_id) REFERENCES event(event_id)
);

COMMIT TRANSACTION;
