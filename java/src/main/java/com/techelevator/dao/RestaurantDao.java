package com.techelevator.dao;

import com.techelevator.model.Open;
import com.techelevator.model.Restaurant;

import java.util.List;

public interface RestaurantDao {
    public List<Restaurant> addRestaurants(List<Restaurant> restaurants, int eventDao);
    public Restaurant createRestaurant(Restaurant restaurant);
    public Restaurant getRestaurantById(String restaurantId);
    public List<Restaurant> getRestaurantsByEventId(int eventId);
    public boolean doesRestaurantExist(String restaurantId);
    public List<Open> getHours(String restaurantId);
    public int getCategoryId(String categoryName);
    public int getTransactionId(String transactionName);
    public List<Open> createHours(String restaurantId, List<Open> hours);
    public int createCategory(String categoryName);
    public int createTransaction(String transactionName);
    public List<Restaurant> getAllRestaurants();
}
