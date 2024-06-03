package com.techelevator.model;

public class Restaurant {
    private String id;
    private String name;
    private String category;
    private String address1;
    private String address2;
    private String address3;
    private String city;
    private String state;
    private String country;
    private int zipcode;
    private String[] hours;
    private String openStatus;
    private String imageUrl;
    private String menuUrl;

    public Restaurant(String restaurantId, String restaurantName){
        this.id = restaurantId;
        this.name = restaurantName;
    }
}
