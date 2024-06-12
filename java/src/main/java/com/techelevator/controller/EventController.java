package com.techelevator.controller;

import com.techelevator.dao.EventDao;
import com.techelevator.dao.RestaurantDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Event;
import com.techelevator.model.Restaurant;
import com.techelevator.model.User;
import com.techelevator.service.YelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/events")
@CrossOrigin
public class EventController {

    @Autowired
    YelpService yelpService;

    @Autowired
    private EventDao eventDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RestaurantDao restaurantDao;


    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Event> getAllEvents() {
        return eventDao.getAllEvents();
    }

    @RequestMapping(path = "/{eventId}", method = RequestMethod.GET)
    public Event getEventById(@PathVariable int eventId) {

        Event event = eventDao.getEventById(eventId);

        if (event == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found.");
        }

        LocalDateTime now =LocalDateTime.now();

        DateTimeFormatter formatter =DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        String now2 = now.toString().substring(0,16);
        String timeString = event.getDecisionDate();
        String timeStringModify = timeString.replace(" ", "T");
        LocalDateTime eventDate =LocalDateTime.parse(timeStringModify);

        if(now.isBefore(eventDate)) {
            return event;
        }else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The current time is after the event's decision date/time.");
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/create")
    public Event createEvent(@RequestBody Event event, Principal principal) {

        if (event.getEventName() == null || event.getEventName().isEmpty() ||
                event.getLocation() == null || event.getLocation().isEmpty() ||
                event.getDecisionDate() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please provide event name, location and date/time.");
        }

        User organizer = userDao.getUserByUsername(principal.getName());

       if (event.getEventLink() == null || event.getEventLink().isEmpty()) {
           event.setEventLink(generateUniqueEventLink());
        }

        System.out.println("[Event Controller] createEvent() Event unique URL: " );

        if (organizer == null) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User does not exist.");
        }

        event.setOrganizerId(organizer.getId());

        if (event.getEventLink() == null || event.getEventLink().isEmpty()) {
            event.setEventLink(generateUniqueEventLink());
        }

        System.out.println("[Event Controller] createEvent() Event unique URL: " + event.getEventLink() );


        return eventDao.createEvent(event);
    }

    private String generateUniqueEventLink() {

        // Chris's note...
        // Had to update because including the localhost URL will break the event search...
        String url = "http://localhost:9000/events/";
        String uniqueId = UUID.randomUUID().toString();
        //String newUrl = url + uniqueId;
        System.out.println("[Event Controller] generateUniqueEventLink() Unique URL: " + uniqueId);
        return uniqueId;
    }

    @GetMapping(path = "/{eventId}/restaurants")
    public List<Restaurant> getRestaurants(@PathVariable int eventId) {
        System.out.println("Getting restaurants for event ID: " + eventId);
        return restaurantDao.getRestaurantsByEventId(eventId);
    }

    @RequestMapping(path = "/link/{eventLink}", method = RequestMethod.GET)
    public Event accessEventLink(@PathVariable String eventLink) {
        Event event = eventDao.getEventByLink(eventLink);

        if (event == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event does not exist.");
        }
        return event;
    }

    // Chris's Edits...
    // This creates a unique link for an event that the organizer can sent to a specific user.
    @GetMapping(path = "/{eventId}/create-link")
    public String generateUniqueUserEventLink(@PathVariable int eventId) {
        String uniqueId = UUID.randomUUID().toString();
        System.out.println("[Event Controller] generateUniqueEventLink() Unique URL: " + uniqueId);
        eventDao.insertUniqueEventUserLink(uniqueId, eventId); // Hardcoded user ID needs to get the principal ID.
        System.out.println("[Event Controller] generateUniqueUserEventLink() created using a hardcoded user ID.");
        return uniqueId;
    }

    // This returns the event based on its unique event link.
    @GetMapping(path = "/attendee-link/{eventLink}")
    public Event getEventByLink(@PathVariable String eventLink) {
        return new Event();
    }

    @GetMapping(path = "/yes-votes/{eventId}/{restaurantId}")
    public int getRestaurantEventYesVotes(@PathVariable int eventId, @PathVariable String restaurantId) {
        return eventDao.getRestaurantEventYesVotes(eventId, restaurantId);
    }

    @GetMapping(path = "/no-votes/{eventId}/{restaurantId}")
    public int getRestaurantEventNoVotes(@PathVariable int eventId, @PathVariable String restaurantId) {
        return eventDao.getRestaurantEventNoVotes(eventId, restaurantId);
    }

    @PostMapping(path = "/add-yes-vote/{eventId}/{restaurantId}")
    public String addRestaurantEventYesVote(@PathVariable int eventId, @PathVariable String restaurantId) {
        return eventDao.addRestaurantEventYesVote(eventId, restaurantId);
    }

    @PostMapping(path = "/add-no-vote/{eventId}/{restaurantId}")
    public String addRestaurantEventNoVote(@PathVariable int eventId, @PathVariable String restaurantId) {
        return eventDao.addRestaurantEventNoVote(eventId, restaurantId);
    }

    @RequestMapping(path = "/organizer", method = RequestMethod.GET)
    public Event getEventByUserId(Principal principal){
        System.out.println(principal);
        User organizer = userDao.getUserByUsername(principal.getName());
        int organizerId = organizer.getId();

        return eventDao.getEventByUserId(organizerId);
    }

   // @RequestMapping(path = "/{eventId}/restaurants/{restaurantId}/vote")
   // public List<TotalVoteCount> getVotesByEventIdAndRestaurantId(@PathVariable int eventId, @PathVariable String restaurantId) {
      //  return eventDao.getVotesByEventIdAndRestaurantId(eventId,restaurantId);
    //}

}




