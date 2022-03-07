package com.example.PocketMaths;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Columns for Tasks Table:
    public static final String TASKS_TABLE = "TASKS_TABLE";
    public static final String COLUMN_TASK_ID = "COLUMN_TASK_ID";
    public static final String COLUMN_TASK_NAME = "CONTACT_TASK_NAME";
    public static final String COLUMN_TASK_REWARD = "COLUMN_TASK_REWARD";
    public static final String COLUMN_TASK_QUESTION_SET_ID = "COLUMN_TASK_QUESTION_SET_ID";
    public static final String COLUMN_TASK_COMPLETED = "COLUMN_TASK_COMPLETED";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "database", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase database) {
        // Creating Tasks Table:
        database.execSQL("CREATE TABLE IF NOT EXISTS " + TASKS_TABLE +
                " (" + COLUMN_TASK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TASK_NAME + " TEXT, " +
                COLUMN_TASK_REWARD + " TEXT, " +
                COLUMN_TASK_QUESTION_SET_ID + " INTEGER, " +
                COLUMN_TASK_COMPLETED + " BOOL)"
        );
    }

        @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Called if the version number changes. No need to add anything here.
    }

    //// TASKS TABLE:
    public boolean addTask(Task task){
        // This comes from the default properties from the class we are inheriting from:
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        // Putting the relevant item in each column of the row:
        contentValues.put(COLUMN_TASK_NAME, task.getName());
        contentValues.put(COLUMN_TASK_REWARD, task.getReward());
        contentValues.put(COLUMN_TASK_COMPLETED, task.isCompleted());
        // No need to put ID as it is an auto-increment value.

        long insert = database.insert(TASKS_TABLE, null, contentValues);

        // if insert is negative, it has failed, if it is positive, it was successful:
        if (insert == -1){return false;}
        else {return true;}
    }

    public ArrayList<Task> getTasks(){
        ArrayList<Task> tasks = new ArrayList<>();

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TASKS_TABLE, null);

        if (cursor.moveToFirst()){
            do {
                tasks.add(new Task(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                        cursor.getInt(3), cursor.getInt(4) == 1));
            } while(cursor.moveToNext());
        }

        // Cleaning Up:
        cursor.close();
        database.close();

        return tasks;
    }



    public boolean deleteTask(Task task){
        SQLiteDatabase database = this.getWritableDatabase();

        // Delete from the table an item for which the IDs match:
        long result = database.delete(TASKS_TABLE, COLUMN_TASK_ID + "=?", new String[] {String.valueOf(task.getId())}  );

        // Cleaning Up:
        database.close();

        if (result == -1){
            return false;
        }

        return true;
    }
}
