package com.techelevator.model;

import java.util.List;

public class Hours {

    private List<Hour> hours;

    public Hours(List<Hour> hourList) {
        this.hours = hourList;
    }

    public List<Hour> getHours() {
        return hours;
    }

    public void setHours(List<Hour> hours) {
        this.hours = hours;
    }
}
