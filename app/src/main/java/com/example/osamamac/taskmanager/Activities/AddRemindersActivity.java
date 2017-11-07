package com.example.osamamac.taskmanager.Activities;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.osamamac.taskmanager.Adapters.CommentsAdapter;
import com.example.osamamac.taskmanager.Adapters.RemindersAdapter;
import com.example.osamamac.taskmanager.Database.SQLiteHandler;
import com.example.osamamac.taskmanager.Model.Comment;
import com.example.osamamac.taskmanager.Model.Reminder;
import com.example.osamamac.taskmanager.R;
import com.example.osamamac.taskmanager.Services.BootReceiver;
import com.example.osamamac.taskmanager.Services.OneTimeAlarmReceiver;
import com.example.osamamac.taskmanager.Utilities.Time;
import com.example.osamamac.taskmanager.Utilities.Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddRemindersActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView remindersListView;
    private ArrayList<Reminder> remindersArray;
    private RemindersAdapter remindersAdapter;

    private Button btnDateAndTime;
    private Button btnAddReminder;

    private Spinner spinner;

    private Calendar calendar;
    private Time time;

    String fullTime = "";
    String showDate = "";

    private String[] reminderTypes;
    private int reminderTypePosition = 0;

    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private TimePickerDialog.OnTimeSetListener onTimeSetListener;

    private SQLiteHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminders);

        setTitle("Add Reminders");

        remindersListView = (ListView) findViewById(R.id.addNewRemindersListView);

        remindersArray = new ArrayList<>();

        db = new SQLiteHandler(this);

        final List<Reminder> tempRemindersList = db.getAllTemporaryReminders();

        if(tempRemindersList != null && tempRemindersList.size() > 0){
            remindersArray = (ArrayList<Reminder>) tempRemindersList;

            //Toast.makeText(this, tempRemindersList.size() + "", Toast.LENGTH_LONG).show();
        }

        spinner = (Spinner) findViewById(R.id.addNewReminderSpinner);

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.reminder_types, android.R.layout.simple_spinner_item);

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        reminderTypes = getResources().getStringArray(R.array.reminder_types);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                reminderTypePosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner.setAdapter(spinnerAdapter);

        remindersAdapter = new RemindersAdapter(this, remindersArray);

        remindersListView.setAdapter(remindersAdapter);

        btnDateAndTime = (Button) findViewById(R.id.addNewReminderDateBtn);
        btnAddReminder = (Button) findViewById(R.id.addNewReminderAddBtn);

        btnDateAndTime.setOnClickListener(this);
        btnAddReminder.setOnClickListener(this);

        calendar = Calendar.getInstance();

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

//                dateTextView.setText(Utils.getStringMonth(calendar.get(Calendar.MONTH)) + " " + calendar.get(Calendar.DATE) + ", " + calendar.get(Calendar.YEAR));
//                Toast.makeText(AddNewTaskActivity.this, calendar.get(Calendar.MONTH) + " " + calendar.get(Calendar.DATE) + ", " + calendar.get(Calendar.YEAR), Toast.LENGTH_SHORT).show();

                fullTime = calendar.get(Calendar.DATE) + "-" + String.valueOf(calendar.get(Calendar.MONTH)+1) + "-" + calendar.get(Calendar.YEAR);

                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date d = new Date();

                Date todaysDate = null;

                try {
                    todaysDate = dateFormat.parse(d.getDate() + "-" + String.valueOf(d.getMonth()+1) + "-" + String.valueOf(d.getYear()+1900));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                try {
                    Date selectedDate = dateFormat.parse(String.valueOf(calendar.get(Calendar.DATE)) + "-" + String.valueOf(calendar.get(Calendar.MONTH)+1) + "-" + String.valueOf(calendar.get(Calendar.YEAR)));

                    if(todaysDate.compareTo(selectedDate) == 0){
                        showDate = "Today ";
//                        Log.i("Reminder", "True");
                    }else{
                        showDate = Utils.getStringMonth(calendar.get(Calendar.MONTH)) + " " + calendar.get(Calendar.DATE) + ", " + calendar.get(Calendar.YEAR);
//                        Log.i("Reminder", "False");
                    }

//                    Log.i("Reminder", String.valueOf(calendar.get(Calendar.DATE)));
//                    Log.i("Reminder", String.valueOf(calendar.get(Calendar.MONTH)));
//                    Log.i("Reminder", String.valueOf(calendar.get(Calendar.YEAR)));
//                    Log.i("Reminder", String.valueOf(todaysDate));
//                    Log.i("Reminder", String.valueOf(selectedDate));

                    TimePickerDialog timePickerDialog = new TimePickerDialog(AddRemindersActivity.this, onTimeSetListener, calendar.getTime().getHours(), calendar.getTime().getMinutes(), false);

                    timePickerDialog.setCancelable(false);

                    timePickerDialog.show();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                /*if(date == Integer.parseInt(dateFormat.format(todaysDate))){
                    showDate = "Today ";
                }else{
                    showDate = Utils.getStringMonth(calendar.get(Calendar.MONTH)) + " " + calendar.get(Calendar.DATE) + ", " + calendar.get(Calendar.YEAR);
                }

                TimePickerDialog timePickerDialog = new TimePickerDialog(AddRemindersActivity.this, onTimeSetListener, calendar.getTime().getHours(), calendar.getTime().getMinutes(), false);

                timePickerDialog.setCancelable(false);

                timePickerDialog.show();*/
            }

        };

        onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                time = Utils.getTime(hourOfDay, minute);

                fullTime += " " + time.getHours() + ":" + time.getMinutes() + " " + time.getMode();

                showDate += " " + time.getHours() + ":" + time.getMinutes() + " " + time.getMode();

                btnDateAndTime.setText(showDate);
                btnAddReminder.setEnabled(true);
            }
        };

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addNewReminderDateBtn:

                final DatePickerDialog datePickerDialog = new DatePickerDialog(AddRemindersActivity.this, onDateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.setCanceledOnTouchOutside(false);

                datePickerDialog.show();

                break;

            case R.id.addNewReminderAddBtn:
                Reminder newTempReminder = new Reminder(fullTime, reminderTypes[reminderTypePosition]);

                db.addTemporaryReminder(newTempReminder);

                remindersArray.add(newTempReminder);

                remindersAdapter.notifyDataSetChanged();

                btnDateAndTime.setText("Select Date and Time");
                btnAddReminder.setEnabled(false);

                setReminders(newTempReminder);

                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(Utils.REMINDERS_RESULT_EXTRA, (remindersArray.size() > 0) ? String.valueOf(remindersArray.size()) + " Reminders" : "No Reminders");
        setResult(Utils.REMINDERS_RESULT_CODE, intent);
        finish();
    }

    private void setReminders(Reminder reminder){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent notificationIntent = new Intent("android.media.action.DISPLAY_NOTIFICATION");
        PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar cal = Calendar.getInstance();

        String time = reminder.getDateTime();

        Toast.makeText(this, time, Toast.LENGTH_SHORT).show();
        Log.i("DATE", time);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm a");

        try {
            Date date = simpleDateFormat.parse(time);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, date.getTime(), broadcast);
            }else{
                alarmManager.set(AlarmManager.RTC_WAKEUP, date.getTime(), broadcast);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
