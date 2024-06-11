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
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping (path = "/events")
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
        return eventDao.getEventById(eventId);
    }

    //@ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/create")
    public Event createEvent(@RequestBody Event event) {

//        if (event.getEventName() == null || event.getEventName().isEmpty() ||
//                event.getLocation() == null || event.getLocation().isEmpty() ||
//                event.getDecisionDate() == null) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please provide event name, zipcode and date/time.");
//        }

//        User organizer = userDao.getUserByUsername(principal.getName());
//        if (organizer == null) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User does not exist.");
//        }

        // event.setOrganizerId(organizer.getId());
        // event.setOrganizerId(1);

//       if (event.getEventLink() == null || event.getEventLink().isEmpty()) {
//        }

        String newUrl = generateUniqueEventLink();
        event.setEventLink(newUrl);
        System.out.println("[Event Controller] createEvent() Event unique URL: " );

        // Make a decision date...
        // Date date = new Date(2024, 7, 10);
        // long time = date.getTime();
        // System.out.println(time);
        //event.setDecisionDate(new Timestamp(time));

        Event newEvent = eventDao.createEvent(event);

        return newEvent;
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
    public List<Restaurant> getRestaurants(@PathVariable int eventId){
        return restaurantDao.getRestaurantsByEventId(eventId);
    }

    @RequestMapping(path = "/link/{eventLink}", method = RequestMethod.GET)
    public Event accessEventLink (@PathVariable String eventLink) {
        Event event = eventDao.getEventByLink(eventLink);

        if( event == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event does not exist.");
        }

        // LocalDateTime now = LocalDateTime.now();
        // System.out.println(now);
        // if (now.isAfter(event.getDecisionDate().toLocalDateTime())) {
        //    throw new ResponseStatusException(HttpStatus.LOCKED, "Can not accessed link");
        // }

        return event;
    }

    // Chris's Edits...
    // This creates a unique link for an event that the organizer can sent to a specific user.
    @GetMapping(path = "/{eventId}/create-link")
    public String generateUniqueUserEventLink(@PathVariable int eventId){
        String uniqueId = UUID.randomUUID().toString();
        System.out.println("[Event Controller] generateUniqueEventLink() Unique URL: " + uniqueId);
        eventDao.insertUniqueEventUserLink(uniqueId, eventId); // Hardcoded user ID needs to get the principal ID.
        System.out.println("[Event Controller] generateUniqueUserEventLink() created using a hardcoded user ID.");
        return uniqueId;
    }

    // This returns the event based on its unique event link.
    @GetMapping(path = "/attendee-link/{eventLink}")
    public Event getEventByLink(@PathVariable String eventLink){
        return new Event();
    }

    @GetMapping(path = "/yes-votes/{eventId}/{restaurantId}")
    public int getRestaurantEventYesVotes(@PathVariable int eventId, @PathVariable String restaurantId){
        return eventDao.getRestaurantEventYesVotes(eventId, restaurantId);
    }

    @GetMapping(path = "/no-votes/{eventId}/{restaurantId}")
    public int getRestaurantEventNoVotes(@PathVariable int eventId, @PathVariable String restaurantId){
        return eventDao.getRestaurantEventNoVotes(eventId, restaurantId);
    }

    @PostMapping(path = "/add-yes-vote/{eventId}/{restaurantId}")
    public String addRestaurantEventYesVote(@PathVariable int eventId, @PathVariable String restaurantId){
        return eventDao.addRestaurantEventYesVote(eventId, restaurantId);
    }

    @PostMapping(path = "/add-no-vote/{eventId}/{restaurantId}")
    public String addRestaurantEventNoVote(@PathVariable int eventId, @PathVariable String restaurantId){
        return eventDao.addRestaurantEventNoVote(eventId, restaurantId);
    }
}




