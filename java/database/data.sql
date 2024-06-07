BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role, email, profile_image_url) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 'supercoolmel@gmail.com', 'http://example.com/profiles/user.jpg');
INSERT INTO users (username,password_hash,role, email, profile_image_url) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN', 'maryb@gmail.com', 'http://example.com/profiles/user.jpg');

INSERT INTO event (organizer_id, event_name, location, event_link, decision_date)
VALUES (1, 'Birthday Brunch', 'New York', 'http://fakeurl.com/event1', '2024-06-04 23:59:59' ),
       (2, 'Meet and Greet', 'Bronx','http://fakeurl.com/event2', '2024-06-04 23:59:59'),
       (1, 'Friendsgiving', 'Center City','http://fakeurl.com/event3', '2024-06-04 23:59:59'),
       (2, 'Holiday Night', 'Fashion District','http://fakeurl.com/event4', '2024-06-04 23:59:59'),
       (1, 'BabyShower Brunch', 'China Town','http://fakeurl.com/event5', '2024-06-04 23:59:59'),
       (2, 'Engagement Dinner', 'Jersey City','http://fakeurl.com/event6', '2024-06-04 23:59:59');

INSERT INTO event_attendees (user_id, event_id)
VALUES (1, 1),
       (2, 1),
       (1, 2),
       (2, 3),
       (2, 6),
       (1, 4),
       (2, 5);

INSERT INTO restaurant (restaurant_id, name, phone, address1, address2, address3, city, country, state, zipcode, image_url, menu_url, rating, latitude, longitude)
VALUES ('A123','Five Guys', '123-456-7890', '123 Melly St', '', '', 'Philadelphia', 'USA', 'PA', '19107', 'http://example.com/image1.jpg', 'http://example.com/menu1.pdf', 4.5, 40.712776, -74.005974),
       ('B234','Wendys', '234-567-8900', '456 Christ Ave', '', '', 'Brooklyn','USA','NY', '11212', 'http://example.com/image2.jpg', 'http://example.com/menu2.pdf', 4.0, 34.052235, -118.243683),
       ('C345','Shake Shack', '345-678-9801', '55 Lines Ave', '', '', 'Trenton', 'USA','NJ', '08054', 'http://example.com/image3.jpg', 'http://example.com/menu3.pdf', 3.9, 41.762776, -94.675971),
       ('D456','McDonalds', '456-675-3210', '837 Seth St', '', '', 'New Castle', 'USA', 'DE', '90301', 'http://example.com/image4.jpg', 'http://example.com/menu2.pdf', 2.6, 14.748235, -158.245973);

INSERT INTO restaurant_event (event_id, restaurant_id, yes_votes, no_votes )
VALUES (1, 'A123', 5, 3),
       (2, 'B234', 7, 1),
       (3, 'C345', 8, 4),
       (4, 'D456', 6, 2),
       (5, 'A123', 2, 3),
       (6, 'B234', 4, 6);

INSERT INTO transactions (transaction_name)
VALUES ('Pickup'),
       ('Pickup'),
       ('Pickup'),
       ('Pickup');

INSERT INTO restaurant_transactions (restaurant_id, transaction_id)
VALUES ('A123', 1),
       ('B234', 2),
       ('C345', 3),
       ('D456', 4);


INSERT INTO category (category_name)
VALUES ('Fast Food'),
       ('Italian'),
       ('Mexican'),
       ('Chinese'),
       ('Soul Food'),
       ('Indian');

INSERT INTO restaurant_category (restaurant_id, category_id)
VALUES      ('A123', 1),
            ('B234', 2),
            ('C345', 3),
            ('D456', 4);


INSERT INTO restaurant_hours (hours_id, restaurant_id, day_id, day_name, start_time, end_time)
VALUES (1,'A123', 1, 'Monday', '1100', '1800'),
       (2,'B234', 2, 'Tuesday', '1100', '1400'),
       (3,'C345', 3, 'Wednesday', '1100', '2000'),
       (4,'D456', 4, 'Thursday', '1100', '1800'),
       (5,'A123', 5, 'Friday', '1100', '1700'),
       (6,'B234', 6, 'Saturday', '1100', '1500');




COMMIT TRANSACTION;
