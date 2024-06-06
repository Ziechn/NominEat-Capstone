package com.techelevator.dao;
import com.techelevator.model.Event;
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
        Event event = null;

        String sql = "SELECT event_id, organizer_id, event_name, " +
                "zipcode, event_link, decision_date\n" +
                "FROM event;";


        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            event = mapRowToEvent(results);
            events.add(event);
        }
        return events;
    }

    @Override
    public Event getEventById(int eventId) {
        Event event = null;

        String sql = "SELECT event_id, organizer_id, event_name, " +
                "zipcode, event_link, decision_date\n" +
                "FROM event\n" +
                "WHERE event_id =?;";


        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, eventId);
        if (results.next()) {
            event = mapRowToEvent(results);
        }
        return event;

    }

    @Override
    public Event createEvent(Event event) {
        Event newEvent = null;

        String sql = "INSERT INTO event (organizer_id, event_name, " +
                "zipcode, event_link, decision_date)\n" +
                "VALUES (?, ?, ?, ?, ?)\n" +
                "RETURNING event_id;";
        try {
            int eventId = jdbcTemplate.queryForObject(sql, int.class, event.getOrganizerId(),
                    event.getEventName(), event.getZipcode(),event.getEventLink(),
                    event.getDecisionDate());

            newEvent = event;
            newEvent.setEventId(eventId);
        } catch (Exception ex) {
            System.out.println("Something went wrong" + ex.getMessage());
        }
        return newEvent;

    }

    @Override
    public Event getEventByLink(String eventLink) {
        Event event = null;

        String sql = "SELECT event_id, organizer_id, event_name, zipcode, event_link, decision_date\n" +
                "FROM event\n" +
                "WHERE event_link = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, eventLink);
        if (results.next()) {
            event = mapRowToEvent(results);
        }
        return event;
    }


    private Event mapRowToEvent(SqlRowSet results) {
        Event event = new Event();
        event.setEventId(results.getInt("event_id"));
        event.setEventName(results.getString("event_name"));
        event.setOrganizerId(results.getInt("organizer_id"));
        event.setZipcode(results.getString("zipcode"));
        event.setEventLink(results.getString("event_link"));
        event.setDecisionDate(results.getTimestamp("decision_date"));
        return event;


    }
}



