BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role, email, profile_image_url) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 'supercoolmel@gmail.com', 'http://example.com/profiles/user.jpg');
INSERT INTO users (username,password_hash,role, email, profile_image_url) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN', 'maryb@gmail.com', 'http://example.com/profiles/user.jpg');

INSERT INTO event (organizer_id, event_name, location, event_link, decision_date)
VALUES (1, 'Birthday Brunch', 'New York', 'http://fakeurl.com/event1', '2024-06-04 23:59:59' );


INSERT INTO event_attendees (user_id, event_id)
VALUES (1, 1);


INSERT INTO restaurant (restaurant_id, name, phone, address1, address2, address3, city, country, state, zipcode, image_url, menu_url, rating, latitude, longitude)
VALUES ('A123','Five Guys', '123-456-7890', '123 Melly St', '', '', 'Philadelphia', 'USA', 'PA', '19107', 'http://example.com/image1.jpg', 'http://example.com/menu1.pdf', 4.5, 40.712776, -74.005974);


INSERT INTO restaurant_event (event_id, restaurant_id, yes_votes, no_votes )
VALUES (1, 'A123', 5, 3);


INSERT INTO transactions (transaction_name)
VALUES ('Pickup');


INSERT INTO restaurant_transactions (restaurant_id, transaction_id)
VALUES ('A123', 1);



INSERT INTO category (category_name)
VALUES ('Fast Food');


INSERT INTO restaurant_category (restaurant_id, category_id)
VALUES      ('A123', 1);



INSERT INTO restaurant_hours (restaurant_id, day_id, day_name, start_time, end_time)
VALUES ('A123', 1, 'Monday', '1100', '1800');




COMMIT TRANSACTION;
