package com.techelevator.model;

import java.util.List;

public class Restaurant {
    private String id;
    private String name;
    private String phoneNumber;
    private List<String> categories;
    private String address1;
    private String address2;
    private String address3;
    private String city;
    private String state;
    private String country;
    private String zipcode;
    private List<Open> hours;
    private boolean isOpenNow;
    private String imageUrl;
    private String menuUrl;

    public Restaurant(String restaurantId, String restaurantName,
                      String phoneNumber, List<String> categories,
                      String address1, String address2, String address3,
                      String city, String state, String country,
                      String zipcode, String imageUrl,
                      String menuUrl, List<Open> hours){
        this.id = restaurantId;
        this.name = restaurantName;
        this.phoneNumber = phoneNumber;
        this.categories = categories;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipcode = zipcode;
        this.imageUrl = imageUrl;
        this.menuUrl = menuUrl;
        this.hours = hours;
    }

    public String getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    public List<String> getCategories() {
        return categories;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getAddress3() {
        return address3;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public List<Open> getHours(){
        return this.hours;
    }

    public boolean getIsOpenNow() {
        return isOpenNow;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void SetIsOpenNow(boolean isOpenNow){
        this.isOpenNow = isOpenNow;
    }
}
