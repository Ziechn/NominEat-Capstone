package com.techelevator.model;

public class Resturant {
    private int resturantId;
    private String resturantName;
    private String resturantCuisine;
    private String resturantAddress;
    private int resturantZipCode;
    private String[] resturantHours;
    private String openStatus;
    private String thumbnailLink;
    private String menuLink;

    public Resturant(int resturantId, String resturantName,
                     String resturantCuisine, String resturantAddress,
                     int resturantZipCode,
                     String[] resturantHours, String openStatus,
                     String thumbnailLink, String menuLink) {
        this.resturantId = resturantId;
        this.resturantName = resturantName;
        this.resturantCuisine = resturantCuisine;
        this.resturantAddress = resturantAddress;
        this.resturantZipCode = resturantZipCode;
        this.resturantHours = resturantHours;
        this.openStatus = openStatus;
        this.thumbnailLink = thumbnailLink;
        this.menuLink = menuLink;
    }

    public int getResturantId() {
        return resturantId;
    }

    public void setResturantId(int resturantId) {
        this.resturantId = resturantId;
    }

    public String getResturantName() {
        return resturantName;
    }

    public void setResturantName(String resturantName) {
        this.resturantName = resturantName;
    }

    public String getResturantCuisine() {
        return resturantCuisine;
    }

    public void setResturantCuisine(String resturantCuisine) {
        this.resturantCuisine = resturantCuisine;
    }

    public String getResturantAddress() {
        return resturantAddress;
    }

    public void setResturantAddress(String resturantAddress) {
        this.resturantAddress = resturantAddress;
    }

    public int getResturantZipCode() {
        return resturantZipCode;
    }

    public void setResturantZipCode(int resturantZipCode) {
        this.resturantZipCode = resturantZipCode;
    }

    public String[] getResturantHours() {
        return resturantHours;
    }

    public void setResturantHours(String[] resturantHours) {
        this.resturantHours = resturantHours;
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
