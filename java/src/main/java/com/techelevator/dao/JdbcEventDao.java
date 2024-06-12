package com.techelevator.dao;
import com.techelevator.model.Event;
import com.techelevator.model.Restaurant;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;




@Component
public class JdbcEventDao implements EventDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcEventDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();

        String sql = "SELECT * FROM event;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                events.add(mapRowToEvent(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("[Restaurant JDBC DAO] Unable to connect to server or database");
            throw new CannotGetJdbcConnectionException("" + e);
        } catch (DataIntegrityViolationException e) {
            System.out.println("[Restaurant JDBC DAO] getAllEvents() Problem getting all events");
            throw new DataIntegrityViolationException("" + e);
        }

        return events;
    }

    @Override
    public Event getEventById(int eventId) {
        Event event = null;

        String sql = "SELECT * FROM event WHERE event_id = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, eventId);
            if (results.next()) {
                event = mapRowToEvent(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("[Restaurant JDBC DAO] Unable to connect to server or database");
            throw new CannotGetJdbcConnectionException("" + e);
        } catch (DataIntegrityViolationException e) {
            System.out.println("[Restaurant JDBC DAO] getEventById() Problem getting event id: " + eventId);
            throw new DataIntegrityViolationException("" + e);
        }

        return event;
    }

    @Override
    public Event createEvent(Event event) {
        Event newEvent = null;

        String sql = "INSERT INTO event (organizer_id, event_name, location, event_link, decision_date) " +
                "VALUES (?, ?, ?, ?, ?) RETURNING event_id;";
        String date= event.getDecisionDate().substring(0,10)+ " "+ event.getDecisionDate().substring(11)+":00";
        Timestamp time= Timestamp.valueOf(date);
        try {
            int eventId = jdbcTemplate.queryForObject(sql, int.class,
                    event.getOrganizerId(),
                    event.getEventName(),
                    event.getLocation(),
                    event.getEventLink(),
                    time);


            newEvent = getEventById(eventId);
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("[Restaurant JDBC DAO] Unable to connect to server or database");
            throw new CannotGetJdbcConnectionException("" + e);
        } catch (DataIntegrityViolationException e) {
            System.out.println("[Restaurant JDBC DAO] createEvent() Problem creating event name " + event.getEventName());
            throw new DataIntegrityViolationException("" + e);
        }

        return newEvent;
    }

    @Override
    public Event getEventByLink(String eventLink) {

        String sql = "SELECT * FROM event WHERE event_link = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, eventLink);
            if (results.next()) {
                return mapRowToEvent(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("[Restaurant JDBC DAO] Unable to connect to server or database");
            throw new CannotGetJdbcConnectionException("" + e);
        } catch (DataIntegrityViolationException e) {
            System.out.println("[Restaurant JDBC DAO] getEventByLink() Problem getting event from url: " + eventLink);
            throw new DataIntegrityViolationException("" + e);
        }

        return new Event();
    }

    private Event mapRowToEvent(SqlRowSet results) {
        Event event = new Event();
        event.setEventId(results.getInt("event_id"));
        event.setEventName(results.getString("event_name"));
        event.setOrganizerId(results.getInt("organizer_id"));
        event.setLocation(results.getString("location"));
        event.setEventLink(results.getString("event_link"));
        event.setDecisionDate(results.getString("decision_date"));
        return event;
    }

    // Chris's edits
    public void insertUniqueEventUserLink(String userEventLink, int eventId){
        String sql = "INSERT INTO event_attendee_link (event_link, event_id, yes_votes, no_votes) " +
                "VALUES (?, ?, 0, 0);";

        try {
            jdbcTemplate.update(sql, userEventLink, eventId);
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("[Restaurant JDBC DAO] Unable to connect to server or database");
            throw new CannotGetJdbcConnectionException("" + e);
        } catch (DataIntegrityViolationException e) {
            System.out.println("[Restaurant JDBC DAO] createUniqueEventUserLink() Problem creating unique event link: " + userEventLink);
            throw new DataIntegrityViolationException("" + e);
        }

        System.out.println("[Restaurant JDBC DAO] createUniqueEventUserLink() Unique user event link created successfully");
    }

    // Get an event by the unique link

    // Get yes votes from the restaurant_event table.
    public int getRestaurantEventYesVotes(int eventId, String restaurantId){
        String sql = "SELECT yes_votes FROM restaurant_event WHERE event_id = ? AND restaurant_id = ?;";

        try {
            return jdbcTemplate.queryForObject(sql, int.class, eventId, restaurantId);
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("[Restaurant JDBC DAO] Unable to connect to server or database");
            throw new CannotGetJdbcConnectionException("" + e);
        } catch (DataIntegrityViolationException e) {
            System.out.println("[Restaurant JDBC DAO] getRestaurantEventYesVotes() Problem getting yes votes for event ID: " + eventId + " and restaurant ID: " + restaurantId);
            throw new DataIntegrityViolationException("" + e);
        }
    }

    // Get no votes from the restaurant_event table.
    public int getRestaurantEventNoVotes(int eventId, String restaurantId){
        String sql = "SELECT no_votes FROM restaurant_event WHERE event_id = ? AND restaurant_id = ?;";

        try {
            return jdbcTemplate.queryForObject(sql, int.class, eventId, restaurantId);
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("[Restaurant JDBC DAO] Unable to connect to server or database");
            throw new CannotGetJdbcConnectionException("" + e);
        } catch (DataIntegrityViolationException e) {
            System.out.println("[Restaurant JDBC DAO] getRestaurantEventYesVotes() Problem getting yes votes for event ID: " + eventId + " and restaurant ID: " + restaurantId);
            throw new DataIntegrityViolationException("" + e);
        }
    }

    // Get yes votes from the event_attendee_link table.
    // Get yes votes from the event_attendee_link table.

    // Add yes vote to the restaurant_event table.
    public String addRestaurantEventYesVote(int eventId, String restaurantId){
        // Get the current votes.
        int newVoteCount = 1 + getRestaurantEventYesVotes(eventId, restaurantId);

        String sql = "UPDATE restaurant_event SET yes_votes = ? WHERE event_id = ? AND restaurant_id = ?;";

        try {
            jdbcTemplate.update(sql, newVoteCount, eventId, restaurantId);
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("[Restaurant JDBC DAO] Unable to connect to server or database");
            throw new CannotGetJdbcConnectionException("" + e);
        } catch (DataIntegrityViolationException e) {
            System.out.println("[Restaurant JDBC DAO] addRestaurantEventYesVote() Event ID: " + eventId + ", Problem voting yes for restaurant ID: " + restaurantId);
            throw new DataIntegrityViolationException("" + e);
        }

        return "Added";
    }

    // Add no vote to the restaurant_event table.
    public String addRestaurantEventNoVote(int eventId, String restaurantId){
        // Get the current votes.
        int newVoteCount = 1 + getRestaurantEventNoVotes(eventId, restaurantId);

        String sql = "UPDATE restaurant_event SET no_votes = ? WHERE event_id = ? AND restaurant_id = ?;";

        try {
            jdbcTemplate.update(sql, newVoteCount, eventId, restaurantId);
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("[Restaurant JDBC DAO] Unable to connect to server or database");
            throw new CannotGetJdbcConnectionException("" + e);
        } catch (DataIntegrityViolationException e) {
            System.out.println("[Restaurant JDBC DAO] addRestaurantEventNoVote() Event ID: " + eventId + ", Problem voting no for restaurant ID: " + restaurantId);
            throw new DataIntegrityViolationException("" + e);
        }

        return "Added";
    }

    public Event getEventByUserId(int organizerId){
        String sql = "SELECT * FROM event WHERE organizer_id = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, organizerId);
            if (results.next()){
                return mapRowToEvent(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("[Restaurant JDBC DAO] Unable to connect to server or database");
            throw new CannotGetJdbcConnectionException("" + e);
        } catch (DataIntegrityViolationException e) {
            System.out.println("[Restaurant JDBC DAO] getEventByUserId() problem getting event by user ID: " + organizerId);
            throw new DataIntegrityViolationException("" + e);
        }

        return new Event();
    }
}



