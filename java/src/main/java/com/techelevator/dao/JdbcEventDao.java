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

    public JdbcEventDao (JdbdTemplate jdbdTemplate) {
        this.jdbcTemplate = jdbdTemplate;
    }


    @Override
    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        Event event = null;

        Sring sql = "SELECT event_id, organizer_id, event_name\n" +
                "FROM event;";

        SqlRow results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            event = mapRowToEvent(results);
            events.add(events);
        }
        return events;


        @Override
        public Event getEventById (int eventId) {
            Event event = null;

            String sql = "SELECT event_id, organizer_id, event_name\n" +
                    "FROM event\n" +
                    "WHERE event_id = ?;";

            SqlRowSet results = jdbcTemplate.queryForRowSet (sql, eventId);
            if (results.next()) {
                event = mapRowToEvent(results);
            }
            return event;

        }
        @Override
        public Event createEvent (Event event) {
            Event newEvent = null;

            String sql = "INSERT INTO event (organizer_id, event_name)\n" +
                    "VALUES (?,?)\n" +
                    "RETURNING event_id;";

            try {
                int eventId = jdbcTemplate.queryForObject(sql, int.class, event.getOrganizerId(), getEventName());

                newEvent = event;
                newEvent.setEventId(eventId);
            } catch (Exception ex) {
                System.out.println ("Something went wrong" + ex.getMessage());
            }
            return newEvent;

        }

        private Event mapRowToEvent (SqlRowSet results){
            Event event = new Event ();
            event.setEventId(results.getInt("event_id"));
            event.setEventName(results.getString("event_name"));
            event.setOrganizerId(results.getInt("organizer_id"));
            return event;

        }


    }

}