package com.example.osamamac.taskmanager.Model;

import com.example.osamamac.taskmanager.Utilities.Time;

import java.util.Date;

public class Comment {
    private String comment;
    private Date date;
    private Time time;

    public Comment(String comment,Date date, Time time) {
        this.comment = comment;
        this.date = date;
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
