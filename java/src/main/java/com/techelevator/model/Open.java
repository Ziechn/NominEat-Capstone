
package com.techelevator.model;

public class Open {

    private boolean isOvernight;
    private String start;
    private String end;
    private int day;

    public Open(boolean isOvernight, String start,
                String end, int day) {
        this.isOvernight = isOvernight;
        this.start = start;
        this.end = end;
        this.day = day;
    }

    public boolean getIsOvernight() {
        return isOvernight;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public int getDay() {
        return day;
    }
}
