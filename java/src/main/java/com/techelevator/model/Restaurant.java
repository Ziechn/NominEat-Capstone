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

    private double rating;
    private Coordinates coordinates;

    private List<String> transactions;

    public Restaurant(String restaurantId, String restaurantName,
                      String phoneNumber, List<String> categories,
                      String address1, String address2, String address3,
                      String city, String state, String country,
                      String zipcode, String imageUrl,
                      String menuUrl,
                      double rating, Coordinates coordinates,
                      List<String> transactions){
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
        this.rating = rating;
        this.coordinates = coordinates;
        this.transactions = transactions;
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
        return this.isOpenNow;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public String getMenuUrl() {
        return this.menuUrl;
    }

    public double getRating(){
        return this.rating;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public List<String> getTransactions(){
        return transactions;
    }

    // Setters:
    public void setHours(List<Open> hours) {
        this.hours = hours;
    }

    public void SetIsOpenNow(boolean isOpenNow){
        this.isOpenNow = isOpenNow;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setOpenNow(boolean openNow) {
        isOpenNow = openNow;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setTransactions(List<String> transactions) {
        this.transactions = transactions;
    }
}
