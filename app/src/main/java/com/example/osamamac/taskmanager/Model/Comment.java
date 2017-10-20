package com.example.osamamac.taskmanager.Model;

import com.example.osamamac.taskmanager.Utilities.Time;

import java.util.Date;

public class Comment {

    private String comment;
    private String dateTime;

    public Comment(String comment, String dateTime) {
        this.comment = comment;
        this.dateTime = dateTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
