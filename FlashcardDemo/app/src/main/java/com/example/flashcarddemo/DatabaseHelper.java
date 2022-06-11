package com.example.flashcarddemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, "database", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("CREATE TABLE IF NOT EXISTS FLASHCARDS (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "DECK_ID INTEGER NOT NULL, " +
                "INDX INTEGER NOT NULL, " +
                "FRONT TEXT NOT NULL, " +
                "BACK TEXT NOT NULL" +
                ")");

        database.execSQL("CREATE TABLE IF NOT EXISTS DECKS (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "NAME TEXT NOT NULL, " +
                "TOPIC_ID INTEGER NOT NULL" +
                ")");

        database.execSQL("CREATE TABLE IF NOT EXISTS TOPICS (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "NAME TEXT NOT NULL" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public boolean updateFlashcard(Flashcard flashcard, int deckId){

        SQLiteDatabase database = this.getWritableDatabase();

        // Checking if in database:
        Cursor cursor = database.rawQuery("SELECT * FROM FLASHCARDS WHERE ID = ?",
                new String[]{String.valueOf(flashcard.getId())});

        // If existing flashcard, just updating:
        if (cursor.moveToFirst()){
            database.execSQL("UPDATE FLASHCARDS SET INDX = ?, FRONT = ?, BACK = ? WHERE ID = ?",
                    new String[]{String.valueOf(flashcard.getIndex()), String.valueOf(flashcard.getFront()), String.valueOf(flashcard.getBack())});

            // Cleaning Up:
            database.close();
            cursor.close();

            return true;
        }
        // If new flashcard, adding:
        ContentValues contentValues = new ContentValues();

        contentValues.put("DECK_ID", deckId);
        contentValues.put("INDX", flashcard.getIndex());
        contentValues.put("FRONT", flashcard.getFront());
        contentValues.put("BACK", flashcard.getBack());

        long insert = database.insert("FLASHCARDS", null, contentValues);

        // Cleaning Up:
        database.close();

        // If insert is negative, it has failed, if it is positive, it was successful:
        return insert > 0;
    }

    public ArrayList<Flashcard> getDeckFlashcards(int deckId){

        ArrayList<Flashcard> flashcards = new ArrayList<>();

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM FLASHCARDS WHERE DECK_ID = ? ORDER BY INDX ASC", new String[]{String.valueOf(deckId)});

        if (cursor.moveToFirst()) {
            do {
                flashcards.add(new Flashcard(
                        cursor.getInt(0),
                        deckId,
                        cursor.getInt(2),
                        cursor.getString(3),
                        cursor.getString(4)
                        ));
            } while (cursor.moveToNext());
        }

        // Cleaning Up:
        database.close();
        cursor.close();

        return flashcards;
    }

    public boolean addDeck(Deck deck){

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("NAME", deck.getName());
        contentValues.put("TOPIC_ID", deck.getTopicId());

        long insert = database.insert("DECKS", null, contentValues);

        // Retrieving the ID of the deck we just added to pass for each Flashcard:
        Cursor cursor = database.rawQuery("SELECT ID FROM DECKS ORDER BY ID DESC", null);
        int deckId = 0;
        if (cursor.moveToFirst()){
            deckId = cursor.getInt(0);
            System.out.println("Got Deck ID as " + deckId);
        }

        boolean success = true;
        for (Flashcard flashcard: deck.getFlashcards()){
            if(!updateFlashcard(flashcard, deckId)){
                success = false;
            }

        }

        // Cleaning Up:
        database.close();
        cursor.close();

        // Return true if all inserts have been successful:
        return (insert > 0) && success;

    }

    public Deck getDeck(int deckId){

        SQLiteDatabase database = this.getWritableDatabase();


        Cursor cursor = database.rawQuery("SELECT * FROM DECKS WHERE ID = ?", new String[]{String.valueOf(deckId)});


        if (cursor.moveToFirst()) {
            Deck deck = new Deck(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        this.getDeckFlashcards(cursor.getInt(0))
                );

            // Cleaning Up:
            database.close();
            cursor.close();

            return deck;
            }

        // Cleaning Up:
        database.close();

        return null;

    }

    public ArrayList<Deck> getAllDecks(){

        ArrayList<Deck> decks = new ArrayList<>();

        SQLiteDatabase database = this.getWritableDatabase();


        Cursor cursor = database.rawQuery("SELECT * FROM DECKS", null);

        if (cursor.moveToFirst()) {
            do {
                decks.add(new Deck(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        this.getDeckFlashcards(cursor.getInt(0))
                ));
            } while (cursor.moveToNext());
        }

        // Cleaning Up:
        database.close();
        cursor.close();

        return decks;
    }
}
