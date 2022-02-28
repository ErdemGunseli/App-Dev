package com.example.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper  extends SQLiteOpenHelper {

    public static final String CONTACT_TABLE = "CONTACT_TABLE";
    public static final String COLUMN_CONTACT_ID = "COLUMN_CONTACT_ID";
    public static final String COLUMN_CONTACT_FIRST_NAME = "CONTACT_FIRST_NAME";
    public static final String COLUMN_CONTACT_LAST_NAME = "COLUMN_CONTACT_LAST_NAME";
    public static final String COLUMN_CONTACT_AGE = "CONTACT_AGE";
    public static final String COLUMN_CONTACT_ADMIN = "COLUMN_CONTACT_ADMIN";

    public DatabaseHelper(@Nullable Context context) {
        // Hardcoding these for testing:
        super(context, "dbContact", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        // Called the first time the database is accessed.
        String tableName ="CONTACT_TABLE";
        String createTableStatement = "CREATE TABLE " + CONTACT_TABLE +
                " (" + COLUMN_CONTACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CONTACT_FIRST_NAME + " TEXT, " +
                COLUMN_CONTACT_LAST_NAME + " TEXT, " +
                COLUMN_CONTACT_AGE + " INT, " +
                COLUMN_CONTACT_ADMIN + " BOOL)";

        database.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Called if the version number changes, preventing previous versions from breaking.

    }

    public boolean add(Contact contact){
        // This comes from the default properties from the class we are inheriting from:
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        // We are associating a column name with a String value:

        contentValues.put(COLUMN_CONTACT_FIRST_NAME, contact.getFirstName());
        contentValues.put(COLUMN_CONTACT_LAST_NAME, contact.getLastName());
        contentValues.put(COLUMN_CONTACT_AGE, contact.getAge());
        contentValues.put(COLUMN_CONTACT_ADMIN, contact.isAdmin());
        // No need to put ID as it is an auto-increment value.

        long insert = database.insert(CONTACT_TABLE, null, contentValues);

        // if insert is negative, it has failed, if it is positive, it was successful:

        if (insert == -1){return false;}
        else {return true;}

    }

    public ArrayList<Contact> getDatabase(){
        ArrayList<Contact> contacts = new ArrayList<>();

        String queryString = "SELECT * FROM " + CONTACT_TABLE;

        // We do not need the writable database:
        // We are not getting the writable database as one instance of it can exist:
        SQLiteDatabase database = this.getReadableDatabase();



        return contacts;
    }



}
