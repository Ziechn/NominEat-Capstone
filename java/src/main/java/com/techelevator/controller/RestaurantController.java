package com.techelevator.controller;

import com.techelevator.dao.RestaurantDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Restaurant;
import com.techelevator.model.User;
import com.techelevator.service.YelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping (path = "/restaurants")
@CrossOrigin
public class RestaurantController {

    @Autowired
    YelpService yelpService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RestaurantDao restaurantDao;

    @GetMapping("/")
    public void connected(Principal principal){
        System.out.println(principal + " connected!");
    }

    @GetMapping("/search")
    public List<Restaurant> searchResults(@RequestParam (defaultValue = "45202") String zipcode, @RequestParam (defaultValue = "10") int limit, @RequestParam (defaultValue = "food") String term) {
        return yelpService.getSearchResults(zipcode, limit, term);
        // 002-backend
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/create")
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant) {
        if (restaurant != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please provide a restaurant object.");
        }
        return restaurantDao.createRestaurant(restaurant);
    }
}
