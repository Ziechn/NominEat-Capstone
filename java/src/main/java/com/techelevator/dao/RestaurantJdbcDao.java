package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Coordinates;
import com.techelevator.model.Open;
import com.techelevator.model.Restaurant;
import jdk.jfr.Category;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestaurantJdbcDao implements RestaurantDao {
    private final JdbcTemplate jdbcTemplate;
    public RestaurantJdbcDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Restaurant> addRestaurants(List<Restaurant> restaurants, int eventId){
        // Make a new list...
        List<Restaurant> createdRestaurantList = new ArrayList<>();

        // Create a restaurant for each restaurant in provided list.
        for (Restaurant restaurant : restaurants){
            System.out.println("[Restaurant JDBC DAO] addRestaurants() Original restaurant ID: " + restaurant.getId());
            createdRestaurantList.add(createRestaurant(restaurant));

            associateEventWithRestaurants(restaurant.getId(), eventId);
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
            System.out.println("[Restaurant JDBC DAO] createRestaurant() Existing restaurant ID: " + newRestaurant.getId());
        } else {
            System.out.println("[Restaurant JDBC DAO] createRestaurant() Restaurant does not exist. Creating...");

            // If not, add to the restaurant table.
            String sql = "INSERT INTO restaurant (restaurant_id, name, phone, address1, address2, address3, " +
                    "city, country, state, zipcode, image_url, menu_url, rating, " +
                    "latitude, longitude) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING restaurant_id;";

            try {
                String restaurantId = jdbcTemplate.queryForObject(sql, String.class,
                        restaurant.getId(),
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
            } catch (CannotGetJdbcConnectionException e) {
                System.out.println("[Restaurant JDBC DAO] Unable to connect to server or database");
            } catch (DataIntegrityViolationException e) {
                System.out.println("[Restaurant JDBC DAO] createRestaurant() Problem adding restaurant to the database.");
                throw new DataIntegrityViolationException("" + e);
            }

            System.out.println("[Restaurant JDBC DAO] createRestaurant() New Restaurant ID: " + newRestaurant.getId());

            // Add the hours to the restaurant_hours table
            createHours(restaurant.getId(), restaurant.getHours());
        }

        // If not, check to see if each category exists in the category table
        for (String category : restaurant.getCategories()){
            // First check to see if the association exists.
            if (getCategoryAssociationRowCount(restaurant.getId(), category) < 1){
                if (getCategoryId(category) > 0){
                    // If so, return the category_id.
                    int categoryId = getCategoryId(category);
                    System.out.println("[Restaurant JDBC DAO] createRestaurant() Category found. ID: " + categoryId);

                    // Associate the restaurant_id with the category_id in the restaurant_category table.
                    associateCategoryAndRestaurant(restaurant.getId(), categoryId);
                } else {
                    // If not, add them to the category table, and return the category_id.
                    System.out.println("[Restaurant JDBC DAO] createRestaurant() Category not found. Creating new category.");

                    int categoryId = createCategory(category);

                    // Associate the restaurant_id with the category_id in the restaurant_category table.
                    associateCategoryAndRestaurant(restaurant.getId(), categoryId);
                }
            } else {
                System.out.println("[Restaurant JDBC DAO] createRestaurant() Category association found for restaurant ID: " +restaurant.getId() + " and transaction name: " + category);
            }
        }

        // If not, check to see if each transaction type exists in the transactions table.
        for (String transaction : restaurant.getTransactions()) {
            // First check to see if the association exists...
            if (getTransactionAssociationRowCount(restaurant.getId(), transaction) < 1) {
                if (getTransactionId(transaction) > 0) {
                    // If so, return the category_id.
                    int transactionId = getTransactionId(transaction);
                    System.out.println("[Restaurant JDBC DAO] createRestaurant() Category found. ID: " + transactionId);

                    // Associate the restaurant_id with the transaction_id on the restaurant_transactions table.
                    associateTransactionAndRestaurant(restaurant.getId(), transactionId);
                } else {
                    // If not, add them to the transactions table, and return the transaction_id.
                    System.out.println("[Restaurant JDBC DAO] createRestaurant() Transaction not found. Creating new transaction.");

                    int transactionId = createTransaction(transaction);

                    // Associate the restaurant_id with the transaction_id on the restaurant_transactions table.
                    associateTransactionAndRestaurant(restaurant.getId(), transactionId);
                }
            } else {
                System.out.println("[Restaurant JDBC DAO] createRestaurant() Transaction association found for restaurant ID: " +restaurant.getId() + " and transaction name: " + transaction);
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
            if (results.next()){
                System.out.println("[Restaurant JDBC DAO] doesRestaurantExist() Restaurant found. ID: " + results.getString("restaurant_id"));
                return true;
            } else {
                System.out.println("[Restaurant JDBC DAO] doesRestaurantExist() Restaurant not found.");
                return false;
            }
        }catch (CannotGetJdbcConnectionException e) {
            System.out.println("[Restaurant JDBC DAO] Unable to connect to server or database");
            throw new CannotGetJdbcConnectionException("" + e);
        } catch (DataIntegrityViolationException e) {
            System.out.println("[Restaurant JDBC DAO] doesRestaurantExist() Problem checking if restaurant id: " + restaurantId + " exists.");
            throw new DataIntegrityViolationException("" + e);
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

            return newRestaurant;
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("[Restaurant JDBC DAO] Unable to connect to server or database");
            throw new CannotGetJdbcConnectionException("" + e);
        } catch (DataIntegrityViolationException e) {
            System.out.println("[Restaurant JDBC DAO] getRestaurantById() Problem selection restaurant by id: " + restaurantId);
            throw new DataIntegrityViolationException("" + e);
        }
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

            // By referencing the restaurant_event table.
            return newRestaurants;
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("[Restaurant JDBC DAO] Unable to connect to server or database");
            throw new CannotGetJdbcConnectionException("" + e);
        } catch (DataIntegrityViolationException e) {
            System.out.println("[Restaurant JDBC DAO] getRestaurantByEventId() Problem selecting restaurants by event id: " + eventId);
            throw new DataIntegrityViolationException("" + e);
        }
    }

    @Override
    public List<Open> getHours(String restaurantId){
        List<Open> hours = new ArrayList<>();

        String sql = "SELECT * FROM restaurant_hours WHERE restaurant_id = ?;";

        // Return the hours from the database.
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, restaurantId);
            while (results.next()){
                // Create a new Open object and populate with the relevant information.
                Open open = new Open();
                open.setDay(results.getInt("day_id"));
                open.setDayName(results.getString("day_name"));
                open.setStart(results.getInt("start_time"));
                open.setEnd(results.getInt("end_time"));

                hours.add(open);
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("[Restaurant JDBC DAO] Unable to connect to server or database");
            throw new CannotGetJdbcConnectionException("" + e);
        } catch (DataIntegrityViolationException e) {
            System.out.println("[Restaurant JDBC DAO] getHours() Problem getting hours for restaurant id: " + restaurantId);
            throw new DataIntegrityViolationException("" + e);
        }

        return hours;
    }

    public String getCategoryById(int categoryId){
        String sql = "SELECT category_name FROM category WHERE category_id = ?;";

        try {
            String categoryName = jdbcTemplate.queryForObject(sql, String.class, categoryId);
            System.out.println("[Restaurant JDBC DAO] getCategoryById() category name: " + categoryName);
            return categoryName;
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("[Restaurant JDBC DAO] Unable to connect to server or database");
            throw new CannotGetJdbcConnectionException("" + e);
        } catch (DataIntegrityViolationException e) {
            System.out.println("[Restaurant JDBC DAO] getCategoryById() Problem getting category name for id: " + categoryId);
            throw new DataIntegrityViolationException("" + e);
        }
    }

    public int getCategoryAssociationRowCount(String restaurantId, String categoryName) {

        String sql = "SELECT COUNT(*) FROM restaurant_category WHERE restaurant_id = ? AND category_id = ?;";

        try {
            int rowCount = jdbcTemplate.queryForObject(sql, int.class, restaurantId, getCategoryId(categoryName));
            System.out.println("[Restaurant JDBC DAO] getCategoryAssociationRowCount() Row count: " + rowCount);
            return rowCount;
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("[Restaurant JDBC DAO] Unable to connect to server or database");
        } catch (EmptyResultDataAccessException e){
            System.out.println("[Restaurant JDBC DAO] getCategoryAssociationRowCount() Category association not found for restaurant ID: " + restaurantId + " and category name: " + categoryName);
        }

        return -1;
    }

    public List<String> getCategoriesByRestaurantId(String restaurantId){
        List<String> newCategoryList = new ArrayList<>();

        String sql = "SELECT category_id FROM restaurant_category WHERE restaurant_id = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, restaurantId);
            while (results.next()){
                newCategoryList.add(getCategoryById(results.getInt("category_id")));
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("[Restaurant JDBC DAO] Unable to connect to server or database");
            throw new CannotGetJdbcConnectionException("" + e);
        } catch (DataIntegrityViolationException e) {
            System.out.println("[Restaurant JDBC DAO] getCategoriesByRestaurantId() Problem getting categories for restaurant id: " + restaurantId);
            throw new DataIntegrityViolationException("" + e);
        }

        return newCategoryList;
    }

    @Override
    public int getCategoryId(String categoryName) {
        // Check to see if the category exists in the category table.
        String sql = "SELECT category_id FROM category WHERE category_name = ?;";

        System.out.println("[Restaurant JDBC DAO] getCategoryById() Category name: " + categoryName);

        try {
            // Return the category id.
            int categoryId = jdbcTemplate.queryForObject(sql, int.class, categoryName);
            System.out.println("[Restaurant JDBC DAO] getCategoryById() Category ID: " + categoryId);
            return categoryId;
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("[Restaurant JDBC DAO] Unable to connect to server or database");
        } catch (EmptyResultDataAccessException e){
            System.out.println("[Restaurant JDBC DAO] getCategoryId() Category ID not found for: " + categoryName);
        }

        return -1;
    }

    public String getTransactionById(int transactionId){
        String sql = "SELECT transaction_name FROM transactions WHERE transaction_id = ?;";

        try {
            String transactionName = jdbcTemplate.queryForObject(sql, String.class, transactionId);
            System.out.println("[Restaurant JDBC DAO] getTransactionById() category name: " + transactionName);
            return transactionName;
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("[Restaurant JDBC DAO] Unable to connect to server or database");
            throw new CannotGetJdbcConnectionException("" + e);
        } catch (DataIntegrityViolationException e) {
            System.out.println("[Restaurant JDBC DAO] getTransactionById() Problem getting category name for id: " + transactionId);
            throw new DataIntegrityViolationException("" + e);
        }
    }

    public int getTransactionAssociationRowCount(String restaurantId, String transactionName){

        String sql = "SELECT COUNT(*) FROM restaurant_transactions WHERE restaurant_id = ? AND transaction_id = ?;";

        try {
            int rowCount = jdbcTemplate.queryForObject(sql, int.class, restaurantId, getTransactionId(transactionName));
            System.out.println("[Restaurant JDBC DAO] getTransactionAssociationRowCount() Row count: " + rowCount);
            return rowCount;
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("[Restaurant JDBC DAO] Unable to connect to server or database");
        } catch (EmptyResultDataAccessException e){
            System.out.println("[Restaurant JDBC DAO] getTransactionAssociationRowCount() Category association not found for restaurant ID: " + restaurantId + " and category name: " + transactionName);
        }

        return -1;
    }

    public List<String> getTransactionsByRestaurantId(String restaurantId){
        List<String> newTransactionList = new ArrayList<>();

        String sql = "SELECT transaction_id FROM restaurant_transactions WHERE restaurant_id = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, restaurantId);
            while (results.next()){
                newTransactionList.add(getTransactionById(results.getInt("transaction_id")));
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("[Restaurant JDBC DAO] Unable to connect to server or database");
            throw new CannotGetJdbcConnectionException("" + e);
        } catch (DataIntegrityViolationException e) {
            System.out.println("[Restaurant JDBC DAO] getTransactionsByRestaurantId() Problem getting transactions for restaurant id: " + restaurantId);
            throw new DataIntegrityViolationException("" + e);
        }

         return newTransactionList;
    }

    @Override
    public int getTransactionId(String transactionName) {
        // Check to see if the transaction type exists in the transactions table.
        String sql = "SELECT transaction_id FROM transactions WHERE transaction_name = ?;";

        try {
            // Return the transaction id.
            int transactionId = jdbcTemplate.queryForObject(sql, Integer.class, transactionName);
            System.out.println("[Restaurant JDBC DAO] getCategoryById() Category ID: " + transactionId);
            return transactionId;
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("[Restaurant JDBC DAO] Unable to connect to server or database");
        } catch (EmptyResultDataAccessException e){
            System.out.println("[Restaurant JDBC DAO] getCategoryId() Category ID not found for: " + transactionName);
        }

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
                open.setStart(results.getInt("start_time"));
                open.setEnd(results.getInt("end_time"));
            }

            return open;

        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("[Restaurant JDBC DAO] Unable to connect to server or database");
            throw new CannotGetJdbcConnectionException("" + e);
        } catch (DataIntegrityViolationException e) {
            System.out.println("[Restaurant JDBC DAO] getOpen() Problem getting hours for openId: " + openId);
            throw new DataIntegrityViolationException("" + e);
        }
    }

    @Override
    public List<Open> createHours(String restaurantId, List<Open> hours){
        // Create a new opens list.
        List<Open> opens = new ArrayList<>();

        // Add a hours to the restaurant_hours table.
        String sql = "INSERT INTO restaurant_hours (restaurant_id, day_id, day_name, " +
                "start_time, end_time) " +
                "VALUES (?, ?, ?, ?, ?) RETURNING hours_id;";

        try {
            for (Open open : hours) {
                int openId = jdbcTemplate.queryForObject(sql, int.class,
                        restaurantId,
                        open.getDay(),
                        open.getDayName(open.getDay()),
                        Integer.parseInt(open.getStart()),
                        Integer.parseInt(open.getEnd()));

                // Get the hour object.
                Open newOpen = getOpen(openId);

                // Add to the opens list.
                opens.add(newOpen);
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("[Restaurant JDBC DAO] Unable to connect to server or database");
            throw new CannotGetJdbcConnectionException("" + e);
        } catch (DataIntegrityViolationException e) {
            System.out.println("[Restaurant JDBC DAO] createHours() Problem creating hours for restaurant id: " + restaurantId);
            throw new DataIntegrityViolationException("" + e);
        }

        // Return the created hours list.
        return opens;
    }

    @Override
    public int createCategory(String categoryName){
        // Get the last category id and add 1 to create the new id.
        // Is this needed if the primary key auto increments?

        // Add the category into the category table.
        String sql = "INSERT INTO category (category_name) " +
                "VALUES (?) RETURNING category_id;";

        try {
            // Return the new category_id.
            return jdbcTemplate.queryForObject(sql, int.class, categoryName);
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("[Restaurant JDBC DAO] Unable to connect to server or database");
            throw new CannotGetJdbcConnectionException("" + e);
        } catch (DataIntegrityViolationException e) {
            System.out.println("[Restaurant JDBC DAO] createCategory() problem creating category: " + categoryName);
            throw new DataIntegrityViolationException("" + e);
        }
    }

    @Override
    public int createTransaction(String transactionName){
        // Get the last transaction_id and add 1 to create the new id.
        // Is this needed if the primary key auto increments?

        // Add the transaction type into the transactions table.
        String sql = "INSERT INTO transactions (transaction_name) " +
                "VALUES (?) RETURNING transaction_id;";

        try {
            // Return the new transaction_id.
            return jdbcTemplate.queryForObject(sql, int.class, transactionName);
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("[Restaurant JDBC DAO] Unable to connect to server or database");
            throw new CannotGetJdbcConnectionException("" + e);
        } catch (DataIntegrityViolationException e) {
            System.out.println("Restaurant JDBC DAO] createTransaction() Problem creating transaction: " + transactionName);
            throw new DataIntegrityViolationException("" + e);
        }
    }

    public void associateEventWithRestaurants(String restaurantId, int eventId){
        String sql = "INSERT INTO restaurant_event (restaurant_id, event_id, yes_votes, no_votes) " +
                "VALUES (?, ?, ?, ?)";

        try {
            jdbcTemplate.update(sql, restaurantId, eventId, 0, 0);
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("[Restaurant JDBC DAO] Unable to connect to server or database");
            throw new CannotGetJdbcConnectionException("" + e);
        } catch (DataIntegrityViolationException e) {
            System.out.println("[Restaurant JDBC DAO] associateEventWithRestaurants() problem associating restaurant id: " + restaurantId + " with event id: " + eventId);
            throw new DataIntegrityViolationException("" + e);
        }

        System.out.println("[Restaurant JDBC DAO] Restaurant ID: " + restaurantId + " successfully associated with event ID: " + eventId);

    }

    public void associateCategoryAndRestaurant(String restaurantId, int categoryId){
        // Insert restaurant_id and category_id into the restaurant_category table.
        String sql = "INSERT INTO restaurant_category (restaurant_id, category_id) " +
                "VALUES (?, ?);";

        try {
            // Return new category association id.
            jdbcTemplate.update(sql, restaurantId, categoryId);
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("[Restaurant JDBC DAO] Unable to connect to server or database");
            throw new CannotGetJdbcConnectionException("" + e);
        } catch (DataIntegrityViolationException e) {
            System.out.println("[Restaurant JDBC DAO] associateCategoryAndRestaurant() problem associating restaurant id: " + restaurantId + " with category id: " + categoryId);
            throw new DataIntegrityViolationException("" + e);
        }

        System.out.println("[Restaurant JDBC DAO] Restaurant ID: " + restaurantId + " successfully associated with category ID: " + categoryId);
    }

    public void associateTransactionAndRestaurant(String restaurantId, int transactionId){
        // Insert restaurant_id and transaction_id into the restaurant_transaction table.
        String sql = "INSERT INTO restaurant_transactions (restaurant_id, transaction_id) " +
                "VALUES (?, ?);";

        try {
            // Return new transactions association id.
            jdbcTemplate.update(sql, restaurantId, transactionId);
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("[Restaurant JDBC DAO] Unable to connect to server or database");
            throw new CannotGetJdbcConnectionException("" + e);
        } catch (DataIntegrityViolationException e) {
            System.out.println("[Restaurant JDBC DAO] associateTransactionAndRestaurant() problem associating restaurant Id: " + restaurantId + " with transaction id: " + transactionId);
            throw new DataIntegrityViolationException("" + e);
        }

        System.out.println("[Restaurant JDBC DAO] Restaurant ID: " + restaurantId + " successfully associated with transaction ID: " + transactionId);
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

        newRestaurant.setHours(getHours(results.getString("restaurant_id")));

        // Get a list of category names.
        newRestaurant.setCategories(getCategoriesByRestaurantId(results.getString("restaurant_id")));

        // Get a list of transaction names.
        newRestaurant.setTransactions(getTransactionsByRestaurantId(results.getString("restaurant_id")));

        return newRestaurant;
    }
}



