package com.example.osamamac.taskmanager.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.osamamac.taskmanager.Model.Comment;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = "SQLiteHandler";

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "task_manager_database";

    // Table Names
    private static final String TABLE_COMMENTS = "table_temporary_comments";

    // Comments Table Columns Names
    public static final String KEY_TEMP_COLUMN_ID = "temp_column_id";
    public static final String KEY_TEMP_COLUMN_TEXT = "temp_column_text";
    public static final String KEY_TEMP_COLUMN_DATE = "temp_column_date";

    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_COMMENTS_TABLE = "CREATE TABLE " + TABLE_COMMENTS + "("
                + KEY_TEMP_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TEMP_COLUMN_TEXT + " TEXT,"
                + KEY_TEMP_COLUMN_DATE + " DATE)";

        db.execSQL(CREATE_COMMENTS_TABLE);

        Log.i(TAG, "Database tables created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);

        Log.i(TAG, "Database tables dropped");

        // Create tables again
        onCreate(db);
    }

    public void addComment(Comment tempComment){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TEMP_COLUMN_TEXT, tempComment.getComment());
        values.put(KEY_TEMP_COLUMN_DATE, String.valueOf(tempComment.getDateTime()));

        // Inserting Row
        long id = db.insert(TABLE_COMMENTS, null, values);
        db.close(); // Closing database connection

       Log.d(TAG, "New Temporary Comment inserted with ID: " + id);
    }

    public List<Comment> getAllTemporaryComments() {
        List<Comment> tempCommentsList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_COMMENTS;

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

        Log.d(TAG, "Fetching temp comment from Sqlite: " + tempCommentsList.toString());

        return tempCommentsList;
    }
}
