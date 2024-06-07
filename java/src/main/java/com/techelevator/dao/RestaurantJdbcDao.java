package com.techelevator.dao;

import com.techelevator.model.Open;
import com.techelevator.model.Restaurant;
import jdk.jfr.Category;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestaurantJdbcDao implements RestaurantDao {
    private final JdbcTemplate jdbcTemplate;
    public RestaurantJdbcDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Restaurant> addRestaurants(List<Restaurant> restaurants){
        // Make a new list...
        List<Restaurant> createdRestaurantList = new ArrayList<>();

        // Create a restaurant for each restaurant in provided list.
        for (Restaurant restaurant : restaurants){
            createdRestaurantList.add(createRestaurant(restaurant));
        }

        // Return that list.
        return createdRestaurantList;
    }
    @Override
    public Restaurant createRestaurant(Restaurant restaurant) {
        // Create a new restaurant.
        Restaurant newRestaurant = new Restaurant();

        // Check to see if the id exists in the table.
        if (doesRestaurantExist(restaurant.getId())) {
            newRestaurant = getRestaurantById(restaurant.getId());
        } else {
            // If not, add to the restaurant table.
            String sql = "INSERT INTO restaurant (name, phone, address1, address2, address3, " +
                    "city, country, state, zipcode, image_url, menu_url, rating, " +
                    "latitude, longitude" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";

            try {
                String restaurantId = jdbcTemplate.queryForObject(sql, String.class,
                        restaurant.getName(),
                        restaurant.getPhoneNumber(),
                        restaurant.getAddress1(),
                        restaurant.getAddress2(),
                        restaurant.getAddress3(),
                        restaurant.getCity(),
                        restaurant.getCountry(),
                        restaurant.getState(),
                        restaurant.getZipcode(),
                        restaurant.getImageUrl(),
                        restaurant.getMenuUrl(),
                        restaurant.getRating(),
                        restaurant.getCoordinates().getLatitude(),
                        restaurant.getCoordinates().getLongitude());

                newRestaurant = getRestaurantById(restaurantId);
            } catch (Exception ex) {
                System.out.println("Something went wrong" + ex.getMessage());
            }

            // Add the hours to the restaurant_hours table
            createHours(restaurant.getId(), restaurant.getHours());
        }

        // Check to see if each category exists in the category table
        for (String category : restaurant.getCategories()){
            if (getCategoryId(category) > 0){
                // If so, return the category_id.
                int categoryId = getCategoryId(category);

                // Associate the restaurant_id with the category_id in the restaurant_category table.
                associateCategoryAndRestaurant(restaurant.getId(), categoryId);
            } else {
                // If not, add them to the category table, and return the category_id.
                int categoryId = createCategory(category);

                // Associate the restaurant_id with the category_id in the restaurant_category table.
                associateCategoryAndRestaurant(restaurant.getId(), categoryId);
            }
        }

        // Check to see if each transaction type exists in the transactions table.
        for (String transaction : restaurant.getTransactions()) {
            if (getTransactionId(transaction) > 0){
                // If so, return the category_id.
                int transactionId = getTransactionId(transaction);

                // Associate the restaurant_id with the transaction_id on the restaurant_transactions table.
                associateTransactionAndRestaurant(restaurant.getId(), transactionId);
            } else {
                // If not, add them to the transactions table, and return the transaction_id.
                int transactionId = createTransaction(transaction);
                // Associate the restaurant_id with the transaction_id on the restaurant_transactions table.
                associateTransactionAndRestaurant(restaurant.getId(), transactionId);
            }
        }

        return newRestaurant;
    }

    @Override
    public boolean doesRestaurantExist(String restaurantId){
        // Check to see if the restaurant exists in the restaurant table.
        return false;
    }

    @Override
    public Restaurant getRestaurantById(String restaurantId) {
        // Get a restaurant by the restaurant_id from the restaurant table.
        return new Restaurant();
    }

    @Override
    public List<Restaurant> getRestaurantsByEventId(int eventId) {
        // Get a list of restaurants by an event id.
        // By referencing the restaurant_event table.
        return new ArrayList<>();
    }

    @Override
    public List<Open> getHours(String restaurantId){
        // Return the hours from the database.
        return new ArrayList<>();
    }

    @Override
    public int getCategoryId(String categoryName){
        // Check to see if the category exists in the category table.
        // Return the category id.
        return -1;
    }

    @Override
    public int getTransactionId(String transactionName){
        // Check to see if the transaction type exists in the transactions table.
        // Return the transaction id.
        return -1;
    }

    @Override
    public List<Open> createHours(String restaurantId, List<Open> hours){
        // Add a hours to the restaurant_hours table.
        // Return the created hours list.
        return new ArrayList<>();
    }

    @Override
    public int createCategory(String categoryName){
        // Get the last category id and add 1 tp create the new id.
        // Add the category into the category table.
        // Return the new category_id.
        return -1;
    }

    @Override
    public int createTransaction(String transactionName){
        // Get the last transaction_id and add 1 to create the new id.
        // Add the transaction type into the transactions table.
        // Return the new transaction_id.
        return -1;
    }

    public int associateCategoryAndRestaurant(String restaurantId, int categoryId){
        // Insert restaurant_id and category_id into the restaurant_category table.
        return -1;
    }

    public int associateTransactionAndRestaurant(String restaurantId, int transactionId){
        // Insert restaurant_id and transaction_id into the restaurant_transaction table.
        return -1;
    }
}
