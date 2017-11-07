package com.example.osamamac.taskmanager.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.osamamac.taskmanager.Model.Comment;
import com.example.osamamac.taskmanager.Model.Reminder;
import com.example.osamamac.taskmanager.R;
import com.example.osamamac.taskmanager.Utilities.Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class RemindersAdapter extends ArrayAdapter<Reminder> {


    public RemindersAdapter(Context context, ArrayList<Reminder> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View currentView = convertView;

        if(currentView == null){
            currentView = LayoutInflater.from(getContext()).inflate(R.layout.reminder_list_item, parent, false);
        }

        Reminder reminder = getItem(position);

        TextView reminderDateTime = currentView.findViewById(R.id.reminderListItemDateTime);
        TextView reminderType = currentView.findViewById(R.id.reminderListItemType);
        reminderType.setText(reminder.getType());

        String dateTime[] = reminder.getDateTime().split(" ");

        String reminderFullDate = dateTime[0];

        String[] dateArray = reminderFullDate.split("-");

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date d = new Date();

        Date todaysDate = null;
        Date selectedDate = null;

        try {
            selectedDate = dateFormat.parse(reminder.getDateTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            todaysDate = dateFormat.parse(d.getDate() + "-" + String.valueOf(d.getMonth()+1) + "-" + String.valueOf(d.getYear()+1900));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(todaysDate.compareTo(selectedDate) == 0){
            reminderDateTime.setText("Today - " + dateTime[1] + " " + dateTime[2]);
        }else{

            String convertString = Utils.getStringMonth(Integer.parseInt(dateArray[1])-1) + " " + dateArray[0] + ", " + dateArray[2].substring(2);

            reminderDateTime.setText(convertString + " - " + dateTime[1] + " " + dateTime[2]);
        }

//        Log.i("Reminder", String.valueOf(calendar.get(Calendar.DATE)));
//        Log.i("Reminder", String.valueOf(calendar.get(Calendar.MONTH)));
//        Log.i("Reminder", String.valueOf(calendar.get(Calendar.YEAR)));
//        Log.i("Reminder", "DateTime: " + reminder.getDateTime());
//        Log.i("Reminder", "Today: " + String.valueOf(todaysDate));
//        Log.i("Reminder", "Selected: " + String.valueOf(selectedDate));

        return currentView;
    }
}
