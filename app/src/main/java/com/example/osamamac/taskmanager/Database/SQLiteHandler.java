package com.example.osamamac.taskmanager.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.osamamac.taskmanager.Model.Comment;
import com.example.osamamac.taskmanager.Model.Location;
import com.example.osamamac.taskmanager.Model.Reminder;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = "SQLiteHandler";

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 3;

    // Database Name
    private static final String DATABASE_NAME = "task_manager_database";

    // Table Names
    private static final String TABLE_TEMP_COMMENTS = "table_temporary_comments";
    private static final String TABLE_TEMP_REMINDERS = "table_temporary_reminders";
    private static final String TABLE_TEMP_LOCATIONS = "table_temporary_locations";

    // Temporary Comments Table Columns Names
    public static final String KEY_TEMP_COLUMN_ID = "temp_column_id";
    public static final String KEY_TEMP_COLUMN_TEXT = "temp_column_text";
    public static final String KEY_TEMP_COLUMN_DATE = "temp_column_date";

    // Temporary Reminders Table Columns Names
    public static final String KEY_TEMP_REMINDER_ID = "temp_reminder_id";
    public static final String KEY_TEMP_REMINDER_DATE = "temp_reminder_date";
    public static final String KEY_TEMP_REMINDER_TYPE = "temp_reminder_type";

    // Temporary Locations Table Columns Names
    public static final String KEY_TEMP_LOCATION_ID = "temp_location_id";
    public static final String KEY_TEMP_LOCATION_LATITUDE = "temp_location_latitude";
    public static final String KEY_TEMP_LOCATION_LONGITUDE = "temp_location_longitude";
    public static final String KEY_TEMP_LOCATION_PLACE_INFO = "temp_location_place_info";

    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TEMP_COMMENTS_TABLE = "CREATE TABLE " + TABLE_TEMP_COMMENTS + "("
                + KEY_TEMP_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TEMP_COLUMN_TEXT + " TEXT,"
                + KEY_TEMP_COLUMN_DATE + " DATE)";

        db.execSQL(CREATE_TEMP_COMMENTS_TABLE);

        String CREATE_TEMP_REMINDERS_TABLE = "CREATE TABLE " + TABLE_TEMP_REMINDERS + "("
                + KEY_TEMP_REMINDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TEMP_REMINDER_DATE + " DATE,"
                + KEY_TEMP_REMINDER_TYPE + " TEXT)";

        db.execSQL(CREATE_TEMP_REMINDERS_TABLE);

        String CREATE_TEMP_LOCATIONS_TABLE = "CREATE TABLE " + TABLE_TEMP_LOCATIONS + "("
                + KEY_TEMP_LOCATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TEMP_LOCATION_LATITUDE + " TEXT,"
                + KEY_TEMP_LOCATION_LONGITUDE + " TEXT," + KEY_TEMP_LOCATION_PLACE_INFO +" TEXT)";

        db.execSQL(CREATE_TEMP_LOCATIONS_TABLE);

        Log.i(TAG, "Database tables created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEMP_COMMENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEMP_REMINDERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEMP_LOCATIONS);

        Log.i(TAG, "Database tables dropped");

        // Create tables again
        onCreate(db);
    }

    // TEMPORARY COMMENTS - METHODS

    public void addComment(Comment tempComment){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TEMP_COLUMN_TEXT, tempComment.getComment());
        values.put(KEY_TEMP_COLUMN_DATE, tempComment.getDateTime());

        // Inserting Row
        long id = db.insert(TABLE_TEMP_COMMENTS, null, values);
        db.close(); // Closing database connection

       Log.d(TAG, "New Temporary Comment inserted with ID: " + id);
    }

    public List<Comment> getAllTemporaryComments() {
        List<Comment> tempCommentsList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_TEMP_COMMENTS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row

        if(cursor.getCount() > 0){
            cursor.moveToFirst();

            do{
                Comment tempComment = new Comment(cursor.getString(1), cursor.getString(2));
                tempCommentsList.add(tempComment);

            }while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        Log.d(TAG, "Fetching temp comments from Sqlite: " + tempCommentsList.toString());

        return tempCommentsList;
    }

    // TEMPORARY REMINDERS - METHODS

    public void addTemporaryReminder(Reminder tempReminder){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TEMP_REMINDER_DATE, tempReminder.getDateTime());
        values.put(KEY_TEMP_REMINDER_TYPE, tempReminder.getType());

        // Inserting Row
        long id = db.insert(TABLE_TEMP_REMINDERS, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New Temporary Reminder inserted with ID: " + id);
    }

    public List<Reminder> getAllTemporaryReminders() {
        List<Reminder> tempRemindersList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_TEMP_REMINDERS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row

        if(cursor.getCount() > 0){
            cursor.moveToFirst();

            do{
                Reminder tempReminder = new Reminder(cursor.getString(1), cursor.getString(2));
                tempRemindersList.add(tempReminder);
            }while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        Log.d(TAG, "Fetching temp reminders from Sqlite: " + tempRemindersList.toString());

        return tempRemindersList;
    }

    // TEMPORARY LOCATIONS - METHODS

    public void addTemporaryLocation(Location tempLocation){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TEMP_LOCATION_LATITUDE, String.valueOf(tempLocation.getLatitude()));
        values.put(KEY_TEMP_LOCATION_LONGITUDE, String.valueOf(tempLocation.getLongitude()));
        values.put(KEY_TEMP_LOCATION_PLACE_INFO, tempLocation.getPlaceInfo());

        // Inserting Row
        long id = db.insert(TABLE_TEMP_LOCATIONS, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New Temporary Location inserted with ID: " + id);
    }

    public List<Location> getAllTemporaryLocations() {
        List<Location> tempLocationsList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_TEMP_LOCATIONS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row

        if(cursor.getCount() > 0){
            cursor.moveToFirst();

            do{
                Location tempLocation = new Location(Double.parseDouble(cursor.getString(1)), Double.parseDouble(cursor.getString(2)), cursor.getString(3));
                tempLocationsList.add(tempLocation);
            }while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        Log.d(TAG, "Fetching temp locations from Sqlite: " + tempLocationsList.toString());

        return tempLocationsList;
    }

    public void discardTemporaryComments(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_TEMP_COMMENTS, null, null);
        db.close();

        Log.d(TAG, "All Temporary Comments Deleted");
    }

    public void discardTemporaryReminders(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_TEMP_REMINDERS, null, null);
        db.close();

        Log.d(TAG, "All Temporary Reminders Deleted");
    }

    public void discardTemporaryLocations(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_TEMP_LOCATIONS, null, null);
        db.close();

        Log.d(TAG, "All Temporary Locations Deleted");
    }
}
