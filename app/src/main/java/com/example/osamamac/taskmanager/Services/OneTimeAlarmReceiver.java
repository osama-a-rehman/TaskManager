package com.example.osamamac.taskmanager.Services;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

import com.example.osamamac.taskmanager.R;

public class OneTimeAlarmReceiver extends BroadcastReceiver {
    public static String NOTIFICATION_ID = "notification-id";
    public static String NOTIFICATION = "notification";

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "Alarm Set", Toast.LENGTH_SHORT).show();

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        //Notification notification = intent.getParcelableExtra(NOTIFICATION);
        /*Notification.Builder builder = new Notification.Builder(context);
        builder.setContentTitle("Scheduled Notification");
        builder.setContentText("Hello World");
        builder.setSmallIcon(R.drawable.blurred_burger);
        //int id = intent.getIntExtra(NOTIFICATION_ID, 0);
        notificationManager.notify(1, builder.build());*/

    }
}
