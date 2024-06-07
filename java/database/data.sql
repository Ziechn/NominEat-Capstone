BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO event (organizer_id, event_name, zipcode, event_link, decision_date)
VALUES (1, 'Birthday Brunch', '90210', 'http://fakeurl.com/event1', '2024-06-04 23:59:59' ),
       (2, 'Meet and Greet', '10466','http://fakeurl.com/event2', '2024-06-04 23:59:59'),
       (1, 'Friendsgiving', '19139','http://fakeurl.com/event3', '2024-06-04 23:59:59'),
       (2, 'Holiday Night', '10454','http://fakeurl.com/event4', '2024-06-04 23:59:59'),
       (1, 'BabyShower Brunch', '10030','http://fakeurl.com/event5', '2024-06-04 23:59:59'),
       (2, 'Engagement Dinner', '11212','http://fakeurl.com/event6', '2024-06-04 23:59:59');

INSERT INTO event_attendees (user_id, event_id)
VALUES (1, 1),
       (2, 1),
       (1, 2),
       (2, 3),
       (2, 6),
       (1, 4),
       (2, 5);

INSERT INTO restaurant (name, phone, address1, address2, address3, city, state, zipcode, image_url, menu_url, rating, latitude, longitude)
VALUES ('Five Guys', '123-456-7890', '123 Melly St', '', '', 'Philadelphia', 'PA', '19107', 'http://example.com/image1.jpg', 'http://example.com/menu1.pdf', 4.5, 40.712776, -74.005974),
       ('Wendys', '234-567-8900', '456 Christ Ave', '', '', 'Brooklyn', 'NY', '11212', 'http://example.com/image2.jpg', 'http://example.com/menu2.pdf', 4.0, 34.052235, -118.243683),
       ('Shake Shack', '345-678-9801', '55 Lines Ave', '', '', 'Trenton', 'NJ', '08054', 'http://example.com/image3.jpg', 'http://example.com/menu3.pdf', 3.9, 41.762776, -94.675971),
       ('McDonalds', '456-675-3210', '837 Seth St', '', '', 'New Castle', 'De', '90301', 'http://example.com/image4.jpg', 'http://example.com/menu2.pdf', 2.6, 14.748235, -158.245973);

INSERT INTO restaurant_event (event_id, restaurant_id, yes_votes, no_votes )
VALUES (1, 1, 5, 3),
       (2, 2, 7, 1),
       (3, 3, 8, 4),
       (4, 4, 6, 2),
       (5, 1, 2, 3),
       (6, 2, 4, 6);

INSERT INTO transactions (transaction_name)
VALUES ('Order 234'),
       ('Order 346'),
       ('Order 673'),
       ('Order 564');

INSERT INTO restaurant_transactions (restaurant_id, transaction_id)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4);


INSERT INTO category (category_name)
VALUES ('Fast Food'),
       ('Italian'),
       ('Mexican'),
       ('Chinese'),
       ('Soul Food'),
       ('Indian');

INSERT INTO restaurant_category (restaurant_id, category_id)
VALUES      (1, 1),
            (2, 2),
            (3, 3),
            (4, 4);


INSERT INTO restaurant_hours (restaurant_id, day_id, day_name, start_time, end_time)
VALUES (1, 1, 'Monday', '11:00:00', '18:00:00'),
       (2, 2, 'Tuesday', '11:00:00', '14:00:00'),
       (3, 3, 'Wednesday', '11:00:00', '20:00:00'),
       (4, 4, 'Thursday', '11:00:00', '18:00:00'),
       (1, 5, 'Friday', '11:00:00', '17:00:00'),
       (2, 6, 'Saturday', '11:00:00', '15:00:00');




COMMIT TRANSACTION;
