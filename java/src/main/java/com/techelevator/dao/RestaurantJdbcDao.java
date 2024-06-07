package com.techelevator.dao;

import com.techelevator.model.Open;
import com.techelevator.model.Restaurant;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestaurantJdbcDao implements RestaurantDao {


    @Override
    public Restaurant createRestaurant(Restaurant restaurant) {
        // Create a new restaurant:
        // Create the sql query:

        // Check to see if the id exists in the table
        // If not, add to the restaurant table

        // Add the hours to the restaurant_hours table

        // Check to see if each category exists in the category table
        // If so, return the category_id.
        // If not, add them to the category table, and return the category_id.

        // Associate the restaurant_id with the category_id in the restaurant_category table.

        // Check to see if each transaction type exists in the transactions table.
        // If so, return the category_id.
        // If not, add them to the transactions table, and return the transaction_id.

        // Associate the restaurant_id with the transaction_id on the restaurant_transactions table.

        return new Restaurant();
    }

    @Override
    public Restaurant getRestaurantById(String restaurantId) {

        return new Restaurant();
    }


    @Override
    public List<Restaurant> getRestaurantsByEventId(int eventId) {
        // Get a list of restaurants by an event id.
        // By referencing the restaurant_event table.
        return new ArrayList<>();
    }

    @Override
    public boolean doesRestaurantExist(String restaurantId) {
        // Check to see if the restaurant exists in the restaurant table.
        return false;
    }

    @Override
    public List<Open> getHours(String restaurantId) {
        // Return the hours from the database.
        return new ArrayList<>();
    }

    @Override
    public int getCategoryId(String categoryName) {
        // Check to see if the category exists in the category table.
        // Return the category id.
        return -1;
    }

    @Override
    public int getTransactionId(String transactionName) {
        // Check to see if the transaction type exists in the transactions table.
        // Return the transaction id.
        return -1;
    }

    @Override
    public List<Open> createHours(String restaurantId, List<Open> hours) {
        // Add a hours to the restaurant_hours table.
        // Return the created hours list.
        return new ArrayList<>();
    }

    @Override
    public int createCategory(String categoryName) {
        // Add the category into the category table.
        // Return the new category_id.
        return -1;
    }

    @Override
    public int createTransaction(String transactionName) {
        // Add the transaction type into the transactions table.
        // Return the new transaction_id.
        return -1;
    }


}



