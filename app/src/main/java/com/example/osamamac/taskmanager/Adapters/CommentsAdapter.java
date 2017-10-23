package com.example.osamamac.taskmanager.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.osamamac.taskmanager.Model.Comment;
import com.example.osamamac.taskmanager.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentsAdapter extends ArrayAdapter<Comment> {

    public CommentsAdapter(Context context, ArrayList<Comment> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View currentView = convertView;

        if(currentView == null){
            currentView = LayoutInflater.from(getContext()).inflate(R.layout.comment_list_item, parent, false);
        }

        Comment comment = getItem(position);

        TextView commentText = currentView.findViewById(R.id.commentListItemText);
        commentText.setText(comment.getComment());

        TextView commentTime = currentView.findViewById(R.id.commentListItemTime);

        String dateTime[] = comment.getDateTime().split(" ");

        String commentFullDate = dateTime[0];

        String date = commentFullDate.split("-")[0];

        int dateInt = Integer.parseInt(date);

        DateFormat dateFormat = new SimpleDateFormat("dd");
        Date todaysDate = new Date();

        if(dateInt == Integer.parseInt(dateFormat.format(todaysDate))){
            commentTime.setText("Today " + dateTime[1] + " " + dateTime[2]);
        }else if(dateInt+1 == Integer.parseInt(dateFormat.format(todaysDate))){
            commentTime.setText("Yesterday " + dateTime[1] + " " + dateTime[2]);
        }else{
            commentTime.setText(comment.getDateTime());
        }

        return currentView;
    }
}
