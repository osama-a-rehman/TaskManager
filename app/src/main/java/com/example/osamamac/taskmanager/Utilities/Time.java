package com.example.osamamac.taskmanager.Utilities;


public class Time {

    private String hours;
    private String minutes;
    private String mode;

    public Time(){

    }

    public Time(String hours, String minutes, String mode){
        this.hours = hours;
        this.minutes = minutes;
        this.mode = mode;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }


}
