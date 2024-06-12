package com.techelevator.dao;
import com.techelevator.model.Event;
import java.util.List;
import com.techelevator.model.TotalVoteCount;

public interface EventDao {

    List<Event> getAllEvents ();
    Event getEventById (int eventId);
    Event createEvent (Event event);
    Event getEventByLink (String eventLink);
   // List<TotalVoteCount>getVotesByEventIdAndRestaurantId (int eventId, String restaurantId);

    // Chris's edits...
    public void insertUniqueEventUserLink(String userEventLink, int eventId);
    public int getRestaurantEventYesVotes(int eventId, String restaurantId);
    public int getRestaurantEventNoVotes(int eventId, String restaurantId);
    public String addRestaurantEventYesVote(int eventId, String restaurantId);
    public String addRestaurantEventNoVote(int eventId, String restaurantId);
    public Event getEventByUserId(int userId);
}