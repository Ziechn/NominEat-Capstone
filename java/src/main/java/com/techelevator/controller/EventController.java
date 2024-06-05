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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/create")
    public Event createEvent(@RequestBody Event event, @RequestParam String username) {
        if (event.getEventName() == null || event.getEventName().isEmpty() ||
                event.getLocation() == null || event.getLocation().isEmpty() ||
                event.getDecisionDate() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please provide event name, location and date/time.");
        }
        User organizer = userDao.getUserByUsername(username);
        if (organizer == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User does not exist.");
        }
        event.setOrganizerId(organizer.getId());

       if (event.getEventLink() == null || event.getEventLink().isEmpty()) {
           event.setEventLink(generateUniqueEventLink());
        }

        return eventDao.createEvent(event);
    }

    private String generateUniqueEventLink() {
       String url = "http://localhost:9000/event/";
        String uniqueId = UUID.randomUUID().toString();
        return url + uniqueId;

    }



    @RequestMapping(path = "/{eventId}/restaurants", method = RequestMethod.GET)
    public List<Restaurant> getEventRestaurants (@PathVariable int eventId, @RequestParam (defaultValue = "10") int limit) {
       Event event = eventDao.getEventById(eventId);

       if (event == null) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event does not exist.");
       }
       return yelpService.getSearchResults(event.getLocation(), limit);
    }
}




