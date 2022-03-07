package com.example.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Data Access Object (DAO) is considered a best-practice in software design.

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

        // Getting every item from the contact table:
        String queryString = "SELECT * FROM " + CONTACT_TABLE;

        // We do not need the writable database:
        // We are not getting the writable database as one instance of it can exist:
        SQLiteDatabase database = this.getReadableDatabase();
        
        // We could choose execute SQL or raw query. We are using raw query for the cursor return data type:
        // This function also has selectionArgs as a parameter which is a string array that will
        // replace any question marks in the query string.

        Cursor cursor = database.rawQuery(queryString, null);

        // A cursor is the result set from a SQL statement - a complex array of items (rows upon rows etc.)

        // Moving to the first result in the result set:
        // This will return true if there are results obtained.
        if (cursor.moveToFirst()){
          // Looping through the cursor and creating new customer objects for each row, inserting each
          // into the return list:

          do {
              // Introducing local variables for the data types we expect to receive:

              // If there was not an integer at the specified index, the program would crash:
              int contactID = cursor.getInt(0);

              String contactFirstName = cursor.getString(1);

              String contactLastName = cursor.getString(2);

              int contactAge = cursor.getInt(3);


              // To get the boolean value, we have to convert the result from an integer to a boolean:
              // In SQLite, there is no such thing as a boolean data type, rather an integer that is either 0 or 1:
              boolean contactIsAdmin = (cursor.getInt(4) == 1);


              Contact contact = new Contact(contactID, contactFirstName, contactLastName, contactAge, contactIsAdmin);

              contacts.add(contact);

          } while(cursor.moveToNext());
        }

        // Cleaning Up:
        cursor.close();
        database.close();

        return contacts;
    }



    public boolean deleteUser(Contact contact){

         SQLiteDatabase database = this.getWritableDatabase();

         long result = database.delete(CONTACT_TABLE, COLUMN_CONTACT_ID + "=?", new String[] {String.valueOf(contact.getId())});

         database.close();

         if (result == -1){
             return false;
         }

         return true;

    }


}
