package com.techelevator.model;

public class Restaurant {
    private int restaurantId;
    private String restaurantName;
    private String restaurantCuisine;
    private String restaurantAddress;
    private int restaurantZipCode;
    private String[] restaurantHours;
    private String openStatus;
    private String thumbnailLink;
    private String menuLink;

    public Restaurant(int restaurantId, String restaurantName,
                      String restaurantCuisine, String restaurantAddress,
                      int restaurantZipCode,
                      String[] restaurantHours, String openStatus,
                      String thumbnailLink, String menuLink) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.restaurantCuisine = restaurantCuisine;
        this.restaurantAddress = restaurantAddress;
        this.restaurantZipCode = restaurantZipCode;
        this.restaurantHours = restaurantHours;
        this.openStatus = openStatus;
        this.thumbnailLink = thumbnailLink;
        this.menuLink = menuLink;
    }

    public int getrestaurantId() {
        return restaurantId;
    }

    public void setrestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getrestaurantName() {
        return restaurantName;
    }

    public void setrestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getrestaurantCuisine() {
        return restaurantCuisine;
    }

    public void setrestaurantCuisine(String restaurantCuisine) {
        this.restaurantCuisine = restaurantCuisine;
    }

    public String getrestaurantAddress() {
        return restaurantAddress;
    }

    public void setrestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public int getrestaurantZipCode() {
        return restaurantZipCode;
    }

    public void setrestaurantZipCode(int restaurantZipCode) {
        this.restaurantZipCode = restaurantZipCode;
    }

    public String[] getrestaurantHours() {
        return restaurantHours;
    }

    public void setrestaurantHours(String[] restaurantHours) {
        this.restaurantHours = restaurantHours;
    }

    public String getOpenStatus() {
        return openStatus;
    }

    public void setOpenStatus(String openStatus) {
        this.openStatus = openStatus;
    }

    public String getThumbnailLink() {
        return thumbnailLink;
    }

    public void setThumbnailLink(String thumbnailLink) {
        this.thumbnailLink = thumbnailLink;
    }

    public String getMenuLink() {
        return menuLink;
    }

    public void setMenuLink(String menuLink) {
        this.menuLink = menuLink;
    }
}
