package com.example.osamamac.taskmanager.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.osamamac.taskmanager.Adapters.CommentsAdapter;
import com.example.osamamac.taskmanager.Model.Comment;
import com.example.osamamac.taskmanager.R;
import com.example.osamamac.taskmanager.Utilities.Time;

import java.util.ArrayList;
import java.util.Date;

public class AddCommentsActivity extends AppCompatActivity {

    private ListView commentsListView;
    private ArrayList<Comment> commentsArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comments);

        commentsListView = (ListView) findViewById(R.id.addNewCommentsListView);

        commentsArray = new ArrayList<>();

        commentsArray.add(new Comment("hello there", new Date(2017, 8, 12), new Time("10", "57", "PM")));
        commentsArray.add(new Comment("hello there", new Date(2017, 2, 11), new Time("10", "52", "AM")));
        commentsArray.add(new Comment("hello there", new Date(2017, 3, 10), new Time("10", "12", "PM")));
        commentsArray.add(new Comment("hello there", new Date(2017, 5, 8), new Time("10", "47", "AM")));
        commentsArray.add(new Comment("hello there", new Date(2017, 8, 12), new Time("10", "57", "PM")));
        commentsArray.add(new Comment("hello there", new Date(2017, 2, 11), new Time("10", "52", "AM")));
        commentsArray.add(new Comment("hello there", new Date(2017, 3, 10), new Time("10", "12", "PM")));
        commentsArray.add(new Comment("hello there", new Date(2017, 5, 8), new Time("10", "47", "AM")));
        commentsArray.add(new Comment("hello there", new Date(2017, 8, 12), new Time("10", "57", "PM")));
        commentsArray.add(new Comment("hello there", new Date(2017, 2, 11), new Time("10", "52", "AM")));
        commentsArray.add(new Comment("hello there", new Date(2017, 3, 10), new Time("10", "12", "PM")));
        commentsArray.add(new Comment("hello there", new Date(2017, 5, 8), new Time("10", "47", "AM")));
        commentsArray.add(new Comment("hello there", new Date(2017, 8, 12), new Time("10", "57", "PM")));
        commentsArray.add(new Comment("hello there", new Date(2017, 2, 11), new Time("10", "52", "AM")));
        commentsArray.add(new Comment("hello there", new Date(2017, 3, 10), new Time("10", "12", "PM")));
        commentsArray.add(new Comment("hello there", new Date(2017, 5, 8), new Time("10", "47", "AM")));

        CommentsAdapter adapter = new CommentsAdapter(this, commentsArray);

        commentsListView.setAdapter(adapter);
    }
}
