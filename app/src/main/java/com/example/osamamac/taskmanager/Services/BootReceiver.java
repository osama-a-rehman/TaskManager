package com.example.osamamac.taskmanager.Services;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class BootReceiver extends BroadcastReceiver {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            /*//context = ApplicationActivity.class;
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            Intent mIntent = new Intent(context, WakefulReceiver.class);
            PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, mIntent, 0);

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            //// TODO: use calendar.add(Calendar.SECOND,MINUTE,HOUR, int);
            calendar.add(Calendar.SECOND, 5);

            //ALWAYS recompute the calendar after using add, set, roll
            Date date = calendar.getTime();

            alarmManager.setExact(AlarmManager.RTC_WAKEUP, date.getTime(), alarmIntent);

            Toast.makeText(context, "Alarm set", Toast.LENGTH_SHORT).show();*/


        }
    }
}