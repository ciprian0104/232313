package com.example.ekgapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ekgapp.database.DatabaseContract.DatabaseEntry;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TAG = DatabaseHelper.class.getSimpleName();

    /**
     * Name of the database file
     */
    private static final String DATABASE_NAME = "database.db";

    //The database version
    private static final int DATABASE_VERSION = 1;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_INVENTORY = "CREATE TABLE " + DatabaseEntry.TABLE_NAME + " ("
                + DatabaseEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DatabaseEntry.COLUMN_NAME + " TEXT NOT NULL, "
                + DatabaseEntry.COLUMN_QUANTITY + " INTEGER NOT NULL DEFAULT 0, "
                + DatabaseEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL DEFAULT 'New Product ' "
                + ");";

        db.execSQL(SQL_CREATE_INVENTORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
}


