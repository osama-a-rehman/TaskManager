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

import java.util.ArrayList;
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

        /*TextView commentTime = currentView.findViewById(R.id.commentListItemTime);
        commentTime.setText(comment.getDate() + " " + comment.getTime());*/

        return currentView;
    }
}
