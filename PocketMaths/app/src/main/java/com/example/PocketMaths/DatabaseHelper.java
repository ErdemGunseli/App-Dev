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

    // Columns for Tasks Table:
    public static final String TASKS_TABLE = "TASKS_TABLE";
    public static final String COLUMN_TASK_ID = "COLUMN_TASK_ID";
    public static final String COLUMN_TASK_NAME = "CONTACT_TASK_NAME";
    public static final String COLUMN_TASK_REWARD = "COLUMN_TASK_REWARD";
    public static final String COLUMN_TASK_QUESTION_SET_ID = "COLUMN_TASK_QUESTION_SET_ID";
    public static final String COLUMN_TASK_COMPLETED = "COLUMN_TASK_COMPLETED";

    public static final String TASKS_TABLE_CREATE_SQLs = "CREATE TABLE IF NOT EXISTS " + TASKS_TABLE +
            " (" + COLUMN_TASK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_TASK_NAME + " TEXT, " +
            COLUMN_TASK_REWARD + " TEXT, " +
            COLUMN_TASK_QUESTION_SET_ID + " INTEGER, " +
            COLUMN_TASK_COMPLETED + " BOOL)";


    // Columns for Question Answer Options Table:
    public static final String QUESTION_ANSWER_OPTIONS_TABLE = "QUESTION_ANSWER_OPTIONS_TABLE";
    public static final String COLUMN_QUESTION_ANSWER_OPTION_ID = "COLUMN_QUESTION_ANSWER_OPTION_ID";
    public static final String COLUMN_QUESTION_ANSWER_OPTION_QUESTION_ID = "COLUMN_QUESTION_ANSWER_OPTION_QUESTION_ID";
    public static final String COLUMN_QUESTION_ANSWER_OPTION = "COLUMN_QUESTION_ANSWER_OPTION";

    public static final String QUESTION_ANSWER_OPTIONS_TABLE_CREATE_SQLs = "CREATE TABLE IF NOT EXISTS " + QUESTION_ANSWER_OPTIONS_TABLE +
            " (" + COLUMN_QUESTION_ANSWER_OPTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_QUESTION_ANSWER_OPTION_QUESTION_ID + " INTEGER, " +
            COLUMN_QUESTION_ANSWER_OPTION + " TEXT)";


    // Columns for Questions Table:
    public static final String QUESTIONS_TABLE = "QUESTIONS_TABLE";
    public static final String COLUMN_QUESTION_ID = "COLUMN_QUESTION_ID";
    public static final String COLUMN_QUESTION_TEXT = "COLUMN_QUESTION_TEXT";
    public static final String COLUMN_QUESTION_IMAGE_ID = "COLUMN_QUESTION_IMAGE_ID";
    public static final String COLUMN_QUESTION_TYPE = "COLUMN_QUESTION_TYPE";
    public static final String COLUMN_QUESTION_CHOSEN_ANSWER_INDEX = "COLUMN_CHOSEN_ANSWER_INDEX";
    public static final String COLUMN_QUESTION_CORRECT_WRITTEN_ANSWER = "COLUMN_QUESTION_CORRECT_WRITTEN_ANSWER";
    public static final String COLUMN_QUESTION_WRITTEN_ANSWER = "COLUMN_QUESTION_WRITTEN_ANSWER";
    public static final String COLUMN_QUESTION_TOPIC = "COLUMN_QUESTION_TOPIC";
    public static final String COLUMN_QUESTION_MODEL = "COLUMN_QUESTION_MODEL";
    public static final String COLUMN_QUESTION_ATTEMPTS = "COLUMN_QUESTION_ATTEMPTS";
    public static final String COLUMN_QUESTION_POINTS_POSSIBLE = "COLUMN_QUESTION_POINTS_POSSIBLE";
    public static final String COLUMN_QUESTION_POINTS_EARNED = "COLUMN_QUESTION_POINTS_EARNED";

    public static final String QUESTIONS_TABLE_CREATE_SQLs = "CREATE TABLE IF NOT EXISTS " + QUESTIONS_TABLE +
            " (" + COLUMN_QUESTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_QUESTION_TEXT + " TEXT, " +
            COLUMN_QUESTION_IMAGE_ID + " INTEGER, " +
            COLUMN_QUESTION_TYPE + " TEXT, " +
            COLUMN_QUESTION_CHOSEN_ANSWER_INDEX + " INTEGER, " +
            COLUMN_QUESTION_CORRECT_WRITTEN_ANSWER + " TEXT, " +
            COLUMN_QUESTION_WRITTEN_ANSWER + " TEXT, " +
            COLUMN_QUESTION_TOPIC + " TEXT, " +
            COLUMN_QUESTION_MODEL + " TEXT, " +
            COLUMN_QUESTION_ATTEMPTS + " INTEGER, " +
            COLUMN_QUESTION_POINTS_POSSIBLE + " INTEGER, " +
            COLUMN_QUESTION_POINTS_EARNED + " INTEGER)";



    //TODO: IF THE QUESTION SET TABLE IS FULLY IMPLEMENTED, THIS WOULD NEED TO CHANGE FOR NORMALISATION
    public static final String QUESTION_SET_RESULTS_TABLE = "QUESTION_SET_RESULTS_TABLE";
    public static final String QUESTION_SET_RESULT_ID = "QUESTION_SET_RESULT_ID";
    public static final String COLUMN_QUESTION_SET_ID = "COLUMN_QUESTION_SET_ID";
    public static final String COLUMN_QUESTION_SET_POINTS_EARNED = "COLUMN_QUESTION_SET_POINTS_EARNED";
    public static final String COLUMN_QUESTION_SET_POINTS_POSSIBLE = "COLUMN_QUESTION_SET_POINTS_POSSIBLE";
    public static final String COLUMN_FIRST_ATTEMPT = "COLUMN_FIRST_ATTEMPT";
    public static final String COLUMN_SECOND_ATTEMPT = "COLUMN_SECOND_ATTEMPT";
    public static final String COLUMN_MORE_ATTEMPTS = "COLUMN_MORE_ATTEMPTS";
    public static final String COLUMN_DATE_COMPLETED = "COLUMN_DATE_COMPLETED";

    public static final String QUESTION_SET_RESULT_TABLE_CREATE_SQLs = "CREATE TABLE IF NOT EXISTS " + QUESTION_SET_RESULTS_TABLE +
            " (" + QUESTION_SET_RESULT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_QUESTION_SET_ID + " INTEGER, " +
            COLUMN_QUESTION_SET_POINTS_EARNED + " INTEGER, " +
            COLUMN_QUESTION_SET_POINTS_POSSIBLE + " INTEGER, " +
            COLUMN_FIRST_ATTEMPT + " INTEGER, " +
            COLUMN_SECOND_ATTEMPT + " INTEGER, " +
            COLUMN_MORE_ATTEMPTS + " INTEGER, " +
            COLUMN_DATE_COMPLETED + " INTEGER)";



    private String createSQL;

    public DatabaseHelper(@Nullable Context context, String createSQL) {
        //TODO: Pass Create and Delete statements:
        super(context, "database", null, 1);
        this.createSQL = createSQL;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(this.createSQL);
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
        contentValues.put(COLUMN_TASK_NAME, task.getName());
        contentValues.put(COLUMN_TASK_REWARD, task.getReward());
        contentValues.put(COLUMN_TASK_QUESTION_SET_ID, task.getQuestionSetId());
        contentValues.put(COLUMN_TASK_COMPLETED, task.isCompleted());
        // No need to put ID as it is an auto-increment value.

        long insert = database.insert(TASKS_TABLE, null, contentValues);

        // Cleaning Up:
        database.close();

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


    //// QUESTIONS TABLE
    public boolean addQuestion(Question question){
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues questionValues = new ContentValues();

        questionValues.put(COLUMN_QUESTION_TEXT, question.getText());
        questionValues.put(COLUMN_QUESTION_IMAGE_ID, question.getImageId());

        String questionType = question.getType();
        questionValues.put(COLUMN_QUESTION_TYPE, questionType);

        if (questionType.equals(MULTIPLE_CHOICE)){
            questionValues.put(COLUMN_QUESTION_CHOSEN_ANSWER_INDEX, question.getChosenAnswerIndex());
        }
        else if (questionType.equals(WRITTEN)){
            questionValues.put(COLUMN_QUESTION_CORRECT_WRITTEN_ANSWER, question.getCorrectWrittenAnswer());
            questionValues.put(COLUMN_QUESTION_WRITTEN_ANSWER, question.getWrittenAnswer());
        }

        questionValues.put(COLUMN_QUESTION_TOPIC, question.getTopic());
        questionValues.put(COLUMN_QUESTION_MODEL, question.getModel());
        questionValues.put(COLUMN_QUESTION_ATTEMPTS, question.getAttempts());
        questionValues.put(COLUMN_QUESTION_POINTS_POSSIBLE, question.getPointsPossible());
        questionValues.put(COLUMN_QUESTION_POINTS_EARNED, question.getPointsEarned());

        long insertQuestions = database.insert(QUESTIONS_TABLE, null, questionValues);



        if (questionType.equals(MULTIPLE_CHOICE)) {
            //addAnswerOptions(question.getAnswerOptions());

            //long questionId = this.getTableLength(QUESTIONS_TABLE);
            int questionId = 0;

            ContentValues contentValues = new ContentValues();
            // Creating answer options table:
            database.execSQL(QUESTION_ANSWER_OPTIONS_TABLE_CREATE_SQLs);

            // Putting answer options in answer options table:
            for (String answerOption: question.getAnswerOptions()){
                contentValues.put(COLUMN_QUESTION_ANSWER_OPTION_QUESTION_ID, questionId);
                contentValues.put(COLUMN_QUESTION_ANSWER_OPTION, answerOption);
            }
            long insertOptions = database.insert(QUESTION_ANSWER_OPTIONS_TABLE, null, contentValues);

            if (insertOptions == -1){
                System.out.println("Inserting Answers unsuccessful");
                return false;
            }
        }

        database.close();

            // if insert is negative, it has failed, if it is positive, it was successful:
        if (insertQuestions == -1){
            System.out.println("Inserting Questions unsuccessful");

            return false;}
        else {return true;}

    }

    public boolean addAnswerOptions(String[] answerOptions){

        SQLiteDatabase database = this.getWritableDatabase();

        long questionId = this.getTableLength(QUESTIONS_TABLE);

        ContentValues contentValues = new ContentValues();
        // Putting answer options in answer options table:
        for (String answerOption: answerOptions){
            contentValues.put(COLUMN_QUESTION_ANSWER_OPTION_QUESTION_ID, questionId);
            contentValues.put(COLUMN_QUESTION_ANSWER_OPTION, answerOption);
        }
        long insert = database.insert(QUESTION_ANSWER_OPTIONS_TABLE, null, contentValues);

        if (insert == -1){return false;}
        else {return true;}

    }

    //// QUESTION SET RESULT TABLE
    public boolean addQuestionSetResult(QuestionSetResult questionSetResult){
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_QUESTION_SET_ID, questionSetResult.getQuestionSetId());
        contentValues.put(COLUMN_QUESTION_SET_POINTS_EARNED, questionSetResult.getPointsEarned());
        contentValues.put(COLUMN_QUESTION_SET_POINTS_POSSIBLE, questionSetResult.getPointsPossible());
        contentValues.put(COLUMN_FIRST_ATTEMPT, questionSetResult.getFirstAttempt());
        contentValues.put(COLUMN_SECOND_ATTEMPT, questionSetResult.getSecondAttempt());
        contentValues.put(COLUMN_MORE_ATTEMPTS, questionSetResult.getMoreAttempts());
        contentValues.put(COLUMN_DATE_COMPLETED, questionSetResult.getDateCompleted());

        long insert = database.insert(QUESTION_SET_RESULTS_TABLE, null, contentValues);

        // Cleaning Up:
        database.close();

        // if insert is negative, it has failed, if it is positive, it was successful:
        if (insert == -1){return false;}
        else {return true;}
    }

    public ArrayList<QuestionSetResult> getQuestionSetResults(){
        ArrayList<QuestionSetResult> questionSetResults = new ArrayList<>();

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + QUESTION_SET_RESULTS_TABLE, null);

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
                        cursor.getString(7)
                ));
            } while(cursor.moveToNext());
        }

        // Cleaning Up:
        cursor.close();
        database.close();

        return questionSetResults;
    }

}
