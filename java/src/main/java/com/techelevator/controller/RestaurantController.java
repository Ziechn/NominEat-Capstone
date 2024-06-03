package com.techelevator.controller;

import com.techelevator.model.Restaurant;
import com.techelevator.service.YelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class RestaurantController {

    @Autowired
    YelpService yelpService;

    @GetMapping("/")
    public void connected(){
        System.out.println("Connected");
    }

    @GetMapping("/search")
    public List<Restaurant> searchResults(@RequestParam String zipcode) {
        return yelpService.getSearchResults(zipcode);
    }
}
