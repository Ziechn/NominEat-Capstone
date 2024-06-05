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

--INSERT INTO restaurant_event (event_id, restaurant_id, yes_votes, no_votes )

COMMIT TRANSACTION;
