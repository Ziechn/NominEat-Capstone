package com.techelevator.model;
import java.util.List;

public class Event {

    private int eventId;

    private String eventName;

    private int organizerId;





    public Event(int eventId, String eventName, int organizerId) {
        this.eventId =eventId;
        this.eventName =eventName;
        this.organizerId=organizerId;

    }

    public Event() {

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

}