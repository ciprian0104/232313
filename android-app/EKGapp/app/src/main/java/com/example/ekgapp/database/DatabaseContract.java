package com.example.ekgapp.database;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;


public class DatabaseContract {


    public static final String CONTENT_AUTHORITY = "com.example.ekgapp";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_INVENTORY = "database";

    // Create an empty constructor.
    public DatabaseContract() {
    }

    // The inner class that defines the constant values

    public static final class DatabaseEntry implements BaseColumns {

        /**
         * The content URI to access the inventory data in the provider
         */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_INVENTORY);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_INVENTORY;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_INVENTORY;

        /**
         * Name of database table for
         */
        public final static String TABLE_NAME = "data";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NAME = "name";
        public final static String COLUMN_QUANTITY = "quantity";
        public final static String COLUMN_DESCRIPTION = "description";



    }

}
