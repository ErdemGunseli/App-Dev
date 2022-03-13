package com.example.PocketMaths;

import static com.example.PocketMaths.Question.MULTIPLE_CHOICE;
import static com.example.PocketMaths.Question.WRITTEN;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        //TODO: Pass Create and Delete statements:
        super(context, "database", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        // Create Tasks table:
        database.execSQL("CREATE TABLE IF NOT EXISTS TASKS (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "ACCOUNT_ID INTEGER NOT NULL, " +
                "NAME TEXT NOT NULL, " +
                "PASS_MARK INTEGER NOT NULL, " +
                "REWARD TEXT NOT NULL, " +
                "QUESTION_SET_ID INTEGER NOT NULL, " +
                "IS_COMPLETED BOOL NOT NULL" +
                ")");

        database.execSQL("CREATE TABLE IF NOT EXISTS QUESTION_SET_RESULTS (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "QUESTION_SET_ID INTEGER NOT NULL, " +
                "ACCOUNT_ID INTEGER NOT NULL, " +
                "QUESTION_SET_POINTS_EARNED INTEGER NOT NULL, " +
                "QUESTION_SET_POINTS_POSSIBLE INTEGER NOT NULL, " +
                "FIRST_ATTEMPT INTEGER, " +
                "SECOND_ATTEMPT INTEGER, " +
                "MORE_ATTEMPTS INTEGER, " +
                "DATE_COMPLETED INTEGER" +
                ")");

        database.execSQL("CREATE TABLE IF NOT EXISTS ACCOUNTS (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "PARENT_NAME TEXT NOT NULL," +
                "STUDENT_NAME TEXT NOT NULL," +
                "EMAIL TEXT NOT NULL," +
                "PASSWORD TEXT NOT NULL," +
                "PIN TEXT NOT NULL" +
                ")");

        database.execSQL("CREATE TABLE IF NOT EXISTS CURRENT_ACCOUNT (" +
                "ACCOUNT_ID INTEGER PRIMARY KEY NOT NULL" +
                ")");
    }


        @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Called if the version number changes. No need to add anything here.
    }

    public long getTableLength(String tableName){
        SQLiteDatabase database = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(database, tableName);
        database.close();
        return count;
    }

    //// TASKS TABLE:
    public boolean addTask(Task task){
        // This comes from the default properties from the class we are inheriting from:
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        // Putting the relevant item in each column of the row:
        contentValues.put("ACCOUNT_ID", task.getAccountId());
        contentValues.put("NAME", task.getName());
        contentValues.put("REWARD", task.getReward());
        contentValues.put("PASS_MARK", task.getPassMark());
        contentValues.put("QUESTION_SET_ID", task.getQuestionSetId());
        contentValues.put("IS_COMPLETED", task.isCompleted());
        // No need to put ID as it is an auto-increment value.

        long insert = database.insert("TASKS", null, contentValues);

        // Cleaning Up:
        database.close();

        // if insert is negative, it has failed, if it is positive, it was successful:
        if (insert == -1){return false;}
        else {return true;}
    }

    public ArrayList<Task> getTasks(){
        ArrayList<Task> tasks = new ArrayList<>();

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM TASKS WHERE ACCOUNT_ID=?", new String[] {String.valueOf(Utils.getInstance().getUserAccount().getId())});

        if (cursor.moveToFirst()){
            do {
                tasks.add(new Task(cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getString(4),
                        cursor.getInt(5),
                        cursor.getInt(6) == 1));
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
        long result = database.delete("TASKS", "ID =?", new String[] {String.valueOf(task.getId())});

        // Cleaning Up:
        database.close();

        if (result == -1){
            return false;
        }

        return true;
    }

    //// QUESTION SET RESULT TABLE
    public boolean addQuestionSetResult(QuestionSetResult questionSetResult, int accountId){
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("QUESTION_SET_ID", questionSetResult.getQuestionSetId());
        contentValues.put("ACCOUNT_ID", accountId);
        contentValues.put("QUESTION_SET_POINTS_EARNED", questionSetResult.getPointsEarned());
        contentValues.put("QUESTION_SET_POINTS_POSSIBLE", questionSetResult.getPointsPossible());
        contentValues.put("FIRST_ATTEMPT", questionSetResult.getFirstAttempt());
        contentValues.put("SECOND_ATTEMPT", questionSetResult.getSecondAttempt());
        contentValues.put("MORE_ATTEMPTS", questionSetResult.getMoreAttempts());
        contentValues.put("DATE_COMPLETED", questionSetResult.getDateCompleted());

        long insert = database.insert("QUESTION_SET_RESULTS", null, contentValues);

        // Cleaning Up:
        database.close();

        // if insert is negative, it has failed, if it is positive, it was successful:
        if (insert == -1){return false;}
        else {return true;}
    }

    public ArrayList<QuestionSetResult> getQuestionSetResults(){
        ArrayList<QuestionSetResult> questionSetResults = new ArrayList<>();

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM QUESTION_SET_RESULTS WHERE ACCOUNT_ID=?", new String[] {String.valueOf(Utils.getInstance().getUserAccount().getId())});

        if (cursor.moveToFirst()){
            do {
                questionSetResults.add(new QuestionSetResult(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getInt(2),
                        cursor.getInt(3),
                        cursor.getInt(4),
                        cursor.getInt(5),
                        cursor.getInt(6),
                        cursor.getInt(7),
                        cursor.getString(8)
                ));
            } while(cursor.moveToNext());
        }

        // Cleaning Up:
        cursor.close();
        database.close();

        return questionSetResults;
    }

    public boolean addAccount(Account account){
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("PARENT_NAME", account.getParentName());
        contentValues.put("STUDENT_NAME", account.getStudentName());
        contentValues.put("EMAIL", account.getEmail());
        contentValues.put("PASSWORD", account.getPassword());
        contentValues.put("PIN", account.getPin());

        long insert = database.insert("ACCOUNTS", null, contentValues);

        // Cleaning Up:
        database.close();

        //TODO: CHANGE PIN TO INT
        //todo: Return account id

        // if insert is negative, it has failed, if it is positive, it was successful:
        if (insert == -1){return false;}
        else {return true;}
    }

    public Account getAccountById(int id){

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM ACCOUNTS WHERE ID = ?", new String[] {String.valueOf(id)});

        Account account = new Account();
        if (cursor.moveToFirst()){
                account = new Account(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)
                );
        }

        // Cleaning Up:
        cursor.close();
        database.close();

         if (account.getAccountType().equals(Account.Guest)){
             return null;
         }
        return account;
    }

    public Account getAccountByEmail(String email){

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM ACCOUNTS WHERE EMAIL = ?", new String[] {email});

        Account account = new Account();
        if (cursor.moveToFirst()){
            account = new Account(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5)
            );
        }

        // Cleaning Up:
        cursor.close();
        database.close();

        if (account.getAccountType().equals(Account.Guest)){
            return null;
        }
        return account;
    }

    public boolean useAccount(int id){
        Account account = getAccountById(id);
        if (account == null){
            return false;
        }
        else{

            SQLiteDatabase database = this.getWritableDatabase();



            ContentValues contentValues = new ContentValues();
            contentValues.put("ACCOUNT_ID", account.getId());

            long insert = database.insert("CURRENT_ACCOUNT", null, contentValues);



            // if insert is negative, it has failed, if it is positive, it was successful:
            if (insert == -1){
                // Cleaning Up:
                database.close();
                return false;}
            else {
                // If the new account has successfully been set, delete any pre-existing current account:
                database.execSQL("DELETE FROM CURRENT_ACCOUNT WHERE NOT ACCOUNT_ID =?", new String[]{String.valueOf(id)});

                // Cleaning Up:
                database.close();
                return true;
            }
        }

    }

    public boolean removeCurrentAccount(){
        SQLiteDatabase database = this.getWritableDatabase();

        database.execSQL("DELETE FROM CURRENT_ACCOUNT");


        // Cleaning Up:
        database.close();

        return (getTableLength("CURRENT_ACCOUNT") == 0);
    }

    public Account getCurrentAccount(){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM CURRENT_ACCOUNT", null);

        Account account = new Account();
        if (cursor.moveToFirst()){
            account = getAccountById(cursor.getInt(0));
        }

        // Cleaning Up:
        cursor.close();
        database.close();

        if (account.getAccountType().equals(Account.Guest)){
            return null;
        }
        return account;

    }

}
