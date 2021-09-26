package com.example.avengers.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final java.lang.String DATABASE_NAME = "Feed.db";
    private static final java.lang.String SQL_CREATE_ENTRIES_USER =
            "CREATE TABLE " + FeedReaderContract.FeedEntryUser.TABLE_NAME + " (" +
                    FeedReaderContract.FeedEntryUser._ID + " INTEGER PRIMARY KEY," +
                    FeedReaderContract.FeedEntryUser.COLUMN_NAME + " TEXT," +
                    FeedReaderContract.FeedEntryUser.COLUMN_PASSWORD + " TEXT," +
                    FeedReaderContract.FeedEntryUser.COLUMN_TYPE + " TEXT)";
    private static final java.lang.String SQL_DELETE_ENTRIES_USER =
            "DROP TABLE IF EXISTS " + FeedReaderContract.FeedEntryUser.TABLE_NAME;
    private static final java.lang.String SQL_CREATE_ENTRIES_MESSAGE =
            "CREATE TABLE " + FeedReaderContract.FeedEntryMessage.TABLE_NAME + " (" +
                    FeedReaderContract.FeedEntryMessage._ID + " INTEGER PRIMARY KEY," +
                    FeedReaderContract.FeedEntryMessage.COLUMN_USER + " TEXT," +
                    FeedReaderContract.FeedEntryMessage.COLUMN_SUBJECT + " TEXT," +
                    FeedReaderContract.FeedEntryMessage.COLUMN_MESSAGE + " TEXT)";
    private static final java.lang.String SQL_DELETE_ENTRIES_MESSAGE =
            "DROP TABLE IF EXISTS " + FeedReaderContract.FeedEntryMessage.TABLE_NAME;

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES_USER);
        db.execSQL(SQL_CREATE_ENTRIES_MESSAGE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES_USER);
        db.execSQL(SQL_DELETE_ENTRIES_MESSAGE);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    //Save User
    public long saveUser(java.lang.String name, java.lang.String password, java.lang.String type) {

        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntryUser.COLUMN_NAME, name);
        values.put(FeedReaderContract.FeedEntryUser.COLUMN_PASSWORD, password);
        values.put(FeedReaderContract.FeedEntryUser.COLUMN_TYPE, type);

// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(FeedReaderContract.FeedEntryUser.TABLE_NAME, null, values);
        return newRowId;

    }


    //User Login
    public Cursor loadUser(java.lang.String userName, java.lang.String password) {

        SQLiteDatabase db = getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        java.lang.String[] projection = {
                BaseColumns._ID,
                FeedReaderContract.FeedEntryUser.COLUMN_NAME,
                FeedReaderContract.FeedEntryUser.COLUMN_PASSWORD,
                FeedReaderContract.FeedEntryUser.COLUMN_TYPE
        };

// Filter results WHERE "title" = 'My Title'
        java.lang.String selection = FeedReaderContract.FeedEntryUser.COLUMN_NAME + " = ? AND " + FeedReaderContract.FeedEntryUser.COLUMN_PASSWORD + " = ?";
        java.lang.String[] selectionArgs = {userName, password};

// How you want the results sorted in the resulting Cursor
        java.lang.String sortOrder =
                FeedReaderContract.FeedEntryUser.COLUMN_NAME + " DESC";

        Cursor cursor = db.query(
                FeedReaderContract.FeedEntryUser.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        return cursor;
    }

//    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////  Message  //////////////////////////////////////////
//    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //Save Message
    public long saveMessage(java.lang.String user, java.lang.String subject, java.lang.String message) {

        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntryMessage.COLUMN_USER, user);
        values.put(FeedReaderContract.FeedEntryMessage.COLUMN_SUBJECT, subject);
        values.put(FeedReaderContract.FeedEntryMessage.COLUMN_MESSAGE, message);


// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(FeedReaderContract.FeedEntryMessage.TABLE_NAME, null, values);

        return newRowId;
    }

    public ArrayList loadAllMessage() {

        SQLiteDatabase db = getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        java.lang.String[] projection = {
                BaseColumns._ID,
                FeedReaderContract.FeedEntryMessage.COLUMN_USER,
                FeedReaderContract.FeedEntryMessage.COLUMN_SUBJECT,
                FeedReaderContract.FeedEntryMessage.COLUMN_MESSAGE
        };


// How you want the results sorted in the resulting Cursor
        java.lang.String sortOrder =
                FeedReaderContract.FeedEntryMessage.COLUMN_USER + " DESC";

        Cursor cursor = db.query(
                FeedReaderContract.FeedEntryMessage.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        ArrayList itemIds = new ArrayList<>();
        while (cursor.moveToNext()) {
            java.lang.String itemId = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntryMessage.COLUMN_USER));
            java.lang.String name = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntryMessage.COLUMN_SUBJECT));
            java.lang.String msg = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntryMessage.COLUMN_MESSAGE));
            java.lang.String dd =  name+"\n"+msg;

//            MessageEtt message = new MessageEtt(itemId, name, msg);
            itemIds.add(dd);
        }
        cursor.close();
        return itemIds;
    }

    public java.lang.String loadMessage(java.lang.String pos) {
        SQLiteDatabase db = getReadableDatabase();

        java.lang.String[] projection = {
                BaseColumns._ID,
                FeedReaderContract.FeedEntryMessage.COLUMN_USER,
                FeedReaderContract.FeedEntryMessage.COLUMN_SUBJECT,
                FeedReaderContract.FeedEntryMessage.COLUMN_MESSAGE
        };
        java.lang.String selection = FeedReaderContract.FeedEntryMessage.COLUMN_SUBJECT + " = ?";
        java.lang.String[] selectionArgs = {pos};

// How you want the results sorted in the resulting Cursor
        java.lang.String sortOrder =
                FeedReaderContract.FeedEntryMessage.COLUMN_SUBJECT + " DESC";

        Cursor cursor = db.query(
                FeedReaderContract.FeedEntryMessage.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        cursor.moveToFirst();
        java.lang.String message = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntryMessage.COLUMN_MESSAGE));
        return message;

    }

    public void delete(java.lang.String nn) {
        SQLiteDatabase db = getWritableDatabase();
        // Define 'where' part of query.
        java.lang.String selection = FeedReaderContract.FeedEntryMessage.COLUMN_SUBJECT + " LIKE ?";
// Specify arguments in placeholder order.
        java.lang.String[] selectionArgs = {nn};
// Issue SQL statement.
        int deletedRows = db.delete(FeedReaderContract.FeedEntryMessage.TABLE_NAME, selection, selectionArgs);
    }
}