package com.example.osamamac.taskmanager.Activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.osamamac.taskmanager.Fragments.AddNewTaskPriorityFragment;
import com.example.osamamac.taskmanager.Fragments.AddNewTaskProjectFragment;
import com.example.osamamac.taskmanager.Interfaces.AddNewTaskFragmentsInterface;
import com.example.osamamac.taskmanager.R;
import com.example.osamamac.taskmanager.Utilities.Utils;

import java.util.Calendar;

public class AddNewTaskActivity extends AppCompatActivity implements View.OnClickListener, AddNewTaskFragmentsInterface {

    private RelativeLayout projectLayout, dateLayout, priorityLayout, labelsLayout, commentsLayout, remindersLayout, locationsLayout;
    private TextView projectTextView, dateTextView, priorityTextView, labelsTextView, commentsTextView, remindersTextView, locationsTextView;

    private EditText taskName;

    private FragmentManager fragmentManager;

    private Calendar calendar;
    private DatePickerDialog.OnDateSetListener dateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_task);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        init();
    }

    private void init(){
        projectLayout = (RelativeLayout) findViewById(R.id.addNewTaskRelLayoutProject);
        dateLayout = (RelativeLayout) findViewById(R.id.addNewTaskRelLayoutDate);
        priorityLayout = (RelativeLayout) findViewById(R.id.addNewTaskRelLayoutPriority);
        labelsLayout = (RelativeLayout) findViewById(R.id.addNewTaskRelLayoutLabels);
        commentsLayout = (RelativeLayout) findViewById(R.id.addNewTaskRelLayoutComments);
        remindersLayout = (RelativeLayout) findViewById(R.id.addNewTaskRelLayoutReminders);
        locationsLayout = (RelativeLayout) findViewById(R.id.addNewTaskRelLayoutLocations);

        projectTextView = (TextView) findViewById(R.id.addNewTaskProject);
        dateTextView = (TextView) findViewById(R.id.addNewTaskDate);
        priorityTextView = (TextView) findViewById(R.id.addNewTaskPriority);
        labelsTextView = (TextView) findViewById(R.id.addNewTaskLabels);
        commentsTextView = (TextView) findViewById(R.id.addNewTaskComments);
        remindersTextView = (TextView) findViewById(R.id.addNewTaskReminders);
        locationsTextView = (TextView) findViewById(R.id.addNewTaskLocations);

        projectLayout.setOnClickListener(this);
        dateLayout.setOnClickListener(this);
        priorityLayout.setOnClickListener(this);
        labelsLayout.setOnClickListener(this);
        commentsLayout.setOnClickListener(this);
        remindersLayout.setOnClickListener(this);
        locationsLayout.setOnClickListener(this);

        calendar = Calendar.getInstance();

        dateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            }

        };

        fragmentManager = getSupportFragmentManager();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addNewTaskRelLayoutProject:

                AddNewTaskProjectFragment projectFragment = new AddNewTaskProjectFragment(this, projectTextView.getText().toString());
                projectFragment.show(fragmentManager, "Project Fragment");

                break;

            case R.id.addNewTaskRelLayoutDate:

                //new DatePickerDialog();

                break;

            case R.id.addNewTaskRelLayoutPriority:

                AddNewTaskPriorityFragment priorityFragment = new AddNewTaskPriorityFragment(this, priorityTextView.getText().toString());
                priorityFragment.show(fragmentManager, "Priority Fragment");

                break;

            case R.id.addNewTaskRelLayoutLabels:

                Intent intent = new Intent(AddNewTaskActivity.this, SelectLabelActivity.class);
                startActivity(intent);

                break;

            case R.id.addNewTaskRelLayoutComments:
                break;

            case R.id.addNewTaskRelLayoutReminders:
                break;

            case R.id.addNewTaskRelLayoutLocations:
                break;
        }
    }

    @Override
    public void doneSelecting(int id, String value) {
        if(value != null && !value.equals("")){
            if(id == Utils.PROJECT_FRAGMENT_ID){
                projectTextView.setText(value);
            }else if(id == Utils.PRIORITY_FRAGMENT_ID){
                priorityTextView.setText(value);
            }


        }
        //Toast.makeText(this, value, Toast.LENGTH_LONG).show();
    }
}
