package com.techelevator.model;
import java.sql.Timestamp;


public class Event {

    private int eventId;

    private String eventName;

    private int organizerId;

    private String zipcode;

    private String eventLink;

    private Timestamp decisionDate;




    public Event() {
    }



    public Event(int eventId, String eventName, int organizerId, String zipcode,
                 String eventLink, Timestamp decisionDate) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.organizerId = organizerId;
        this.zipcode = zipcode;
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

    public String getZipcode(){
        return zipcode;
    }

    public void setZipcode(String location) {
        this.zipcode = location;
    }

    public String getEventLink () {
        return eventLink;
    }

    public void setEventLink(String eventLink) {
        this.eventLink = eventLink;
    }

    public Timestamp getDecisionDate () {
        return  decisionDate;
    }

    public void setDecisionDate(Timestamp decisionDate) {
        this.decisionDate = decisionDate;
    }
}