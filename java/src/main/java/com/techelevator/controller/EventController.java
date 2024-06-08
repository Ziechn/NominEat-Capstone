package com.techelevator.controller;

import com.techelevator.dao.EventDao;
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
    public int createEvent(@RequestBody Event event) {

        System.out.println("[Event Controller] createEvent()");

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

        String newUrl =generateUniqueEventLink();
        event.setEventLink(newUrl);
        System.out.println("[Event Controller] createEvent() Event unique URL: " );

        Event newEvent = eventDao.createEvent(event);

        return newEvent.getEventId();
    }

    private String generateUniqueEventLink() {
        String url = "http://localhost:9000/event/";
        String uniqueId = UUID.randomUUID().toString();
        String newUrl = url + uniqueId;
        System.out.println("[Event Controller] generateUniqueEventLink() Unique URL: " + newUrl);
        return newUrl;
    }

    @RequestMapping(path = "/{eventId}/restaurants", method = RequestMethod.GET)
    public List<Restaurant> getEventRestaurants (@PathVariable int eventId, @RequestParam (defaultValue = "10") int limit,@RequestParam (defaultValue = "food") String term) {
       Event event = eventDao.getEventById(eventId);

       if (event == null) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event does not exist.");
       }
        return yelpService.getSearchResults(event.getLocation(), limit, term);
    }

    @RequestMapping(path = "/access/{eventLink}", method = RequestMethod.GET)
    public Event accessEventLink (@PathVariable String eventLink) {
        Event event = eventDao.getEventByLink(eventLink);

        if( event == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event does not exist.");
        }

        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(event.getDecisionDate().toLocalDateTime())) {
            throw new ResponseStatusException(HttpStatus.LOCKED, "Can not accessed link");
        }
        return event;
    }
}




