package com.example.osamamac.taskmanager.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.osamamac.taskmanager.Adapters.CommentsAdapter;
import com.example.osamamac.taskmanager.Database.SQLiteHandler;
import com.example.osamamac.taskmanager.Model.Comment;
import com.example.osamamac.taskmanager.R;
import com.example.osamamac.taskmanager.Utilities.Time;
import com.example.osamamac.taskmanager.Utilities.Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddCommentsActivity extends AppCompatActivity {

    private ListView commentsListView;
    private ArrayList<Comment> commentsArray;

    private EditText addCommentEditText;
    private Button btnAddComment;

    private SQLiteHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comments);

        setTitle("Add Comments");

        commentsListView = (ListView) findViewById(R.id.addNewCommentsListView);

        commentsArray = new ArrayList<>();

        db = new SQLiteHandler(this);

//        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
//        Date date = new Date();
//
//        Toast.makeText(this, dateFormat.format(date) + "", Toast.LENGTH_SHORT).show();

        final List<Comment> tempCommentsList = db.getAllTemporaryComments();

        if(tempCommentsList != null && tempCommentsList.size() > 0){
            commentsArray = (ArrayList<Comment>) tempCommentsList;

            Toast.makeText(this, tempCommentsList.size() + "", Toast.LENGTH_LONG).show();
        }

        /*commentsArray.add(new Comment("hello there", new Date(2017, 8, 12), new Time("10", "57", "PM")));
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
*/
        final CommentsAdapter adapter = new CommentsAdapter(this, commentsArray);

        commentsListView.setAdapter(adapter);

        addCommentEditText = (EditText) findViewById(R.id.addNewCommentsEditText);
        btnAddComment = (Button) findViewById(R.id.addNewCommentsButton);

        btnAddComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
                Date date = new Date();

                String commentText = addCommentEditText.getText().toString();

                Comment newTempComment = new Comment(commentText, dateFormat.format(date));

                db.addComment(newTempComment);

                commentsArray.add(newTempComment);

                adapter.notifyDataSetChanged();

                addCommentEditText.setText("");
            }
        });

        addCommentEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String commentText = addCommentEditText.getText().toString();

                if(commentText != null && !commentText.equals("")){
                    btnAddComment.setEnabled(true);
                }else{
                    btnAddComment.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        Intent intent = new Intent();
        intent.putExtra(Utils.COMMENTS_RESULT_EXTRA, (commentsArray.size() > 0) ? String.valueOf(commentsArray.size()) + " Comments" : "No Comments");
        setResult(Utils.COMMENTS_RESULT_CODE, intent);
        finish();
    }
}
