package com.techelevator.controller;

import com.techelevator.model.Restaurant;
import com.techelevator.service.YelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class RestaurantController {

    @Autowired
    YelpService yelpService;

    @GetMapping("/")
    public void connected(Principal principal){
        System.out.println(principal + " connected!");
    }

    @GetMapping("/search")
    public List<Restaurant> searchResults(@RequestParam (defaultValue = "45202") String zipcode, @RequestParam (defaultValue = "10") int limit, @RequestParam (defaultValue = "food") String term) {
        return yelpService.getSearchResults(zipcode, limit, term);
        // Main
    }
}
