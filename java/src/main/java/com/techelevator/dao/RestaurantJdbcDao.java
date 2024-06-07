package com.techelevator.dao;

import com.techelevator.model.Coordinates;
import com.techelevator.model.Open;
import com.techelevator.model.Restaurant;
import jdk.jfr.Category;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
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
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?;";

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
                System.out.println("[Restaurant JDBC DAO] Problem adding restaurant to the database. " + ex.getMessage());
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
        String sql = "SELECT restaurant_id FROM restaurant WHERE restaurant_id = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, restaurantId);
            if (results.wasNull()){
                return false;
            } else {
                return true;
            }
        } catch (DataAccessException e) {
            System.out.println("[Restaurant JDBC DAO] Problem checking if restaurant exists.");
            return false;
        }
    }

    @Override
    public Restaurant getRestaurantById(String restaurantId) {
        // Create a new restaurant.
        Restaurant newRestaurant = null;

        // Get a restaurant by the restaurant_id from the restaurant table.
        String sql = "SELECT * FROM restaurant WHERE restaurant_id = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, restaurantId);
            if (results.next()){
                newRestaurant = mapRowToRestaurant(results);
            }
        } catch (DataAccessException e) {
            System.out.println("[Restaurant JDBC DAO] Problem selection restaurant by id.");
        }

        return newRestaurant;
    }

    @Override
    public List<Restaurant> getRestaurantsByEventId(int eventId) {
        // Create a new list.
        List<Restaurant> newRestaurants = new ArrayList<>();

        // Get a list of restaurant_id's by an event id.
        String sql = "SELECT * FROM restaurant_event WHERE event_id = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, eventId);
            if (results.next()){
                newRestaurants.add(getRestaurantById(results.getString("restaurant_id")));
            }
        } catch (DataAccessException e) {
            System.out.println("[Restaurant JDBC DAO] Problem selecting restaurants by event id.");
        }

        // By referencing the restaurant_event table.
        return newRestaurants;
    }

    @Override
    public List<Open> getHours(String restaurantId){
        List<Open> hours = new ArrayList<>();

        String sql = "SELECT * FROM restaurant_hours WHERE restaurant_id = ?;";

        // Return the hours from the database.
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, restaurantId);
            if (results.next()){
                // Create a new Open object and populate with the relevant information.
                Open open = new Open();
                open.setDay(results.getInt("day_id"));
                open.setDayName(results.getString("day_name"));
                open.setStart(results.getInt("start"));
                open.setEnd(results.getInt("end"));

                hours.add(open);
            }
        } catch (DataAccessException e) {
            System.out.println("[Restaurant JDBC DAO] Problem selecting restaurants by event id.");
        }

        return hours;
    }

    @Override
    public int getCategoryId(String categoryName){
        // Check to see if the category exists in the category table.
        String sql = "SELECT * FROM category WHERE category_name = ?;";

        try {
            // Return the category id.
            return jdbcTemplate.queryForObject(sql, Integer.class, categoryName);
        } catch (DataAccessException e){
            System.out.println("[Restaurant JDBC DAO] Problem accessing category id.");
        }

        // Return -1 is category cannot be found.
        return -1;
    }

    @Override
    public int getTransactionId(String transactionName){
        // Check to see if the transaction type exists in the transactions table.
        String sql = "SELECT * FROM transactions WHERE transaction_name = ?;";

        try {
            // Return the transaction id.
            return jdbcTemplate.queryForObject(sql, Integer.class, transactionName);
        } catch (DataAccessException e) {
            System.out.println("[Restaurant JDBC DAO] Problem accessing transaction id.");
        }

        // Return -1 if the transaction id cannot be found.
        return -1;
    }

    public Open getOpen(int openId) {
        Open open = null;

        String sql = "SELECT * FROM restaurant_hours WHERE hours_id = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, openId);
            if (results.next()){
                open = new Open();
                open.setDay(results.getInt("day_id"));
                open.setDayName(results.getString("day_name"));
                open.setStart(results.getInt("start"));
                open.setEnd(results.getInt("end"));
            }
        } catch (DataAccessException e) {
            System.out.println("[Restaurant JDBC DAO] Problem getting hours.");
        }

        return open;
    }

    @Override
    public List<Open> createHours(String restaurantId, List<Open> hours){
        List<Open> opens = new ArrayList<>();
        // Add a hours to the restaurant_hours table.
        String sql = "INSERT INTO hours (restaurant_id, day_id, day_name," +
                "start, end " +
                "VALUE (?, ?, ?, ?, ?);";

        try {
            for (Open open : hours) {
                int openId = jdbcTemplate.queryForObject(sql, int.class,
                        restaurantId,
                        open.getDay(),
                        open.getDayName(open.getDay()),
                        open.getStart(),
                        open.getEnd());

                // Get the hour object.
                Open newOpen = getOpen(openId);
            }

        } catch (DataAccessException e) {
            System.out.println("[Restaurant JDBC DAO] Problem adding hours.");
        }

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

    Restaurant mapRowToRestaurant(SqlRowSet results){
        Restaurant newRestaurant = new Restaurant();

        // Map sql row set to restaurant.
        newRestaurant.setId(results.getString("restaurant_id"));
        newRestaurant.setName(results.getString("name"));
        newRestaurant.setPhoneNumber(results.getString("phone"));
        newRestaurant.setAddress1(results.getString("address1"));
        newRestaurant.setAddress2(results.getString("address2"));
        newRestaurant.setAddress3(results.getString("address3"));
        newRestaurant.setCity(results.getString("city"));
        newRestaurant.setCountry(results.getString("country"));
        newRestaurant.setState(results.getString("state"));
        newRestaurant.setZipcode(results.getString("zipcode"));
        newRestaurant.setImageUrl(results.getString("image_url"));
        newRestaurant.setMenuUrl(results.getString("menu_url"));
        newRestaurant.setRating(results.getDouble("rating"));
        newRestaurant.setCoordinates(new Coordinates(
                results.getDouble("latitude"),
                results.getDouble("longitude")
        ));

        return newRestaurant;
    }
}
