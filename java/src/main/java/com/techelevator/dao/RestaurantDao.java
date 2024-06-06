package com.techelevator.dao;

import com.techelevator.model.Restaurant;

import java.util.List;

public interface RestaurantDao {
    public Restaurant createRestaurant(Restaurant restaurant);
    public Restaurant getRestaurantById(String restaurantId);
    public List<Restaurant> getRestaurantsByEventId(int eventId);
}
