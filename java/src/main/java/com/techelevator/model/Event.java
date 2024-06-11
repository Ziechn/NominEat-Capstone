package com.techelevator.model;
import java.sql.Timestamp;


public class Event {

    private int eventId;

    private String eventName;

    private int organizerId;

    private String location;

    private String eventLink;

    private String decisionDate;




    public Event() {
    }



    public Event(int eventId, String eventName, int organizerId, String location,
                 String eventLink, String decisionDate) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.organizerId = organizerId;
        this.location = location;
        this.eventLink = eventLink;
        this.decisionDate = decisionDate;

    }



    public int getEventId() {
        return eventId;
    }

    public void setEventId (int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName (String eventName) {
        this.eventName = eventName;
    }

    public int getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId (int organizerId) {
        this.organizerId = organizerId;
    }

    public String getLocation(){
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEventLink () {
        return eventLink;
    }

    public void setEventLink(String eventLink) {
        this.eventLink = eventLink;
    }

    public String getDecisionDate () {
        return  decisionDate;
    }

    public void setDecisionDate(String decisionDate) {
        this.decisionDate = decisionDate;
    }
}