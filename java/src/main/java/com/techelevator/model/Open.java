
package com.techelevator.model;

public class Open {

    private int day;

    private String dayName;
    private boolean isOvernight;
    private String start;
    private String end;


    public Open(){
        this.day = -1;
        this.dayName = "A new day.";
        this.isOvernight = false;
        this.start = "";
        this.end = "";
    }

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

    public void setDay(int dayId){
        this.day = dayId;
    }

    public void setDayName(int dayId){
        if (dayId == 0){
            dayName = "Sunday";
        } else if (dayId == 1){
            dayName = "Monday";
        } else if (dayId == 2) {
            dayName = "Tuesday";
        } else if (dayId == 3) {
            dayName = "Wednesday";
        } else if (dayId == 4) {
            dayName = "Thursday";
        } else if (dayId == 5) {
            dayName = "Friday";
        } else if (dayId == 6) {
            dayName = "Saturday";
        } else {
            System.out.println("[Open] Invalid dayId provided.");
        }
    }

    public void setStart(int start){
        this.start = start + "";
    }

    public void setEnd(int end){
        this.end = end + "";
    }
}
