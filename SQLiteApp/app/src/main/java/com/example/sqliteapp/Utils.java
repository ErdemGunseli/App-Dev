package com.example.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;

public class Utils extends AppCompatActivity {

    private static Utils instance;

    private DatabaseHelper databaseHelper = new DatabaseHelper(this);


    private Utils(){}

    public static synchronized Utils getInstance() {
        if (instance == null){
            instance = new Utils();
        }
        return instance;
    }

    public DatabaseHelper getDatabaseHelper() {
        return databaseHelper;
    }

    public void setDatabaseHelper(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }
}
