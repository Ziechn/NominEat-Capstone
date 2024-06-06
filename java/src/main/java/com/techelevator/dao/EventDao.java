package com.techelevator.dao;
import com.techelevator.model.Event;
import java.util.List;


public interface EventDao {

    List<Event> getAllEvents ();
    Event getEventById (int eventId);
    Event createEvent (Event event);
    Event getEventByLink (String eventLink);



}