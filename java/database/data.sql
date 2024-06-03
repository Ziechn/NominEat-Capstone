BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO event (organizer_id, event_name)
VALUES (1, 'Birthday Brunch'),
       (2, 'Meet and Greet'),
       (1, 'Friendsgiving'),
       (2, 'Holiday Night'),
       (1, 'BabyShower Brunch'),
       (2, 'Engagement Dinner');

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
