package com.example.osamamac.taskmanager.Activities;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bumptech.glide.util.Util;
import com.example.osamamac.taskmanager.Fragments.AddNewTaskPriorityFragment;
import com.example.osamamac.taskmanager.Fragments.AddNewTaskProjectFragment;
import com.example.osamamac.taskmanager.Interfaces.AddNewTaskFragmentsInterface;
import com.example.osamamac.taskmanager.R;
import com.example.osamamac.taskmanager.Utilities.Time;
import com.example.osamamac.taskmanager.Utilities.Utils;

import java.util.Calendar;
import java.util.Date;

public class AddNewTaskActivity extends AppCompatActivity implements View.OnClickListener, AddNewTaskFragmentsInterface {

    private RelativeLayout projectLayout, dateLayout, priorityLayout, labelsLayout, commentsLayout, remindersLayout, locationsLayout;
    private TextView projectTextView, dateTextView, priorityTextView, labelsTextView, commentsTextView, remindersTextView, locationsTextView;

    private EditText taskName;

    private FragmentManager fragmentManager;

    private Calendar calendar;
    private Time time;

    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private TimePickerDialog.OnTimeSetListener onTimeSetListener;

    private String fullTime = "";

    private boolean showDiscardDialog = false;

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

        /*Intent intent = new Intent(this, Mote.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 1253, intent, PendingIntent.FLAG_UPDATE_CURRENT|  Intent.FILL_IN_DATA);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),pendingIntent );
        Toast.makeText(this, "Alarm worked.", Toast.LENGTH_LONG).show();*/

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

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

//                dateTextView.setText(Utils.getStringMonth(calendar.get(Calendar.MONTH)) + " " + calendar.get(Calendar.DATE) + ", " + calendar.get(Calendar.YEAR));
//                Toast.makeText(AddNewTaskActivity.this, calendar.get(Calendar.MONTH) + " " + calendar.get(Calendar.DATE) + ", " + calendar.get(Calendar.YEAR), Toast.LENGTH_SHORT).show();

                fullTime = Utils.getStringMonth(calendar.get(Calendar.MONTH)) + " " + calendar.get(Calendar.DATE) + ", " + calendar.get(Calendar.YEAR);

                dateTextView.setText(fullTime);

                TimePickerDialog timePickerDialog = new TimePickerDialog(AddNewTaskActivity.this, onTimeSetListener, calendar.getTime().getHours(), calendar.getTime().getMinutes(), false);

                timePickerDialog.setCancelable(false);

                timePickerDialog.show();
            }

        };

        onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                time = Utils.getTime(hourOfDay, minute);

                fullTime += " - " + time.getHours() + ":" + time.getMinutes() + " " + time.getMode();

                dateTextView.setText
                        (fullTime);
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

                final DatePickerDialog datePickerDialog = new DatePickerDialog(AddNewTaskActivity.this, onDateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.setCanceledOnTouchOutside(false);

                datePickerDialog.show();

                break;

            case R.id.addNewTaskRelLayoutPriority:

                AddNewTaskPriorityFragment priorityFragment = new AddNewTaskPriorityFragment(this, priorityTextView.getText().toString());
                priorityFragment.show(fragmentManager, "Priority Fragment");

                break;

            case R.id.addNewTaskRelLayoutLabels:

                Intent labelIntent = new Intent(AddNewTaskActivity.this, SelectLabelActivity.class);

                if(!labelsTextView.getText().toString().equals("No Labels")){
                    labelIntent.putExtra(Utils.SET_LABEL_EXTRA, labelsTextView.getText().toString());
                }

                startActivityForResult(labelIntent, Utils.LABEL_REQUEST_CODE);

                break;

            case R.id.addNewTaskRelLayoutComments:
                Intent commIntent = new Intent(AddNewTaskActivity.this, AddCommentsActivity.class);
                startActivityForResult(commIntent, Utils.COMMENTS_REQUEST_CODE);

                break;

            case R.id.addNewTaskRelLayoutReminders:
                break;

            case R.id.addNewTaskRelLayoutLocations:

                Intent locIntent = new Intent(AddNewTaskActivity.this, AddLocationsActivity.class);
                startActivityForResult(locIntent, Utils.LOCATIONS_REQUEST_CODE);

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);

        Log.i("Request", String.valueOf(requestCode));
        Log.i("Request", String.valueOf(resultCode));

        Toast.makeText(this, String.valueOf(requestCode), Toast.LENGTH_SHORT).show();

        if(requestCode == Utils.LABEL_REQUEST_CODE && resultCode == Utils.LABEL_RESULT_CODE){
            if(data.hasExtra(Utils.LABEL_RESULT_EXTRA)){
                labelsTextView.setText(data.getStringExtra(Utils.LABEL_RESULT_EXTRA).toString());
                showDiscardDialog = true;
//                Toast.makeText(this, data.getStringExtra(Utils.LABEL_RESULT_EXTRA).toString(), Toast.LENGTH_SHORT).show();
            }
        }

        if(requestCode == Utils.COMMENTS_REQUEST_CODE && resultCode == Utils.COMMENTS_RESULT_CODE){
            if(data.hasExtra(Utils.COMMENTS_RESULT_EXTRA)){
                commentsTextView.setText(data.getStringExtra(Utils.COMMENTS_RESULT_EXTRA).toString());
                showDiscardDialog = true;
            }
        }
    }

    @Override
    public void doneSelecting(int id, String value) {
        if(value != null && !value.equals("")){
            if(id == Utils.PROJECT_FRAGMENT_ID){
                projectTextView.setText(value);
                showDiscardDialog = true;

            }else if(id == Utils.PRIORITY_FRAGMENT_ID){
                priorityTextView.setText(value);
                showDiscardDialog = true;
            }
        }
        //Toast.makeText(this, value, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        if(showDiscardDialog){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            alertDialogBuilder.setTitle("Discard Task ?");
            alertDialogBuilder.setMessage("Discard your changes to the task ?");

            alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

            alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            alertDialogBuilder.show();
        }else{
            super.onBackPressed();
        }
    }
}
