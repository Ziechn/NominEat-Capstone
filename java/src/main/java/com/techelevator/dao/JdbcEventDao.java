package com.techelevator.dao;
import com.techelevator.model.Event;
import com.techelevator.model.Restaurant;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
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

        try {
            int eventId = jdbcTemplate.queryForObject(sql, int.class,
                    event.getOrganizerId(),
                    event.getEventName(),
                    event.getLocation(),
                    event.getEventLink(),
                    event.getDecisionDate());

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
        Event event = null;

        String sql = "SELECT event_id, organizer_id, event_name, location, event_link, decision_date " +
                "FROM event " +
                "WHERE event_link = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, eventLink);
            if (results.next()) {
                event = mapRowToEvent(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("[Restaurant JDBC DAO] Unable to connect to server or database");
            throw new CannotGetJdbcConnectionException("" + e);
        } catch (DataIntegrityViolationException e) {
            System.out.println("[Restaurant JDBC DAO] getEventByLink() Problem getting event from url: " + eventLink);
            throw new DataIntegrityViolationException("" + e);
        }

        return event;
    }

    private Event mapRowToEvent(SqlRowSet results) {
        Event event = new Event();
        event.setEventId(results.getInt("event_id"));
        event.setEventName(results.getString("event_name"));
        event.setOrganizerId(results.getInt("organizer_id"));
        event.setLocation(results.getString("location"));
        event.setEventLink(results.getString("event_link"));
        event.setDecisionDate(results.getTimestamp("decision_date"));
        return event;
    }
}



