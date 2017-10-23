package com.example.osamamac.taskmanager.Model;

public class Reminder {
    private String dateTime;
    private String type;

    public Reminder(String dateTime, String type) {
        this.dateTime = dateTime;
        this.type = type;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
