package com.example.osamamac.taskmanager.Services;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.widget.Toast;

import com.example.osamamac.taskmanager.Activities.AddNewTaskActivity;
import com.example.osamamac.taskmanager.R;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Notification", Toast.LENGTH_SHORT).show();

        Intent notificationIntent = new Intent(context, AddNewTaskActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(AddNewTaskActivity.class);
        stackBuilder.addNextIntent(notificationIntent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        Notification notification = builder.setContentTitle("Demo App Notification")
                .setContentText("New Notification From Demo App..")
                .setTicker("New Message Alert!")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent).build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }
}
